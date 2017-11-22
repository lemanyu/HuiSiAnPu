package com.hsap.huisianpu.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.utils.ToastUtils;
import com.zhy.android.percent.support.PercentLinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 请假页面
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
    @BindView(R.id.tv_qingjialeixing)
    TextView tvQingjialeixing;

    @Override
    public int getLayoutId() {

        return R.layout.activity_work_leave;
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
        switch (v.getId()) {
            case R.id.bt_learve_commit:
                ToastUtils.showToast(LeaveActivity.this, "提交");
                break;
            case R.id.pll_qingjia:
                showSingleChoiceDialog();
                break;
            case R.id.pll_begin:
                ToastUtils.showToast(LeaveActivity.this, "开始");
                break;
            case R.id.pll_end:
                ToastUtils.showToast(LeaveActivity.this, "结束");
                break;
        }
    }

    private void showSingleChoiceDialog() {
        final int[] choose = {0};
        final String[] item = {"事假", "病假", "产假", "婚假", "丧假"};
        ToastUtils.showToast(LeaveActivity.this, "请假");
        AlertDialog.Builder builder = new AlertDialog.Builder(LeaveActivity.this);
        builder.setTitle("请选择请假事由");
        builder.setSingleChoiceItems(item, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                choose[0] =i;
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tvQingjialeixing.setText(item[choose[0]]);
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
