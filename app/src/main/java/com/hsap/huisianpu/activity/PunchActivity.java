package com.hsap.huisianpu.activity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
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
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.SpUtils;
import com.hsap.huisianpu.utils.ToastUtils;
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
    @BindView(R.id.rlv_punch)
    RecyclerView rlvPunch;
    @BindView(R.id.tv_punch_date)
    TextView tvPunchDate;
    @BindView(R.id.punch_fab)
    FloatingActionButton punchFab;
    private int nowYear;
    private int nowMonth;
    private int nowDay;
    //声明mlocationClient对象
    public AMapLocationClient mlocationClient;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;
    private int nowHour;

    @Override
    public int getLayoutId() {
        return R.layout.activity_punch;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        Calendar calendar = Calendar.getInstance();
        nowYear = calendar.get(Calendar.YEAR);
        nowMonth = calendar.get(Calendar.MONTH) + 1;
        nowDay = calendar.get(Calendar.DAY_OF_MONTH);
        nowHour = calendar.get(Calendar.HOUR_OF_DAY);
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
    public void processClick( View v) {
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
                                                                       int month= monthOfYear + 1;
                                                                       String date = year + "." + month + "." + dayOfMonth;
                                                                       tvPunchDate.setText(date);

                                                                       //todo 判断是否为当日 显示fab
                                                                       if (nowYear==year&&nowMonth==month&&nowDay==dayOfMonth){
                                                                           punchFab.setVisibility(View.VISIBLE);
                                                                       }else{
                                                                           punchFab.setVisibility(View.GONE);
                                                                           Snackbar.make(v,"当前日期不能打卡",Snackbar.LENGTH_SHORT).show();
                                                                       }
                                                                   }
                                                               }, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.setVersion(DatePickerDialog.Version.VERSION_2);
        dialog.show(getFragmentManager(), "aaaaaaaaaa");
    }

    private void punchPermission() {
        AndPermission.with(PunchActivity.this)
                .requestCode(1)
                .permission(Permission.PHONE,Permission.LOCATION,Permission.STORAGE)
                .rationale(rationaleListener)
                .callback(permissionListener)
                .start();
    }
    private PermissionListener permissionListener=new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
            switch (requestCode){
                case 1:
                    获取位置();
                    break;
            }
        }

        @Override
        public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
            ToastUtils.showToast(PunchActivity.this,"请到设置-权限管理中开启");
            if (AndPermission.hasAlwaysDeniedPermission(PunchActivity.this, deniedPermissions)){
                // 第一种：用默认的提示语。
                AndPermission.defaultSettingDialog(PunchActivity.this,1).show();
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
                if (aMapLocation!= null) {
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
                        String locale=province+city+district+street+streetNum;//定位地点
                        mlocationClient.stopLocation();
                        daka();
                    } else {
                        //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                       ToastUtils.showToast(PunchActivity.this,"获取错误");
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

    private RationaleListener rationaleListener =new RationaleListener() {
        @Override
        public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
            AndPermission.rationaleDialog(PunchActivity.this,rationale).show();
        }
    };
    //打卡操作
    private void daka() {

        if(SpUtils.getBoolean(ConstantUtils.IsPunch,PunchActivity.this)){
            //下班打卡
            ToastUtils.showToast(PunchActivity.this,"下班打卡成功");
            SpUtils.putBoolean(ConstantUtils.IsPunch,false,PunchActivity.this);
            //提交服务器
        }else{
            //上班打卡
            ToastUtils.showToast(PunchActivity.this,"上班打卡成功");
            SpUtils.putBoolean(ConstantUtils.IsPunch,true,PunchActivity.this);

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
