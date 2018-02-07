package com.hsap.huisianpu.pager.work;

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
import com.hsap.huisianpu.bean.ApprovalBean;
import com.hsap.huisianpu.details.DetailsWorkApproval;
import com.hsap.huisianpu.details.DongDetailsWorkApproval;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.Calendar;
import java.util.List;

/**
 * Created by zhao on 2017/12/23.
 */

public class WorkManagerPager extends BaseFragmentPager {
    private static final String TAG = "WorkManagerPager";
    private int i;
    private int year;
    private int month;
    private RecyclerView rlvWorkHaveApproval;

    public WorkManagerPager setI(int i) {
        this.i = i;
        return this;
    }

    public WorkManagerPager setYearAndMonth(int year, int month) {
        if (this.year != year || this.month != month) {
            this.year = year;
            this.month = month;
            initData();
        }
        return this;
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.pager_work_have_approval, null);
        rlvWorkHaveApproval = view.findViewById(R.id.rlv_work_have_approval);
        rlvWorkHaveApproval.setLayoutManager(new LinearLayoutManager(mActivity));
        Log.d(TAG, "initView: " + rlvWorkHaveApproval);
        return view;
    }

    @Override
    public void initData() {
        Calendar calendar = Calendar.getInstance();
        if (year == 0 || year < 1000 || year > 3000) {
            year = calendar.get(Calendar.YEAR);
        }
        if (month == 0) {
            month = calendar.get(Calendar.MONTH) + 1;
        }
        Log.d(TAG, "initData: ");
        OkGo.<String>post(NetAddressUtils.getAuditList)
                .params("type", i)
                .params("opinion",1)
                .params("year", year)
                .params("month", month)
                .params("no",4)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d(TAG, "onSuccess: " + response.body());
                        ApprovalBean bean = new Gson().fromJson(response.body(), ApprovalBean.class);
                        MyAdapter myAdapter;
                        if (rlvWorkHaveApproval.getAdapter() == null) {
                            Log.d(TAG, "onSuccess: setAdapter");
                            myAdapter = new MyAdapter(R.layout.item_work_approval, bean.getData());
                            rlvWorkHaveApproval.setAdapter(myAdapter);

                            myAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Log.d(TAG, "onItemClick: " + adapter.getData());

                                    ApprovalBean.DataBean bean = (ApprovalBean.DataBean) adapter.getData().get(position);
                                    Log.d(TAG, "onItemClick: " + bean);
                                    Intent intent = new Intent(mActivity, DongDetailsWorkApproval.class);
                                    intent.putExtra("name", bean.getName());
                                    intent.putExtra("type", bean.getType());
                                    intent.putExtra("projectId", bean.getProjectId());
                                    intent.putExtra("opinion", bean.getOpinion());
                                    startActivity(intent);
                                }
                            });
                        } else {
                            myAdapter = (WorkManagerPager.MyAdapter) rlvWorkHaveApproval.getAdapter();
                            myAdapter.setNewData(bean.getData());
                            myAdapter.notifyDataSetChanged();
                        }
                    }
                });
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
            Log.d(TAG, "convert: " + item);
            helper.getView(R.id.tv_work_approval_zhuangtai).setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_work_approval_name, "申请人：" + item.getName())
                    .setText(R.id.tv_work_approval_type, "申请类型：" + item.getTypeName())
                    .setText(R.id.tv_work_approval_zhuangtai, "审批状态：" + choice(item.getOpinion()))
                    .setText(R.id.tv_work_approval_time, "审批时间：" +
                            item.getCreateTime().getYear() + "-" + item.getCreateTime().getMonthValue()
                            + "-" + item.getCreateTime().getDayOfMonth());

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
    }
}
