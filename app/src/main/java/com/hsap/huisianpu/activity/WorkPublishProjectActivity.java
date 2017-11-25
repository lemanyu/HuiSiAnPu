package com.hsap.huisianpu.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.utils.ToastUtils;
import com.zhy.android.percent.support.PercentLinearLayout;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 发布项目
 */

public class WorkPublishProjectActivity extends BaseBackActivity {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.bt_publish_commit)
    Button btPublishCommit;
    @BindView(R.id.et_publish_name)
    EditText etPublishName;
    @BindView(R.id.et_publish_number)
    EditText etPublishNumber;
    @BindView(R.id.et_publish_person)
    EditText etPublishPerson;
    @BindView(R.id.tv_publish_time)
    TextView tvPublishTime;
    @BindView(R.id.pll_publish_time)
    PercentLinearLayout pllPublishTime;
    @BindView(R.id.et_publish_days)
    EditText etPublishDays;
    @BindView(R.id.et_publish_content)
    EditText etPublishContent;
    private StringBuilder time=new StringBuilder();
    @Override
    public int getLayoutId() {

        return R.layout.activity_work_publish_project;
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
        btPublishCommit.setOnClickListener(this);
        pllPublishTime.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {
            switch (v.getId()){
                case R.id.bt_publish_commit:
                    commit();
                    break;
                case R.id.pll_publish_time:
                    time();
                    break;
            }
    }

    private void time() {
        time.setLength(0);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute=calendar.get(Calendar.MINUTE);
        final TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                time.append(i + ":" + i1);
                tvPublishTime.setText(time);
            }
        }, hour, minute, true);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                time.append(i+"-"+i1+"-"+i2+" ");
                timePickerDialog.show();
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void commit() {
        if(TextUtils.isEmpty(etPublishName.getText().toString().trim())){
            ToastUtils.showToast(this,"请填写项目名称");
            return;
        }
        if(TextUtils.isEmpty(etPublishNumber.getText().toString().trim())){
            ToastUtils.showToast(this,"请填写项目编号");
            return;
        }
        if(TextUtils.isEmpty(etPublishPerson.getText().toString().trim())){
            ToastUtils.showToast(this,"请填写项目负责人");
            return;
        }
        if (tvPublishTime.getText().toString().trim().equals("请选择（必填）")){
            ToastUtils.showToast(this,"请选择开始时间");
            return;
        }
        if(TextUtils.isEmpty(etPublishDays.getText().toString().trim())){
            ToastUtils.showToast(this,"请填写预计天数");
            return;
        }
        if(TextUtils.isEmpty(etPublishContent.getText().toString().trim())){
            ToastUtils.showToast(this,"请填写项目内容");
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
