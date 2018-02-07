package com.hsap.huisianpu.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.google.gson.Gson;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.adapter.MyAdapter;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.bean.PushProjectBean;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.SpUtils;
import com.hsap.huisianpu.utils.ToastUtils;
import com.hsap.huisianpu.view.MyGridView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.warkiz.widget.IndicatorSeekBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author zhao 项目详情
 */

public class WorkSeeActivity extends BaseBackActivity {
    private static final String TAG = "WorkSeeActivity";
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.tv_see_title)
    TextView tvSeeTitle;
    @BindView(R.id.vs_see_project)
    ViewStub vsSeeProject;
    @BindView(R.id.bt_see_edit)
    Button btSeeEdit;
    private int projectId;
    private int now;
    private String name;
    private boolean type;
    private TextView tv_see_jindu;
    private int state=0;
    private int bar;
    private LoadingDailog dailog;
    private int state1;
    private IndicatorSeekBar see_icb;

    @Override
    public int getLayoutId() {
        return R.layout.activity_see_project;
    }

    @Override
    public void initView() {
        dailog = ToastUtils.showDailog(this, "获取数据中");
        dailog.show();
        state1 = getIntent().getIntExtra("state", 0);
        projectId = getIntent().getIntExtra("projectId", 0);
        type = getIntent().getBooleanExtra("type", false);
        name = getIntent().getStringExtra("name");
        now = getIntent().getIntExtra("now", 0);
         if (now==8){
             now=7;
         }
        switch (now) {
            case 1:
                tvSeeTitle.setText("立项阶段");
                break;
            case 2:
                tvSeeTitle.setText("总体设计阶段");
                break;
            case 3:
                tvSeeTitle.setText("产品开发阶段");
                break;
            case 4:
                tvSeeTitle.setText("自测阶段");
                break;
            case 5:
                tvSeeTitle.setText("测试阶段");
                break;
            case 6:
                tvSeeTitle.setText("新产品验收阶段");
                break;
            case 7:
                tvSeeTitle.setText("结项阶段");
                break;
            default:
        }

    }

    @Override
    public void initData() {

        vsSeeProject.inflate();
        see_icb = findViewById(R.id.see_icb);
        if (type&&state1==0) {
            see_icb.setVisibility(View.VISIBLE);
        } else {
            see_icb.setVisibility(View.GONE);
        }
        tv_see_jindu = findViewById(R.id.tv_see_jindu);
        final TextView et_publish_name = findViewById(R.id.et_publish_name);
        final TextView et_publish_number = findViewById(R.id.et_publish_number);
        final TextView et_publish_person = findViewById(R.id.et_publish_person);
        final TextView tv_publish_time = findViewById(R.id.tv_publish_time);
        final TextView et_publish_days = findViewById(R.id.et_publish_days);
        final TextView et_publish_content = findViewById(R.id.et_publish_content);
        final LinearLayout ll_project = findViewById(R.id.ll_project);
        final MyGridView gv_approval_project = findViewById(R.id.gv_approval_project);
        OkGo.<String>post(NetAddressUtils.getMyProjectInfo).
                params("projectId", projectId).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e(TAG, "onSuccess: " + response.body().toString());
                        PushProjectBean projectBean = new Gson().fromJson(response.body().toString(), PushProjectBean.class);
                        if (projectBean.isSuccess()) {
                            dailog.dismiss();
                            PushProjectBean.DataBean.InfoBean info = projectBean.getData().getInfo();
                            et_publish_name.setText(info.getProjectName());
                            et_publish_number.setText(info.getProjectNumber());
                            et_publish_person.setText(name);
                            tv_publish_time.setText(info.getEsStartTime().getYear() + "-" + info.getEsStartTime().getMonthValue() + "-" +
                                    info.getEsStartTime().getDayOfMonth());
                            et_publish_days.setText(String.valueOf(info.getEsCycle()));
                            et_publish_content.setText(info.getBody());
                            if (projectBean.getData().getPersonList() != null && projectBean.getData().getPersonList().size() != 0) {
                                ll_project.setVisibility(View.VISIBLE);
                                ArrayList<String> nameList = new ArrayList<>();
                                for (int i = 0; i < projectBean.getData().getPersonList().size(); i++) {
                                    nameList.add(projectBean.getData().getPersonList().get(i).getName());
                                }
                                gv_approval_project.setAdapter(new MyAdapter(WorkSeeActivity.this, nameList));
                            } else {
                                ll_project.setVisibility(View.GONE);
                            }
                            tv_see_jindu.setText("当前进度：" + projectBean.getData().getFilePath().get(now-1).getBar());
                            if ( projectBean.getData().getFilePath().get(now-1).getBar()==100){
                                see_icb.setVisibility(View.GONE);
                            }
                            if (see_icb.getVisibility()==View.VISIBLE){
                                choiceProgress(see_icb, projectBean.getData().getFilePath());
                            }

                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dailog.dismiss();
                        ToastUtils.showToast(WorkSeeActivity.this, "获取失败");
                    }
                });
    }

    private void choiceProgress(final IndicatorSeekBar see_icb, List<PushProjectBean.DataBean.FilePathBean> filePath) {
        state=filePath.get(now-1).getBar();
        see_icb.setProgress(filePath.get(now-1).getBar());
        see_icb.setOnSeekChangeListener(new IndicatorSeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(IndicatorSeekBar seekBar, int progress, float progressFloat, boolean fromUserTouch) {
                tv_see_jindu.setText("当前进度：" + progress);
            }

            @Override
            public void onSectionChanged(IndicatorSeekBar seekBar, int thumbPosOnTick, String textBelowTick, boolean fromUserTouch) {
            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar, int thumbPosOnTick) {

            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {
                bar=seekBar.getProgress();
                 if(state ==seekBar.getProgress()){
                     btSeeEdit.setVisibility(View.GONE);
                 }  else {
                     btSeeEdit.setVisibility(View.VISIBLE);
                 }
            }
        });
    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
        btSeeEdit.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()){
            case R.id.bt_see_edit:
                edit();
                break;
                default:
        }
    }

    private void edit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确定已经提交过文件");
        builder.setNegativeButton("取消",null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final LoadingDailog 提交中 = ToastUtils.showDailog(WorkSeeActivity.this, "提交中");
                提交中.show();
                OkGo.<String>post(NetAddressUtils.setProjectBar).
                        params("id",projectId).
                        params("workerId", SpUtils.getInt(ConstantUtils.UserId,WorkSeeActivity.this)).
                        params("bar",bar).
                        params("activity","com.hsap.huisianpu.push.PushProject").
                        execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                提交中.dismiss();
                                if(see_icb.getProgress()==100){
                                    see_icb.setVisibility(View.GONE);
                                }else {
                                    see_icb.setVisibility(View.VISIBLE);
                                }
                                btSeeEdit.setVisibility(View.GONE);
                                ToastUtils.showToast(WorkSeeActivity.this,"提交成功");

                            }

                            @Override
                            public void onError(Response<String> response) {
                                super.onError(response);
                                提交中.dismiss();
                                ToastUtils.showToast(WorkSeeActivity.this,"提交失败");
                            }
                        });

            }
        });
        builder.show();
    }

}
