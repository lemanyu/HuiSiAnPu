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
import com.hsap.huisianpu.bean.MineDayBean;
import com.hsap.huisianpu.details.DetailsMineDay;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.SpUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 我的周报
 */

public class MineMonthPager extends BaseFragmentPager {
    @BindView(R.id.mine_rlv_month)
    RecyclerView mineRlvMonth;
    Unbinder unbinder;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.pager_mine_month, null);
        return view;
    }

    @Override
    public void initData() {

    }
    private void dataFormNet() {
        OkGo.<String>post(NetAddressUtils.getMyReportForms).
                params("id", SpUtils.getInt(ConstantUtils.UserId,mActivity)).
                params("type",2).
                params("page",0).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        final MineDayBean bean = new Gson().fromJson(response.body().toString(), MineDayBean.class);
                        mineRlvMonth.setLayoutManager(new LinearLayoutManager(mActivity));
                        MyAdapter adapter = new MyAdapter(R.layout.item_mine_day, bean.getData().getList());
                        mineRlvMonth.setAdapter(adapter);
                        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                Intent intent = new Intent(mActivity, DetailsMineDay.class);
                                intent.putExtra("type",0);
                                intent.putExtra("finishwork",bean.getData().getList().get(position).getFinishWork().toString());
                                intent.putExtra("summary",bean.getData().getList().get(position).getSummary());
                                intent.putExtra("workplay",bean.getData().getList().get(position).getWorkPlay());
                                intent.putExtra("coordinatecork",bean.getData().getList().get(position).getCoordinateWork());
                                startActivity(intent);
                            }
                        });
                    }
                });
    }
    @Override
    public void initListener() {

    }
    class MyAdapter extends BaseQuickAdapter<MineDayBean.DataBean.ListBean,BaseViewHolder> {


        public MyAdapter(int layoutResId, @Nullable List<MineDayBean.DataBean.ListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MineDayBean.DataBean.ListBean item) {
            helper.setText(R.id.tv_time,
                    item.getCreateTime().getYear()+"-"+
                            item.getCreateTime().getMonthValue()+"-"+
                            item.getCreateTime().getDayOfMonth());
        }
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
}
