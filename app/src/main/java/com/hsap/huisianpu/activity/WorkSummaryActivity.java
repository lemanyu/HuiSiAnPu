package com.hsap.huisianpu.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.google.gson.Gson;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.adapter.MyAdapter;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.bean.FalseBean;
import com.hsap.huisianpu.bean.SummayBean;
import com.hsap.huisianpu.push.PushTirpActivity;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.SpUtils;
import com.hsap.huisianpu.utils.ToastUtils;
import com.hsap.huisianpu.view.MyGridView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhy.android.percent.support.PercentLinearLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

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
    TextView etSummaryLocation;
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
    @BindView(R.id.gv_trip_person)
    MyGridView gvTripPerson;
    @BindView(R.id.ll_approval_trip)
    LinearLayout llApprovalTrip;
    @BindView(R.id.et_summary_mingcheng)
    EditText etSummaryMingcheng;
    private String city;
    private ArrayList<Integer> nameid;
    private int id;
    private StringBuilder begin = new StringBuilder();
    @Override
    public int getLayoutId() {

        return R.layout.activity_summary;
    }

    @Override
    public void initView() {
        int state = getIntent().getIntExtra("state", 0);
        if (state == 1) {
            btSummaryCommit.setText("再次提交");
            LoadingDailog 获取数据中 = ToastUtils.showDailog(this, "获取数据中");
            获取数据中.show();
            dataFormNet(id, 获取数据中);
        } else {
            btSummaryCommit.setText("提交");
           String time = getIntent().getStringExtra("time");
            tvSummaryTime.setText(time);
            city = getIntent().getStringExtra("city");
            etSummaryLocation.setText(city);
            id = getIntent().getIntExtra("id", 0);
            nameid = getIntent().getIntegerArrayListExtra("nameid");
            ArrayList<String> namelist = getIntent().getStringArrayListExtra("namelist");
            if (namelist.size() != 0 && namelist != null) {
                llApprovalTrip.setVisibility(View.VISIBLE);
                gvTripPerson.setAdapter(new MyAdapter(WorkSummaryActivity.this, namelist));
            } else {
                llApprovalTrip.setVisibility(View.GONE);
            }
        }
    }

    private void dataFormNet(int id, final LoadingDailog 获取数据中) {
        OkGo.<String>post(NetAddressUtils.queryTTBSummar).
                params("id",id).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        获取数据中.dismiss();
                        SummayBean bean = new Gson().fromJson(response.body().toString(), SummayBean.class);
                        if (bean.getWaIntegration().getStartTime().getHour() == 8) {
                            begin.setLength(0);
                            begin.append("上午");
                        } else {
                            begin.setLength(0);
                            begin.append("下午");
                        }
                        tvSummaryTime.setText(bean.getWaIntegration().getStartTime().getYear()+"-"+
                                bean.getWaIntegration().getStartTime().getMonthValue()+"-"+
                                bean.getWaIntegration().getStartTime().getDayOfMonth()+" "+begin);
                        etSummaryLocation.setText(bean.getWaIntegration().getType2());
                        etSummaryProducts.setText(bean.getSummary().getType());
                        etSummaryMingcheng.setText(bean.getSummary().getCname());
                        etSummaryCustomer.setText(bean.getSummary().getCmanager());
                        etSummaryScene.setText(bean.getSummary().getBody1());
                        etSummaryPreliminary.setText(bean.getSummary().getBody2());
                        etSummaryPractice.setText(bean.getSummary().getBody3());
                        etSummaryTechnology.setText(bean.getSummary().getBody4());
                        if (bean.getNameList().size()!=0&&bean.getNameList()!=null){
                            llApprovalTrip.setVisibility(View.VISIBLE);
                            ArrayList<String> list = new ArrayList<>();
                            for (int i = 0; i <bean.getNameList().size() ; i++) {
                                list.add(bean.getNameList().get(i).getName());
                            }
                            gvTripPerson.setAdapter(new MyAdapter(WorkSummaryActivity.this,list));
                        }else {
                            llApprovalTrip.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        获取数据中.dismiss();
                        ToastUtils.showToast(WorkSummaryActivity.this,"当前无网络");
                    }
                });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
        btSummaryCommit.setOnClickListener(this);
        //pllSummaryTime.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.bt_summary_commit:
                commit();
                break;
            case R.id.pll_summary_time:
                // time();
                break;
            default:
        }
    }


    private void commit() {
       /* if (tvSummaryTime.getText().toString().trim().equals("请选择（必填）")){
            ToastUtils.showToast(this,"请选择出差时间");
            return;
        }
        if(TextUtils.isEmpty(etSummaryLocation.getText().toString().trim())){
            ToastUtils.showToast(this,"请输入出差地点");
            return;
        }*/
        if (TextUtils.isEmpty(etSummaryProducts.getText().toString().trim())) {
            ToastUtils.showToast(this, "请输入产品类别");
            return;
        }
        if (TextUtils.isEmpty(etSummaryMingcheng.getText().toString().trim())) {
            ToastUtils.showToast(this, "请输入客户名称");
            return;
        }
        if (TextUtils.isEmpty(etSummaryCustomer.getText().toString().trim())) {
            ToastUtils.showToast(this, "请输入客户负责人");
            return;
        }
        if (TextUtils.isEmpty(etSummaryScene.getText().toString().trim())) {
            ToastUtils.showToast(this, "请输入现场情况信息");
            return;
        }
        if (TextUtils.isEmpty(etSummaryPreliminary.getText().toString().trim())) {
            ToastUtils.showToast(this, "请输入初步解决方案");
            return;
        }
        if (TextUtils.isEmpty(etSummaryPractice.getText().toString().trim())) {
            ToastUtils.showToast(this, "请输入实际解决方案");
            return;
        }
        if (TextUtils.isEmpty(etSummaryTechnology.getText().toString().trim())) {
            ToastUtils.showToast(this, "请输入经验总结信息");
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确定要提交嘛");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final LoadingDailog 提交中 = ToastUtils.showDailog(WorkSummaryActivity.this, "提交中");
                提交中.show();

                OkGo.<String>post(NetAddressUtils.insertTTBSummary).
                        params("tripId", id).
                        params("workerId", SpUtils.getInt(ConstantUtils.UserId, WorkSummaryActivity.this)).
                        params("body1", etSummaryScene.getText().toString().trim()).
                        params("body2", etSummaryPreliminary.getText().toString().trim()).
                        params("body3", etSummaryPractice.getText().toString().trim()).
                        params("body4", etSummaryTechnology.getText().toString().trim()).
                        params("type", etSummaryProducts.getText().toString().trim()).
                        params("cName", etSummaryMingcheng.getText().toString().trim()).
                        params("cManager", etSummaryCustomer.getText().toString().trim()).
                        params("activity", "com.hsap.huisianpu.push.PushTirpActivity").
                        execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                提交中.dismiss();
                                ToastUtils.showToast(WorkSummaryActivity.this, "提交成功");
                                EventBus.getDefault().post(new FalseBean("bb"));
                            }

                            @Override
                            public void onError(Response<String> response) {
                                super.onError(response);
                                提交中.dismiss();
                                ToastUtils.showToast(WorkSummaryActivity.this, "提交失败");
                            }
                        });
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
