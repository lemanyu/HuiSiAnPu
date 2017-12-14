package com.hsap.huisianpu.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.SpUtils;
import com.hsap.huisianpu.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhy.android.percent.support.PercentLinearLayout;

import butterknife.BindView;

/**
 * @author zhao
 * @date 2017/12/9  发布公告
 */

public class AnnouncementActivity extends BaseBackActivity {
    private static final String TAG = "AnnouncementActivity";
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.bt_commit)
    Button btCommit;
    @BindView(R.id.et_gonggao)
    EditText etGonggao;
    @BindView(R.id.tv_annouc_reason)
    TextView tvAnnoucReason;
    @BindView(R.id.pll_annou_reason)
    PercentLinearLayout pllAnnouReason;
     private int state=0;
    @Override
    public int getLayoutId() {

        return R.layout.activity_announcement;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
        btCommit.setOnClickListener(this);
        pllAnnouReason.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.bt_commit:
                if(tvAnnoucReason.getText().toString().trim().equals("请选择（必填）")){
                    ToastUtils.showToast(getApplicationContext(),"请选择发布对象");
                    return;
                }
                if (TextUtils.isEmpty(etGonggao.getText().toString().trim())) {
                    ToastUtils.showToast(getApplicationContext(), "公告内容不能为空");
                    return;
                }
                //TODO、提交数据
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("确定要发布此公告嘛？");
                builder.setNegativeButton("取消", null);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final LoadingDailog 发布中 = ToastUtils.showDailog(AnnouncementActivity.this, "发布中");
                        发布中.show();
                        OkGo.<String>post(NetAddressUtils.addNotice).
                                params("type ", 1).
                                params("body", etGonggao.getText().toString().trim()).
                                params("workersId",SpUtils.getInt(ConstantUtils.UserId, getApplicationContext())).
                                params("level",state ).
                                params("activity","com.hsap.huisianpu.push.PushAnnouncenment").
                                execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(Response<String> response) {
                                        发布中.dismiss();
                                        ToastUtils.showToast(getApplicationContext(),"发布成功");
                                       // setResult(201);
                                    }

                                    @Override
                                    public void onError(Response<String> response) {
                                        super.onError(response);
                                        发布中.dismiss();
                                        ToastUtils.showToast(getApplicationContext(),"发布失败，无网络");
                                    }
                                });
                    }

                });
                builder.show();
                break;
            case R.id.pll_annou_reason:
                final int[] choose = {0};
                final String[] item = {"部门员工", "全体经理", "全公司"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("请选择发布对象");
                dialog.setSingleChoiceItems(item, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        choose[0] = i;
                        state=i;
                        Log.e(TAG, "onClick: "+state );


                    }
                });
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        tvAnnoucReason.setText(item[choose[0]]);
                    }
                });
                dialog.show();
                break;
            default:
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        setResult(Activity.RESULT_FIRST_USER);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


}
