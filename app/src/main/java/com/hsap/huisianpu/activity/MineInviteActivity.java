package com.hsap.huisianpu.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.google.gson.Gson;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.adapter.InviteRecycleAdapter;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.bean.InvitationBean;
import com.hsap.huisianpu.bean.InviteBean;
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

/**
 * 我的邀请页面
 */

public class MineInviteActivity extends BaseBackActivity {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.rlv_mine_invite)
    RecyclerView rlvMineInvite;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mine_invite;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        OkGo.<String>post(NetAddressUtils.invitationList).
                params("id", SpUtils.getInt(ConstantUtils.UserId,MineInviteActivity.this)).
                params("state",3).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                InviteBean bean = new Gson().fromJson(response.body().toString(), InviteBean.class);
                if(bean.isSuccess()){
                    List<InviteBean.DataBean> data = bean.getData();
                    rlvMineInvite.setLayoutManager(new LinearLayoutManager(MineInviteActivity.this));
                    rlvMineInvite.setAdapter(new InviteRecycleAdapter(R.layout.item_mine_invite,data));
                }else {
                    ToastUtils.showToast(MineInviteActivity.this,"当前网络不好");
                }
            }
        });
    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
