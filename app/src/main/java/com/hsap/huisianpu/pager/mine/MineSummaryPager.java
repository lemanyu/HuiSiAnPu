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
import com.hsap.huisianpu.bean.MineLeaveBean;
import com.hsap.huisianpu.bean.MineSummaryBean;
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
    private static String TAG="MineSummaryPager";
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
        rlvSummary.setLayoutManager(new LinearLayoutManager(mActivity));
        dataFormNet(year,month);
    }

    @Override
    public void initListener() {

    }
    class MyAdapter extends BaseQuickAdapter<MineSummaryBean.ListBean, BaseViewHolder> {

        public MyAdapter(int layoutResId, @Nullable List<MineSummaryBean.ListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MineSummaryBean.ListBean item) {
            helper.setText(R.id.mine_tv_time, "总结时间：" +
                    item.getDate());
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDate(EventDate event) {

        dataFormNet(event.getYear(), event.getMonth());
    }

    private void dataFormNet(final int year, final int month) {
        OkGo.<String>post(NetAddressUtils.queryTTBSummarAll).
                params("workersId", SpUtils.getInt(ConstantUtils.UserId, mActivity)).
                params("year", year).
                params("month", month).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e(TAG, "onSuccess: "+response.body().toString() );
                        final MineSummaryBean bean = new Gson().fromJson(response.body().toString(), MineSummaryBean.class);

                            if (adapter == null) {
                                adapter = new MyAdapter(R.layout.item_mine_trip, bean.getList());
                                rlvSummary.setAdapter(adapter);
                            } else {
                                adapter.setNewData(bean.getList());
                                adapter.notifyDataSetChanged();
                            }
                            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(mActivity, DetailsMineTrip.class);
                                    intent.putExtra("type", 6);
                                    intent.putExtra("workid", bean.getList().get(position).getId());
                                    intent.putExtra("year",year);
                                    intent.putExtra("flag",false);
                                    intent.putExtra("month",month);
                                    startActivity(intent);
                                }
                            });

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
