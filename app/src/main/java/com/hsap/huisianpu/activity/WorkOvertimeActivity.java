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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhao on 2017/11/23.
 */

public class WorkOvertimeActivity extends BaseBackActivity {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.bt_overtime_commit)
    Button btOvertimeCommit;
    @BindView(R.id.tv_overtime_begin)
    TextView tvOvertimeBegin;
    @BindView(R.id.pll_overtime_begin)
    PercentLinearLayout pllOvertimeBegin;
    @BindView(R.id.tv_overtime_end)
    TextView tvOvertimeEnd;
    @BindView(R.id.pll_overtime_end)
    PercentLinearLayout pllOvertimeEnd;
    @BindView(R.id.tv_overtime_day)
    TextView tvOvertimeDay;
    @BindView(R.id.et_overtime_cause)
    EditText etOvertimeCause;
    StringBuilder beginTime=new StringBuilder();
    StringBuilder endTime=new StringBuilder();
    @Override
    public int getLayoutId() {

        return R.layout.activity_work_overtime;
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
      btOvertimeCommit.setOnClickListener(this);
      pllOvertimeBegin.setOnClickListener(this);
      pllOvertimeEnd.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {
            switch (v.getId()){
                case R.id.bt_overtime_commit:
                    showCommit();
                    break;
                case R.id.pll_overtime_begin:
                    showBegin();
                    break;
                case R.id.pll_overtime_end:
                    showEnd();
                    break;
            }
    }

    private void showEnd() {
        endTime.setLength(0);
        if(tvOvertimeBegin.getText().toString().trim().equals("请选择（必填）")){
            ToastUtils.showToast(this,"请先选择开始时间");
            return;
        }
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        final TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                endTime.append(" " + i + ":" + i1);
                tvOvertimeEnd.setText(endTime);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                try {
                    Date begin = format.parse(beginTime.toString());
                    Date end=format.parse(endTime.toString());
                    tvOvertimeDay.setText((end.getTime()-begin.getTime())/(60*60*1000*24)+"天");

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }, hour, minute, true);
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                endTime.append(i + "-" + (i1 + 1) + "-" + i2);
                timePickerDialog.show();
            }
        },year,month,day).show();
    }


    private void showBegin() {
        beginTime.setLength(0);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        final TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                beginTime.append(" " + i + ":" + i1);
                tvOvertimeBegin.setText(beginTime);
            }
        }, hour, minute, true);
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                beginTime.append(i + "-" + (i1 + 1) + "-" + i2);
                timePickerDialog.show();
            }
        },year,month,day).show();
    }

    private void showCommit() {
        if(tvOvertimeBegin.getText().toString().trim().equals("请选择（必填）")){
            ToastUtils.showToast(this,"请选择开始时间");
            return;
        }
        if(tvOvertimeEnd.getText().toString().trim().equals("请选择（必填）")){
            ToastUtils.showToast(this,"请选择结束时间");
            return;
        }
        if(TextUtils.isEmpty(etOvertimeCause.getText().toString().trim())){
            ToastUtils.showToast(this,"请输入加班原因");
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
