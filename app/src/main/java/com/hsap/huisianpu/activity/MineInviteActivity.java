package com.hsap.huisianpu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.adapter.InviteRecycleAdapter;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.bean.EventDate;
import com.hsap.huisianpu.bean.InvitationBean;
import com.hsap.huisianpu.bean.InviteBean;
import com.hsap.huisianpu.bean.PerformanceBean;
import com.hsap.huisianpu.details.DetailsPerformance;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.SpUtils;
import com.hsap.huisianpu.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的邀请页面
 */

public class MineInviteActivity extends BaseBackActivity {
    private static final String TAG = "MineInviteActivity";
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.rlv_mine_invite)
    RecyclerView rlvMineInvite;
    private int year;
    private int month;
    private int day;
    private MyAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mine_invite;
    }

    @Override
    public void initView() {
        Calendar instance = Calendar.getInstance();
        year = instance.get(Calendar.YEAR);
        month = instance.get(Calendar.MONTH) + 1;
        day = instance.get(Calendar.DAY_OF_MONTH);
        rlvMineInvite.setLayoutManager(new LinearLayoutManager(MineInviteActivity.this));
    }

    @Override
    public void initData() {
        dataFromNet(year,month,day);

    }

    private void dataFromNet(final int year, final int month, final int day) {
        OkGo.<String>post(NetAddressUtils.selectByMyPutMonth).
                params("workerId", SpUtils.getInt(ConstantUtils.UserId,this)).
                params("year", year ).
                params("month",month).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        //Log.e(TAG, "onSuccess: "+response.body().toString() );
                        final PerformanceBean bean = new Gson().fromJson(response.body().toString(), PerformanceBean.class);
                             if (bean.getList()==null){
                                 adapter.setEmptyView(R.layout.emptyview);
                             }
                            if(adapter==null){
                                adapter = new MyAdapter(R.layout.item_work_performance,bean.getList());

                                rlvMineInvite.setAdapter(adapter);
                            }else {
                                adapter.setNewData(bean.getList());
                                adapter.notifyDataSetChanged();
                            }
                            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(MineInviteActivity.this, DetailsPerformance.class);
                                    intent.putExtra("workid",bean.getList().get(position).getWorkerId());
                                    intent.putExtra("id",bean.getList().get(position).getId());
                                    intent.putExtra("year",year);
                                    intent.putExtra("month",month);
                                    intent.putExtra("day",day);
                                    intent.putExtra("name",bean.getList().get(position).getName());
                                    intent.putExtra("size",bean.getList().get(position).getSize());
                                    if (bean.getList().get(position).getManagerScore()==-1){
                                        intent.putExtra("state",true);//经理能编写
                                    }else {
                                        intent.putExtra("state",false);
                                    }
                                    if(bean.getList().get(position).getM2Score()==-1){
                                        intent.putExtra("zong",true);//总监能编写
                                    }else {
                                        intent.putExtra("zong",false);
                                    }
                                    startActivity(intent);
                                }
                            });
                        }
                });
    }

    @Override
    public void initListener() {

        back.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {

    }
    class MyAdapter extends BaseQuickAdapter<PerformanceBean.ListBean,BaseViewHolder>{

        public MyAdapter(int layoutResId, @Nullable List<PerformanceBean.ListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, PerformanceBean.ListBean item) {
            if (item.getSize()==1){
                helper.getView(R.id.tv_work_performance_zhuangtai).setVisibility(View.GONE);
            }else {
                helper.getView(R.id.tv_work_performance_zhuangtai).setVisibility(View.VISIBLE);
            }
            Log.e(TAG, "convert: "+ item.getMyScore());
            helper.setText(R.id.tv_work_performance_name,"提交人："+item.getName())
                    .setText(R.id.tv_work_performance_myscore,"自评打分："+item.getMyScore())
                    .setText(R.id.tv_work_performance_zhuangtai,"经理打分："+choice(item.getManagerScore())).
                    setText(R.id.tv_work_performance_m2Score,"总监打分："+choice(item.getM2Score()))
                    .setText(R.id.tv_work_performance_time,"提交时间："+item.getCreateTime());
        }


    }

    private String choice(int managerScore) {
        if (managerScore==-1){
            return "尚未打分";
        }else {
            return managerScore+"";
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDate(EventDate event) {
        Log.e(TAG, "onEventDate: " + event.getYear());
        Log.e(TAG, "onEventDate: " + event.getMonth());
        Log.e(TAG, "onEventDate: " + event.getDay());
        dataFromNet(event.getYear(),event.getMonth(),event.getDay());
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
