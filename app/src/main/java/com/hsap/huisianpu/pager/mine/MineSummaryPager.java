package com.hsap.huisianpu.pager.mine;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseFragmentPager;
import com.hsap.huisianpu.bean.EventDate;
import com.hsap.huisianpu.bean.MineLeaveBean;
import com.hsap.huisianpu.details.DetailsMineTrip;
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
import butterknife.Unbinder;

/**
 * 我的出差总结
 */

public class MineSummaryPager extends BaseFragmentPager {
    @BindView(R.id.rlv_summary)
    RecyclerView rlvSummary;
    Unbinder unbinder;
    private MyAdapter adapter;
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.pager_mine_summary, null);
        return view;
    }

    @Override
    public void initData() {
        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH) + 1;
        dataFormNet(year,month);
    }

    @Override
    public void initListener() {

    }
    class MyAdapter extends BaseQuickAdapter<MineLeaveBean.DataBean, BaseViewHolder> {

        public MyAdapter(int layoutResId, @Nullable List<MineLeaveBean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MineLeaveBean.DataBean item) {
            helper.setText(R.id.mine_tv_time, "总结时间：" +
                    item.getStartTime());
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDate(EventDate event) {

        dataFormNet(event.getYear(), event.getMonth());
    }

    private void dataFormNet(int year, int month) {
        OkGo.<String>post(NetAddressUtils.selectIntegration).
                params("workersId", SpUtils.getInt(ConstantUtils.UserId, mActivity)).
                params("type", 6).params("year", year).params("month", month).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        final MineLeaveBean bean = new Gson().fromJson(response.body().toString(), MineLeaveBean.class);
                        if (bean.isSuccess()) {
                            rlvSummary.setLayoutManager(new LinearLayoutManager(mActivity));
                            if (adapter == null) {
                                adapter = new MyAdapter(R.layout.item_mine_trip, bean.getData());
                                rlvSummary.setAdapter(adapter);
                            } else {
                                adapter.setNewData(bean.getData());
                                adapter.notifyDataSetChanged();
                            }
                            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(mActivity, DetailsMineTrip.class);
                                    intent.putExtra("type", 1);
                                    intent.putExtra("workid", bean.getData().get(position).getId());
                                    startActivity(intent);
                                }
                            });
                        } else {
                            ToastUtils.showToast(mActivity, "当前没有请假数据");
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
