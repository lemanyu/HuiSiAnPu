package com.hsap.huisianpu.pager.mine;

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
import com.hsap.huisianpu.bean.MinePerformanceBean;
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
 * 我的绩效
 */

public class MinePerformancePager extends BaseFragmentPager {
    @BindView(R.id.rlv_mine_performance)
    RecyclerView rlvMinePerformance;
    Unbinder unbinder;
   private static String TAG="MinePerformancePager";
    private int year;
    private MyAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.pager_mine_performance, null);
        return view;
    }

    @Override
    public void initData() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        dataFormNet(year, month, day);
    }

    @Override
    public void initListener() {

    }
    class MyAdapter extends BaseQuickAdapter<MinePerformanceBean.DataBean.InfoBean,BaseViewHolder> {

        public MyAdapter(int layoutResId, @Nullable List<MinePerformanceBean.DataBean.InfoBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MinePerformanceBean.DataBean.InfoBean item) {
            Log.e(TAG, "convert: "+ item.getMyScore());
            helper.getView(R.id.tv_work_performance_name).setVisibility(View.GONE);
            helper.setText(R.id.tv_work_performance_myscore,"自评打分："+item.getMyScore())
                    .setText(R.id.tv_work_performance_zhuangtai,"经理打分："+choice(item.getManagerScore()))
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
        dataFormNet(event.getYear(),event.getMonth(),event.getDay());
    }

    private void dataFormNet(final int year, final int month, final int day) {
        OkGo.<String>post(NetAddressUtils.selectMySumScoreMonth).
                params("workerId", SpUtils.getInt(ConstantUtils.UserId,mActivity)).
                params("year",year).
                execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.e(TAG, "onSuccess: "+response.body().toString() );
                final MinePerformanceBean bean = new Gson().fromJson(response.body().toString(), MinePerformanceBean.class);
                if (bean.isSuccess()){
                    if(adapter ==null){
                        adapter =new MyAdapter(R.layout.item_work_performance,bean.getData().getInfo());
                        rlvMinePerformance.setLayoutManager(new LinearLayoutManager(mActivity));
                        rlvMinePerformance.setAdapter(adapter);
                    }else {
                        adapter.setNewData(bean.getData().getInfo());
                        adapter.notifyDataSetChanged();
                    }
                    adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            Intent intent = new Intent(mActivity, DetailsPerformance.class);
                            intent.putExtra("id",bean.getData().getInfo().get(position).getId());
                            intent.putExtra("year",year);
                            intent.putExtra("month",month);
                            intent.putExtra("day",day);
                            if (bean.getData().getInfo().get(position).getManagerScore()==-1){
                                intent.putExtra("style",1);
                            }else {
                                intent.putExtra("style",0);
                            }

                            intent.putExtra("state",false);//能编写
                            startActivity(intent);
                        }
                    });
                }
            }
        });
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
