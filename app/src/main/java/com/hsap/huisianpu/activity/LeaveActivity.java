package com.hsap.huisianpu.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.google.gson.Gson;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.adapter.ApproveGridViewAdapter;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.bean.Bean;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.SpUtils;
import com.hsap.huisianpu.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
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
    @BindView(R.id.tv_qingjialeixing)
    TextView tvQingjialeixing;
    @BindView(R.id.gv_leave)
    GridView gvLeave;
    @BindView(R.id.tv_beginTime)
    TextView tvBeginTime;
    @BindView(R.id.tv_endTime)
    TextView tvEndTime;
    @BindView(R.id.tv_shichang)
    TextView tvShichang;
    private ApproveGridViewAdapter adapter;
    private List<Bean> list = new ArrayList<>();
    private List<String> idList = new ArrayList<>();//存放 审批人的id
    private int[] color = {R.mipmap.chengyuan, R.mipmap.fenyuan, R.mipmap.lanyuan,
            R.mipmap.luyuan, R.mipmap.ziyuan, R.mipmap.hongyuan};
    private int year;
    private int month;
    private int day;
    private StringBuffer endtime = new StringBuffer();
    private StringBuffer begintime=new StringBuffer();
    private StringBuffer Pm=new StringBuffer();//下午
    private StringBuffer Am=new StringBuffer();//上午
    @Override
    public int getLayoutId() {

        return R.layout.activity_work_leave;
    }

    @Override
    public void initView() {
        adapter = new ApproveGridViewAdapter(LeaveActivity.this, list);
        gvLeave.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gvLeave.setAdapter(adapter);
        gvLeave.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == list.size()) {
                    //跳到选择联系人页面
                    startActivityForResult(
                            new Intent(LeaveActivity.this,
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
        btLearveCommit.setOnClickListener(this);
        pllQingjia.setOnClickListener(this);
        pllBegin.setOnClickListener(this);
        pllEnd.setOnClickListener(this);

    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.bt_learve_commit:
                commit();
                break;
            case R.id.pll_qingjia:
                showSingleChoiceDialog();
                break;
            case R.id.pll_begin:
                showBeginTime();
                break;
            case R.id.pll_end:
                showEndTime();
                break;
        }
    }

    private void showEndTime() {
        endtime.setLength(0);
        Pm.setLength(0);
        if(tvBeginTime.getText().toString().trim().equals("请选择（必填）")){
            ToastUtils.showToast(this,"请选择开始时间");
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
                tvEndTime.setText(endtime+" "+Pm);
                if(tvBeginTime.getText().toString().equals(tvEndTime.getText().toString())){
                  tvShichang.setText("0.5");
                   return;
                }
                if(endtime.toString().equals(begintime.toString())&&!Pm.toString().equals(Am.toString())){
                    tvShichang.setText("1");
                    return;
                }
                if(!endtime.toString().equals(begintime.toString())&&Pm.toString().equals(Am.toString())){
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
                    try {
                        Date begin = sdf.parse(begintime.toString());
                        Date end = sdf.parse(endtime.toString());
                        int day = (int) ((end.getTime() - begin.getTime()) / (1000 * 3600 * 24));
                        tvShichang.setText(day+".5");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }else {
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
                    try {
                        Date begin = sdf.parse(begintime.toString());
                        Date end = sdf.parse(endtime.toString());
                        int day = (int) ((end.getTime() - begin.getTime()) / (1000 * 3600 * 24));
                        tvShichang.setText(day+1+"");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        builder.setNegativeButton("取消",null);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dateDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                endtime.append(i + "-" + (i1 + 1) + "-" + i2);
                builder.show();

            }
        }, year, month, day);
        dateDialog.show();
    }

    private void showBeginTime() {
        begintime.setLength(0);
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
                tvBeginTime.setText(begintime+" "+Am);
            }
        });
        builder.setNegativeButton("取消",null);

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dateDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                begintime.append(i + "-" + (i1 + 1) + "-" + i2);
                builder.show();
            }
        }, year, month, day);
        dateDialog.show();
    }

    private void commit() {
        if(tvQingjialeixing.getText().toString().trim().equals("请选择类型")){
             ToastUtils.showToast(this,"请选择请假类型");
             return;
        }
        if(tvBeginTime.getText().toString().trim().equals("请选择（必填）")){
            ToastUtils.showToast(this,"请选择开始时间");
            return;
        }
        if(tvEndTime.getText().toString().trim().equals("请选择（必填）")){
            ToastUtils.showToast(this,"请选择结束时间");
            return;
        }
        if (Long.valueOf(tvShichang.getText().toString().trim())<0){
            ToastUtils.showToast(this,"请选择正确的结束日期");
            return;
        }
        if(list.size()==0){
            ToastUtils.showToast(this,"请选择审批人");
            return;
        }
        if(TextUtils.isEmpty(etLeave.getText().toString().trim())){
            ToastUtils.showToast(this,"请输入请假事由");
            return;
        }
        final LoadingDailog dailog = ToastUtils.showDailog(LeaveActivity.this, "审批中");
        dailog.show();
        OkGo.<String>post(NetAddressUtils.leave).
                params("workersId",
                        SpUtils.getInt(ConstantUtils.UserId,
                                LeaveActivity.this)).
                params("leaveType",tvQingjialeixing.getText().toString()).
                params("startTime",begintime+"").
                params("endTime",endtime+"").
                params("reason",etLeave.getText().toString()).
                params("ids",new Gson().toJson(idList))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dailog.dismiss();
                       ToastUtils.showToast(LeaveActivity.this,"提交成功");
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dailog.dismiss();
                        ToastUtils.showToast(LeaveActivity.this,"提交失败，当前网络不好");
                    }
                });
    }

    private void showSingleChoiceDialog() {
        final int[] choose = {0};
        final String[] item = {"事假", "病假", "产假", "婚假", "丧假"};
        AlertDialog.Builder builder = new AlertDialog.Builder(LeaveActivity.this);
        builder.setTitle("请选择请假事由");
        builder.setSingleChoiceItems(item, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                choose[0] = i;
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
