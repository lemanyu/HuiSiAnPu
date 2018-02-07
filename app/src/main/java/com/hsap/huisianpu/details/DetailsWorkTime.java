package com.hsap.huisianpu.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.bean.MineDayBean;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 选择时间汇报
 */

public class DetailsWorkTime extends BaseBackActivity {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.rlv_work_time)
    RecyclerView rlvWorkTime;

    @Override
    public int getLayoutId() {
        return R.layout.details_work_time;
    }

    @Override
    public void initView() {
        int id = getIntent().getIntExtra("id", 0);
        final int type = getIntent().getIntExtra("type", 0);
        rlvWorkTime.setLayoutManager(new LinearLayoutManager(DetailsWorkTime.this));
        OkGo.<String>post(NetAddressUtils.getMyReportForms).
                params("id",id).params("type",type).
                params("page",0).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                final MineDayBean bean = new Gson().fromJson(response.body().toString(), MineDayBean.class);

                MyAdapter adapter = new MyAdapter(R.layout.item_mine_day, bean.getData().getList());
                rlvWorkTime.setAdapter(adapter);
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent intent = new Intent(DetailsWorkTime.this, DetailsMineDay.class);
                        intent.putExtra("type",type);
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
    public void initData() {

    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {

    }
    class MyAdapter extends BaseQuickAdapter<MineDayBean.DataBean.ListBean,BaseViewHolder>{


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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
