package com.hsap.huisianpu.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.activity.LoginActivity;
import com.hsap.huisianpu.activity.MainActivity;
import com.hsap.huisianpu.activity.MineApprovalActivity;
import com.hsap.huisianpu.activity.MineInfoActivity;
import com.hsap.huisianpu.activity.MineInviteActivity;
import com.hsap.huisianpu.activity.MineProjectActivity;
import com.hsap.huisianpu.activity.MineReportActivity;
import com.hsap.huisianpu.adapter.MineRecycleAdapter;
import com.hsap.huisianpu.base.BaseFragment;
import com.hsap.huisianpu.bean.Bean;
import com.hsap.huisianpu.utils.ActivityManagerUtils;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.SpUtils;
import com.hsap.huisianpu.utils.ToastUtils;
import com.tencent.android.tpush.XGPushManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zhao on 2017/11/15.
 */

public class MineFragment extends BaseFragment {
    @BindView(R.id.mine_toolbar)
    Toolbar mineToolbar;
    @BindView(R.id.mine_rlv)
    RecyclerView mineRlv;
    Unbinder unbinder;
    @BindView(R.id.bt_mine)
    Button btMine;
    private ArrayList<Bean> list;

    @Override
    public View initView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_mine, null);
        return view;
    }

    @Override
    public void initData() {
        setHasOptionsMenu(true);
        mineToolbar.setTitle("我的");
        ((AppCompatActivity) getActivity()).setSupportActionBar(mineToolbar);
        list = new ArrayList<>();
        list.add(new Bean("我的信息", R.drawable.wodexinxi));
        list.add(new Bean("我的邀请", R.drawable.wodeyaoqing));
        list.add(new Bean("我的汇报", R.drawable.wodehuibao));
        list.add(new Bean("我的审批", R.drawable.wodeshenpi));
        list.add(new Bean("我的项目",R.drawable.wodexiangmu));
    }


    @Override
    public void initListener() {
        MineRecycleAdapter adapter = new MineRecycleAdapter(R.layout.item_mine, list);
        mineRlv.setLayoutManager(new LinearLayoutManager(mActivity));
        mineRlv.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(mActivity, MineInfoActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(mActivity, MineInviteActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(mActivity, MineReportActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(mActivity, MineApprovalActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(mActivity, MineProjectActivity.class));
                        break;
                }
            }
        });
          btMine.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                  builder.setTitle("退出登录").setMessage("您确定要退出登录嘛？");
                  builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialogInterface, int i) {
                          XGPushManager.registerPush(mActivity,SpUtils.getString(ConstantUtils.Username,mActivity));
                          XGPushManager.unregisterPush(mActivity);
                          SpUtils.putString(ConstantUtils.Username,null,mActivity);
                          SpUtils.putBoolean(ConstantUtils.Login,false,mActivity);
                          SpUtils.putInt(ConstantUtils.UserId,0,mActivity);
                          startActivity(new Intent(mActivity, LoginActivity.class));
                          ActivityManagerUtils.getInstance().finishActivityclass(MainActivity.class);

                      }
                  });
                  builder.setNegativeButton("取消",null);
                  builder.show();
              }
          });
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
