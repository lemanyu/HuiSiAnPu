package com.hsap.huisianpu.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.hsap.huisianpu.R;
import com.hsap.huisianpu.adapter.ApproveGridViewAdapter;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.bean.Bean;
import com.hsap.huisianpu.utils.ToastUtils;
import com.hsap.huisianpu.utils.Utils;
import com.hsap.huisianpu.view.MyGridView;
import com.zhy.android.percent.support.PercentLinearLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 用车表
 */

public class WorkCarActivity extends BaseBackActivity {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.bt_car_commit)
    Button btCarCommit;
    @BindView(R.id.tv_car_begin)
    TextView tvCarBegin;
    @BindView(R.id.pll_car_begin)
    PercentLinearLayout pllCarBegin;
    @BindView(R.id.tv_car_end)
    TextView tvCarEnd;
    @BindView(R.id.pll_car_end)
    PercentLinearLayout pllCarEnd;
    @BindView(R.id.tv_car_choice)
    TextView tvCarChoice;
    @BindView(R.id.pll_car_choice)
    PercentLinearLayout pllCarChoice;
    @BindView(R.id.et_car_phone)
    EditText etCarPhone;
    @BindView(R.id.et_car_number)
    EditText etCarNumber;
    @BindView(R.id.et_car_matters)
    EditText etCarMatters;
    @BindView(R.id.et_car_location)
    EditText etCarLocation;
    @BindView(R.id.gv_car)
    MyGridView gvCar;
    private ApproveGridViewAdapter adapter;
    private List<Bean> list = new ArrayList<>();
    private List<String> idList = new ArrayList<>();//存放 审批人的id
    private int[] color = {R.mipmap.chengyuan, R.mipmap.fenyuan, R.mipmap.lanyuan,
            R.mipmap.luyuan, R.mipmap.ziyuan, R.mipmap.hongyuan};
    @Override
    public int getLayoutId() {
        return R.layout.activity_work_car;
    }

    @Override
    public void initView() {
        adapter = new ApproveGridViewAdapter(this, list);
        gvCar.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gvCar.setAdapter(adapter);
        gvCar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == list.size()) {
                    //跳到选择联系人页面
                    startActivityForResult(
                            new Intent(WorkCarActivity.this,
                                    SelectApproverActivity.class), 100);
                } else {
                    list.remove(position);
                    idList.remove(position);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
        btCarCommit.setOnClickListener(this);
        pllCarBegin.setOnClickListener(this);
        pllCarEnd.setOnClickListener(this);
        pllCarChoice.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.bt_car_commit:
                showCommit();
                break;
            case R.id.pll_car_begin:
                showBegin();
                break;
            case R.id.pll_car_end:
                showEnd();
                break;
            case R.id.pll_car_choice:
                showChoice();
                break;
        }
    }

    private void showChoice() {
        final int[] choose = {0};
        final String[] item = {"商务用车", "公务用车"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择车辆类型");
        builder.setSingleChoiceItems(item, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                choose[0] = i;
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tvCarChoice.setText(item[choose[0]]);
            }
        });
        builder.show();
    }

    private void showEnd() {
        final StringBuilder endTime = new StringBuilder();
        if (tvCarBegin.getText().toString().trim().equals("请选择（必填）")) {
            ToastUtils.showToast(this, "请先选择开始时间");
            return;
        }
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        final TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                endTime.append(" " + i + ":" + i1);
                tvCarEnd.setText(endTime);
            }
        }, hour, minute, true);
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                endTime.append(i + "-" + (i1 + 1) + "-" + i2);
                timePickerDialog.show();
            }
        }, year, month, day).show();

    }

    private void showBegin() {
        final StringBuilder beginTime = new StringBuilder();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) ;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        final TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                beginTime.append(" " + i + ":" + i1);
                tvCarBegin.setText(beginTime);
            }
        }, hour, minute, true);
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                beginTime.append(i + "-" + (i1 + 1) + "-" + i2);
                timePickerDialog.show();
            }
        }, year, month, day).show();
    }

    private void showCommit() {
        if (tvCarBegin.getText().toString().trim().equals("请选择（必填）")) {
            ToastUtils.showToast(this, "请选择用车时间");
            return;
        }
        if (tvCarEnd.getText().toString().trim().equals("请选择（必填）")) {
            ToastUtils.showToast(this, "请选择还车时间");
            return;
        }
        if (tvCarChoice.getText().toString().trim().equals("请选择（必填）")) {
            ToastUtils.showToast(this, "请选择车辆类型");
            return;
        }
        if (TextUtils.isEmpty(etCarPhone.getText().toString().trim())) {
            ToastUtils.showToast(this, "请输入您的手机号");
            return;
        }
        if(!Utils.isPhone(etCarPhone.getText().toString().trim())){
            ToastUtils.showToast(this, "请输入正确的手机号");
            return;
        }
        if (TextUtils.isEmpty(etCarNumber.getText().toString().trim())) {
            ToastUtils.showToast(this, "请输入用车人数");
            return;
        }
        if (TextUtils.isEmpty(etCarMatters.getText().toString().trim())) {
            ToastUtils.showToast(this, "请输入办理事项");
            return;
        }
        if (TextUtils.isEmpty(etCarLocation.getText().toString().trim())) {
            ToastUtils.showToast(this, "请输入办事地点");
            return;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == 101) {
                Bundle bundle = data.getExtras();
                String name = bundle.getString("name");
                String id = bundle.getString("id");
                Bean bean = new Bean();
                bean.setName(name);
                bean.setPic(color[(int) (Math.random() * 6)]);
                list.add(bean);
                idList.add(id);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
