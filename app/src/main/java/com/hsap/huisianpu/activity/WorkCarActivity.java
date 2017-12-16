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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.android.tu.loadingdialog.LoadingDailog;
import com.google.gson.Gson;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.adapter.AccompanyGvidViewAdapter;
import com.hsap.huisianpu.adapter.ApproveGridViewAdapter;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.bean.Bean;
import com.hsap.huisianpu.bean.CarBean;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.SpUtils;
import com.hsap.huisianpu.utils.ToastUtils;
import com.hsap.huisianpu.utils.Utils;
import com.hsap.huisianpu.view.MyGridView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhy.android.percent.support.PercentLinearLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 用车表
 */

public class WorkCarActivity extends BaseBackActivity {
    private static final String TAG = "WorkCarActivity";
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
    @BindView(R.id.et_car_matters)
    EditText etCarMatters;
    @BindView(R.id.et_car_location)
    EditText etCarLocation;
    @BindView(R.id.gv_car_person)
    MyGridView gvCarPerson;
    @BindView(R.id.bt_leave_again)
    Button btLeaveAgain;
    /*@BindView(R.id.gv_car)
    MyGridView gvCar;*/
    private ApproveGridViewAdapter adapter;
    private List<Bean> list = new ArrayList<>();
    private List<String> idList = new ArrayList<>();//存放 审批人的id
    private List<Bean> personList = new ArrayList<>();//陪同人
    private List<Integer> personIdList = new ArrayList<>();//陪同人id
    private AccompanyGvidViewAdapter accompanyGvidViewAdapter;
    private int[] color = {R.mipmap.chengyuan, R.mipmap.fenyuan, R.mipmap.lanyuan,
            R.mipmap.luyuan, R.mipmap.ziyuan, R.mipmap.hongyuan};

    @Override
    public int getLayoutId() {
        return R.layout.activity_work_car;
    }

    @Override
    public void initView() {
        accompanyGvidViewAdapter = new AccompanyGvidViewAdapter(this, personList);
        gvCarPerson.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gvCarPerson.setAdapter(accompanyGvidViewAdapter);
        gvCarPerson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == personList.size()) {
                    //跳转到选择陪同人
                    Intent intent = new Intent(WorkCarActivity.this, ChooseAccompanyActivity.class);
                    startActivityForResult(intent, 200);
                } else {
                    personList.remove(position);
                    personIdList.remove(position);
                    accompanyGvidViewAdapter.notifyDataSetChanged();
                }
            }
        });

        /*gvCar.setSelector(new ColorDrawable(Color.TRANSPARENT));
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
        });*/
    }

    @Override
    public void initData() {
        int id = getIntent().getIntExtra("id", 0);
        int state = getIntent().getIntExtra("state", 0);
        if (state == 1) {
            LoadingDailog 获取数据中 = ToastUtils.showDailog(this, "获取数据中");
            获取数据中.show();
            btCarCommit.setVisibility(View.GONE);
            btLeaveAgain.setVisibility(View.VISIBLE);
            dataFormNet(id, 获取数据中);
        } else {
            btCarCommit.setVisibility(View.VISIBLE);
            btLeaveAgain.setVisibility(View.GONE);
        }
    }

    private void dataFormNet(int id, final LoadingDailog 获取数据中) {
        OkGo.<String>post(NetAddressUtils.selectOneIntergration).
                params("id", id).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        CarBean bean = new Gson().fromJson(response.body(), CarBean.class);
                        if (bean.isSuccess()) {
                            获取数据中.dismiss();

                            tvCarBegin.setText(bean.getData().getWaIntegration().getStartTime().getYear() + "-" +
                                    bean.getData().getWaIntegration().getStartTime().getMonthValue() + "-"
                                    + bean.getData().getWaIntegration().getStartTime().getDayOfMonth() + " " +
                                    bean.getData().getWaIntegration().getStartTime().getHour() + ":" +
                                    bean.getData().getWaIntegration().getStartTime().getMinute());
                            tvCarEnd.setText(bean.getData().getWaIntegration().getEndTime().getYear() + "-" +
                                    bean.getData().getWaIntegration().getEndTime().getMonthValue() + "-"
                                    + bean.getData().getWaIntegration().getEndTime().getDayOfMonth() + " " +
                                    bean.getData().getWaIntegration().getEndTime().getHour() + ":" +
                                    bean.getData().getWaIntegration().getEndTime().getMinute());
                            tvCarChoice.setText(bean.getData().getObject().getLeixing());
                            etCarPhone.setText(bean.getData().getWaIntegration().getType2());
                            etCarMatters.setText(bean.getData().getObject().getShixiang());
                            etCarLocation.setText(bean.getData().getObject().getDidian());
                            if (bean.getData().getNameList().size() != 0 && bean.getData().getNameList() != null) {
                                for (int i = 0; i <bean.getData().getNameList().size(); i++) {
                                    personList.add(new Bean(bean.getData().getNameList().get(i),color[(int) (Math.random() * 6)]));
                                    personIdList.add(bean.getData().getNameId().get(i));
                                }
                                gvCarPerson.setSelector(new ColorDrawable(Color.TRANSPARENT));
                                gvCarPerson.setAdapter(accompanyGvidViewAdapter);
                            } else {
                            }
                        } else {
                            获取数据中.dismiss();
                            ToastUtils.showToast(WorkCarActivity.this, "当前无网络");
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        获取数据中.dismiss();
                        ToastUtils.showToast(WorkCarActivity.this, "当前无网络");
                    }
                });
    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
        btCarCommit.setOnClickListener(this);
        btLeaveAgain.setOnClickListener(this);
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
            case R.id.bt_leave_again:
                showCommit();
                break;
                default:
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
        int month = calendar.get(Calendar.MONTH);
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date begin = sdf.parse(tvCarBegin.getText().toString().trim());
            Date end = sdf.parse(tvCarEnd.getText().toString().trim());
            if (end.getTime() - begin.getTime() <= 0) {
                ToastUtils.showToast(this, "请选择正确的还车时间");
                return;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (tvCarChoice.getText().toString().trim().equals("请选择（必填）")) {
            ToastUtils.showToast(this, "请选择车辆类型");
            return;
        }
        if (TextUtils.isEmpty(etCarPhone.getText().toString().trim())) {
            ToastUtils.showToast(this, "请输入您的手机号");
            return;
        }
        if (!Utils.isPhone(etCarPhone.getText().toString().trim())) {
            ToastUtils.showToast(this, "请输入正确的手机号");
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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确定要提交吗？");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                HashMap map = new HashMap();
                map.put("shixiang", etCarMatters.getText().toString().trim());
                map.put("didian", etCarLocation.getText().toString().trim());
                map.put("leixing", tvCarChoice.getText().toString().trim());
                final LoadingDailog dailog = ToastUtils.showDailog(WorkCarActivity.this, "提交中");
                dailog.show();
                OkGo.<String>post(NetAddressUtils.insertIntegration).
                        params("workersId", SpUtils.getInt(ConstantUtils.UserId, WorkCarActivity.this)).
                        params("startTime", tvCarBegin.getText().toString().trim()).
                        params("endTime", tvCarEnd.getText().toString().trim()).
                        params("type", 4).
                        params("type2", etCarPhone.getText().toString().trim()).
                        params("ids", new Gson().toJson(personIdList)).
                        params("activity", "com.hsap.huisianpu.push.PushTirpActivity").
                        params("o", map.toString()).execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dailog.dismiss();
                        ToastUtils.showToast(WorkCarActivity.this, "提交成功");
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dailog.dismiss();
                        ToastUtils.showToast(WorkCarActivity.this, "提交失败");
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
        if (requestCode == 200) {
            if (resultCode == 201) {
                Log.e(TAG, "onActivityResult: " );
                ArrayList<String> namelist = data.getStringArrayListExtra("namelist");
                ArrayList<Integer> idlist = data.getIntegerArrayListExtra("idlist");
                for (int i = 0; i < namelist.size(); i++) {
                    Bean bean = new Bean();
                    bean.setName(namelist.get(i));
                    bean.setPic(color[(int) (Math.random() * 6)]);
                    personList.add(bean);
                }
                personIdList.addAll(idlist);
                accompanyGvidViewAdapter.notifyDataSetChanged();

            }
        }
    }
}
