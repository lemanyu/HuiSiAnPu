package com.hsap.huisianpu.pager.work;

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
import com.hsap.huisianpu.bean.ApprovalBean;
import com.hsap.huisianpu.bean.EventDate;
import com.hsap.huisianpu.bean.FalseBean;
import com.hsap.huisianpu.details.DetailsWorkApproval;
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
 * 我已审批
 */

public class WorkHaveApprovalPager extends BaseFragmentPager {
    private static final String TAG = "WorkHaveApprovalPager";
    @BindView(R.id.rlv_work_have_approval)
    RecyclerView rlvWorkHaveApproval;
    Unbinder unbinder;
    private MyAdapter adapter;
    private int year;
    private int month;


    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.pager_work_have_approval, null);
        return view;
    }

    @Override
    public void initData() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        rlvWorkHaveApproval.setLayoutManager(new LinearLayoutManager(mActivity));
        dataFromNet(year, month);

    }

    @Override
    public void initListener() {

    }

    class MyAdapter extends BaseQuickAdapter<ApprovalBean.DataBean, BaseViewHolder> {

        public MyAdapter(int layoutResId, @Nullable List<ApprovalBean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ApprovalBean.DataBean item) {
            helper.getView(R.id.tv_work_approval_zhuangtai).setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_work_approval_name, "申请人：" + item.getName())
                    .setText(R.id.tv_work_approval_type, "申请类型：" + item.getTypeName())
                    .setText(R.id.tv_work_approval_zhuangtai, "审批状态：" + choice(item.getOpinion()))
                    .setText(R.id.tv_work_approval_time, "审批时间：" +
                            item.getCreateTime().getYear() + "-" + item.getCreateTime().getMonthValue()
                            + "-" + item.getCreateTime().getDayOfMonth());

        }
    }

    private String choice(int opinion) {
        String s = "";
        switch (opinion) {
            case 1:
                s += "已同意";
                break;
            case 2:
                s += "已拒绝";
                break;
            default:
        }
        return s;
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDate(EventDate event) {
        Log.e(TAG, "onEventDate: " + event.getYear());
        Log.e(TAG, "onEventDate: " + event.getMonth());
        dataFromNet(event.getYear(), event.getMonth());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFalse(FalseBean event) {
        dataFromNet(year, month);
    }

    private void dataFromNet(int year, int month) {
        OkGo.<String>post(NetAddressUtils.getAuditList).
                params("managerId", SpUtils.getInt(ConstantUtils.UserId, mActivity)).
                params("opinion", -1).
                params("year", year).params("month", month).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        final ApprovalBean bean = new Gson().fromJson(response.body().toString(), ApprovalBean.class);
                        if (bean.isSuccess()) {

                            if (adapter == null) {
                                adapter = new MyAdapter(R.layout.item_work_approval, bean.getData());
                                rlvWorkHaveApproval.setAdapter(adapter);
                            } else {
                                adapter.setNewData(bean.getData());
                                adapter.notifyDataSetChanged();

                            }

                            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(mActivity, DetailsWorkApproval.class);
                                    intent.putExtra("name", bean.getData().get(position).getName());
                                    intent.putExtra("type", bean.getData().get(position).getType());
                                    intent.putExtra("projectId", bean.getData().get(position).getProjectId());
                                    intent.putExtra("opinion", bean.getData().get(position).getOpinion());

                                    startActivityForResult(intent, 1024);
                                }
                            });
                        } else {
                            ToastUtils.showToast(mActivity, "当前没有审批记录");
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
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
