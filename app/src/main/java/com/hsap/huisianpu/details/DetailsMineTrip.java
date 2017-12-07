package com.hsap.huisianpu.details;

import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.google.gson.Gson;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.adapter.MyAdapter;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.bean.MineLeaveBean;
import com.hsap.huisianpu.bean.MineTripBean;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.ToastUtils;
import com.hsap.huisianpu.view.MyGridView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import butterknife.BindView;

/**
 * 我的外勤
 */

public class DetailsMineTrip extends BaseBackActivity {
    private static final String TAG ="DetailsMineTrip" ;
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.details_mine_trip)
    TextView detailsMineTrip;
    @BindView(R.id.ps_leave)
    ViewStub psLeave;
    @BindView(R.id.ps_out)
    ViewStub psOut;
    @BindView(R.id.ps_trip)
    ViewStub psTrip;
    @BindView(R.id.ps_overtime)
    ViewStub psOvertime;
    @BindView(R.id.ps_car)
    ViewStub psCar;
    @BindView(R.id.details_bt_cancel)
    Button detailsBtCancel;
    @BindView(R.id.bt_details_finish)
    Button btDetailsFinish;
    StringBuilder begin=new StringBuilder();
    StringBuilder end=new StringBuilder();


    @Override
    public int getLayoutId() {
        return R.layout.details_mine_trip;
    }

    @Override
    public void initView() {
        int type = getIntent().getIntExtra("type", 0);
        int workid = getIntent().getIntExtra("workid", 0);
        LoadingDailog 获取数据中 = ToastUtils.showDailog(this, "获取数据中");
        获取数据中.show();
        choiceBoundary(type,workid,获取数据中);
    }

    private void choiceBoundary(int type, int workid, LoadingDailog 获取数据中) {

        switch (type) {
            case 0:
                detailsMineTrip.setText("我的请假");
                psLeave.inflate();
                dataFormLeave(workid,获取数据中);
                break;
            case 1:
                detailsMineTrip.setText("我的外出");
                psOut.inflate();
                break;
            case 2:
                detailsMineTrip.setText("我的出差");
                psTrip.inflate();
                dataFromTrip(workid,获取数据中);
                break;
            case 3:
                detailsMineTrip.setText("我的加班");
                psOvertime.inflate();
                break;
            case 4:
                detailsMineTrip.setText("我的用车");
                psCar.inflate();
                break;
                default:
        }
    }

    private void dataFormLeave(int workid, final LoadingDailog 获取数据中) {
         OkGo.<String>post(NetAddressUtils.getOneLeave).
                 params("id",workid).
                 execute(new StringCallback() {
                     @Override
                     public void onSuccess(Response<String> response) {
                         Log.e(TAG, "onSuccess: " );
                         Log.e(TAG, response.body().toString() );
                         MineLeaveBean bean = new Gson().fromJson(response.body().toString(), MineLeaveBean.class);
                         if (bean.isSuccess()){
                             获取数据中.dismiss();
                             if (bean.getData().getState()==1){
                                 btDetailsFinish.setVisibility(View.VISIBLE);
                             }else {
                                 btDetailsFinish.setVisibility(View.GONE);
                             }
                             if(bean.getData().getState()==4){
                                 detailsBtCancel.setVisibility(View.VISIBLE);
                             }else {
                                 detailsBtCancel.setVisibility(View.GONE);
                             }
                             if(bean.getData().getStartTime().getHour()==8){
                                 begin.setLength(0);
                                 begin.append("上午");
                             }else {
                                 begin.setLength(0);
                                 begin.append("下午");
                             }
                             if(bean.getData().getEndTime().getHour()==8){
                                 end.setLength(0);
                                 end.append("上午");
                             }else {
                                 end.setLength(0);
                                 end.append("下午");
             TextView tv_qingjialeixing = findViewById(R.id.tv_qingjialeixing);
                                 tv_qingjialeixing.setText(bean.getData().getLeaveType());
                                 TextView tv_beginTime = findViewById(R.id.tv_beginTime);
                                 tv_beginTime.setText(bean.getData().getStartTime().getYear()+"-"+
                                         bean.getData().getStartTime().getMonthValue()+"-"+
                                         bean.getData().getStartTime().getDayOfMonth()+"-"+" "+begin);
                                 TextView tv_endTime = findViewById(R.id.tv_endTime);
                                 tv_endTime.setText(bean.getData().getEndTime().getYear()+"-"+
                                         bean.getData().getEndTime().getMonthValue()+"-"+
                                         bean.getData().getEndTime().getDayOfMonth()+"-"+" "+end);
                                 TextView tvShiChang = findViewById(R.id.tv_shichang);
                                 tvShiChang.setText(bean.getData().getTotalTime()+"");
                                 TextView et_leave = findViewById(R.id.et_leave);
                                 et_leave.setText(bean.getData().getReason());
                             }
                         }else {
                             获取数据中.dismiss();
                             ToastUtils.showToast(DetailsMineTrip.this,"当前没有数据");
                         }
                     }

                 });
    }

    private void dataFromTrip(int workid, final LoadingDailog 获取数据中) {
        OkGo.<String>post(NetAddressUtils.getOneBusinessTrip).
                 params("id",workid).execute(new StringCallback() {
             @Override
             public void onSuccess(Response<String> response) {
                 Log.e(TAG, "onSuccess: " );
                 Log.e(TAG, response.body().toString() );
                 MineTripBean bean = new Gson().fromJson(response.body().toString(), MineTripBean.class);
                 if (bean.isSuccess()){
                     获取数据中.dismiss();
                     if (bean.getData().getWaTravelBusiness().getState()==1){
                         btDetailsFinish.setVisibility(View.VISIBLE);
                     }else {
                         btDetailsFinish.setVisibility(View.GONE);
                     }
                     if(bean.getData().getWaTravelBusiness().getState()==4){
                         detailsBtCancel.setVisibility(View.VISIBLE);
                     }else {
                         detailsBtCancel.setVisibility(View.GONE);
                     }
                     if(bean.getData().getWaTravelBusiness().getDepartureTime().getHour()==8){
                         begin.setLength(0);
                         begin.append("上午");
                     }else {
                         begin.setLength(0);
                         begin.append("下午");
                     }
                     if(bean.getData().getWaTravelBusiness().getReturnTime().getHour()==8){
                         end.setLength(0);
                         end.append("上午");
                     }else {
                         end.setLength(0);
                         end.append("下午");
                     }
                     TextView et_trip_reason = findViewById(R.id.et_trip_reason);
                     et_trip_reason.setText(bean.getData().getWaTravelBusiness().getReason());
                     TextView et_trip_city=findViewById(R.id.et_trip_city);
                     et_trip_city.setText(bean.getData().getWaTravelBusiness().getPlace());
                     TextView tv_trip_begin=findViewById(R.id.tv_trip_begin);
                     tv_trip_begin.setText(bean.getData().getWaTravelBusiness().getDepartureTime().getYear()+"-"+
                             bean.getData().getWaTravelBusiness().getDepartureTime().getMonthValue()+"-"+
                             bean.getData().getWaTravelBusiness().getDepartureTime().getDayOfMonth()+"-"+" "+begin);
                     TextView tv_trip_end = findViewById(R.id.tv_trip_end);
                     tv_trip_end.setText(bean.getData().getWaTravelBusiness().getReturnTime().getYear()+"-"+
                             bean.getData().getWaTravelBusiness().getReturnTime().getMonthValue()+"-"+
                             bean.getData().getWaTravelBusiness().getReturnTime().getDayOfMonth()+"-"+" "+end);
                     TextView tv_trip_day = findViewById(R.id.tv_trip_day);
                     tv_trip_day.setText(bean.getData().getWaTravelBusiness().getTotal()+"");
                     LinearLayout ll_approval_trip = findViewById(R.id.ll_approval_trip);
                     MyGridView gv_trip_person = findViewById(R.id.gv_trip_person);
                     if(bean.getData().getAccompanying().size()!=0&&bean.getData().getAccompanying()!=null){
                         ll_approval_trip.setVisibility(View.VISIBLE);
                         gv_trip_person.setAdapter(new MyAdapter(DetailsMineTrip.this,bean.getData().getAccompanying()));
                     }else {
                         ll_approval_trip.setVisibility(View.GONE);
                     }
                 }else{
                     获取数据中.dismiss();
                     ToastUtils.showToast(DetailsMineTrip.this,"当前没有数据");
                 }
             }

             @Override
             public void onError(Response<String> response) {
                 super.onError(response);
                 获取数据中.dismiss();
                 ToastUtils.showToast(DetailsMineTrip.this,"获取数据失败");
             }
         });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        detailsBtCancel.setOnClickListener(this);
        btDetailsFinish.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {
           switch (v.getId()){
               case R.id.details_bt_cancel:
                   cancel();//撤销本条
                   break;
               case R.id.bt_details_finish:
                    detailsFinish();//完成
                   break;
           }
    }

    private void detailsFinish() {

    }

    private void cancel() {

    }

}
