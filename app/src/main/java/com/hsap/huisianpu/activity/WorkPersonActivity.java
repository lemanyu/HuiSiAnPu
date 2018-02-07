package com.hsap.huisianpu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.adapter.ChooseAdapter;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.bean.SelectApproverBean;
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

/**
 * 修改个人信息
 */

public class WorkPersonActivity extends BaseBackActivity {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.rlv_work_person)
    RecyclerView rlvWorkPerson;

    @Override
    public int getLayoutId() {
        return R.layout.activity_work_person;
    }

    @Override
    public void initView() {
        rlvWorkPerson.setLayoutManager(new LinearLayoutManager(this));
        final LoadingDailog dailog = ToastUtils.showDailog(this, "获取中");
        dailog.show();
        OkGo.<String>post(NetAddressUtils.getWorkersList).
                params("id", SpUtils.getInt(ConstantUtils.UserId,this)).
                execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                final SelectApproverBean bean = new Gson().fromJson(response.body().toString(), SelectApproverBean.class);

                    dailog.dismiss();
                    MyAdapter adapter = new MyAdapter(R.layout.item_work_person, bean.getUsers());
                    rlvWorkPerson.setAdapter(adapter);
                    adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                           Intent intent= new Intent(WorkPersonActivity.this,WorkInfoActivity.class);
                            intent.putExtra("id",bean.getUsers().get(position).getUser().getId());
                            intent.putExtra("name",bean.getUsers().get(position).getUser().getName());
                            startActivity(intent);
                        }
                    });

            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                dailog.dismiss();
                ToastUtils.showToast(WorkPersonActivity.this,"获取失败，当前网络不好");
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

    class MyAdapter extends BaseQuickAdapter<SelectApproverBean.UsersBean,BaseViewHolder>{

        public MyAdapter(int layoutResId, @Nullable List<SelectApproverBean.UsersBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, SelectApproverBean.UsersBean item) {
            helper.setText(R.id.tv_name_person,item.getUser().getName());
        }
    }
}
