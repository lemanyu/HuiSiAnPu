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
 * 工作日报
 */

public class WorkDayNewPaperActivity extends BaseBackActivity {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.bt_work_day_commit)
    Button btWorkDayCommit;
    @BindView(R.id.et_day_finish_work)
    EditText etDayFinishWork;
    @BindView(R.id.et_day_unfinish_work)
    EditText etDayUnfinishWork;
    @BindView(R.id.et_day_coordination_work)
    EditText etDayCoordinationWork;
    @BindView(R.id.et_day_remark)
    EditText etDayRemark;

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
        if(TextUtils.isEmpty(etDayUnfinishWork.getText().toString().trim())){
            ToastUtils.showToast(this,"请填写未完成工作信息");
            return;
        }
        if(TextUtils.isEmpty(etDayCoordinationWork.getText().toString().trim())){
            ToastUtils.showToast(this,"请填写协调工作信息");
            return;
        }
        if (TextUtils.isEmpty(etDayRemark.getText().toString().trim())){
            ToastUtils.showToast(this,"请填写备注信息");
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
