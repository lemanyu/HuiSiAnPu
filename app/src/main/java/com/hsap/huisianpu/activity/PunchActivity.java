package com.hsap.huisianpu.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.google.gson.Gson;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.bean.DaySignPushBean;
import com.hsap.huisianpu.bean.IsSignPushBean;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.SpUtils;
import com.hsap.huisianpu.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 打卡页面
 */

public class PunchActivity extends BaseBackActivity {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.ll_time)
    LinearLayout llTime;
    @BindView(R.id.punch_toolbar)
    Toolbar punchToolbar;
    @BindView(R.id.tv_punch_date)
    TextView tvPunchDate;
    @BindView(R.id.punch_fab)
    FloatingActionButton punchFab;
    @BindView(R.id.tv_shangbantime)
    TextView tvShangbantime;
    @BindView(R.id.tv_shangbandidian)
    TextView tvShangbandidian;
    @BindView(R.id.ll_shangban)
    LinearLayout llShangban;
    @BindView(R.id.tv_xiabantime)
    TextView tvXiabantime;
    @BindView(R.id.tv_xiabandidian)
    TextView tvXiabandidian;
    @BindView(R.id.ll_xiaban)
    LinearLayout llXiaban;
    @BindView(R.id.tv_zhuangtai)
    TextView tvZhuangtai;
    private int nowYear;
    private int nowMonth;
    private int nowDay;
    //声明mlocationClient对象
    public AMapLocationClient mlocationClient;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;
    private int nowHour;
    private String s;


    @Override
    public int getLayoutId() {
        return R.layout.activity_punch;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        tvZhuangtai.setText("打开记录时间和位置");
        Calendar calendar = Calendar.getInstance();
        nowYear = calendar.get(Calendar.YEAR);
        nowMonth = calendar.get(Calendar.MONTH) + 1;
        nowDay = calendar.get(Calendar.DAY_OF_MONTH);
        nowHour = calendar.get(Calendar.HOUR_OF_DAY);
        timeAndLocalformNet(nowYear,nowMonth,nowDay);
        if (nowHour>7&&nowHour<9){
            punchFab.setVisibility(View.VISIBLE);
        }else {
            punchFab.setVisibility(View.GONE);
        }
        if(nowHour>17&&nowHour<18){
            punchFab.setVisibility(View.VISIBLE);
        }else {
            punchFab.setVisibility(View.GONE);
        }
        tvPunchDate.setText(nowYear + "." + nowMonth + "." + nowDay);
    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
        llTime.setOnClickListener(this);
        punchFab.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.ll_time:
                updateTime(v);
                break;
            case R.id.punch_fab:
                punchPermission();
                break;
        }
    }

    private void updateTime(final View v) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                                                                   @Override
                                                                   public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                                                       int month = monthOfYear + 1;
                                                                       String date = year + "." + month + "." + dayOfMonth;
                                                                       tvPunchDate.setText(date);

                                                                      timeAndLocalformNet(year,month,dayOfMonth);


                                                                   }
                                                               }, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.setVersion(DatePickerDialog.Version.VERSION_2);
        dialog.show(getFragmentManager(), "aaaaaaaaaa");
    }

    private void timeAndLocalformNet(int year, int month, int dayOfMonth) {
        OkGo.<String>post(NetAddressUtils.getDaySignPush).
                params("id",SpUtils.getInt(ConstantUtils.UserId,PunchActivity.this)).
                params("year",year).
                params("month",month).params("day",dayOfMonth).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e("getDaySignPush",response.body().toString());
                        DaySignPushBean bean = new Gson().fromJson(response.body().toString(), DaySignPushBean.class);
                        if (bean.isSuccess()){
                            tvZhuangtai.setText("打开记录时间和位置");
                            if(bean.getData().getTopSign()==null){
                                tvShangbantime.setText("");
                                tvShangbandidian.setText("");
                            }else {
                                tvShangbantime.setText("上班打卡时间"+bean.getData().
                                        getTopSign().getHour()+":"+bean.getData().getTopSign().getMinute());
                                tvShangbandidian.setText(bean.getData().getTopPosition());
                            }
                            if (bean.getData().getDownSign()==null){
                                tvXiabantime.setText("");
                                tvXiabandidian.setText("");
                            }else {
                                tvXiabantime.setText("下班打卡时间"+bean.getData().getDownSign().getHour()+
                                        ":"+bean.getData().getDownSign().getMinute());
                                tvXiabandidian.setText(bean.getData().getDownPosition()+"");
                            }

                        }else {
                            tvZhuangtai.setText(bean.getMsg()+"");
                            tvShangbantime.setText("");
                            tvShangbandidian.setText("");
                            tvXiabantime.setText("");
                            tvXiabandidian.setText("");
                        }

                    }
                });
    }

    private void punchPermission() {
        AndPermission.with(PunchActivity.this)
                .requestCode(1)
                .permission(Permission.PHONE, Permission.LOCATION, Permission.STORAGE)
                .rationale(rationaleListener)
                .callback(permissionListener)
                .start();
    }

    private PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
            switch (requestCode) {
                case 1:
                    获取位置();
                    break;
            }
        }

        @Override
        public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
            ToastUtils.showToast(PunchActivity.this, "请到设置-权限管理中开启");
            if (AndPermission.hasAlwaysDeniedPermission(PunchActivity.this, deniedPermissions)) {
                // 第一种：用默认的提示语。
                AndPermission.defaultSettingDialog(PunchActivity.this, 1).show();
            }
        }
    };

    private void 获取位置() {
        mlocationClient = new AMapLocationClient(this);
//初始化定位参数
        mLocationOption = new AMapLocationClientOption();
//设置定位监听
        mlocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        //定位成功回调信息，设置相关消息
                        String province = aMapLocation.getProvince();//省信息
                        String city = aMapLocation.getCity();//城市信息
                        String district = aMapLocation.getDistrict();//城区信息
                        String street = aMapLocation.getStreet();//街道信息
                        String streetNum = aMapLocation.getStreetNum();//街道门牌号信息
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date(aMapLocation.getTime());
                        String localeTime = df.format(date);//定位时间
                        String locale = province + city + district + street + streetNum;//定位地点
                        mlocationClient.stopLocation();
                        daka(locale);
                    } else {
                        //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                        ToastUtils.showToast(PunchActivity.this, "获取错误");
                        return;
                    }
                }
            }
        });
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
//设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
// 注意设置合适的定位时间的间隔（最小间隔支持为1000ms），并且在合适时间调用stopLocation()方法来取消定位请求
// 在定位结束后，在合适的生命周期调用onDestroy()方法
// 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
//启动定位
        mlocationClient.startLocation();
    }

    private RationaleListener rationaleListener = new RationaleListener() {
        @Override
        public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
            AndPermission.rationaleDialog(PunchActivity.this, rationale).show();
        }
    };

    //打卡操作
    private void daka(final String locale) {
        OkGo.<String>post(NetAddressUtils.isSignPush).params("id", SpUtils.getInt(ConstantUtils.UserId, PunchActivity.this)).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                IsSignPushBean bean = new Gson().fromJson(response.body().toString(), IsSignPushBean.class);
                s = bean.getData();

            }
        });
        if (s.equals("未打卡")) {
            OkGo.<String>post(NetAddressUtils.signTopPush).
                    params("id", SpUtils.getInt(ConstantUtils.UserId, PunchActivity.this))
                    .params("position", locale).execute(new StringCallback() {
                @Override
                public void onSuccess(Response<String> response) {
                    Calendar calendar = Calendar.getInstance();
                    int hour = calendar.get(Calendar.HOUR_OF_DAY);
                    int minute = calendar.get(Calendar.MINUTE);
                    //上班打卡
                    ToastUtils.showToast(PunchActivity.this, "上班打卡成功");
                    llShangban.setVisibility(View.VISIBLE);
                    tvShangbantime.setText("上班打卡时间" + hour + ":" + minute);
                    tvShangbandidian.setText(locale);
                }
            });
        } else {
            OkGo.<String>post(NetAddressUtils.signDownPush).params("id", SpUtils.getInt(ConstantUtils.UserId, PunchActivity.this))
                    .params("position", locale).execute(new StringCallback() {
                @Override
                public void onSuccess(Response<String> response) {
                    Calendar calendar = Calendar.getInstance();
                    int hour = calendar.get(Calendar.HOUR_OF_DAY);
                    int minute = calendar.get(Calendar.MINUTE);
                    //下班打卡
                    ToastUtils.showToast(PunchActivity.this, "下班打卡成功");
                    llXiaban.setVisibility(View.VISIBLE);
                    tvXiabantime.setText("下班打卡时间" + hour + ":" + minute);
                    tvXiabandidian.setText(locale);
                    punchFab.setVisibility(View.GONE);
                }
            });

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
