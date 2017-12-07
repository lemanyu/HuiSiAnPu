package com.hsap.huisianpu.pager.work;

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
import com.hsap.huisianpu.bean.ApprovalBean;
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
 * 我已审批
 */

public class WorkHaveApprovalPager extends BaseFragmentPager {
    private static final String TAG = "WorkHaveApprovalPager";
    @BindView(R.id.rlv_work_have_approval)
    RecyclerView rlvWorkHaveApproval;
    Unbinder unbinder;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.pager_work_have_approval, null);
        return view;
    }

    @Override
    public void initData() {
        OkGo.<String>post(NetAddressUtils.getAuditList).
                params("managerId", SpUtils.getInt(ConstantUtils.UserId, mActivity)).
                params("opinion", -1).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        final ApprovalBean bean = new Gson().fromJson(response.body().toString(), ApprovalBean.class);
                        if (bean.isSuccess()){
                            rlvWorkHaveApproval.setLayoutManager(new LinearLayoutManager(mActivity));
                            MyAdapter adapter = new MyAdapter(R.layout.item_work_approval, bean.getData());
                            rlvWorkHaveApproval.setAdapter(adapter);
                            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                                }
                            });
                        }else {
                            ToastUtils.showToast(mActivity,"当前没有审批记录");
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        ToastUtils.showToast(mActivity,"当前网络不好");
                    }
                });
    }

    @Override
    public void initListener() {

    }
  class MyAdapter extends BaseQuickAdapter<ApprovalBean.DataBean,BaseViewHolder>{

      public MyAdapter(int layoutResId, @Nullable List<ApprovalBean.DataBean> data) {
          super(layoutResId, data);
      }

      @Override
      protected void convert(BaseViewHolder helper, ApprovalBean.DataBean item) {
           helper.setText(R.id.tv_work_approval_name,"申请人："+item.getName())
           .setText(R.id.tv_work_approval_type,"申请类型："+item.getTypeName())
           .setText(R.id.tv_mine_invite_time,"申请时间："+
                   item.getCreateTime().getYear()+"-"+item.getCreateTime().getMonthValue()
                   +"-"+item.getCreateTime().getDayOfMonth());

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
