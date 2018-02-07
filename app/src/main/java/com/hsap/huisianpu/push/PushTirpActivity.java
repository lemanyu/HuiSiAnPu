package com.hsap.huisianpu.push;

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
import com.hsap.huisianpu.adapter.MyAdapter;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.bean.CarBean;
import com.hsap.huisianpu.bean.CarcarBean;
import com.hsap.huisianpu.bean.FalseBean;
import com.hsap.huisianpu.bean.HavePermissionBean;
import com.hsap.huisianpu.bean.PurchaseBean;
import com.hsap.huisianpu.bean.PushBean;
import com.hsap.huisianpu.bean.PushProjectBean;
import com.hsap.huisianpu.bean.PushTripBean;
import com.hsap.huisianpu.bean.SummayBean;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.SpUtils;
import com.hsap.huisianpu.utils.ToastUtils;
import com.hsap.huisianpu.view.MyGridView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushManager;
import com.zhy.android.percent.support.PercentLinearLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.ps_purchase)
    ViewStub psPurchase;
    @BindView(R.id.bt_push_zhuangtai)
    Button btPushZhuangtai;
    private StringBuilder begin = new StringBuilder();
    private StringBuilder end = new StringBuilder();
    private LoadingDailog dailog;
    private PushBean pushBean;
    private int type;

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
          back.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
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
        String s;
        if (type==5){
            s="com.hsap.huisianpu.push.PushProject";
        }else {
            s="com.hsap.huisianpu.push.PushTirpActivity";
        }
        final LoadingDailog 提交中 = ToastUtils.showDailog(this, "提交中");
        提交中.show();
        OkGo.<String>post(NetAddressUtils.setAudit).
                params("projectId",pushBean.getId()).
                params("type",pushBean.getType()).
                params("opinion",1).
                params("managerId", SpUtils.getInt(ConstantUtils.UserId,this)).
                params("activity",s).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        SpUtils.putInt(ConstantUtils.Approve,
                                SpUtils.getInt(ConstantUtils.Approve,PushTirpActivity.this)-1,PushTirpActivity.this);
                        btPushZhuangtai.setVisibility(View.VISIBLE);
                        btPushZhuangtai.setText("已同意");
                        llPs.setVisibility(View.GONE);
                        提交中.dismiss();
                        ToastUtils.showToast(PushTirpActivity.this,"提交成功");
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        提交中.dismiss();
                        ToastUtils.showToast(PushTirpActivity.this,"提交失败");

                    }
                });
    }

    private void refuse() {
        final LoadingDailog 提交中 = ToastUtils.showDailog(this, "提交中");
        提交中.show();
        String s;
        if (type==5){
            s="com.hsap.huisianpu.push.PushProject";
        }else {
            s="com.hsap.huisianpu.push.PushTirpActivity";
        }
        OkGo.<String>post(NetAddressUtils.setAudit).
                params("projectId",pushBean.getId()).
                params("type",pushBean.getType()).
                params("opinion",2).
                params("activity",s).
                params("managerId", SpUtils.getInt(ConstantUtils.UserId,this)).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        btPushZhuangtai.setVisibility(View.VISIBLE);
                        btPushZhuangtai.setText("已拒绝");
                        llPs.setVisibility(View.GONE);
                        SpUtils.putInt(ConstantUtils.Approve,
                                SpUtils.getInt(ConstantUtils.Approve,PushTirpActivity.this)-1,PushTirpActivity.this);
                        提交中.dismiss();
                        ToastUtils.showToast(PushTirpActivity.this,"提交成功");
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        提交中.dismiss();
                        ToastUtils.showToast(PushTirpActivity.this,"提交失败");

                    }
                });

    }

    @Override
    protected void onResume() {
        super.onResume();
        XGPushClickedResult clickedResult = XGPushManager.onActivityStarted(this);
        if (clickedResult != null) {
            String content = clickedResult.getCustomContent();
            Log.e(TAG, content);
            if (content != null) {
                dailog = ToastUtils.showDailog(this, "获取中，请稍后");
                dailog.show();
                pushBean = new Gson().fromJson(content, PushBean.class);
                choice(pushBean.getState());
                type = pushBean.getType();
                switch (type) {
                    case 0:
                        tvPushTitle.setText("请假申请");
                        psLeave.inflate();
                        initLeaveForNet(pushBean.getId());
                        break;
                    case 1:
                        tvPushTitle.setText("外出申请");
                        psOut.inflate();
                        initOutFormNet(pushBean.getId());
                        break;
                    case 2:
                        tvPushTitle.setText("出差申请");
                        psTrip.inflate();
                        initTripFormNet(pushBean.getId());
                        break;
                    case 3:
                        tvPushTitle.setText("加班申请");
                        psOvertime.inflate();
                        initOverTimeFormNet(pushBean.getId());
                        break;
                    case 4:
                        tvPushTitle.setText("用车申请");
                        psCar.inflate();
                        initCarFormNet(pushBean.getId());
                        break;
                    case 5:
                        tvPushTitle.setText("项目");
                        psProject.inflate();
                        initProjcetFormNet(pushBean.getId());
                        break;
                    case 6:
                        tvPushTitle.setText("出差总结");
                        psSummary.inflate();
                        initSummaryFormNet(pushBean.getId());
                        break;
                    case 12:
                        tvPushTitle.setText("采购申请");
                        psPurchase.inflate();
                        initPurchaseFormNet(pushBean.getId());
                        break;
                    default:
                }
            }
        }

    }

    private void initProjcetFormNet(int id) {
        llPs.setVisibility(View.GONE);
        btPushZhuangtai.setText("");
        final TextView tv_see_jindu = findViewById(R.id.tv_see_jindu);
        tv_see_jindu.setVisibility(View.GONE);
        final TextView et_publish_name = findViewById(R.id.et_publish_name);
        final TextView et_publish_number = findViewById(R.id.et_publish_number);
        final TextView et_publish_person = findViewById(R.id.et_publish_person);
        final TextView tv_publish_time = findViewById(R.id.tv_publish_time);
        final TextView et_publish_days = findViewById(R.id.et_publish_days);
        final TextView et_publish_content = findViewById(R.id.et_publish_content);
        final LinearLayout ll_project = findViewById(R.id.ll_project);

        final MyGridView gv_approval_project = findViewById(R.id.gv_approval_project);
        OkGo.<String>post(NetAddressUtils.getMyProjectInfo).
                params("projectId",id).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e(TAG, "onSuccess: "+response.body().toString());
                        PushProjectBean projectBean = new Gson().fromJson(response.body().toString(), PushProjectBean.class);
                        if (projectBean.isSuccess()){
                            dailog.dismiss();
                            PushProjectBean.DataBean.InfoBean info = projectBean.getData().getInfo();
                            et_publish_name.setText(info.getProjectName());
                            et_publish_number.setText(info.getProjectNumber());
                            et_publish_person.setText(projectBean.getData().getName() );
                            tv_publish_time.setText(info.getEsStartTime().getYear()+"-"+info.getEsStartTime().getMonthValue()+"-"+
                                                 info.getEsStartTime().getDayOfMonth());
                            et_publish_days.setText(String.valueOf(info.getEsCycle()));
                            et_publish_content.setText(info.getBody());
                            if (projectBean.getData().getPersonList()!=null&&projectBean.getData().getPersonList().size()!=0){
                                ll_project.setVisibility(View.VISIBLE);
                                ArrayList<String> nameList = new ArrayList<>();
                                for (int i = 0; i < projectBean.getData().getPersonList().size(); i++) {
                                    nameList.add(projectBean.getData().getPersonList().get(i).getName());
                                }
                                gv_approval_project.setAdapter(new MyAdapter(PushTirpActivity.this,nameList));
                            }else {
                                ll_project.setVisibility(View.GONE);
                            }
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dailog.dismiss();
                        ToastUtils.showToast(PushTirpActivity.this,"获取失败");
                    }
                });
    }

    public void choice(int state){
     switch (state){
         case 0:
             btPushZhuangtai.setText("待处理");
             llPs.setVisibility(View.VISIBLE);
             break;
         case 1:
             btPushZhuangtai.setText("已同意");
             llPs.setVisibility(View.GONE);
             break;
         case 2:
             btPushZhuangtai.setText("已拒绝");
             llPs.setVisibility(View.GONE);
             break;
         case 3:
             btPushZhuangtai.setText("待撤销");
             llPs.setVisibility(View.VISIBLE);
             break;
         case 4:
             btPushZhuangtai.setText("已完成");
             llPs.setVisibility(View.GONE);
             break;
         default:
     }
 }
    private void initSummaryFormNet(int id) {
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
        OkGo.<String>post(NetAddressUtils.queryTTBSummar).
                params("id",id).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e(TAG, "onSuccess: "+response.body().toString());
                        dailog.dismiss();
                        SummayBean bean = new Gson().fromJson(response.body().toString(), SummayBean.class);
                        choice(bean.getWaIntegration().getState());
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
                        if (bean.getNameList().size()!=0&&bean.getNameList()!=null){
                            ll_approval_trip.setVisibility(View.VISIBLE);
                            ArrayList<String> list = new ArrayList<>();
                            for (int i = 0; i <bean.getNameList().size() ; i++) {
                                list.add(bean.getNameList().get(i).getName());
                            }
                            gv_trip_person.setAdapter(new MyAdapter(PushTirpActivity.this,list));
                        }else {
                            ll_approval_trip.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dailog.dismiss();
                        ToastUtils.showToast(PushTirpActivity.this,"当前无网络");
                    }
                });
    }

    private void initPurchaseFormNet(int id) {
        final TextView et_purchase_reason = findViewById(R.id.et_purchase_reason);
        final TextView tv_purchase_type = findViewById(R.id.tv_purchase_type);
        final TextView tv_purchase_time = findViewById(R.id.tv_purchase_time);
        final RecyclerView rlv_purchase = findViewById(R.id.rlv_purchase);
        rlv_purchase.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        OkGo.<String>post(NetAddressUtils.selectOneIntergration).
                params("id", id).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        PurchaseBean bean = new Gson().fromJson(response.body().toString(), PurchaseBean.class);
                        ArrayList<PurchaseBean.DataBean.ObjectBean.ListBean> list = new ArrayList<>();
                        if (bean.isSuccess()){
                            dailog.dismiss();
                            list.addAll(bean.getData().getObject().getList());
                            et_purchase_reason.setText(bean.getData().getWaIntegration().getReason()+"");
                            tv_purchase_type.setText(bean.getData().getWaIntegration().getType2());
                            tv_purchase_time.setText(bean.getData().getWaIntegration().getEndTime().getYear()+"-"
                                    +bean.getData().getWaIntegration().getEndTime().getMonthValue()+"-"+
                                    bean.getData().getWaIntegration().getEndTime().getDayOfMonth());

                            rlv_purchase.setAdapter(new PurchaseMyAdapter(R.layout.item_push_purchase,list,bean.getData().getWaIntegration().getType2()));
                        }else {
                            dailog.dismiss();
                            ToastUtils.showToast(getApplicationContext(),"当前无网络");
                        }

                    }


                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dailog.dismiss();
                        ToastUtils.showToast(getApplicationContext(),"当前无网络");
                    }
                });
    }
 class PurchaseMyAdapter extends BaseQuickAdapter<PurchaseBean.DataBean.ObjectBean.ListBean,BaseViewHolder>{
     private String type2;

     public PurchaseMyAdapter(int layoutResId,@Nullable List<PurchaseBean.DataBean.ObjectBean.ListBean> data, String type2) {
         super(layoutResId, data);
         this.type2=type2;
     }

     @Override
     protected void convert(BaseViewHolder helper, PurchaseBean.DataBean.ObjectBean.ListBean item) {
         TextView et_shuliang = helper.getView(R.id.et_shuliang);
         TextView et_danjia = helper.getView(R.id.et_danjia);
         LinearLayout ll_purchase_caigou = helper.getView(R.id.ll_purchase_caigou);
         if (type2.equals("生产原料")){
             ll_purchase_caigou.setVisibility(View.VISIBLE);
             helper.getView(R.id.ll_piaoju).setVisibility(View.GONE);
             helper.getView(R.id.ll_beizhu).setVisibility(View.VISIBLE);
         }else {
             helper.getView(R.id.ll_beizhu).setVisibility(View.GONE);
         }
         helper.setText(R.id.et_mingcheng,item.getName())
                 .setText(R.id.et_xinghao,item.getGuige())
                 .setText(R.id.et_shuliang,item.getShuliang())
                 .setText(R.id.et_danjia,item.getDanjia())
                 .setText(R.id.tv_jine,Double.valueOf(et_shuliang.getText().toString())*Double.valueOf(et_danjia.getText().toString())+"")
                 .setText(R.id.et_piaoju,item.getPiaoju())
                 .setText(R.id.et_yongtu,item.getYongtu())
                 .setText(R.id.et_fengzhuang,item.getFengzhuang())
                 .setText(R.id.et_fenlei,item.getFenlei())
                 .setText(R.id.et_pinpai,item.getPinpai())
                 .setText(R.id.et_lianxiren,item.getLianxiren())
                 .setText(R.id.et_dianhua,item.getDianhua());
     }
 }
    private void initCarFormNet(int id) {
        final TextView tv_car_begin = findViewById(R.id.tv_car_begin);
        final TextView tv_car_end = findViewById(R.id.tv_car_end);
        final TextView tv_car_choice = findViewById(R.id.tv_car_choice);
        final TextView et_car_phone = findViewById(R.id.et_car_phone);
        final TextView et_car_matters = findViewById(R.id.et_car_matters);
        final TextView et_car_location = findViewById(R.id.et_car_location);
        final LinearLayout ll_approval_car = findViewById(R.id.ll_approval_car);
        final MyGridView gv_car_person = findViewById(R.id.gv_car_person);
        OkGo.<String>post(NetAddressUtils.selectOneIntergration).
                params("id", id).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e(TAG, "onSuccess: "+response.body().toString() );
                        CarcarBean bean = new Gson().fromJson(response.body(), CarcarBean.class);
                        if (bean.isSuccess()) {
                            dailog.dismiss();
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
                            if (bean.getData().getNameList().size()!=0&& bean.getData().getNameList()!= null) {
                                ll_approval_car.setVisibility(View.VISIBLE);
                                gv_car_person.setAdapter(new MyAdapter(PushTirpActivity.this, bean.getData().getNameList()));
                            } else {
                                ll_approval_car.setVisibility(View.GONE);
                            }
                            OkGo.<String>post(NetAddressUtils.getJurisdiction).
                                    params("id", SpUtils.getInt(ConstantUtils.UserId,PushTirpActivity.this)).
                                    execute(new StringCallback() {
                                        @Override
                                        public void onSuccess(Response<String> response) {
                                            HavePermissionBean bean = new Gson().fromJson(response.body().toString(), HavePermissionBean.class);
                                            if (bean.isSuccess()) {
                                                psBtConfirm.setVisibility(View.VISIBLE);
                                                psBtRefuse.setVisibility(View.VISIBLE);
                                            }else {
                                                psBtConfirm.setVisibility(View.GONE);
                                                psBtRefuse.setVisibility(View.GONE);
                                            }
                                        }

                                        @Override
                                        public void onError(Response<String> response) {
                                            super.onError(response);
                                            psBtConfirm.setVisibility(View.GONE);
                                            psBtRefuse.setVisibility(View.GONE);
                                        }
                                    });
                        } else {
                            dailog.dismiss();
                            ToastUtils.showToast(PushTirpActivity.this, "当前无网络");
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dailog.dismiss();
                        ToastUtils.showToast(PushTirpActivity.this, "当前无网络");
                    }
                });
    }

    private void initOverTimeFormNet(int id) {
        final TextView tv_overtime_begin = findViewById(R.id.tv_overtime_begin);
        final TextView tv_overtime_end = findViewById(R.id.tv_overtime_end);
        final TextView et_overtime_cause = findViewById(R.id.et_overtime_cause);
        final LinearLayout ll_overtime = findViewById(R.id.ll_overtime);
        final MyGridView gv_overtime_person = findViewById(R.id.gv_overtime_person);
        OkGo.<String>post(NetAddressUtils.selectOneIntergration).
                params("id", id).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        PushTripBean bean = new Gson().fromJson(response.body().toString(), PushTripBean.class);
                        if (bean.isSuccess()) {
                            dailog.dismiss();
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
                                gv_overtime_person.setAdapter(new MyAdapter(PushTirpActivity.this, bean.getData().getNameList()));
                            } else {
                                ll_overtime.setVisibility(View.GONE);
                            }
                            OkGo.<String>post(NetAddressUtils.getJurisdiction).
                                    params("id", SpUtils.getInt(ConstantUtils.UserId,PushTirpActivity.this)).
                                    execute(new StringCallback() {
                                        @Override
                                        public void onSuccess(Response<String> response) {
                                            HavePermissionBean bean = new Gson().fromJson(response.body().toString(), HavePermissionBean.class);
                                            if (bean.isSuccess()) {
                                                psBtConfirm.setVisibility(View.VISIBLE);
                                                psBtRefuse.setVisibility(View.VISIBLE);
                                            }else {
                                                psBtConfirm.setVisibility(View.GONE);
                                                psBtRefuse.setVisibility(View.GONE);
                                            }
                                        }

                                        @Override
                                        public void onError(Response<String> response) {
                                            super.onError(response);
                                            psBtConfirm.setVisibility(View.GONE);
                                            psBtRefuse.setVisibility(View.GONE);
                                        }
                                    });
                        } else {
                            dailog.dismiss();
                            ToastUtils.showToast(PushTirpActivity.this, "当前无网络");
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dailog.dismiss();
                        ToastUtils.showToast(PushTirpActivity.this, "当前无网络");
                    }
                });
    }

    private void initOutFormNet(int id) {
        OkGo.<String>post(NetAddressUtils.selectOneIntergration).
                params("id", id).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        PushTripBean bean = new Gson().fromJson(response.body().toString(), PushTripBean.class);
                        if (bean.isSuccess()) {
                            dailog.dismiss();
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
                            dailog.dismiss();
                            ToastUtils.showToast(PushTirpActivity.this, "当前无网络");
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dailog.dismiss();
                        ToastUtils.showToast(PushTirpActivity.this, "当前无网络");
                    }
                });
    }

    private void initLeaveForNet(int id) {
        OkGo.<String>post(NetAddressUtils.selectOneIntergration).
                params("id", id).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        PushTripBean bean = new Gson().fromJson(response.body().toString(), PushTripBean.class);
                        if (bean.isSuccess()) {
                            dailog.dismiss();
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
                            dailog.dismiss();
                            ToastUtils.showToast(PushTirpActivity.this, "当前无网络");
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dailog.dismiss();
                        ToastUtils.showToast(PushTirpActivity.this, "当前无网络");
                    }
                });
    }

    private void initTripFormNet(int pushBeanId) {
        final TextView et_trip_reason = findViewById(R.id.et_trip_reason);
        final TextView et_trip_city = findViewById(R.id.et_trip_city);
        final TextView tv_trip_begin = findViewById(R.id.tv_trip_begin);
        final TextView tv_trip_end = findViewById(R.id.tv_trip_end);
        final TextView tv_trip_day = findViewById(R.id.tv_trip_day);
        final MyGridView gv_trip_person = findViewById(R.id.gv_trip_person);
        final LinearLayout ll_approval_trip = findViewById(R.id.ll_approval_trip);
        OkGo.<String>post(NetAddressUtils.selectOneIntergration).
                params("id", pushBeanId).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        PushTripBean bean = new Gson().fromJson(response.body().toString(), PushTripBean.class);
                        if (bean.isSuccess()) {
                            dailog.dismiss();
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
                            et_trip_reason.setText(bean.getData().getWaIntegration().getReason() + "");
                            et_trip_city.setText(bean.getData().getWaIntegration().getType2());
                            tv_trip_begin.setText(bean.getData().getWaIntegration().getStartTime().getYear() + "-" +
                                    bean.getData().getWaIntegration().getStartTime().getMonthValue() + "-" +
                                    bean.getData().getWaIntegration().getStartTime().getDayOfMonth() + " " + begin);

                            tv_trip_end.setText(bean.getData().getWaIntegration().getEndTime().getYear() + "-" +
                                    bean.getData().getWaIntegration().getEndTime().getMonthValue() + "-" +
                                    bean.getData().getWaIntegration().getEndTime().getDayOfMonth() + " " + end);
                            tv_trip_day.setText(bean.getData().getWaIntegration().getTotalTime() + "");
                            if (bean.getData().getNameList().size() != 0 && bean.getData().getNameList() != null) {
                                ll_approval_trip.setVisibility(View.VISIBLE);
                                gv_trip_person.setAdapter(new MyAdapter(PushTirpActivity.this,
                                        bean.getData().getNameList()));
                            } else {
                                ll_approval_trip.setVisibility(View.GONE);
                            }
                            OkGo.<String>post(NetAddressUtils.getJurisdiction).
                                    params("id", SpUtils.getInt(ConstantUtils.UserId,PushTirpActivity.this)).
                                    execute(new StringCallback() {
                                        @Override
                                        public void onSuccess(Response<String> response) {
                                            HavePermissionBean bean = new Gson().fromJson(response.body().toString(), HavePermissionBean.class);
                                            if (bean.isSuccess()) {
                                                psBtConfirm.setVisibility(View.VISIBLE);
                                                psBtRefuse.setVisibility(View.VISIBLE);
                                            }else {
                                                psBtConfirm.setVisibility(View.GONE);
                                                psBtRefuse.setVisibility(View.GONE);
                                            }
                                        }

                                        @Override
                                        public void onError(Response<String> response) {
                                            super.onError(response);
                                            psBtConfirm.setVisibility(View.GONE);
                                            psBtRefuse.setVisibility(View.GONE);
                                        }
                                    });
                        } else {
                            dailog.dismiss();
                            ToastUtils.showToast(PushTirpActivity.this, "当前无网络");
                        }


                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dailog.dismiss();
                        ToastUtils.showToast(PushTirpActivity.this, "当前无网络");
                    }
                });

    }

    @Override
    protected void onPause() {
        super.onPause();
        XGPushManager.onActivityStoped(this);
        EventBus.getDefault().post(new FalseBean("qq"));
    }

}
