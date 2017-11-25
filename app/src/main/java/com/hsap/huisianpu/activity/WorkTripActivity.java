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

public class WorkTripActivity extends BaseBackActivity {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.bt_trip_commit)
    Button btTripCommit;
    @BindView(R.id.et_trip_reason)
    EditText etTripReason;
    @BindView(R.id.et_trip_city)
    EditText etTripCity;
    @BindView(R.id.tv_trip_begin)
    TextView tvTripBegin;
    @BindView(R.id.pll_trip_begin)
    PercentLinearLayout pllTripBegin;
    @BindView(R.id.tv_trip_end)
    TextView tvTripEnd;
    @BindView(R.id.pll_trip_end)
    PercentLinearLayout pllTripEnd;
    @BindView(R.id.tv_trip_day)
    TextView tvTripDay;
    @BindView(R.id.et_trip_remark)
    EditText etTripRemark;
   private StringBuilder beginTime=new StringBuilder();
   private StringBuilder endTime=new StringBuilder();
    @Override
    public int getLayoutId() {

        return R.layout.activity_work_trip;
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
          btTripCommit.setOnClickListener(this);
          pllTripBegin.setOnClickListener(this);
          pllTripEnd.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {
          switch (v.getId()){
              case R.id.bt_trip_commit:
                  showCommit();
                  break;
              case R.id.pll_trip_begin:
                  showBegin();
                  break;
              case R.id.pll_trip_end:
                  showEnd();
                  break;
          }
    }

    private void showBegin() {
        beginTime.setLength(0);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                beginTime.append(i + "-" + (i1 + 1) + "-" + i2);
                tvTripBegin.setText(beginTime);
            }
        },year,month,day).show();
    }
    private void showEnd() {
        endTime.setLength(0);
        if(tvTripBegin.getText().toString().trim().equals("请选择（必填）")){
            ToastUtils.showToast(this,"请先选择开始时间");
            return;
        }
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                endTime.append(i + "-" + (i1 + 1) + "-" + i2);
                tvTripEnd.setText(endTime);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date begin = format.parse(beginTime.toString());
                    Date end=format.parse(endTime.toString());
                    int day = (int) ((end.getTime() - begin.getTime()) / (60 * 60 * 1000 * 24));
                    tvTripDay.setText(day+1+"");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        },year,month,day).show();
    }

    private void showCommit() {
        if(TextUtils.isEmpty(etTripReason.getText().toString().trim())){
            ToastUtils.showToast(this,"请输入出差事由");
            return;
        }
        if(TextUtils.isEmpty(etTripCity.getText().toString().trim())){
            ToastUtils.showToast(this,"请输入出差城市");
            return;
        }
        if(tvTripBegin.getText().toString().trim().equals("请选择（必填）")){
            ToastUtils.showToast(this,"请选择开始时间");
            return;
        }
        if(tvTripEnd.getText().toString().trim().equals("请选择（必填）")){
            ToastUtils.showToast(this,"请选择结束时间");
            return;
        }
        if(TextUtils.isEmpty(etTripRemark.getText().toString().trim())){
            ToastUtils.showToast(this,"请填写备注信息");
            return;
        }
        //TOdo 访问网络

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
