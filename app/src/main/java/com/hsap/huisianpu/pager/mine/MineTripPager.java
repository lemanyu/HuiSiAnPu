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
import com.hsap.huisianpu.bean.WorkTripBean;
import com.hsap.huisianpu.details.DetailsMineTrip;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.SpUtils;
import com.hsap.huisianpu.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 我的出差
 */

public class MineTripPager extends BaseFragmentPager {
    private static final String TAG = "MineTripPager";
    @BindView(R.id.mine_rlv_trip)
    RecyclerView mineRlvTrip;
    Unbinder unbinder;
     private StringBuilder begin=new StringBuilder();
     private StringBuilder end=new StringBuilder();
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.pager_mine_trip, null);
        return view;
    }

    @Override
    public void initData() {
        OkGo.<String>post(NetAddressUtils.getMyBusinessTrips).
                params("id", SpUtils.getInt(ConstantUtils.UserId, mActivity)).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        final WorkTripBean bean = new Gson().fromJson(response.body().toString(), WorkTripBean.class);
                        if (bean.isSuccess()) {
                            mineRlvTrip.setLayoutManager(new LinearLayoutManager(mActivity));
                            MyAdapter adapter = new MyAdapter(R.layout.item_mine_trip, bean.getData());
                            mineRlvTrip.setAdapter(adapter);
                            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    if(bean.getData().get(position).getWaTravelBusiness().getDepartureTime().getHour()==8){
                                        begin.setLength(0);
                                        begin.append("上午");
                                    }else {
                                        begin.setLength(0);
                                        begin.append("下午");
                                    }
                                    if(bean.getData().get(position).getWaTravelBusiness().getDepartureTime().getHour()==13){
                                        end.setLength(0);
                                        end.append("上午");
                                    }else {
                                       end.setLength(0);
                                        end.append("下午");
                                    }
                                    Intent intent = new Intent(mActivity, DetailsMineTrip.class);
                                    intent.putExtra("reason",bean.getData().get(position).getWaTravelBusiness().getReason());
                                    intent.putExtra("city",bean.getData().get(position).getWaTravelBusiness().getPlace());
                                    intent.putExtra("begin",
                                            bean.getData().get(position).getWaTravelBusiness().getDepartureTime().getYear() + "-" +
                                                    bean.getData().get(position).getWaTravelBusiness().getDepartureTime().getMonthValue() + "-" +
                                                    bean.getData().get(position).getWaTravelBusiness().getDepartureTime().getDayOfMonth()+" "+begin);
                                    intent.putExtra("end",bean.getData().get(position).getWaTravelBusiness().getReturnTime().getYear() + "-" +
                                            bean.getData().get(position).getWaTravelBusiness().getReturnTime().getMonthValue() + "-" +
                                            bean.getData().get(position).getWaTravelBusiness().getReturnTime().getDayOfMonth()+" "+end);
                                    intent.putExtra("total",bean.getData().get(position).getWaTravelBusiness().getTotal());
                                    intent.putExtra("comment",bean.getData().get(position).getWaTravelBusiness().getComment());
                                    intent.putStringArrayListExtra("accompanying", (ArrayList<String>) bean.getData().get(position).getAccompanying());
                                    intent.putStringArrayListExtra("verifier", (ArrayList<String>) bean.getData().get(position).getVerifier());
                                    startActivity(intent);

                                }
                            });
                        } else {
                            ToastUtils.showToast(mActivity, "当前没有出差记录");
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

    class MyAdapter extends BaseQuickAdapter<WorkTripBean.DataBean, BaseViewHolder> {

        public MyAdapter(int layoutResId, @Nullable List<WorkTripBean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, WorkTripBean.DataBean item) {
            helper.setText(R.id.mine_tv_place, "出差地点：" + item.getWaTravelBusiness().getPlace()).
                    setText(R.id.mine_tv_time, "出差时间：" +
                            item.getWaTravelBusiness().getDepartureTime().getYear() + "-" +
                            item.getWaTravelBusiness().getDepartureTime().getMonth() + "-" +
                            item.getWaTravelBusiness().getDepartureTime().getDayOfMonth());

        }
    }
}
