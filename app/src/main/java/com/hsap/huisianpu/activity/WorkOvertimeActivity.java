package com.hsap.huisianpu.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
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
    private StringBuffer Pm=new StringBuffer();//下午
    private StringBuffer Am=new StringBuffer();//上午
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
        Pm.setLength(0);
        if(tvOvertimeBegin.getText().toString().trim().equals("请选择（必填）")){
            ToastUtils.showToast(this,"请先选择开始时间");
            return;
        }
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择上午/下午");
        final int[] choose = {0};
        final String[] item = {"上午", "下午"};
        builder.setSingleChoiceItems(item, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                choose[0]=i;
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Pm.append(item[choose[0]]);
                tvOvertimeEnd.setText(endTime+" "+Pm);
                if(tvOvertimeBegin.getText().toString().equals(tvOvertimeEnd.getText().toString())){
                    tvOvertimeDay.setText("0.5");
                    return;
                }
                if(endTime.toString().equals(beginTime.toString())&&!Pm.toString().equals(Am.toString())){
                    tvOvertimeDay.setText("1");
                    return;
                }
                if(!endTime.toString().equals(beginTime.toString())&&Pm.toString().equals(Am.toString())){
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
                    try {
                        Date begin = sdf.parse(beginTime.toString());
                        Date end = sdf.parse(endTime.toString());
                        int day = (int) ((end.getTime() - begin.getTime()) / (1000 * 3600 * 24));
                        tvOvertimeDay.setText(day+".5");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }else {
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
                    try {
                        Date begin = sdf.parse(beginTime.toString());
                        Date end = sdf.parse(endTime.toString());
                        int day = (int) ((end.getTime() - begin.getTime()) / (1000 * 3600 * 24));
                        tvOvertimeDay.setText(day+1+"");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        builder.setNegativeButton("取消",null);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                endTime.append(i + "-" + (i1 + 1) + "-" + i2);
                builder.show();
            }
        },year,month,day).show();
    }


    private void showBegin() {
        beginTime.setLength(0);
        Am.setLength(0);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择上午/下午");
        final int[] choose = {0};
        final String[] item = {"上午", "下午"};
        builder.setSingleChoiceItems(item, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                choose[0]=i;
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Am.append(item[choose[0]]);
                tvOvertimeBegin.setText(beginTime+" "+Am);
            }
        });
        builder.setNegativeButton("取消",null);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                beginTime.append(i + "-" + (i1 + 1) + "-" + i2);
                builder.show();
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
