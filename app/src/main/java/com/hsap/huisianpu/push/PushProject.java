package com.hsap.huisianpu.push;

import android.os.Bundle;
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
import com.hsap.huisianpu.bean.HavePermissionBean;
import com.hsap.huisianpu.bean.PushAnnouncenBean;
import com.hsap.huisianpu.bean.PushProjectBean;
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
import com.warkiz.widget.IndicatorSeekBar;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 公告推送
 */

public class PushProject extends BaseBackActivity {
    private static final String TAG = "PushProject";
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.tv_push_announce)
    TextView tvPushAnnounce;
    @BindView(R.id.ps_bt_refuse)
    Button psBtRefuse;
    @BindView(R.id.ps_bt_confirm)
    Button psBtConfirm;
    @BindView(R.id.ll_ps)
    LinearLayout llPs;
    @BindView(R.id.vs)
    ViewStub vs;
    @BindView(R.id.bt_push_zhuangtai)
    Button btPushZhuangtai;
    private LoadingDailog 获取数据中;
    private PushProjectBean projectBean;


    @Override
    public int getLayoutId() {
        return R.layout.push_project_activity;
    }

    @Override
    public void initView() {
        获取数据中 = ToastUtils.showDailog(this, "获取数据中");
        获取数据中.show();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        psBtRefuse.setOnClickListener(this);
        psBtConfirm.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.ps_bt_refuse://拒绝
                refuse();
                break;
            case R.id.ps_bt_confirm:
                confirm();
                break;
            default:
        }
    }

    private void confirm() {
        final LoadingDailog 提交中 = ToastUtils.showDailog(this, "提交中");
        提交中.show();
        OkGo.<String>post(NetAddressUtils.setAudit).
                params("projectId",projectBean.getData().getInfo().getId()).
                params("type",5).
                params("opinion",1).
                params("managerId", SpUtils.getInt(ConstantUtils.UserId,this)).
                params("activity","com.hsap.huisianpu.push.PushProject").
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        SpUtils.putInt(ConstantUtils.Approve,
                                SpUtils.getInt(ConstantUtils.Approve,PushProject.this)-1,PushProject.this);
                        btPushZhuangtai.setVisibility(View.VISIBLE);
                        btPushZhuangtai.setText("已同意");
                        llPs.setVisibility(View.GONE);
                        提交中.dismiss();
                        ToastUtils.showToast(PushProject.this,"提交成功");
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        提交中.dismiss();
                        ToastUtils.showToast(PushProject.this,"提交失败");

                    }
                });
    }

    private void refuse() {
        final LoadingDailog 提交中 = ToastUtils.showDailog(this, "提交中");
        提交中.show();
        OkGo.<String>post(NetAddressUtils.setAudit).
                params("projectId",projectBean.getData().getInfo().getId()).
                params("type",5).
                params("opinion",2).
                params("activity","com.hsap.huisianpu.push.PushProject").
                params("managerId", SpUtils.getInt(ConstantUtils.UserId,this)).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        btPushZhuangtai.setVisibility(View.VISIBLE);
                        btPushZhuangtai.setText("已拒绝");
                        llPs.setVisibility(View.GONE);
                        SpUtils.putInt(ConstantUtils.Approve,
                                SpUtils.getInt(ConstantUtils.Approve,PushProject.this)-1,PushProject.this);
                        提交中.dismiss();
                        ToastUtils.showToast(PushProject.this,"提交成功");
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        提交中.dismiss();
                        ToastUtils.showToast(PushProject.this,"提交失败");

                    }
                });

    }

    @Override
    protected void onResume() {
        super.onResume();
        XGPushClickedResult clickedResult = XGPushManager.onActivityStarted(this);
        if (clickedResult != null) {
            Log.e(TAG, "onResume: " + clickedResult.getCustomContent());
            PushAnnouncenBean bean = new Gson().fromJson(clickedResult.getCustomContent(), PushAnnouncenBean.class);
            choice(bean.getState());
            dataformNet(bean.getId(), bean.getName(),bean.getAdmin());
        }

    }

    private void choice(int state) {
        switch (state) {
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
            default:
        }
    }

    private void dataformNet(int id, final String name, final int admin) {
        vs.inflate();
        final TextView et_publish_name = findViewById(R.id.et_publish_name);
        final TextView et_publish_number = findViewById(R.id.et_publish_number);
        final TextView et_publish_person = findViewById(R.id.et_publish_person);
        final TextView tv_publish_time = findViewById(R.id.tv_publish_time);
        final TextView et_publish_days = findViewById(R.id.et_publish_days);
        final TextView et_publish_content = findViewById(R.id.et_publish_content);
        final LinearLayout ll_project = findViewById(R.id.ll_project);
        final MyGridView gv_approval_project = findViewById(R.id.gv_approval_project);
        final IndicatorSeekBar see_icb = findViewById(R.id.see_icb);
        final TextView tv_see_jindu = findViewById(R.id.tv_see_jindu);
        if (admin!=0){
            tv_see_jindu.setVisibility(View.GONE);
            btPushZhuangtai.setText("已完成");
        }
        see_icb.setVisibility(View.GONE);
        OkGo.<String>post(NetAddressUtils.getMyProjectInfo).
                params("projectId", id).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e(TAG, "onSuccess: " + response.body().toString());
                        projectBean = new Gson().fromJson(response.body().toString(), PushProjectBean.class);
                        if (projectBean.isSuccess()) {
                            PushProjectBean.DataBean.InfoBean info = projectBean.getData().getInfo();
                            et_publish_name.setText(info.getProjectName());
                            et_publish_number.setText(info.getProjectNumber());
                            et_publish_person.setText(name);
                            tv_publish_time.setText(info.getEsStartTime().getYear() + "-" + info.getEsStartTime().getMonthValue() + "-" +
                                    info.getEsStartTime().getDayOfMonth());
                            et_publish_days.setText(String.valueOf(info.getEsCycle()));
                            et_publish_content.setText(info.getBody());
                            if (projectBean.getData().getPersonList() != null && projectBean.getData().getPersonList().size() != 0) {
                                ll_project.setVisibility(View.VISIBLE);
                                ArrayList<String> nameList = new ArrayList<>();
                                for (int i = 0; i < projectBean.getData().getPersonList().size(); i++) {
                                    nameList.add(projectBean.getData().getPersonList().get(i).getName());
                                }
                                gv_approval_project.setAdapter(new MyAdapter(PushProject.this, nameList));
                            } else {
                                ll_project.setVisibility(View.GONE);
                            }
                            choiceJindu(projectBean.getData().getInfo().getProgreddBar(),admin);
                            if (projectBean.getData().getInfo().getProgreddBar()==8){
                                tv_see_jindu.setVisibility(View.GONE);
                            }else {
                                tv_see_jindu.setText("当前进度：" + projectBean.getData().getFilePath().get(projectBean.getData().getInfo().getProgreddBar() - 1).getBar());
                            }  //是否显示按钮
                            OkGo.<String>post(NetAddressUtils.getJurisdiction).
                                    params("id", SpUtils.getInt(ConstantUtils.UserId, PushProject.this)).
                                    execute(new StringCallback() {
                                        @Override
                                        public void onSuccess(Response<String> response) {
                                            获取数据中.dismiss();
                                            HavePermissionBean bean = new Gson().fromJson(response.body().toString(), HavePermissionBean.class);
                                            if (bean.isData()&&(projectBean.getData().getFilePath().get(projectBean.getData().getInfo().getProgreddBar() - 1).getBar()==100)) {
                                                llPs.setVisibility(View.VISIBLE);
                                            } else {
                                               // btPushZhuangtai.setVisibility(View.GONE);
                                                btPushZhuangtai.setText("待完成");
                                                llPs.setVisibility(View.GONE);
                                            }

                                        }

                                    });
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        获取数据中.dismiss();
                        ToastUtils.showToast(PushProject.this, "获取失败");
                    }
                });
    }

    private void choiceJindu(int progreddBar, int admin) {
        progreddBar -=admin;
        switch (progreddBar) {
            case 1:
                tvPushAnnounce.setText("立项阶段进度");
                break;
            case 2:
                tvPushAnnounce.setText("总体设计阶段进度");
                break;
            case 3:
                tvPushAnnounce.setText("产品开发阶段进度");
                break;
            case 4:
                tvPushAnnounce.setText("自测阶段进度");
                break;
            case 5:
                tvPushAnnounce.setText("测试阶段进度");
                break;
            case 6:
                tvPushAnnounce.setText("新产品验收阶段进度");
                break;
            case 7:
                tvPushAnnounce.setText("结项阶段进度");
                break;
            default:
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        XGPushManager.onActivityStoped(this);
        EventBus.getDefault().post(new FalseBean("qq"));
    }

}
