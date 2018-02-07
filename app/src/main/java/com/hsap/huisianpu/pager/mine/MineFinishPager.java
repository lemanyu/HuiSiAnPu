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
import com.hsap.huisianpu.bean.OngoingBean;
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
 * 审批完成
 */

public class MineFinishPager extends BaseFragmentPager {
    @BindView(R.id.rlv_mine_finish)
    RecyclerView rlvMineFinish;
    Unbinder unbinder;
    private MyAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.pager_mine_finish, null);
        return view;
    }

    @Override
    public void initData() {
        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH)+1;
        rlvMineFinish.setLayoutManager(new LinearLayoutManager(mActivity));
        dataFormNet(year,month);
    }

    private void dataFormNet(final int year, final int month) {
        OkGo.<String>post(NetAddressUtils.selectIntegration).
                params("workersId", SpUtils.getInt(ConstantUtils.UserId,mActivity)).
                params("state",3).params("year",year).params("month",month).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        final OngoingBean bean = new Gson().fromJson(response.body().toString(), OngoingBean.class);
                        if (bean.isSuccess()){

                            if(adapter==null){
                                adapter = new MyAdapter(R.layout.item_work_approval, bean.getData());
                                rlvMineFinish.setAdapter(adapter);
                            }else {
                                adapter.setNewData(bean.getData());
                                adapter.notifyDataSetChanged();
                            }

                            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(mActivity, DetailsMineTrip.class);
                                    intent.putExtra("type",bean.getData().get(position).getType());
                                    intent.putExtra("workid",bean.getData().get(position).getId());
                                    intent.putExtra("state",3);
                                    intent.putExtra("year",year);
                                    intent.putExtra("month",month);
                                    startActivity(intent);
                                }
                            });
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        ToastUtils.showToast(mActivity,"当前无网络");
                    }
                });
    }

    @Override
    public void initListener() {

    }
    class MyAdapter extends BaseQuickAdapter<OngoingBean.DataBean, BaseViewHolder> {

        public MyAdapter(int layoutResId, @Nullable List<OngoingBean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, OngoingBean.DataBean item) {
            helper.getView(R.id.tv_work_approval_name).setVisibility(View.GONE);
            helper.setText(R.id.tv_work_approval_type, "申请类型：" + getTitleType(item.getType()))
                    .setText(R.id.tv_work_approval_time, "申请时间：" +
                            item.getStartTime());
        }

    }
    public String getTitleType(Integer type) {
        String title = "";
        switch (type) {
            case 0:
                title += "请假";
                break;
            case 1:
                title += "外出";
                break;
            case 2:
                title += "出差";
                break;
            case 3:
                title += "加班";
                break;
            case 4:
                title += "用车";
                break;
            case 5:
                title += "项目";
                break;
            case 6:
                title += "出差总结";
                break;
            case 12:
                title+="采购";
                break;
            default:
        }
        return title;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDate(EventDate event){

        dataFormNet(event.getYear(), event.getMonth());
    }

}
