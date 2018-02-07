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
import com.hsap.huisianpu.bean.WorkNameBean;
import com.hsap.huisianpu.details.DetailsMineDay;
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
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zhao on 2017/12/13.
 */

public class MineCCPager extends BaseFragmentPager {
    private static final String TAG = "MineCCPager";
    @BindView(R.id.mine_rlv_cc)
    RecyclerView mineRlvCc;
    Unbinder unbinder;
    private MyAdapter adapter;
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.pager_mine_cc, null);
        return view;
    }

    @Override
    public void initData() {
        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH) + 1;
        int day = instance.get(Calendar.DAY_OF_MONTH);
        mineRlvCc.setLayoutManager(new LinearLayoutManager(mActivity));
        dataFromNet(year, month, day);
    }

    @Override
    public void initListener() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDate(EventDate event) {
        dataFromNet(event.getYear(), event.getMonth(), event.getDay());
    }

    private void dataFromNet(int year, int month, int day) {
        Log.e(TAG, "dataFromNet: "+year+month+day );
        OkGo.<String>post(NetAddressUtils.getMyReportForms).
                params("id", SpUtils.getInt(ConstantUtils.UserId, mActivity)).
                params("type", 6).
                params("date", year + "-" + month + "-" + day).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        Log.e(TAG, "onSuccess: " + response.body().toString());
                        final WorkNameBean bean = new Gson().fromJson(response.body().toString(), WorkNameBean.class);

                        if (adapter == null) {
                            adapter = new MyAdapter(R.layout.item_work_week, bean.getData().getList());
                            mineRlvCc.setAdapter(adapter);
                        } else {
                            if (bean.getData().getList()!=null&&bean.getData().getList().size()!=0){
                                adapter.setNewData(bean.getData().getList());
                                adapter.notifyDataSetChanged();
                            }
                        }
                        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                Intent intent = new Intent(mActivity, DetailsMineDay.class);
                                intent.putExtra("id", bean.getData().getList().get(position).getReportForm().getId());
                                intent.putExtra("type", bean.getData().getList().get(position).getReportForm().getType());
                                intent.putExtra("style", 1);//可抄送
                                startActivity(intent);

                            }
                        });
                    }
                });
    }
    class MyAdapter extends BaseQuickAdapter<WorkNameBean.DataBean.ListBean,BaseViewHolder> {


        public MyAdapter(int layoutResId, @Nullable List<WorkNameBean.DataBean.ListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, WorkNameBean.DataBean.ListBean item) {
            choice(item.getReportForm().getType());
            helper.getView(R.id.tv_summary_name).setVisibility(View.GONE);
            helper.getView(R.id.tv_next_name).setVisibility(View.GONE);
            helper.getView(R.id.tv_coord_name).setVisibility(View.GONE);
            helper.setText(R.id.tv_work_name,"抄送人："+item.getName()).
                    setText(R.id.tv_finish_name,"抄送类型："+choice(item.getReportForm().getType())).
                    setText(R.id.tv_create_time,"抄送时间："+item.getReportForm().getCreateTime().getYear()+"-"+
                            item.getReportForm().getCreateTime().getMonthValue()+"-"+item.getReportForm().getCreateTime().getDayOfMonth());
        }


    }

    private String choice(int type) {
        String s="";
        switch (type){
            case 0:
               s+="日报";
               break;
            case 1:
                s+="周报";
                break;
            case 2:
                s+="月报";
                break;
               default:
        }
        return s;
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
