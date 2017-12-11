package com.hsap.huisianpu.pager.mine;

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
import com.hsap.huisianpu.bean.OngoingBean;
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
 * 正在进行审批
 */

public class MineOngoingPager extends BaseFragmentPager {
    @BindView(R.id.mRlvOngoing)
    RecyclerView mRlvOngoing;
    Unbinder unbinder;
   private static final String TAG="MineOngoingPager";
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.pager_mine_ongoing, null);
        return view;
    }

    @Override
    public void initData() {
        OkGo.<String>post(NetAddressUtils.selectIntegration).
                params("workersId", SpUtils.getInt(ConstantUtils.UserId,mActivity)).
                params("state",0).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e(TAG, response.body().toString());
                        OngoingBean bean = new Gson().fromJson(response.body().toString(), OngoingBean.class);
                        if (bean.isSuccess()){
                            mRlvOngoing.setLayoutManager(new LinearLayoutManager(mActivity));
                            MyAdapter adapter = new MyAdapter(R.layout.item_work_approval, bean.getData());
                            mRlvOngoing.setAdapter(adapter);
                            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

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
        }
        return title;
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
