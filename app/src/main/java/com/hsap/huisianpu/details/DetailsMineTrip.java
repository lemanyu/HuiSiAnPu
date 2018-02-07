package com.hsap.huisianpu.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.activity.WorkSummaryActivity;
import com.hsap.huisianpu.adapter.MyAdapter;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.bean.CarBean;
import com.hsap.huisianpu.bean.CarcarBean;
import com.hsap.huisianpu.bean.EventDate;
import com.hsap.huisianpu.bean.FalseBean;
import com.hsap.huisianpu.bean.PurchaseBean;
import com.hsap.huisianpu.bean.PushTripBean;
import com.hsap.huisianpu.bean.SummayBean;
import com.hsap.huisianpu.push.PushTirpActivity;
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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    @BindView(R.id.ps_purchase)
    ViewStub psPurchase;
    @BindView(R.id.details_bt_down)
    Button detailsBtDown;
    @BindView(R.id.details_bt_cancel)
    Button detailsBtCancel;
    @BindView(R.id.tv_details_zhuangtai)
    Button tvDetailsZhuangtai;
    @BindView(R.id.bt_details_wancheng)
    Button btDetailsWancheng;
    @BindView(R.id.ps_summary)
    ViewStub psSummary;
    private int state;
    StringBuilder begin = new StringBuilder();
    StringBuilder end = new StringBuilder();
    private int type;
    private int workid;
    private int year;
    private int month;
    private PurchaseBean bean;

    @Override
    public int getLayoutId() {
        return R.layout.details_mine_trip;
    }

    @Override
    public void initView() {
        LoadingDailog 获取数据中 = ToastUtils.showDailog(this, "获取数据中");
        获取数据中.show();
        type = getIntent().getIntExtra("type", 0);
        workid = getIntent().getIntExtra("workid", 0);
        state = getIntent().getIntExtra("state", 0);
        year = getIntent().getIntExtra("year", 0);
        Log.e(TAG, "initView: " + year);
        month = getIntent().getIntExtra("month", 0);
        boolean flag = getIntent().getBooleanExtra("flag", true);
        choiceBoundary(type, workid, 获取数据中);
        detailsBtDown.setHeight(detailsBtCancel.getHeight());
        detailsBtDown.setMinimumHeight(detailsBtCancel.getMinimumHeight());
        String string = SpUtils.getString("HSAP" + workid + type, this);
        if (TextUtils.isEmpty(string)) {
            detailsBtDown.setText("下载word");
        } else {
            detailsBtDown.setText("打开word");
        }
        if (flag) {
            choiceState(state);
        } else {
            tvDetailsZhuangtai.setVisibility(View.GONE);
            btDetailsWancheng.setVisibility(View.GONE);
            detailsBtDown.setVisibility(View.GONE);
            detailsBtCancel.setVisibility(View.GONE);
        }

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
            case 6:
                detailsMineTrip.setText("我的总结");
                psSummary.inflate();
                dataFormSummary(workid, 获取数据中);
                break;
            case 12:
                detailsMineTrip.setText("我的采购");
                psPurchase.inflate();
                dataFormPurchase(workid, 获取数据中);
                break;
            default:
        }
    }

    private void dataFormSummary(int workid, final LoadingDailog 获取数据中) {
        final TextView tv_summary_time = findViewById(R.id.tv_summary_time);
        final TextView et_summary_location = findViewById(R.id.et_summary_location);
        final TextView et_summary_products = findViewById(R.id.et_summary_products);
        final TextView et_summary_mingcheng = findViewById(R.id.et_summary_mingcheng);
        final TextView et_summary_customer = findViewById(R.id.et_summary_customer);
        final TextView et_summary_scene = findViewById(R.id.et_summary_scene);
        final TextView et_summary_preliminary = findViewById(R.id.et_summary_preliminary);
        final TextView et_summary_practice = findViewById(R.id.et_summary_practice);
        final TextView et_summary_technology = findViewById(R.id.et_summary_technology);
        final LinearLayout ll_approval_trip = findViewById(R.id.ll_approval_trip);
        final MyGridView gv_trip_person = findViewById(R.id.gv_trip_person);
        OkGo.<String>post(NetAddressUtils.queryOneTTBSummar).
                params("tripId",workid).
                params("workerId",SpUtils.getInt(ConstantUtils.UserId,this)).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e(TAG, "onSuccess: "+response.body().toString());
                        获取数据中.dismiss();
                        SummayBean bean = new Gson().fromJson(response.body().toString(), SummayBean.class);

                        if (bean.getWaIntegration().getStartTime().getHour() == 8) {
                            begin.setLength(0);
                            begin.append("上午");
                        } else {
                            begin.setLength(0);
                            begin.append("下午");
                        }
                        tv_summary_time.setText(bean.getWaIntegration().getStartTime().getYear()+"-"+
                                bean.getWaIntegration().getStartTime().getMonthValue()+"-"+
                                bean.getWaIntegration().getStartTime().getDayOfMonth()+" "+begin);
                        et_summary_location.setText(bean.getWaIntegration().getType2());
                        et_summary_products.setText(bean.getSummary().getType());
                        et_summary_mingcheng.setText(bean.getSummary().getCname());
                        et_summary_customer.setText(bean.getSummary().getCmanager());
                        et_summary_scene.setText(bean.getSummary().getBody1());
                        et_summary_preliminary.setText(bean.getSummary().getBody2());
                        et_summary_practice.setText(bean.getSummary().getBody3());
                        et_summary_technology.setText(bean.getSummary().getBody4());
//                        detailsBtDown.setHint(bean.getData().getObject().getFileUrl());
                        if (bean.getNameList().size()!=0&&bean.getNameList()!=null){
                            ll_approval_trip.setVisibility(View.VISIBLE);
                            ArrayList<String> list = new ArrayList<>();
                            for (int i = 0; i <bean.getNameList().size() ; i++) {
                                list.add(bean.getNameList().get(i).getName());
                            }
                            gv_trip_person.setAdapter(new MyAdapter(DetailsMineTrip.this,list));
                        }else {
                            ll_approval_trip.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        获取数据中.dismiss();
                        ToastUtils.showToast(DetailsMineTrip.this,"当前无网络");
                    }
                });
    }

    private void dataFormPurchase(int workid, final LoadingDailog 获取数据中) {
        final TextView et_purchase_reason = findViewById(R.id.et_purchase_reason);
        final TextView tv_purchase_type = findViewById(R.id.tv_purchase_type);
        final TextView tv_purchase_time = findViewById(R.id.tv_purchase_time);
        final RecyclerView rlv_purchase = findViewById(R.id.rlv_purchase);
        OkGo.<String>post(NetAddressUtils.selectOneIntergration).
                params("id", workid).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        PurchaseBean bean = new Gson().fromJson(response.body().toString(), PurchaseBean.class);
                        ArrayList<PurchaseBean.DataBean.ObjectBean.ListBean> list = new ArrayList<>();
                        if (bean.isSuccess()) {
                            获取数据中.dismiss();
                            Log.e(TAG, "onSuccess: " + response.body().toString());
                            list.addAll(bean.getData().getObject().getList());
                            et_purchase_reason.setText(bean.getData().getWaIntegration().getReason() + "");
                            tv_purchase_type.setText(bean.getData().getWaIntegration().getType2());
                            tv_purchase_time.setText(bean.getData().getWaIntegration().getEndTime().getYear() + "-"
                                    + bean.getData().getWaIntegration().getEndTime().getMonthValue() + "-" +
                                    bean.getData().getWaIntegration().getEndTime().getDayOfMonth());
                            rlv_purchase.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            rlv_purchase.setAdapter(new PurchaseMyAdapter(R.layout.item_push_purchase, list, bean.getData().getWaIntegration().getType2()));
                            detailsBtDown.setHint(bean.getData().getObject().getFileUrl());
                        } else {
                            获取数据中.dismiss();
                            ToastUtils.showToast(getApplicationContext(), "当前无网络");
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        获取数据中.dismiss();
                        ToastUtils.showToast(getApplicationContext(), "当前无网络");
                    }
                });
    }



    class PurchaseMyAdapter extends BaseQuickAdapter<PurchaseBean.DataBean.ObjectBean.ListBean, BaseViewHolder> {
        private String type2;

        public PurchaseMyAdapter(int layoutResId, @Nullable List<PurchaseBean.DataBean.ObjectBean.ListBean> data, String type2) {
            super(layoutResId, data);
            this.type2 = type2;
        }

        @Override
        protected void convert(BaseViewHolder helper, PurchaseBean.DataBean.ObjectBean.ListBean item) {
            TextView et_shuliang = helper.getView(R.id.et_shuliang);
            TextView et_danjia = helper.getView(R.id.et_danjia);
            LinearLayout ll_purchase_caigou = helper.getView(R.id.ll_purchase_caigou);
            if (type2.equals("生产原料")) {
                ll_purchase_caigou.setVisibility(View.VISIBLE);
                helper.getView(R.id.ll_piaoju).setVisibility(View.GONE);
                helper.getView(R.id.ll_beizhu).setVisibility(View.VISIBLE);
            }else {
                helper.getView(R.id.ll_beizhu).setVisibility(View.GONE);
            }
            helper.setText(R.id.et_mingcheng, item.getName())
                    .setText(R.id.et_xinghao, item.getGuige())
                    .setText(R.id.et_shuliang, item.getShuliang())
                    .setText(R.id.et_danjia, item.getDanjia())
                    .setText(R.id.tv_jine, Double.valueOf(item.getShuliang()) * Double.valueOf(item.getShuliang()) + "")
                    .setText(R.id.et_piaoju, item.getPiaoju())
                    .setText(R.id.et_yongtu, item.getYongtu())
                    .setText(R.id.et_fengzhuang, item.getFengzhuang())
                    .setText(R.id.et_fenlei, item.getFenlei())
                    .setText(R.id.et_pinpai, item.getPinpai())
                    .setText(R.id.et_lianxiren, item.getLianxiren())
                    .setText(R.id.et_dianhua, item.getDianhua())
                    .setText(R.id.et_beizhu,item.getBeizhu());
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
                        CarcarBean bean = new Gson().fromJson(response.body(), CarcarBean.class);
                        if (bean.isSuccess()) {
                            获取数据中.dismiss();
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
                            tv_car_choice.setText(bean.getData().getObject().getList().get(0).getLeixing());
                            et_car_phone.setText(bean.getData().getWaIntegration().getType2());
                            et_car_matters.setText(bean.getData().getObject().getList().get(0).getShixiang());
                            et_car_location.setText(bean.getData().getObject().getList().get(0).getDidian());
                            detailsBtDown.setHint(bean.getData().getObject().getFileUrl());
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
                tvDetailsZhuangtai.setText("进行中");
                tvDetailsZhuangtai.setVisibility(View.VISIBLE);
                btDetailsWancheng.setVisibility(View.GONE);
                detailsBtCancel.setVisibility(View.VISIBLE);
                detailsBtDown.setVisibility(View.GONE);
                break;
            case 1:
                tvDetailsZhuangtai.setText("已同意");
                tvDetailsZhuangtai.setVisibility(View.VISIBLE);
                btDetailsWancheng.setVisibility(View.VISIBLE);
                detailsBtCancel.setVisibility(View.VISIBLE);
                detailsBtDown.setVisibility(View.VISIBLE);
                break;
            case 2:
                btDetailsWancheng.setVisibility(View.GONE);
                detailsBtCancel.setVisibility(View.GONE);
                detailsBtDown.setVisibility(View.GONE);
                break;
            case 4:
                tvDetailsZhuangtai.setText("已撤销");
                tvDetailsZhuangtai.setVisibility(View.VISIBLE);
                btDetailsWancheng.setVisibility(View.GONE);
                detailsBtCancel.setVisibility(View.GONE);
                detailsBtDown.setVisibility(View.GONE);
                break;
            case 3:
                tvDetailsZhuangtai.setText("已完成");
                tvDetailsZhuangtai.setVisibility(View.VISIBLE);
                btDetailsWancheng.setVisibility(View.GONE);
                detailsBtCancel.setVisibility(View.GONE);
                detailsBtDown.setVisibility(View.VISIBLE);
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
                        PurchaseBean bean = new Gson().fromJson(response.body().toString(), PurchaseBean.class);
                        if (bean.isSuccess()) {
                            获取数据中.dismiss();
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
                            detailsBtDown.setHint(bean.getData().getObject().getFileUrl());
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
                        PurchaseBean bean = new Gson().fromJson(response.body().toString(), PurchaseBean.class);
                        if (bean.isSuccess()) {
                            获取数据中.dismiss();
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
                            detailsBtDown.setHint(bean.getData().getObject().getFileUrl());
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
                        PurchaseBean bean = new Gson().fromJson(response.body().toString(), PurchaseBean.class);
                        if (bean.isSuccess()) {
                            获取数据中.dismiss();
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
                            detailsBtDown.setHint(bean.getData().getObject().getFileUrl());
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
                bean = new Gson().fromJson(response.body().toString(), PurchaseBean.class);
                if (bean.isSuccess()) {
                    获取数据中.dismiss();
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
                            bean.getData().getWaIntegration().getStartTime().getDayOfMonth() + " " + begin);
                    TextView tv_trip_end = findViewById(R.id.tv_trip_end);
                    tv_trip_end.setText(bean.getData().getWaIntegration().getEndTime().getYear() + "-" +
                            bean.getData().getWaIntegration().getEndTime().getMonthValue() + "-" +
                            bean.getData().getWaIntegration().getEndTime().getDayOfMonth() + " " + end);
                    TextView tv_trip_day = findViewById(R.id.tv_trip_day);
                    tv_trip_day.setText(bean.getData().getWaIntegration().getTotalTime() + "");
                    LinearLayout ll_approval_trip = findViewById(R.id.ll_approval_trip);
                    MyGridView gv_trip_person = findViewById(R.id.gv_trip_person);
                    detailsBtDown.setHint(bean.getData().getObject().getFileUrl());
                    if (bean.getData().getNameList().size() != 0 && bean.getData().getNameList() != null) {
                        ll_approval_trip.setVisibility(View.VISIBLE);
                        gv_trip_person.setAdapter(new MyAdapter(DetailsMineTrip.this, bean.getData().getNameList()));
                    } else {
                        ll_approval_trip.setVisibility(View.GONE);
                    }
                    detailsBtDown.setHint(bean.getData().getObject().getFileUrl());
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
        btDetailsWancheng.setOnClickListener(this);
        detailsBtCancel.setOnClickListener(this);
        detailsBtDown.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.details_bt_cancel:
                cancel();//撤销本条
                break;
            case R.id.details_bt_down:
                Utils.down(v);
                break;
            case R.id.bt_details_wancheng:
                detailsFinish();//完成
                break;
            default:

        }
    }


    private void detailsFinish() {
        if (type == 2) {
            if (bean.getData().getWaIntegration().getStartTime().getHour() == 8) {
                begin.setLength(0);
                begin.append("上午");
            } else {
                begin.setLength(0);
                begin.append("下午");
            }
            Intent intent = new Intent(this, WorkSummaryActivity.class);
            intent.putExtra("id", bean.getData().getWaIntegration().getId());
            intent.putExtra("time", bean.getData().getWaIntegration().getStartTime().getYear() + "-" +
                    bean.getData().getWaIntegration().getStartTime().getMonthValue() + "-" +
                    bean.getData().getWaIntegration().getStartTime().getDayOfMonth() + " " + begin);
            intent.putExtra("city", bean.getData().getWaIntegration().getType2());
            intent.putStringArrayListExtra("namelist", (ArrayList<String>) bean.getData().getNameList());
            intent.putIntegerArrayListExtra("nameid", (ArrayList<Integer>) bean.getData().getNameId());
            startActivity(intent);
        } else {
            final LoadingDailog 提交中 = ToastUtils.showDailog(this, "提交中");
            提交中.show();
            OkGo.<String>post(NetAddressUtils.successIntergration)
                    .params("id", workid).params("activity", "com.hsap.huisianpu.push.PushTirpActivity")
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            提交中.dismiss();
                            ToastUtils.showToast(DetailsMineTrip.this, "提交成功");
                            detailsBtCancel.setVisibility(View.GONE);
                            btDetailsWancheng.setVisibility(View.GONE);
                            tvDetailsZhuangtai.setVisibility(View.VISIBLE);
                            tvDetailsZhuangtai.setText("已完成");
                        }

                        @Override
                        public void onError(Response<String> response) {
                            super.onError(response);
                            提交中.dismiss();
                            ToastUtils.showToast(DetailsMineTrip.this, "提交失败");
                        }
                    });
        }
    }

    private void cancel() {
        final LoadingDailog 提交中 = ToastUtils.showDailog(this, "提交中");
        提交中.show();
        OkGo.<String>post(NetAddressUtils.deleteIntergration)
                .params("id", workid).params("activity", "com.hsap.huisianpu.push.PushTirpActivity")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        提交中.dismiss();
                        ToastUtils.showToast(DetailsMineTrip.this, "撤销成功");
                        detailsBtCancel.setVisibility(View.GONE);
                        detailsBtDown.setVisibility(View.GONE);
                        btDetailsWancheng.setVisibility(View.GONE);
                        tvDetailsZhuangtai.setVisibility(View.VISIBLE);
                        tvDetailsZhuangtai.setText("已撤销");
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        提交中.dismiss();
                        ToastUtils.showToast(DetailsMineTrip.this, "撤销失败");
                    }
                });
    }

    @Subscribe
    public void onFlaseBean(FalseBean bean) {
        if ("bb".equals(bean.getName())) {
            tvDetailsZhuangtai.setText("已完成");
            detailsBtCancel.setVisibility(View.GONE);
            btDetailsWancheng.setVisibility(View.GONE);
            final LoadingDailog 提交中 = ToastUtils.showDailog(this, "提交中");
            提交中.show();
            OkGo.<String>post(NetAddressUtils.successIntergration)
                    .params("id", workid).params("activity", "com.hsap.huisianpu.push.PushTirpActivity")
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            提交中.dismiss();
                            ToastUtils.showToast(DetailsMineTrip.this, "提交成功");
                            detailsBtCancel.setVisibility(View.GONE);
                            btDetailsWancheng.setVisibility(View.GONE);
                            tvDetailsZhuangtai.setVisibility(View.VISIBLE);
                            tvDetailsZhuangtai.setText("已完成");
                        }

                        @Override
                        public void onError(Response<String> response) {
                            super.onError(response);
                            提交中.dismiss();
                            ToastUtils.showToast(DetailsMineTrip.this, "提交失败");
                        }
                    });
        }
    }

    @Subscribe
    public void onMessage(File file) {
        Log.d(TAG, "onMessage: " + file.exists());
        detailsBtDown.setText("打开word");
        if (file.exists()) {
            SpUtils.putString("HSAP" + workid + type, file.getName(), this);
            Utils.getWordFileIntent(file.getPath(), this);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: " + year);
        Log.e(TAG, "onPause: " + month);
        EventBus.getDefault().post(new EventDate(year, month));
    }
}
