package com.hsap.huisianpu.pager.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseFragmentPager;
import com.hsap.huisianpu.bean.MineLeaveBean;
import com.hsap.huisianpu.details.DetailsMineTrip;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.SpUtils;
import com.hsap.huisianpu.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 我的用车
 */

public class MineCarPager extends BaseFragmentPager {
    @BindView(R.id.mine_rlv_car)
    RecyclerView mineRlvCar;
    Unbinder unbinder;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.pager_mine_car, null);
        return view;
    }

    @Override
    public void initData() {
        OkGo.<String>post(NetAddressUtils.selectIntegration).
                params("workersId", SpUtils.getInt(ConstantUtils.UserId, mActivity)).
                params("type",4).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        final MineLeaveBean bean = new Gson().fromJson(response.body().toString(), MineLeaveBean.class);
                        if(bean.isSuccess()){
                            mineRlvCar.setLayoutManager(new LinearLayoutManager(mActivity));
                           MyAdapter adapter = new MyAdapter(R.layout.item_mine_trip, bean.getData());
                            mineRlvCar.setAdapter(adapter);
                            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(mActivity, DetailsMineTrip.class);
                                    intent.putExtra("type",4);
                                    intent.putExtra("workid",bean.getData().get(position).getId());
                                    startActivity(intent);
                                }
                            });
                        }else {
                            ToastUtils.showToast(mActivity,"当前没有请假数据");
                        }
                    }
                });
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
            helper.setText(R.id.mine_tv_time, "用车时间：" +
                    item.getStartTime());
        }

    }
}
