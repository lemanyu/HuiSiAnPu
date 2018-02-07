package com.hsap.huisianpu.pager.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 我的出差
 */

public class MineTripPager extends BaseFragmentPager {
    private static final String TAG = "MineTripPager";
    @BindView(R.id.mine_rlv_trip)
    RecyclerView mineRlvTrip;
    Unbinder unbinder;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.pager_mine_trip, null);
        return view;
    }

    @Override
    public void initData() {
        mineRlvTrip.setLayoutManager(new LinearLayoutManager(mActivity));
        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH) + 1;
        dataFormNet(year,month);
    }

    @Override
    public void initListener() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    class MyAdapter extends BaseQuickAdapter<MineLeaveBean.DataBean, BaseViewHolder> {

        public MyAdapter(int layoutResId, @Nullable List<MineLeaveBean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MineLeaveBean.DataBean item) {
            helper.setText(R.id.mine_tv_time, "出差时间：" +item.getStartTime().replace("08:30","上午").replace("13:30","下午"));
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDate(EventDate event) {
        Log.e(TAG, "onEventDate: " + event.getYear());
        Log.e(TAG, "onEventDate: " + event.getMonth());
        dataFormNet(event.getYear(), event.getMonth());
    }

    private void dataFormNet(final int year, final int month) {
        OkGo.<String>post(NetAddressUtils.selectIntegration).
                params("workersId", SpUtils.getInt(ConstantUtils.UserId, mActivity)).
                params("type",2).params("year",year).params("month",month).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e(TAG, response.body().toString() );
                        final MineLeaveBean bean = new Gson().fromJson(response.body().toString(), MineLeaveBean.class);
                        if(bean.isSuccess()){
                            MyAdapter adapter = new MyAdapter(R.layout.item_mine_trip, bean.getData());
                            mineRlvTrip.setAdapter(adapter);
                            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(mActivity, DetailsMineTrip.class);
                                    intent.putExtra("type",2);
                                    intent.putExtra("workid",bean.getData().get(position).getId());
                                    intent.putExtra("flag",false);
                                    intent.putExtra("year",year);
                                    intent.putExtra("month",month);
                                    startActivity(intent);
                                }
                            });
                        }else {
                            ToastUtils.showToast(mActivity, "当前没有出差记录");
                        }
                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        ToastUtils.showToast(mActivity, "当前网络不好");
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
