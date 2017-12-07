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
import com.hsap.huisianpu.bean.DetailsLeaveBean;
import com.hsap.huisianpu.bean.DetailsTripBean;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.ToastUtils;
import com.hsap.huisianpu.view.MyGridView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import butterknife.BindView;

/**
 * 工作审批详情
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
    StringBuilder begin=new StringBuilder();
    StringBuilder end=new StringBuilder();
    @Override
    public int getLayoutId() {
        return R.layout.details_work_approval;
    }

    @Override
    public void initView() {
        LoadingDailog 获取数据中 = ToastUtils.showDailog(this, "获取数据中");
        String name = getIntent().getStringExtra("name");
        int type = getIntent().getIntExtra("type", 0);
        int projectId = getIntent().getIntExtra("projectId", 0);
        loadContent(name,type,projectId,获取数据中);
    }

    private void loadContent(String name, int type, int projectId,LoadingDailog 获取数据中) {
        获取数据中.show();
        switch (type) {
            case 0:
                tvApproveType.setText(name + "的请假");
                vsLeave.inflate();
                leave(projectId,获取数据中);
                break;
            case 1:
                tvApproveType.setText(name + "的外出");
                vsOut.inflate();
                break;
            case 2:
                tvApproveType.setText(name + "的出差");
                vsTrip.inflate();
                trip(projectId,获取数据中);
                break;
            case 3:
                tvApproveType.setText(name + "的加班");
                vsOvertime.inflate();
                break;
            case 4:
                tvApproveType.setText(name + "的用车");
                vsCar.inflate();
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

    private void trip(int projectId, final LoadingDailog 获取数据中) {
        OkGo.<String>post(NetAddressUtils.getOneBusinessTrip).
                params("id",projectId).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        DetailsTripBean bean = new Gson().fromJson(response.body().toString(), DetailsTripBean.class);
                        if (bean.isSuccess()){
                            获取数据中.dismiss();
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
                            TextView et_trip_city = findViewById(R.id.et_trip_city);
                            et_trip_city.setText(bean.getData().getWaTravelBusiness().getPlace());
                            TextView tv_trip_begin = findViewById(R.id.tv_trip_begin);
                            tv_trip_begin.setText(bean.getData().getWaTravelBusiness().getDepartureTime().getYear()+"-"+
                                    bean.getData().getWaTravelBusiness().getDepartureTime().getMonthValue()+"-"+
                                    bean.getData().getWaTravelBusiness().getDepartureTime().getDayOfMonth()+" "+begin);
                            TextView tv_trip_end = findViewById(R.id.tv_trip_end);
                            tv_trip_end.setText(bean.getData().getWaTravelBusiness().getReturnTime().getYear()+"-"+
                                    bean.getData().getWaTravelBusiness().getReturnTime().getMonthValue()+"-"+
                                    bean.getData().getWaTravelBusiness().getReturnTime().getDayOfMonth()+" "+end);
                            TextView tv_trip_day = findViewById(R.id.tv_trip_day);
                            tv_trip_day.setText(bean.getData().getWaTravelBusiness().getTotal()+"");
                            LinearLayout ll_approval_trip = findViewById(R.id.ll_approval_trip);
                            MyGridView gv_trip_person = findViewById(R.id.gv_trip_person);
                            if(bean.getData().getAccompanying().size()!=0&&bean.getData().getAccompanying()!=null){
                                ll_approval_trip.setVisibility(View.VISIBLE);
                                gv_trip_person.setAdapter(new MyAdapter(DetailsWorkApproval.this,bean.getData().getAccompanying()));
                            }
                        }else {
                            获取数据中.dismiss();
                            ToastUtils.showToast(DetailsWorkApproval.this,"获取数据失败");
                        }
                        Log.e(TAG, response.body().toString());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        获取数据中.dismiss();
                        ToastUtils.showToast(DetailsWorkApproval.this,"获取数据失败");
                    }
                });
    }


    private void leave(int projectId, final LoadingDailog 获取数据中) {
        OkGo.<String>post(NetAddressUtils.getOneLeave).
                params("id",projectId).
                execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                DetailsLeaveBean bean = new Gson().fromJson(response.body().toString(), DetailsLeaveBean.class);
                if (bean.isSuccess()){
                    获取数据中.dismiss();
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
                    }
                    TextView tv_qingjialeixing = findViewById(R.id.tv_qingjialeixing);
                    tv_qingjialeixing.setText(bean.getData().getLeaveType());
                    TextView tv_beginTime= findViewById(R.id.tv_beginTime);
                    tv_beginTime.setText(bean.getData().getStartTime().getYear()+"-"+
                            bean.getData().getStartTime().getMonthValue()+"-"+
                            bean.getData().getStartTime().getDayOfMonth()+" "+begin);
                    TextView tv_endTime = findViewById(R.id.tv_endTime);
                    tv_endTime.setText(bean.getData().getEndTime().getYear()+"-"+
                            bean.getData().getEndTime().getMonthValue()+"-"+
                            bean.getData().getEndTime().getDayOfMonth()+" "+begin);
                    TextView tv_shichang = findViewById(R.id.tv_shichang);
                    tv_shichang.setText(bean.getData().getTotalTime()+"");

                }else {
                    获取数据中.dismiss();
                    ToastUtils.showToast(DetailsWorkApproval.this,"获取数据失败");
                }



            }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        获取数据中.dismiss();
                        ToastUtils.showToast(DetailsWorkApproval.this,"获取数据失败");
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
                  switch (v.getId()){
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

    }

    private void confirm() {

    }


}
