package com.hsap.huisianpu.activity;

import android.app.DatePickerDialog;
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

import com.hsap.huisianpu.R;
import com.hsap.huisianpu.adapter.AccompanyGvidViewAdapter;
import com.hsap.huisianpu.adapter.ApproveGridViewAdapter;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.bean.Bean;
import com.hsap.huisianpu.utils.ToastUtils;
import com.hsap.huisianpu.view.MyGridView;
import com.zhy.android.percent.support.PercentLinearLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 出差界面
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
    @BindView(R.id.gv_trip_person)
    MyGridView gvTripPerson;
    @BindView(R.id.gv_trip)
    MyGridView gvTrip;
    private StringBuilder beginTime = new StringBuilder();
    private StringBuilder endTime = new StringBuilder();
    private List<Bean> list = new ArrayList<>();
    private List<String> idList = new ArrayList<>();//存放 审批人的id
    private List<Bean> personList=new ArrayList<>();//陪同人
    private List<Integer> personIdList=new ArrayList<>();//陪同人id
    private int[] color = {R.mipmap.chengyuan, R.mipmap.fenyuan, R.mipmap.lanyuan,
            R.mipmap.luyuan, R.mipmap.ziyuan, R.mipmap.hongyuan};
    private ApproveGridViewAdapter adapter;
    private AccompanyGvidViewAdapter accompanyGvidViewAdapter;
    @Override
    public int getLayoutId() {

        return R.layout.activity_work_trip;
    }

    @Override
    public void initView() {
        accompanyGvidViewAdapter=new AccompanyGvidViewAdapter(WorkTripActivity.this,personList);
        gvTripPerson.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gvTripPerson.setAdapter(accompanyGvidViewAdapter);
        gvTripPerson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if(position==personList.size()){
                    //跳转到选择陪同人
                    Intent intent = new Intent(WorkTripActivity.this, ChooseAccompanyActivity.class);
                    startActivityForResult(intent,200);
                }else {
                    personList.remove(position);
                    personIdList.remove(position);
                    accompanyGvidViewAdapter.notifyDataSetChanged();
                }
            }
        });

        adapter = new ApproveGridViewAdapter(WorkTripActivity.this, list);
        gvTrip.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gvTrip.setAdapter(adapter);
        gvTrip.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == list.size()) {
                    //跳到选择联系人页面
                    startActivityForResult(
                            new Intent(WorkTripActivity.this,
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
        btTripCommit.setOnClickListener(this);
        pllTripBegin.setOnClickListener(this);
        pllTripEnd.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
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
        }, year, month, day).show();
    }

    private void showEnd() {
        endTime.setLength(0);
        if (tvTripBegin.getText().toString().trim().equals("请选择（必填）")) {
            ToastUtils.showToast(this, "请先选择开始时间");
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
                    Date end = format.parse(endTime.toString());
                    int day = (int) ((end.getTime() - begin.getTime()) / (60 * 60 * 1000 * 24));
                    tvTripDay.setText(day + 1 + "");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }, year, month, day).show();
    }

    private void showCommit() {
        if (TextUtils.isEmpty(etTripReason.getText().toString().trim())) {
            ToastUtils.showToast(this, "请输入出差事由");
            return;
        }
        if (TextUtils.isEmpty(etTripCity.getText().toString().trim())) {
            ToastUtils.showToast(this, "请输入出差城市");
            return;
        }
        if (tvTripBegin.getText().toString().trim().equals("请选择（必填）")) {
            ToastUtils.showToast(this, "请选择开始时间");
            return;
        }
        if (tvTripEnd.getText().toString().trim().equals("请选择（必填）")) {
            ToastUtils.showToast(this, "请选择结束时间");
            return;
        }
        if (TextUtils.isEmpty(etTripRemark.getText().toString().trim())) {
            ToastUtils.showToast(this, "请填写备注信息");
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
        if(requestCode==200){
            if (resultCode==201){
                ArrayList<String> namelist = data.getStringArrayListExtra("namelist");
                ArrayList<Integer> idlist = data.getIntegerArrayListExtra("idlist");
                for (int i = 0; i < namelist.size(); i++) {
                    Bean bean = new Bean();
                    bean.setName(namelist.get(i));
                    bean.setPic(color[(int) (Math.random() * 6)]);
                    personList.add(bean);
                }
                accompanyGvidViewAdapter.notifyDataSetChanged();
                personIdList.addAll(idlist);
            }
        }
    }
}
