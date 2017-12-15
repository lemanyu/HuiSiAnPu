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
import com.hsap.huisianpu.bean.FalseBean;
import com.hsap.huisianpu.bean.PushTripBean;
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

import butterknife.BindView;

/**
 * 工作审批详情
 *
 * @author zhao
 */

public class DetailsWorkApproval extends BaseBackActivity {
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
    StringBuilder begin = new StringBuilder();
    StringBuilder end = new StringBuilder();
    @BindView(R.id.tv_work_approval_zhuangtai)
    TextView tvWorkApprovalZhuangtai;
    private int type;
    private int projectId;

    @Override
    public int getLayoutId() {

        return R.layout.details_work_approval;
    }

    @Override
    public void initView() {
        LoadingDailog 获取数据中 = ToastUtils.showDailog(this, "获取数据中");
        String name = getIntent().getStringExtra("name");
        type = getIntent().getIntExtra("type", 0);
        projectId = getIntent().getIntExtra("projectId", 0);
        int opinion = getIntent().getIntExtra("opinion", 0);
        switch (opinion){
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
                break;
            case 2:
                tvWorkApprovalZhuangtai.setVisibility(View.VISIBLE);
                tvWorkApprovalZhuangtai.setText("已拒绝");
                detailsBtRefuse.setVisibility(View.GONE);
                detailsBtConfirm.setVisibility(View.GONE);
                break;
            default:
        }
        loadContent(name, type, projectId, 获取数据中);
    }

    private void loadContent(String name, int type, int projectId, LoadingDailog 获取数据中) {
        获取数据中.show();
        switch (type) {
            case 0:
                tvApproveType.setText(name + "的请假");
                vsLeave.inflate();
                leave(projectId, 获取数据中);
                break;
            case 1:
                tvApproveType.setText(name + "的外出");
                vsOut.inflate();
                out(projectId, 获取数据中);
                break;
            case 2:
                tvApproveType.setText(name + "的出差");
                vsTrip.inflate();
                trip(projectId, 获取数据中);
                break;
            case 3:
                tvApproveType.setText(name + "的加班");
                vsOvertime.inflate();
                overTime(projectId, 获取数据中);
                break;
            case 4:
                tvApproveType.setText(name + "的用车");
                vsCar.inflate();
                car(projectId, 获取数据中);
                break;
            case 5:
                tvApproveType.setText(name + "的出差总结");
                vsSummary.inflate();
                break;
            case 6:
                tvApproveType.setText(name + "的发布项目");
                vsProject.inflate();
                break;
            default:
        }
    }

    private void car(int projectId, final LoadingDailog 获取数据中) {
        final TextView tv_car_begin = findViewById(R.id.tv_car_begin);
        final TextView tv_car_end = findViewById(R.id.tv_car_end);
        final TextView tv_car_choice = findViewById(R.id.tv_car_choice);
        final TextView et_car_phone = findViewById(R.id.et_car_phone);
        final TextView et_car_matters = findViewById(R.id.et_car_matters);
        final TextView et_car_location = findViewById(R.id.et_car_location);
        final LinearLayout ll_approval_car = findViewById(R.id.ll_approval_car);
        final MyGridView gv_car_person = findViewById(R.id.gv_car_person);
        OkGo.<String>post(NetAddressUtils.selectOneIntergration).
                params("id", projectId).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        PushTripBean bean = new Gson().fromJson(response.body(), PushTripBean.class);
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
                            tv_car_choice.setText(bean.getData().getMap().getLeixing());
                            et_car_phone.setText(bean.getData().getWaIntegration().getType2());
                            et_car_matters.setText(bean.getData().getMap().getShixiang());
                            et_car_location.setText(bean.getData().getMap().getDidian());
                            if (bean.getData().getNameList().size() != 0 && bean.getData().getNameList() != null) {
                                ll_approval_car.setVisibility(View.VISIBLE);
                                gv_car_person.setAdapter(new MyAdapter(DetailsWorkApproval.this, bean.getData().getNameList()));
                            } else {
                                ll_approval_car.setVisibility(View.GONE);
                            }
                        } else {
                            获取数据中.dismiss();
                            ToastUtils.showToast(DetailsWorkApproval.this, "当前无网络");
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        获取数据中.dismiss();
                        ToastUtils.showToast(DetailsWorkApproval.this, "当前无网络");
                    }
                });
    }

    private void overTime(int projectId, final LoadingDailog 获取数据中) {
        final TextView tv_overtime_begin = findViewById(R.id.tv_overtime_begin);
        final TextView tv_overtime_end = findViewById(R.id.tv_overtime_end);
        final TextView et_overtime_cause = findViewById(R.id.et_overtime_cause);
        final LinearLayout ll_overtime = findViewById(R.id.ll_overtime);
        final MyGridView gv_overtime_person = findViewById(R.id.gv_overtime_person);
        OkGo.<String>post(NetAddressUtils.selectOneIntergration).
                params("id", projectId).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        PushTripBean bean = new Gson().fromJson(response.body().toString(), PushTripBean.class);
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
                            if (bean.getData().getNameList().size() != 0 && bean.getData().getNameList() != null) {
                                ll_overtime.setVisibility(View.VISIBLE);
                                gv_overtime_person.setAdapter(new MyAdapter(DetailsWorkApproval.this, bean.getData().getNameList()));
                            } else {
                                ll_overtime.setVisibility(View.GONE);
                            }
                        } else {
                            获取数据中.dismiss();
                            ToastUtils.showToast(DetailsWorkApproval.this, "当前无网络");
                        }
                    }
                });
    }

    private void out(int projectId, final LoadingDailog 获取数据中) {
        OkGo.<String>post(NetAddressUtils.selectOneIntergration).
                params("id", projectId).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        PushTripBean bean = new Gson().fromJson(response.body().toString(), PushTripBean.class);
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
                            if (bean.getData().getWaIntegration().getReason().equals("请假") || bean.getData().getWaIntegration().getReason().equals("其他")) {
                                pll_out_articles.setVisibility(View.GONE);
                            } else {
                                pll_out_articles.setVisibility(View.VISIBLE);
                                et_out_articles.setText(bean.getData().getWaIntegration().getType2());
                            }
                        } else {
                            获取数据中.dismiss();
                            ToastUtils.showToast(DetailsWorkApproval.this, "获取数据失败");
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        获取数据中.dismiss();
                        ToastUtils.showToast(DetailsWorkApproval.this, "获取数据失败");
                    }
                });
    }

    private void trip(int projectId, final LoadingDailog 获取数据中) {
        OkGo.<String>post(NetAddressUtils.selectOneIntergration).
                params("id", projectId).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        PushTripBean bean = new Gson().fromJson(response.body().toString(), PushTripBean.class);
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
                            if (bean.getData().getNameList().size() != 0 && bean.getData().getNameList() != null) {
                                ll_approval_trip.setVisibility(View.VISIBLE);
                                gv_trip_person.setAdapter(new MyAdapter(DetailsWorkApproval.this, bean.getData().getNameList()));
                            }
                        } else {
                            获取数据中.dismiss();
                            ToastUtils.showToast(DetailsWorkApproval.this, "获取数据失败");
                        }
                        Log.e(TAG, response.body().toString());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        获取数据中.dismiss();
                        ToastUtils.showToast(DetailsWorkApproval.this, "获取数据失败");
                    }
                });
    }

    private void leave(int projectId, final LoadingDailog 获取数据中) {
        OkGo.<String>post(NetAddressUtils.selectOneIntergration).
                params("id", projectId).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        PushTripBean bean = new Gson().fromJson(response.body().toString(), PushTripBean.class);
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
                                    bean.getData().getWaIntegration().getEndTime().getDayOfMonth() + " " + begin);
                            TextView tv_shichang = findViewById(R.id.tv_shichang);
                            tv_shichang.setText(bean.getData().getWaIntegration().getTotalTime() + "");
                            TextView et_leave = findViewById(R.id.et_leave);
                            et_leave.setText(bean.getData().getWaIntegration().getReason());

                        } else {
                            获取数据中.dismiss();
                            ToastUtils.showToast(DetailsWorkApproval.this, "获取数据失败");
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        获取数据中.dismiss();
                        ToastUtils.showToast(DetailsWorkApproval.this, "获取数据失败");
                    }
                });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
        detailsBtConfirm.setOnClickListener(this);
        detailsBtRefuse.setOnClickListener(this);
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

            default:
        }
    }

    private void refuse() {
        final LoadingDailog 提交中 = ToastUtils.showDailog(this, "提交中");
        提交中.show();
        OkGo.<String>post(NetAddressUtils.setAudit).
                params("projectId",projectId).
                params("type",type).
                params("opinion",2).
                params("managerId", SpUtils.getInt(ConstantUtils.UserId,this)).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        tvWorkApprovalZhuangtai.setVisibility(View.VISIBLE);
                        tvWorkApprovalZhuangtai.setText("已拒绝");
                        detailsBtConfirm.setVisibility(View.GONE);
                        detailsBtRefuse.setVisibility(View.GONE);
                        SpUtils.putInt(ConstantUtils.Approve,
                                SpUtils.getInt(ConstantUtils.Approve,DetailsWorkApproval.this)-1,DetailsWorkApproval.this);
                        提交中.dismiss();
                        ToastUtils.showToast(DetailsWorkApproval.this,"提交成功");
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        提交中.dismiss();
                        ToastUtils.showToast(DetailsWorkApproval.this,"提交失败");

                    }
                });
    }

    private void confirm() {
      //  tvWorkApprovalZhuangtai
        final LoadingDailog 提交中 = ToastUtils.showDailog(this, "提交中");
        提交中.show();
        OkGo.<String>post(NetAddressUtils.setAudit).
                params("projectId",projectId).
                params("type",type).
                params("opinion",1).
                params("managerId", SpUtils.getInt(ConstantUtils.UserId,this)).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        SpUtils.putInt(ConstantUtils.Approve,
                                SpUtils.getInt(ConstantUtils.Approve,DetailsWorkApproval.this)-1,DetailsWorkApproval.this);
                        tvWorkApprovalZhuangtai.setVisibility(View.VISIBLE);
                        tvWorkApprovalZhuangtai.setText("已同意");
                        detailsBtConfirm.setVisibility(View.GONE);
                        detailsBtRefuse.setVisibility(View.GONE);
                        提交中.dismiss();
                       ToastUtils.showToast(DetailsWorkApproval.this,"提交成功");
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        提交中.dismiss();
                        ToastUtils.showToast(DetailsWorkApproval.this,"提交失败");

                    }
                });
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().post(new FalseBean("aa"));
    }
}
