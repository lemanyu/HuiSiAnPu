package com.hsap.huisianpu.pager.work;

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
import com.hsap.huisianpu.bean.WorkNameBean;
import com.hsap.huisianpu.details.DetailsWorkTime;
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
 * Created by zhao on 2017/11/23.
 */

public class WorkWeekPager extends BaseFragmentPager {
    @BindView(R.id.work_rlv_week)
    RecyclerView workRlvWeek;
    Unbinder unbinder;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.pager_work_week, null);
        return view;
    }

    @Override
    public void initData() {
        OkGo.<String>post(NetAddressUtils.getWorkersList).
                params("id", SpUtils.getInt(ConstantUtils.UserId,mActivity)).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                final WorkNameBean bean = new Gson().fromJson(response.body().toString(), WorkNameBean.class);
                workRlvWeek.setLayoutManager(new LinearLayoutManager(mActivity));
                MyAdapter adapter = new MyAdapter(R.layout.item_work_name, bean.getData());
                workRlvWeek.setAdapter(adapter);
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent intent = new Intent(mActivity, DetailsWorkTime.class);
                        intent.putExtra("type",1);
                        intent.putExtra("id",bean.getData().get(position).getId());
                        startActivity(intent);
                    }
                });

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
    class MyAdapter extends BaseQuickAdapter<WorkNameBean.DataBean,BaseViewHolder> {


        public MyAdapter(int layoutResId, @Nullable List<WorkNameBean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, WorkNameBean.DataBean item) {
            helper.setText(R.id.tv_work_name,item.getName());
        }
    }
}
