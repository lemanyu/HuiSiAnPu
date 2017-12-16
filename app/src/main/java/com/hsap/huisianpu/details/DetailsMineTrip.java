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
import com.hsap.huisianpu.bean.CarBean;
import com.hsap.huisianpu.bean.PushTripBean;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.ToastUtils;
import com.hsap.huisianpu.view.MyGridView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhy.android.percent.support.PercentLinearLayout;

import butterknife.BindView;

/**
 * 我的内外勤
 */

public class DetailsMineTrip extends BaseBackActivity {
    private static final String TAG = "DetailsMineTrip";
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
    StringBuilder begin = new StringBuilder();
    StringBuilder end = new StringBuilder();
    @BindView(R.id.bt_details_wancheng)
    Button btDetailsWancheng;
    private int state;


    @Override
    public int getLayoutId() {

        return R.layout.details_mine_trip;
    }

    @Override
    public void initView() {
        LoadingDailog 获取数据中 = ToastUtils.showDailog(this, "获取数据中");
        获取数据中.show();
        int type = getIntent().getIntExtra("type", 0);
        int workid = getIntent().getIntExtra("workid", 0);
        state = getIntent().getIntExtra("state", 0);
        choiceBoundary(type, workid, 获取数据中);
    }

    private void choiceBoundary(int type, int workid, LoadingDailog 获取数据中) {
        switch (type) {
            case 0:
                detailsMineTrip.setText("我的请假");
                psLeave.inflate();
                dataFormLeave(workid, 获取数据中);
                break;
            case 1:
                detailsMineTrip.setText("我的外出");
                psOut.inflate();
                dataFormOut(workid, 获取数据中);
                break;
            case 2:
                detailsMineTrip.setText("我的出差");
                psTrip.inflate();
                dataFromTrip(workid, 获取数据中);
                break;
            case 3:
                detailsMineTrip.setText("我的加班");
                psOvertime.inflate();
                dataFormOverTime(workid, 获取数据中);
                break;
            case 4:
                detailsMineTrip.setText("我的用车");
                psCar.inflate();
                dataFormCar(workid, 获取数据中);
                break;
            default:
        }
    }

    private void dataFormCar(int workid, final LoadingDailog 获取数据中) {
        final TextView tv_car_begin = findViewById(R.id.tv_car_begin);
        final TextView tv_car_end = findViewById(R.id.tv_car_end);
        final TextView tv_car_choice = findViewById(R.id.tv_car_choice);
        final TextView et_car_phone = findViewById(R.id.et_car_phone);
        final TextView et_car_matters = findViewById(R.id.et_car_matters);
        final TextView et_car_location = findViewById(R.id.et_car_location);
        final LinearLayout ll_approval_car = findViewById(R.id.ll_approval_car);
        final MyGridView gv_car_person = findViewById(R.id.gv_car_person);
        OkGo.<String>post(NetAddressUtils.selectOneIntergration).
                params("id", workid).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        CarBean bean = new Gson().fromJson(response.body(), CarBean.class);
                        if (bean.isSuccess()) {
                            获取数据中.dismiss();
                            if(state==1){
                                choiceState(bean.getData().getWaIntegration().getState());
                            }
                            tv_car_begin.setText(bean.getData().getWaIntegration().getStartTime().getYear() + "-" +
                                    bean.getData().getWaIntegration().getStartTime().getMonthValue() + "-"
                                    + bean.getData().getWaIntegration().getStartTime().getDayOfMonth() + " " +
                                    bean.getData().getWaIntegration().getStartTime().getHour() + ":" +
                                    bean.getData().getWaIntegration().getStartTime().getMinute());
                            tv_car_end.setText(bean.getData().getWaIntegration().getEndTime().getYear() + "-" +
                                    bean.getData().getWaIntegration().getEndTime().getMonthValue() + "-"
                                    + bean.getData().getWaIntegration().getEndTime().getDayOfMonth() + " " +
                                    bean.getData().getWaIntegration().getEndTime().getHour() + ":" +
                                    bean.getData().getWaIntegration().getEndTime().getMinute());
                            tv_car_choice.setText(bean.getData().getObject().getLeixing());
                            et_car_phone.setText(bean.getData().getWaIntegration().getType2());
                            et_car_matters.setText(bean.getData().getObject().getShixiang());
                            et_car_location.setText(bean.getData().getObject().getDidian());
                            if (bean.getData().getNameList().size() != 0 && bean.getData().getNameList() != null) {
                                ll_approval_car.setVisibility(View.VISIBLE);
                                gv_car_person.setAdapter(new MyAdapter(DetailsMineTrip.this, bean.getData().getNameList()));
                            } else {
                                ll_approval_car.setVisibility(View.GONE);
                            }
                        } else {
                            获取数据中.dismiss();
                            ToastUtils.showToast(DetailsMineTrip.this, "当前无网络");
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        获取数据中.dismiss();
                        ToastUtils.showToast(DetailsMineTrip.this, "当前无网络");
                    }
                });
    }

    private void choiceState(int state) {
        switch (state) {
            case 0:
                btDetailsWancheng.setVisibility(View.GONE);
                btDetailsFinish.setVisibility(View.GONE);
                detailsBtCancel.setVisibility(View.VISIBLE);
                break;
            case 1:
                btDetailsWancheng.setVisibility(View.GONE);
                btDetailsFinish.setVisibility(View.VISIBLE);
                detailsBtCancel.setVisibility(View.VISIBLE);
                break;
            case 2:
                btDetailsWancheng.setVisibility(View.VISIBLE);
                btDetailsFinish.setVisibility(View.GONE);
                detailsBtCancel.setVisibility(View.GONE);
                break;
            case 3:
                btDetailsWancheng.setVisibility(View.VISIBLE);
                btDetailsFinish.setVisibility(View.GONE);
                detailsBtCancel.setVisibility(View.GONE);
                break;
            case 4:
                btDetailsWancheng.setVisibility(View.GONE);
                btDetailsFinish.setVisibility(View.GONE);
                detailsBtCancel.setVisibility(View.VISIBLE);
                break;
            default:
        }
    }

    private void dataFormOverTime(int workid, final LoadingDailog 获取数据中) {
        final TextView tv_overtime_begin = findViewById(R.id.tv_overtime_begin);
        final TextView tv_overtime_end = findViewById(R.id.tv_overtime_end);
        final TextView et_overtime_cause = findViewById(R.id.et_overtime_cause);
        final LinearLayout ll_overtime = findViewById(R.id.ll_overtime);
        final MyGridView gv_overtime_person = findViewById(R.id.gv_overtime_person);
        OkGo.<String>post(NetAddressUtils.selectOneIntergration).
                params("id", workid).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        PushTripBean bean = new Gson().fromJson(response.body().toString(), PushTripBean.class);
                        if (bean.isSuccess()) {
                            获取数据中.dismiss();
                            if(state==1){
                                choiceState(bean.getData().getWaIntegration().getState());
                            }
                            tv_overtime_begin.setText(
                                    bean.getData().getWaIntegration().getStartTime().getYear() + "-" +
                                            bean.getData().getWaIntegration().getStartTime().getMonthValue() + "-"
                                            + bean.getData().getWaIntegration().getStartTime().getDayOfMonth() + " " +
                                            bean.getData().getWaIntegration().getStartTime().getHour() + ":" +
                                            bean.getData().getWaIntegration().getStartTime().getMinute());
                            tv_overtime_end.setText(bean.getData().getWaIntegration().getEndTime().getYear() + "-" +
                                    bean.getData().getWaIntegration().getEndTime().getMonthValue() + "-"
                                    + bean.getData().getWaIntegration().getEndTime().getDayOfMonth() + " " +
                                    bean.getData().getWaIntegration().getEndTime().getHour() + ":" +
                                    bean.getData().getWaIntegration().getEndTime().getMinute());
                            et_overtime_cause.setText(bean.getData().getWaIntegration().getReason());
                            if (bean.getData().getNameList().size() != 0 && bean.getData().getNameList() != null) {
                                ll_overtime.setVisibility(View.VISIBLE);
                                gv_overtime_person.setAdapter(new MyAdapter(DetailsMineTrip.this, bean.getData().getNameList()));
                            } else {
                                ll_overtime.setVisibility(View.GONE);
                            }
                        } else {
                            获取数据中.dismiss();
                            ToastUtils.showToast(DetailsMineTrip.this, "当前无网络");
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        获取数据中.dismiss();
                        ToastUtils.showToast(DetailsMineTrip.this, "当前无网络");
                    }
                });
    }

    private void dataFormOut(int workid, final LoadingDailog 获取数据中) {
        OkGo.<String>post(NetAddressUtils.selectOneIntergration).
                params("id", workid).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        PushTripBean bean = new Gson().fromJson(response.body().toString(), PushTripBean.class);
                        if (bean.isSuccess()) {
                            获取数据中.dismiss();
                            if(state==1){
                                choiceState(bean.getData().getWaIntegration().getState());
                            }
                            TextView tv_out_reason = findViewById(R.id.tv_out_reason);
                            tv_out_reason.setText(bean.getData().getWaIntegration().getReason());
                            TextView tv_out_time = findViewById(R.id.tv_out_time);
                            tv_out_time.setText(bean.getData().getWaIntegration().getStartTime().getYear() + "-" +
                                    bean.getData().getWaIntegration().getStartTime().getMonthValue() + "-"
                                    + bean.getData().getWaIntegration().getStartTime().getDayOfMonth() + " " +
                                    bean.getData().getWaIntegration().getStartTime().getHour() + ":" +
                                    bean.getData().getWaIntegration().getStartTime().getMinute());
                            TextView tv_return_time = findViewById(R.id.tv_return_time);
                            tv_return_time.setText(bean.getData().getWaIntegration().getEndTime().getYear() + "-" +
                                    bean.getData().getWaIntegration().getEndTime().getMonthValue() + "-"
                                    + bean.getData().getWaIntegration().getEndTime().getDayOfMonth() + " " +
                                    bean.getData().getWaIntegration().getEndTime().getHour() + ":" +
                                    bean.getData().getWaIntegration().getEndTime().getMinute());
                            PercentLinearLayout pll_out_articles = findViewById(R.id.pll_out_articles);
                            TextView et_out_articles = findViewById(R.id.et_out_articles);
                            if (bean.getData().getWaIntegration().getReason().equals("请假") || bean.getData().getWaIntegration().getReason().equals("其他")) {
                                pll_out_articles.setVisibility(View.GONE);
                            } else {
                                pll_out_articles.setVisibility(View.VISIBLE);
                                et_out_articles.setText(bean.getData().getWaIntegration().getType2());
                            }
                        } else {
                            获取数据中.dismiss();
                            ToastUtils.showToast(DetailsMineTrip.this, "当前无网络");
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        获取数据中.dismiss();
                        ToastUtils.showToast(DetailsMineTrip.this, "当前无网络");
                    }
                });
    }

    private void dataFormLeave(int workid, final LoadingDailog 获取数据中) {
        OkGo.<String>post(NetAddressUtils.selectOneIntergration).
                params("id", workid).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        PushTripBean bean = new Gson().fromJson(response.body().toString(), PushTripBean.class);
                        if (bean.isSuccess()) {
                            获取数据中.dismiss();
                            if(state==1){
                                choiceState(bean.getData().getWaIntegration().getState());
                            }
                            if (bean.getData().getWaIntegration().getStartTime().getHour() == 8) {
                                begin.setLength(0);
                                begin.append("上午");
                            } else {
                                begin.setLength(0);
                                begin.append("下午");
                            }
                            if (bean.getData().getWaIntegration().getEndTime().getHour() == 8) {
                                end.setLength(0);
                                end.append("上午");
                            } else {
                                end.setLength(0);
                                end.append("下午");
                            }
                            TextView tv_qingjialeixing = findViewById(R.id.tv_qingjialeixing);
                            tv_qingjialeixing.setText(bean.getData().getWaIntegration().getType2());
                            TextView tv_beginTime = findViewById(R.id.tv_beginTime);
                            tv_beginTime.setText(bean.getData().getWaIntegration().getStartTime().getYear() + "-" +
                                    bean.getData().getWaIntegration().getStartTime().getMonthValue() + "-" +
                                    bean.getData().getWaIntegration().getStartTime().getDayOfMonth() + " " + begin);
                            TextView tv_endTime = findViewById(R.id.tv_endTime);
                            tv_endTime.setText(bean.getData().getWaIntegration().getEndTime().getYear() + "-" +
                                    bean.getData().getWaIntegration().getEndTime().getMonthValue() + "-" +
                                    bean.getData().getWaIntegration().getEndTime().getDayOfMonth() + " " + end);
                            TextView tv_shichang = findViewById(R.id.tv_shichang);
                            tv_shichang.setText(bean.getData().getWaIntegration().getTotalTime() + "");
                            TextView et_leave = findViewById(R.id.et_leave);
                            et_leave.setText(bean.getData().getWaIntegration().getReason() + "");
                        } else {
                            获取数据中.dismiss();
                            ToastUtils.showToast(DetailsMineTrip.this, "当前无网络");
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        获取数据中.dismiss();
                        ToastUtils.showToast(DetailsMineTrip.this, "当前无网络");
                    }
                });
    }

    private void dataFromTrip(int workid, final LoadingDailog 获取数据中) {
        OkGo.<String>post(NetAddressUtils.selectOneIntergration).
                params("id", workid).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.e(TAG, "onSuccess: ");
                Log.e(TAG, response.body().toString());
                PushTripBean bean = new Gson().fromJson(response.body().toString(), PushTripBean.class);
                if (bean.isSuccess()) {
                    获取数据中.dismiss();
                    if(state==1){
                        choiceState(bean.getData().getWaIntegration().getState());
                    }
                    if (bean.getData().getWaIntegration().getStartTime().getHour() == 8) {
                        begin.setLength(0);
                        begin.append("上午");
                    } else {
                        begin.setLength(0);
                        begin.append("下午");
                    }
                    if (bean.getData().getWaIntegration().getEndTime().getHour() == 8) {
                        end.setLength(0);
                        end.append("上午");
                    } else {
                        end.setLength(0);
                        end.append("下午");
                    }
                    TextView et_trip_reason = findViewById(R.id.et_trip_reason);
                    et_trip_reason.setText(bean.getData().getWaIntegration().getReason());
                    TextView et_trip_city = findViewById(R.id.et_trip_city);
                    et_trip_city.setText(bean.getData().getWaIntegration().getType2());
                    TextView tv_trip_begin = findViewById(R.id.tv_trip_begin);
                    tv_trip_begin.setText(bean.getData().getWaIntegration().getStartTime().getYear() + "-" +
                            bean.getData().getWaIntegration().getStartTime().getMonthValue() + "-" +
                            bean.getData().getWaIntegration().getStartTime().getDayOfMonth() + "-" + " " + begin);
                    TextView tv_trip_end = findViewById(R.id.tv_trip_end);
                    tv_trip_end.setText(bean.getData().getWaIntegration().getEndTime().getYear() + "-" +
                            bean.getData().getWaIntegration().getEndTime().getMonthValue() + "-" +
                            bean.getData().getWaIntegration().getEndTime().getDayOfMonth() + "-" + " " + end);
                    TextView tv_trip_day = findViewById(R.id.tv_trip_day);
                    tv_trip_day.setText(bean.getData().getWaIntegration().getTotalTime() + "");
                    LinearLayout ll_approval_trip = findViewById(R.id.ll_approval_trip);
                    MyGridView gv_trip_person = findViewById(R.id.gv_trip_person);
                    if (bean.getData().getNameList().size() != 0 && bean.getData().getNameList() != null) {
                        ll_approval_trip.setVisibility(View.VISIBLE);
                        gv_trip_person.setAdapter(new MyAdapter(DetailsMineTrip.this, bean.getData().getNameList()));
                    } else {
                        ll_approval_trip.setVisibility(View.GONE);
                    }
                } else {
                    获取数据中.dismiss();
                    ToastUtils.showToast(DetailsMineTrip.this, "当前没有数据");
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                获取数据中.dismiss();
                ToastUtils.showToast(DetailsMineTrip.this, "获取数据失败");
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
        switch (v.getId()) {
            case R.id.details_bt_cancel:
                cancel();//撤销本条
                break;
            case R.id.bt_details_finish:
                detailsFinish();//完成
                break;
            case R.id.bt_details_wancheng:
                secondary();//再次申请
                break;
                default:

        }
    }

    private void secondary() {

    }

    private void detailsFinish() {

    }

    private void cancel() {

    }


}