package com.hsap.huisianpu.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.android.tu.loadingdialog.LoadingDailog;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.push.PushActivity;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.SpUtils;
import com.hsap.huisianpu.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 工作日报
 */

public class WorkDayNewPaperActivity extends BaseBackActivity {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.bt_work_day_commit)
    Button btWorkDayCommit;
    @BindView(R.id.et_day_finish_work)
    EditText etDayFinishWork;

    @Override
    public int getLayoutId() {

        return R.layout.activity_work_day;
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
          btWorkDayCommit.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {
           switch (v.getId()){
               case R.id.bt_work_day_commit:
                   commit();
                   break;
           }
    }

    private void commit() {
        if(TextUtils.isEmpty(etDayFinishWork.getText().toString().trim())){
            ToastUtils.showToast(this,"请填写完成工作信息");
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确定要提交吗？");
        builder.setNegativeButton("取消",null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final LoadingDailog 提交中 = ToastUtils.showDailog(WorkDayNewPaperActivity.this, "提交中");
                提交中.show();
                OkGo.<String>post(NetAddressUtils.setReportForm).
                        params("id", SpUtils.getInt(ConstantUtils.UserId,WorkDayNewPaperActivity.this)).
                        params("type",Integer.valueOf(0)).params("finishWork",etDayFinishWork.getText().toString().trim()).
                        execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        提交中.dismiss();
                        ToastUtils.showToast(WorkDayNewPaperActivity.this,"提交成功");
                        finish();

                    }
                });
            }
        });
       builder.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
