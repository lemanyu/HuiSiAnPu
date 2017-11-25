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
 * 出差总结
 */

public class WorkSummaryActivity extends BaseBackActivity {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.bt_summary_commit)
    Button btSummaryCommit;
    @BindView(R.id.tv_summary_time)
    TextView tvSummaryTime;
    @BindView(R.id.pll_summary_time)
    PercentLinearLayout pllSummaryTime;
    @BindView(R.id.et_summary_location)
    EditText etSummaryLocation;
    @BindView(R.id.et_summary_products)
    EditText etSummaryProducts;
    @BindView(R.id.et_summary_customer)
    EditText etSummaryCustomer;
    @BindView(R.id.et_summary_scene)
    EditText etSummaryScene;
    @BindView(R.id.et_summary_preliminary)
    EditText etSummaryPreliminary;
    @BindView(R.id.et_summary_practice)
    EditText etSummaryPractice;
    @BindView(R.id.et_summary_technology)
    EditText etSummaryTechnology;
    private StringBuilder time=new StringBuilder();
    @Override
    public int getLayoutId() {

        return R.layout.activity_summary;
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
           btSummaryCommit.setOnClickListener(this);
           pllSummaryTime.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {
            switch (v.getId()){
                case R.id.bt_summary_commit:
                    commit();
                    break;
                case R.id.pll_summary_time:
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
                tvSummaryTime.setText(time);
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
        if (tvSummaryTime.getText().toString().trim().equals("请选择（必填）")){
            ToastUtils.showToast(this,"请选择出差时间");
            return;
        }
        if(TextUtils.isEmpty(etSummaryLocation.getText().toString().trim())){
            ToastUtils.showToast(this,"请输入出差地点");
            return;
        }
        if(TextUtils.isEmpty(etSummaryProducts.getText().toString().trim())){
            ToastUtils.showToast(this,"请输入产品类别");
            return;
        }
        if(TextUtils.isEmpty(etSummaryCustomer.getText().toString().trim())){
            ToastUtils.showToast(this,"请输入客户负责人");
            return;
        }
        if(TextUtils.isEmpty(etSummaryScene.getText().toString().trim())){
            ToastUtils.showToast(this,"请输入现场情况信息");
            return;
        }
        if(TextUtils.isEmpty(etSummaryPreliminary.getText().toString().trim())){
            ToastUtils.showToast(this,"请输入初步解决方案");
            return;
        }
        if (TextUtils.isEmpty(etSummaryPractice.getText().toString().trim())){
            ToastUtils.showToast(this,"请输入实际解决方案");
            return;
        }
        if (TextUtils.isEmpty(etSummaryTechnology.getText().toString().trim())){
            ToastUtils.showToast(this,"请输入经验总结信息");
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
