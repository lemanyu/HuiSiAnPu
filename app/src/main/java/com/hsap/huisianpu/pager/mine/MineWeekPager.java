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
import butterknife.Unbinder;

/**
 * 我的月报
 */

public class MineWeekPager extends BaseFragmentPager {
    @BindView(R.id.mine_rlv_week)
    RecyclerView mineRlvWeek;
    Unbinder unbinder;
    private MyAdapter adapter;
    private WorkNameBean bean;
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.pager_mine_week, null);
        return view;
    }

    @Override
    public void initData() {
        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH)+1;
        int day = instance.get(Calendar.DAY_OF_MONTH);
        mineRlvWeek.setLayoutManager(new LinearLayoutManager(mActivity));
        dataFromNet(year, month, day);
    }

    @Override
    public void initListener() {

    }
    private void dataFromNet(int year, int month, int day) {
        OkGo.<String>post(NetAddressUtils.getMyReportForms).
                params("id", SpUtils.getInt(ConstantUtils.UserId,mActivity)).
                params("type",4).
                params("date",year+"-"+month+"-"+day).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        bean = new Gson().fromJson(response.body().toString(), WorkNameBean.class);

                        if (adapter == null) {
                            adapter = new MyAdapter(R.layout.item_work_week, bean.getData().getList());
                            mineRlvWeek.setAdapter(adapter);
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
                                intent.putExtra("id",bean.getData().getList().get(position).getReportForm().getId());
                                intent.putExtra("type",1);
                                intent.putExtra("style",1);//可抄送
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
            helper.getView(R.id.tv_work_name).setVisibility(View.GONE);
            helper.setText(R.id.tv_finish_name,"本周完成："+item.getReportForm().getFinishWork()+"")
                    .setText(R.id.tv_summary_name,"本周总结："+item.getReportForm().getSummary()+"")
                    .setText(R.id.tv_next_name,"下周计划："+item.getReportForm().getWorkPlay()+"")
                    .setText(R.id.tv_coord_name,"需协调帮助："+item.getReportForm().getCoordinateWork())
                    .setText(R.id.tv_create_time,"提交时间："+item.getReportForm().getCreateTime().getYear()+"-"+
                            item.getReportForm().getCreateTime().getMonthValue()+"-"+item.getReportForm().getCreateTime().getDayOfMonth());
        }


    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDate(EventDate event){
        dataFromNet(event.getYear(), event.getMonth(), event.getDay());
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
