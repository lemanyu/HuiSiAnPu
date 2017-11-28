package com.hsap.huisianpu.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.utils.ToastUtils;

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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
