package com.hsap.huisianpu.details;

import android.os.Environment;
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
import com.google.gson.reflect.TypeToken;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.adapter.MyAdapter;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.bean.BaseBean;
import com.hsap.huisianpu.bean.CarBean;
import com.hsap.huisianpu.bean.DateBean;
import com.hsap.huisianpu.bean.DongPurchaseDetailBean;
import com.hsap.huisianpu.bean.FalseBean;
import com.hsap.huisianpu.bean.PurchaseBean;
import com.hsap.huisianpu.bean.PushTripBean;
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
import java.util.Map;

import butterknife.BindView;

/**
 * 工作审批详情
 *
 * @author zhao
 */

public class DongDetailsWorkApproval extends BaseBackActivity {
    private static final String TAG = "DetailsWorkApproval";
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.tv_approve_type)
    TextView tvApproveType;
    @BindView(R.id.vs_leave)
    ViewStub vsLeave;
    @BindView(R.id.vs_out)
    ViewStub vsOut;
    @BindView(R.id.vs_trip)
    ViewStub vsTrip;
    @BindView(R.id.vs_overtime)
    ViewStub vsOvertime;
    @BindView(R.id.vs_car)
    ViewStub vsCar;
    @BindView(R.id.vs_summary)
    ViewStub vsSummary;
    @BindView(R.id.vs_project)
    ViewStub vsProject;
    @BindView(R.id.details_bt_refuse)
    Button detailsBtRefuse;
    @BindView(R.id.details_bt_confirm)
    Button detailsBtConfirm;
    String begin = new String();
    String end = new String();
    @BindView(R.id.tv_work_approval_zhuangtai)
    TextView tvWorkApprovalZhuangtai;
    @BindView(R.id.vs_purchase)
    ViewStub vsPurchase;
    @BindView(R.id.details_bt_down)
    Button detailsBtDown;

    private int type;
    private int projectId;
    private String name;
    private PurchaseBean bean;

    @Override
    public int getLayoutId() {
        return R.layout.details_work_approval;
    }

    private LoadingDailog dialog;

    @Override
    public void initView() {
        dialog = ToastUtils.showDailog(this, "获取数据中");
        dialog.show();
        name = getIntent().getStringExtra("name");
        type = getIntent().getIntExtra("type", 0);
        projectId = getIntent().getIntExtra("projectId", 0);

        String string = SpUtils.getString("HSAP" + projectId + type, this);
        Log.e(TAG, "aaa"+string );
        if (TextUtils.isEmpty(string)) {
            detailsBtDown.setText("下载word");
        }else {
            detailsBtDown.setText("打开word");
        }

        int opinion = getIntent().getIntExtra("opinion", 0);
        Log.e(TAG, "initView: " + opinion);
        switch (opinion) {
            case 0:
                tvWorkApprovalZhuangtai.setVisibility(View.GONE);
                detailsBtRefuse.setVisibility(View.VISIBLE);
                detailsBtConfirm.setVisibility(View.VISIBLE);
                break;
            case 1:
                tvWorkApprovalZhuangtai.setVisibility(View.VISIBLE);
                tvWorkApprovalZhuangtai.setText("已同意");
                detailsBtRefuse.setVisibility(View.GONE);
                detailsBtConfirm.setVisibility(View.GONE);
                detailsBtDown.setVisibility(View.VISIBLE);
                fileisDown();
                break;
            case 2:
                tvWorkApprovalZhuangtai.setVisibility(View.VISIBLE);
                tvWorkApprovalZhuangtai.setText("已拒绝");
                detailsBtRefuse.setVisibility(View.GONE);
                detailsBtConfirm.setVisibility(View.GONE);
                break;
            default:
        }
        dialog.show();
        getOkGoData();
    }

    private void fileisDown() {
        Environment.getExternalStorageDirectory();
    }

    private void getOkGoData() {
        OkGo.<String>post(NetAddressUtils.selectOneIntergration).
                params("id", projectId).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e(TAG, "onSuccess: To OkGo" + response.body());
                        bean = new Gson().fromJson(response.body(), PurchaseBean.class);
                        if (bean.isSuccess()) {
                            detailsBtDown.setHint(bean.getData().getObject().getFileUrl());
                            loadContent();
                        } else {
                            ToastUtils.showToast(getApplicationContext(), "当前无网络");
                        }
                        dialog.dismiss();
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dialog.dismiss();
                        ToastUtils.showToast(getApplicationContext(), "当前无网络");
                    }
                });
    }

    private void loadContent() {
        switch (type) {
            case 0:
                tvApproveType.setText(name + "的请假");
                vsLeave.inflate();
                leave();
                break;
            case 1:
                tvApproveType.setText(name + "的外出");
                vsOut.inflate();
                out();
                break;
            case 2:
                tvApproveType.setText(name + "的出差");
                vsTrip.inflate();
                trip();
                break;
            case 3:
                tvApproveType.setText(name + "的加班");
                vsOvertime.inflate();
                overTime();
                break;
            case 4:
                tvApproveType.setText(name + "的用车");
                vsCar.inflate();
                car();
                break;
            case 5:
                tvApproveType.setText(name + "的出差总结");
                vsSummary.inflate();
                break;
            case 6:
                tvApproveType.setText(name + "的发布项目");
                vsProject.inflate();
                break;
            case 12:
                tvApproveType.setText(name + "的采购");
                vsPurchase.inflate();
                purchase();
                break;
            default:
        }
    }


    private void purchase() {
        final TextView et_purchase_reason = findViewById(R.id.et_purchase_reason);
        final TextView tv_purchase_type = findViewById(R.id.tv_purchase_type);
        final TextView tv_purchase_time = findViewById(R.id.tv_purchase_time);
        final RecyclerView rlv_purchase = findViewById(R.id.rlv_purchase);
        ArrayList<PurchaseBean.DataBean.ObjectBean.ListBean> list = new ArrayList<>();
        list.addAll(bean.getData().getObject().getList());
        et_purchase_reason.setText(bean.getData().getWaIntegration().getReason() + "");
        tv_purchase_type.setText(bean.getData().getWaIntegration().getType2());

        DateBean endTime = bean.getData().getWaIntegration().getEndTime();

        tv_purchase_time.setText(endTime.getYear() + "-"
                + endTime.getMonthValue() + "-" +
                endTime.getDayOfMonth());
        rlv_purchase.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rlv_purchase.setAdapter(new PurchaseMyAdapter(bean.getData().getWaIntegration().getType2(),R.layout.item_push_purchase, list));
    }

    class PurchaseMyAdapter extends BaseQuickAdapter<PurchaseBean.DataBean.ObjectBean.ListBean, BaseViewHolder> {
            private String type2;

        public PurchaseMyAdapter(String type2, int layoutResId, @Nullable List<PurchaseBean.DataBean.ObjectBean.ListBean> data) {
            super(layoutResId, data);
            this.type2=type2;
        }

        @Override
        protected void convert(BaseViewHolder helper, PurchaseBean.DataBean.ObjectBean.ListBean item) {
            if (type2.equals("生产原料")){
                helper.getView(R.id.ll_purchase_caigou).setVisibility(View.VISIBLE);
                helper.setText(R.id.et_fengzhuang,item.getFengzhuang())
                        .setText(R.id.et_pinpai,item.getPinpai())
                        .setText(R.id.et_fenlei,item.getFenlei())
                        .setText(R.id.et_lianxiren,item.getLianxiren())
                        .setText(R.id.et_dianhua,item.getDianhua());
                helper.getView(R.id.ll_piaoju).setVisibility(View.GONE);
                helper.getView(R.id.ll_beizhu).setVisibility(View.VISIBLE);
            }
            TextView et_shuliang = helper.getView(R.id.et_shuliang);
            TextView et_danjia = helper.getView(R.id.et_danjia);
            helper.setText(R.id.et_mingcheng, item.getName())
                    .setText(R.id.et_xinghao, item.getGuige())
                    .setText(R.id.et_shuliang, item.getShuliang())
                    .setText(R.id.et_danjia, item.getDanjia())
                    .setText(R.id.tv_jine, Double.valueOf(et_shuliang.getText().toString()) * Double.valueOf(et_danjia.getText().toString()) + "")
                    .setText(R.id.et_piaoju, item.getPiaoju())
                    .setText(R.id.et_yongtu, item.getYongtu())
                    .setText(R.id.et_beizhu,item.getBeizhu());
        }


    }

    private void car() {
        final TextView tvCarBegin = findViewById(R.id.tv_car_begin);
        final TextView tvCarEnd = findViewById(R.id.tv_car_end);
        final TextView tvCarChoice = findViewById(R.id.tv_car_choice);
        final TextView etCarPhone = findViewById(R.id.et_car_phone);
        final TextView etCarMatters = findViewById(R.id.et_car_matters);
        final TextView etCarLocation = findViewById(R.id.et_car_location);
        final LinearLayout llApprovalCar = findViewById(R.id.ll_approval_car);
        final MyGridView gvCarPerson = findViewById(R.id.gv_car_person);

        DateBean startTime = bean.getData().getWaIntegration().getStartTime();
        DateBean endTime = bean.getData().getWaIntegration().getEndTime();

        setDateTimeToTextView(tvCarBegin, startTime);
        setDateTimeToTextView(tvCarEnd, endTime);

        PurchaseBean.DataBean.ObjectBean.ListBean listBean = bean.getData().getObject().getList().get(0);


        tvCarChoice.setText(listBean.getLeixing());
        etCarPhone.setText(bean.getData().getWaIntegration().getType2());
        etCarMatters.setText(listBean.getShixiang());
        etCarLocation.setText(listBean.getDidian());
        if (bean.getData().getNameList().size() != 0 && bean.getData().getNameList() != null) {
            llApprovalCar.setVisibility(View.VISIBLE);
            gvCarPerson.setAdapter(new MyAdapter(DongDetailsWorkApproval.this, bean.getData().getNameList()));
        } else {
            llApprovalCar.setVisibility(View.GONE);
        }

    }

    private void setDateTimeToTextView(TextView v, DateBean dateBean) {
        StringBuilder builder = new StringBuilder(dateBean.getYear() + "-" +
                dateBean.getMonthValue() + "-"
                + dateBean.getDayOfMonth() + " " +
                dateBean.getHour() + ":" +
                dateBean.getMinute());
        v.setText(builder.toString());
    }

    private void setDateTimeToTextView(TextView v, DateBean dateBean, String end) {
        StringBuilder builder = new StringBuilder(dateBean.getYear() + "-" +
                dateBean.getMonthValue() + "-"
                + dateBean.getDayOfMonth() + " " +
                dateBean.getHour() + ":" +
                dateBean.getMinute() + end);
        v.setText(builder.toString());
    }


    private void overTime() {
        final TextView tv_overtime_begin = findViewById(R.id.tv_overtime_begin);
        final TextView tv_overtime_end = findViewById(R.id.tv_overtime_end);
        final TextView et_overtime_cause = findViewById(R.id.et_overtime_cause);
        final LinearLayout ll_overtime = findViewById(R.id.ll_overtime);
        final MyGridView gv_overtime_person = findViewById(R.id.gv_overtime_person);

        setDateTimeToTextView(tv_overtime_begin, bean.getData().getWaIntegration().getStartTime());
        setDateTimeToTextView(tv_overtime_end, bean.getData().getWaIntegration().getEndTime());

        et_overtime_cause.setText(bean.getData().getWaIntegration().getReason());
        if (bean.getData().getNameList().size() != 0 && bean.getData().getNameList() != null) {
            ll_overtime.setVisibility(View.VISIBLE);
            gv_overtime_person.setAdapter(new MyAdapter(DongDetailsWorkApproval.this, bean.getData().getNameList()));
        } else {
            ll_overtime.setVisibility(View.GONE);
        }

    }

    private void out() {

        TextView tv_out_reason = findViewById(R.id.tv_out_reason);
        TextView tv_out_time = findViewById(R.id.tv_out_time);
        TextView tv_return_time = findViewById(R.id.tv_return_time);


        setDateTimeToTextView(tv_out_time, bean.getData().getWaIntegration().getStartTime());
        setDateTimeToTextView(tv_return_time, bean.getData().getWaIntegration().getEndTime());

        tv_out_reason.setText(bean.getData().getWaIntegration().getReason());
        PercentLinearLayout pll_out_articles = findViewById(R.id.pll_out_articles);
        TextView et_out_articles = findViewById(R.id.et_out_articles);
        if (bean.getData().getWaIntegration().getReason().equals("请假") || bean.getData().getWaIntegration().getReason().equals("其他")) {
            pll_out_articles.setVisibility(View.GONE);
        } else {
            pll_out_articles.setVisibility(View.VISIBLE);
            et_out_articles.setText(bean.getData().getWaIntegration().getType2());
        }

    }

    private void trip() {
        DateBean startTime = bean.getData().getWaIntegration().getStartTime();
        DateBean endTime = bean.getData().getWaIntegration().getEndTime();
        if (startTime.getHour() == 8) {
            begin = ("上午");
        } else {
            begin = ("下午");
        }
        if (endTime.getHour() == 8) {
            end = ("上午");
        } else {
            end = ("下午");
        }
        TextView etTripReason = findViewById(R.id.et_trip_reason);
        TextView etTripCity = findViewById(R.id.et_trip_city);
        TextView tvTripBegin = findViewById(R.id.tv_trip_begin);
        TextView tvTripEnd = findViewById(R.id.tv_trip_end);
        TextView tvTripDay = findViewById(R.id.tv_trip_day);
        LinearLayout llApprovalTrip = findViewById(R.id.ll_approval_trip);
        MyGridView gvTripPerson = findViewById(R.id.gv_trip_person);

        String totalTime = bean.getData().getWaIntegration().getTotalTime().toString();

        setDateTimeToTextView(tvTripBegin, startTime, begin);
        setDateTimeToTextView(tvTripEnd, startTime, end);

        etTripCity.setText(bean.getData().getWaIntegration().getType2());
        tvTripDay.setText(totalTime);

        if (bean.getData().getNameList().size() != 0 && bean.getData().getNameList() != null) {
            llApprovalTrip.setVisibility(View.VISIBLE);
            gvTripPerson.setAdapter(new MyAdapter(DongDetailsWorkApproval.this, bean.getData().getNameList()));
        }

    }

    private void leave() {
        DateBean startTime = bean.getData().getWaIntegration().getStartTime();
        DateBean endTime = bean.getData().getWaIntegration().getEndTime();
        if (startTime.getHour() == 8) {
            begin = ("上午");
        } else {
            begin = ("下午");
        }
        if (endTime.getHour() == 8) {
            end = ("上午");
        } else {
            end = ("下午");
        }
        TextView tvQingjialeixing = findViewById(R.id.tv_qingjialeixing);
        TextView tvBegintime = findViewById(R.id.tv_beginTime);
        TextView tvEndtime = findViewById(R.id.tv_endTime);
        TextView tvShichang = findViewById(R.id.tv_shichang);
        TextView etLeave = findViewById(R.id.et_leave);

        String totalTime = bean.getData().getWaIntegration().getTotalTime().toString();


        tvQingjialeixing.setText(bean.getData().getWaIntegration().getType2());
        setDateTimeToTextView(tvBegintime, startTime, end);
        setDateTimeToTextView(tvEndtime, endTime, end);
        tvShichang.setText(totalTime);

        etLeave.setText(bean.getData().getWaIntegration().getReason());

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
        detailsBtConfirm.setOnClickListener(this);
        detailsBtRefuse.setOnClickListener(this);
        detailsBtDown.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.details_bt_confirm:
                //同意的操作
                confirm();
                break;
            case R.id.details_bt_refuse:
                //拒绝理由。
                refuse();
                break;
            case R.id.details_bt_down:
                if (((Button) v).getText().equals("打开Word")) {
                    File file = Environment.getExternalStorageDirectory();
                    Log.d(TAG, "processClick: "+file.getPath());
                    SpUtils.putString("HSAP" + projectId + type, file.getPath(), this);
                    String s = SpUtils.getString("HSAP" + projectId + type, this);
                    Utils.getWordFileIntent(s,this);
                } else {
                    Utils.down(v);
                }
                break;
            default:
        }
    }

    private void refuse() {
        final LoadingDailog 提交中 = ToastUtils.showDailog(this, "提交中");
        提交中.show();
        OkGo.<String>post(NetAddressUtils.setAudit).
                params("projectId", projectId).
                params("type", type).
                params("opinion", 2).
                params("managerId", SpUtils.getInt(ConstantUtils.UserId, this)).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        tvWorkApprovalZhuangtai.setVisibility(View.VISIBLE);
                        tvWorkApprovalZhuangtai.setText("已拒绝");
                        detailsBtConfirm.setVisibility(View.GONE);
                        detailsBtRefuse.setVisibility(View.GONE);
                        SpUtils.putInt(ConstantUtils.Approve,
                                SpUtils.getInt(ConstantUtils.Approve, DongDetailsWorkApproval.this) - 1, DongDetailsWorkApproval.this);
                        提交中.dismiss();
                        ToastUtils.showToast(DongDetailsWorkApproval.this, "提交成功");
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        提交中.dismiss();
                        ToastUtils.showToast(DongDetailsWorkApproval.this, "提交失败");

                    }
                });
    }

    private void confirm() {
        //  tvWorkApprovalZhuangtai
        final LoadingDailog 提交中 = ToastUtils.showDailog(this, "提交中");
        提交中.show();
        OkGo.<String>post(NetAddressUtils.setAudit).
                params("projectId", projectId).
                params("type", type).
                params("opinion", 1).
                params("managerId", SpUtils.getInt(ConstantUtils.UserId, this)).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        SpUtils.putInt(ConstantUtils.Approve,
                                SpUtils.getInt(ConstantUtils.Approve, DongDetailsWorkApproval.this) - 1, DongDetailsWorkApproval.this);
                        tvWorkApprovalZhuangtai.setVisibility(View.VISIBLE);
                        tvWorkApprovalZhuangtai.setText("已同意");
                        detailsBtConfirm.setVisibility(View.GONE);
                        detailsBtRefuse.setVisibility(View.GONE);
                        提交中.dismiss();
                        ToastUtils.showToast(DongDetailsWorkApproval.this, "提交成功");
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        提交中.dismiss();
                        ToastUtils.showToast(DongDetailsWorkApproval.this, "提交失败");
                    }
                });
    }



    @Subscribe
    public void onMessage(File file) {
        Log.d(TAG, "onMessage: " + file.exists());
        detailsBtDown.setText("打开work");
        if (file.exists()) {
            SpUtils.putString("HSAP" + projectId + type,file.getName(), this);
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
}
