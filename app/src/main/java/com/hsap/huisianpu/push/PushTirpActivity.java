package com.hsap.huisianpu.push;

import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.adapter.MyAdapter;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.bean.PushBean;
import com.hsap.huisianpu.bean.PushTripBean;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.ToastUtils;
import com.hsap.huisianpu.view.MyGridView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushManager;
import com.zhy.android.percent.support.PercentLinearLayout;

import butterknife.BindView;

/**
 * 推送的出差
 */

public class PushTirpActivity extends BaseBackActivity {
    private static final String TAG = "PushTirpActivity";
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.tv_push_title)
    TextView tvPushTitle;
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
    @BindView(R.id.ps_summary)
    ViewStub psSummary;
    @BindView(R.id.ps_project)
    ViewStub psProject;
    @BindView(R.id.ps_bt_refuse)
    Button psBtRefuse;
    @BindView(R.id.ps_bt_confirm)
    Button psBtConfirm;
    @BindView(R.id.ll_ps)
    LinearLayout llPs;
    private StringBuilder begin=new StringBuilder();
    private StringBuilder end=new StringBuilder();
    @Override
    public int getLayoutId() {
        return R.layout.push_trip_activity;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        psBtConfirm.setOnClickListener(this);
        psBtRefuse.setOnClickListener(this);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void processClick(View v) {
            switch (v.getId()){
                case R.id.ps_bt_confirm:
                    confirm();//同意
                    break;
                case R.id.ps_bt_refuse:
                    refuse();//拒绝
                    break;
                    default:
            }
    }


    private void confirm() {

    }
    private void refuse() {

    }
    @Override
    protected void onResume() {
        super.onResume();
        XGPushClickedResult clickedResult = XGPushManager.onActivityStarted(this);
        if (clickedResult!=null){
            String content = clickedResult.getCustomContent();
            Log.e(TAG, content );
            if(content!=null){
                PushBean pushBean = new Gson().fromJson(content, PushBean.class);
                if(pushBean.isState()){
                    llPs.setVisibility(View.VISIBLE);
                    tvPushTitle.setText(pushBean.getName()+"的"+clickedResult.getTitle());
                }else {
                    llPs.setVisibility(View.GONE);
                    tvPushTitle.setText("您的"+clickedResult.getTitle());
                }
                switch (pushBean.getType()){
                    case 0:
                        psLeave.inflate();
                        initLeaveForNet(pushBean.getId());
                        break;
                    case 1:
                        psOut.inflate();
                        initOutFormNet(pushBean.getId());
                        break;
                    case 2:
                        psTrip.inflate();
                        initTripFormNet(pushBean.getId());
                        break;
                    case 3:
                        psOvertime.inflate();
                        break;
                    case 4:
                        psCar.inflate();
                        break;
                    case 5:
                        psProject.inflate();
                        break;
                    case 6:
                        psSummary.inflate();
                        break;
                    default:
                }
            }
        }

    }

    private void initOutFormNet(int id) {
        OkGo.<String>post(NetAddressUtils.selectOneIntergration).
                params("id",id).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        PushTripBean bean = new Gson().fromJson(response.body().toString(), PushTripBean.class);
                        if (bean.isSuccess()){
                            PercentLinearLayout pll_out_articles = findViewById(R.id.pll_out_articles);
                            TextView et_out_articles = findViewById(R.id.et_out_articles);
                            if (bean.getData().getWaIntegration().getReason().equals("请假")|| bean.getData().getWaIntegration().getReason().equals("其他")){
                                pll_out_articles.setVisibility(View.GONE);
                            }else {
                                pll_out_articles.setVisibility(View.VISIBLE);
                                et_out_articles.setText(bean.getData().getWaIntegration().getType2());
                            }
                            TextView tv_out_reason = findViewById(R.id.tv_out_reason);
                            tv_out_reason.setText(bean.getData().getWaIntegration().getType2());
                            TextView tv_out_time = findViewById(R.id.tv_out_time);
                            tv_out_reason.setText(bean.getData().getWaIntegration().getStartTime()+"");
                            TextView tv_return_time = findViewById(R.id.tv_return_time);
                            tv_return_time.setText(bean.getData().getWaIntegration().getEndTime()+"");
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        ToastUtils.showToast(PushTirpActivity.this,"当前无网络");
                    }
                });
    }

    private void initLeaveForNet(int id) {
        OkGo.<String>post(NetAddressUtils.selectOneIntergration).
                params("id",id).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e(TAG,response.body().toString());
                        PushTripBean bean = new Gson().fromJson(response.body().toString(), PushTripBean.class);
                        if (bean.isSuccess()){
                            if(bean.getData().getWaIntegration().getStartTime().getHour()==8){
                                begin.setLength(0);
                                begin.append("上午");
                            }else {
                                begin.setLength(0);
                                begin.append("下午");
                            }
                            if (bean.getData().getWaIntegration().getEndTime().getHour()==8){
                                end.setLength(0);
                                end.append("上午");
                            }else {
                                end.setLength(0);
                                end.append("下午");
                            }
                            TextView tv_qingjialeixing = findViewById(R.id.tv_qingjialeixing);
                            tv_qingjialeixing.setText(bean.getData().getWaIntegration().getType2());
                            TextView tv_beginTime = findViewById(R.id.tv_beginTime);
                            tv_beginTime.setText(bean.getData().getWaIntegration().getStartTime().getYear()+"-"+
                                    bean.getData().getWaIntegration().getStartTime().getMonthValue()+"-"+
                                    bean.getData().getWaIntegration().getStartTime().getDayOfMonth()+" "+begin);
                            TextView tv_endTime = findViewById(R.id.tv_endTime);
                            tv_endTime.setText(bean.getData().getWaIntegration().getEndTime().getYear()+"-"+
                                    bean.getData().getWaIntegration().getEndTime().getMonthValue()+"-"+
                                    bean.getData().getWaIntegration().getEndTime().getDayOfMonth()+" "+end);
                            TextView tv_shichang = findViewById(R.id.tv_shichang);
                            tv_shichang.setText(bean.getData().getWaIntegration().getTotalTime()+"");
                            TextView et_leave = findViewById(R.id.et_leave);
                            et_leave.setText(bean.getData().getWaIntegration().getReason()+"");
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        ToastUtils.showToast(PushTirpActivity.this,"当前无网络");
                    }
                });
    }

    @Override
    protected void onPause() {
        super.onPause();
        XGPushManager.onActivityStoped(this);
    }

    private void initTripFormNet(int pushBeanId) {
        final TextView et_trip_reason = findViewById(R.id.et_trip_reason);
        final TextView et_trip_city=findViewById(R.id.et_trip_city);
        final TextView tv_trip_begin =findViewById(R.id.tv_trip_begin);
        final TextView tv_trip_end =findViewById(R.id.tv_trip_end);
        final TextView tv_trip_day =findViewById(R.id.tv_trip_day);
        final MyGridView gv_trip_person =findViewById(R.id.gv_trip_person);
        final LinearLayout ll_approval_trip = findViewById(R.id.ll_approval_trip);
        OkGo.<String>post(NetAddressUtils.getOneBusinessTrip).
                params("id",pushBeanId).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e(TAG, response.body().toString() );
                        PushTripBean bean = new Gson().fromJson(response.body().toString(), PushTripBean.class);
                        et_trip_reason.setText(bean.getData().getWaIntegration().getReason()+"");
                        et_trip_city.setText(bean.getData().getWaIntegration().getType2());
                        if(bean.getData().getWaIntegration().getStartTime().getHour()==8){
                            begin.setLength(0);
                            begin.append("上午");
                        }else {
                            begin.setLength(0);
                            begin.append("下午");
                        }
                        if (bean.getData().getWaIntegration().getEndTime().getHour()==8){
                            end.setLength(0);
                            end.append("上午");
                        }else {
                            end.setLength(0);
                            end.append("下午");
                        }
                        tv_trip_begin.setText(bean.getData().getWaIntegration().getStartTime().getYear()+"-"+
                                       bean.getData().getWaIntegration().getStartTime().getMonthValue()+"-"+
                                       bean.getData().getWaIntegration().getStartTime().getDayOfMonth()+" "+begin);

                        tv_trip_end.setText(bean.getData().getWaIntegration().getEndTime().getYear()+"-"+
                                bean.getData().getWaIntegration().getEndTime().getMonthValue()+"-"+
                                bean.getData().getWaIntegration().getEndTime().getDayOfMonth()+" "+end);
                        tv_trip_day.setText(bean.getData().getWaIntegration().getTotalTime()+"");
                         if(bean.getData().getNameList().size()!=0&&bean.getData().getNameList()!=null){
                             ll_approval_trip.setVisibility(View.VISIBLE);
                             gv_trip_person.setAdapter(new MyAdapter(PushTirpActivity.this,
                                     bean.getData().getNameList()));
                         }else {
                             ll_approval_trip.setVisibility(View.GONE);
                         }

                    }
                });
    }
}
