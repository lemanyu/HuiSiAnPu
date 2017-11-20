package com.hsap.huisianpu.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.utils.ToastUtils;
import com.zhy.android.percent.support.PercentLinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhao on 2017/11/20.
 */

public class LeaveActivity extends BaseBackActivity {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.bt_learve_commit)
    Button btLearveCommit;
    @BindView(R.id.leavte_toobar)
    Toolbar leavteToobar;
    @BindView(R.id.pll_qingjia)
    PercentLinearLayout pllQingjia;
    @BindView(R.id.pll_begin)
    PercentLinearLayout pllBegin;
    @BindView(R.id.pll_end)
    PercentLinearLayout pllEnd;
    @BindView(R.id.et_leave)
    EditText etLeave;
    @BindView(R.id.rlv_leave)
    RecyclerView rlvLeave;

    @Override
    public int getLayoutId() {
        return R.layout.activity_leave;
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
        btLearveCommit.setOnClickListener(this);
        pllQingjia.setOnClickListener(this);
        pllBegin.setOnClickListener(this);
        pllEnd.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {
            switch (v.getId()){
                case R.id.bt_learve_commit:
                    ToastUtils.showToast(LeaveActivity.this,"提交");
                    break;
                case R.id.pll_qingjia:
                    ToastUtils.showToast(LeaveActivity.this,"请假");
                    break;
                case R.id.pll_begin:
                    ToastUtils.showToast(LeaveActivity.this,"开始");
                    break;
                case R.id.pll_end:
                    ToastUtils.showToast(LeaveActivity.this,"结束");
                    break;
            }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
