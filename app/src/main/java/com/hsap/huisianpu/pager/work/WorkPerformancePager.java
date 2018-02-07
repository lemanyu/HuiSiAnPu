package com.hsap.huisianpu.pager.work;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseFragmentPager;
import com.hsap.huisianpu.bean.EventDate;
import com.hsap.huisianpu.bean.JiXiaoBean;
import com.hsap.huisianpu.bean.PerformanceBean;
import com.hsap.huisianpu.details.DetailsPerformance;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.SpUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * 查看的季度考核
 */

public class WorkPerformancePager extends BaseFragmentPager {
    @BindView(R.id.rlv_work_performance)
    RecyclerView rlvWorkPerformance;
    Unbinder unbinder;
    private static String TAG="WorkPerformancePager";
     private MyAdapter adapter;
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.pager_work_performance, null);
        return view;
    }

    @Override
    public void initData() {
        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH) + 1;
        int day = instance.get(Calendar.DAY_OF_MONTH);
        rlvWorkPerformance.setLayoutManager(new LinearLayoutManager(mActivity));
        dataFromNet(year,month,day);

    }
    private void dataFromNet(final int year, final int month, final int day) {
        OkGo.<String>post(NetAddressUtils.selectByMyDepartmentSumScoreMonth).
                params("id", SpUtils.getInt(ConstantUtils.UserId,mActivity)).
                params("year", year ).
                params("month",month).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e(TAG, "onSuccess: "+response.body().toString() );
                        final JiXiaoBean bean = new Gson().fromJson(response.body().toString(), JiXiaoBean.class);
                            if(adapter==null){
                                adapter=new MyAdapter(R.layout.item_work_performance,bean.getData().getList());
                                rlvWorkPerformance.setAdapter(adapter);
                            }else {
                                adapter.setNewData(bean.getData().getList());
                                adapter.notifyDataSetChanged();
                            }
                            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(mActivity, DetailsPerformance.class);
                                    intent.putExtra("workid",bean.getData().getList().get(position).getWorkerId());
                                    intent.putExtra("id",bean.getData().getList().get(position).getId());
                                    intent.putExtra("year",year);
                                    intent.putExtra("month",month);
                                    intent.putExtra("day",day);
                                    intent.putExtra("size",bean.getData().getList().get(position).getSize());
                                    intent.putExtra("name",bean.getData().getList().get(position).getName());
                                    if(bean.getData().getList().get(position).getM2Score()==-1){
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

    }
 class MyAdapter extends BaseQuickAdapter<JiXiaoBean.DataBean.ListBean,BaseViewHolder>{

     public MyAdapter(int layoutResId, @Nullable List<JiXiaoBean.DataBean.ListBean> data) {
         super(layoutResId, data);
     }

     @Override
     protected void convert(BaseViewHolder helper, JiXiaoBean.DataBean.ListBean item) {
         if (item.getSize()==1){
             helper.getView(R.id.tv_work_performance_zhuangtai).setVisibility(View.GONE);
         }else {
             helper.getView(R.id.tv_work_performance_zhuangtai).setVisibility(View.VISIBLE);
         }
         Log.e(TAG, "convert: "+ item.getMyScore());
         helper.setText(R.id.tv_work_performance_name,"提交人："+item.getName())
                 .setText(R.id.tv_work_performance_myscore,"自评打分："+item.getMyScore())
                 .setText(R.id.tv_work_performance_zhuangtai,"经理打分："+choice(item.getManagerScore()))
                 .setText(R.id.tv_work_performance_m2Score,"总监打分："+choice(item.getM2Score()))
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
