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
 * 工作周报
 */

public class WorkWeekNewPaperActivity extends BaseBackActivity {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.bt_work_week_commit)
    Button btWorkWeekCommit;
    @BindView(R.id.et_week_work_summary)
    EditText etWeekWorkSummary;
    @BindView(R.id.et_week_plan_next)
    EditText etWeekPlanNext;
    @BindView(R.id.et_week_coordination_work)
    EditText etWeekCoordinationWork;


    @Override
    public int getLayoutId() {
        return R.layout.activity_work_week;
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
          btWorkWeekCommit.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {
               switch (v.getId()){
                   case R.id.bt_work_week_commit:
                       commit();
                       break;
               }
    }

    private void commit() {
        if(TextUtils.isEmpty(etWeekWorkSummary.getText().toString().trim())){
            ToastUtils.showToast(this,"请填写工作总结信息");
            return;
        }
        if (TextUtils.isEmpty(etWeekPlanNext.getText().toString().trim())){
            ToastUtils.showToast(this,"请填写下周工作计划");
            return;
        }
        if (TextUtils.isEmpty(etWeekCoordinationWork.getText().toString().trim())){
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
