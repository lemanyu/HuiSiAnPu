package com.hsap.huisianpu.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.android.tu.loadingdialog.LoadingDailog;
import com.google.gson.Gson;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.bean.ReportFormStateBean;
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
 * 工作月报
 */

public class WorkMonthNewPaperActivity extends BaseBackActivity {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.bt_work_month_commit)
    Button btWorkMonthCommit;
    @BindView(R.id.et_month_work_summary)
    EditText etMonthWorkSummary;
    @BindView(R.id.et_month_plan_next)
    EditText etMonthPlanNext;
    @BindView(R.id.et_month_coordination_work)
    EditText etMonthCoordinationWork;

    @Override
    public int getLayoutId() {

        return R.layout.activity_work_month;
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
       btWorkMonthCommit.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()){
            case R.id.bt_work_month_commit:
                commit();
                break;
        }

    }

    private void commit() {
        if(TextUtils.isEmpty(etMonthWorkSummary.getText().toString().trim())){
            ToastUtils.showToast(this,"请填写本月工作总结信息");
            return;
        }
        if(TextUtils.isEmpty(etMonthPlanNext.getText().toString().trim())){
            ToastUtils.showToast(this,"请填写下月工作计划信息");
            return;
        }
        if(TextUtils.isEmpty(etMonthCoordinationWork.getText().toString().trim())){
            ToastUtils.showToast(this,"请填写协调工作信息");
            return;
        }
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确定要提交吗？");
        builder.setNegativeButton("取消",null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final LoadingDailog 提交中 = ToastUtils.showDailog(WorkMonthNewPaperActivity.this, "提交中");
                提交中.show();
                OkGo.<String>post(NetAddressUtils.getNowReportFormState).
                        params("id", SpUtils.getInt(ConstantUtils.UserId,WorkMonthNewPaperActivity.this)).
                        params("type",0).execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ReportFormStateBean bean = new Gson().fromJson(response.body().toString(), ReportFormStateBean.class);
                        if(bean.isSuccess()){
                            OkGo.<String>post(NetAddressUtils.setReportForm).
                                    params("id", SpUtils.getInt(ConstantUtils.UserId,WorkMonthNewPaperActivity.this)).
                                    params("type",Integer.valueOf(0)).
                                    params("finishWork",etMonthWorkSummary.getText().toString().trim()).
                                    params("workPlay",etMonthPlanNext.getText().toString().trim()).
                                    params("summary",etMonthCoordinationWork.getText().toString().trim()).
                                    execute(new StringCallback() {
                                        @Override
                                        public void onSuccess(Response<String> response) {
                                            提交中.dismiss();
                                            ToastUtils.showToast(WorkMonthNewPaperActivity.this,"提交成功");
                                            finish();
                                        }
                                    });
                        }else {
                            提交中.dismiss();
                            ToastUtils.showToast(WorkMonthNewPaperActivity.this,"您本月已经提交过");
                        }
                }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        提交中.dismiss();
                        ToastUtils.showToast(WorkMonthNewPaperActivity.this,"当前网络不稳，提交失败");
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
