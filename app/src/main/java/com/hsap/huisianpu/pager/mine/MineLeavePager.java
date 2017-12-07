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
import com.hsap.huisianpu.bean.WorkLeaveBean;
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
 * 我的请假
 */

public class MineLeavePager extends BaseFragmentPager {
    private static final String TAG = "MineLeavePager";
    @BindView(R.id.mine_rlv_leave)
    RecyclerView mineRlvLeave;
    Unbinder unbinder;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.pager_mine_leave, null);
        return view;
    }

    @Override
    public void initData() {
        OkGo.<String>post(NetAddressUtils.getMyAttendance).
                params("id", SpUtils.getInt(ConstantUtils.UserId, mActivity)).
                params("type",0).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        final WorkLeaveBean bean = new Gson().fromJson(response.body().toString(), WorkLeaveBean.class);
                        if(bean.isSuccess()){
                            mineRlvLeave.setLayoutManager(new LinearLayoutManager(mActivity));
                            MyAdapter adapter = new MyAdapter(R.layout.item_mine_trip, bean.getData());
                            mineRlvLeave.setAdapter(adapter);
                            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(mActivity, DetailsMineTrip.class);
                                    intent.putExtra("type",0);
                                    intent.putExtra("workid",bean.getData().get(position).getTypeId());
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
    class MyAdapter extends BaseQuickAdapter<WorkLeaveBean.DataBean, BaseViewHolder> {

        public MyAdapter(int layoutResId, @Nullable List<WorkLeaveBean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, WorkLeaveBean.DataBean item) {
            helper.setText(R.id.mine_tv_time, "出差时间：" +
                    item.getCreateTime().getYear() + "-" +
                    item.getCreateTime().getMonthValue() + "-" +
                    item.getCreateTime().getDayOfMonth());

        }
    }
}
