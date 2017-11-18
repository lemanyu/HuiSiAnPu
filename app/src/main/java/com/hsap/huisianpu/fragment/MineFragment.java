package com.hsap.huisianpu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.activity.ContactsActivity;
import com.hsap.huisianpu.adapter.MineRecycleAdapter;
import com.hsap.huisianpu.base.BaseFragment;
import com.hsap.huisianpu.bean.Bean;
import com.hsap.huisianpu.utils.ToastUtils;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.util.ArrayList;
import java.util.List;

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
        ArrayList<Bean> list = new ArrayList<>();
        list.add(new Bean("我的信息",R.drawable.wodexinxi));
        list.add(new Bean("我的邀请",R.drawable.wodeyaoqing));
        MineRecycleAdapter adapter = new MineRecycleAdapter(R.layout.item_mine, list);
        mineRlv.setLayoutManager(new LinearLayoutManager(mActivity));
        mineRlv.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                     switch (position){
                         case 0:
                             //TODO 个人信息

                         break;
                         case 1:
                            contactsPermission();
                             break;
                     }
            }
        });
    }

    private void contactsPermission() {
        AndPermission.with(mActivity)
                .requestCode(1)
                .permission(Permission.CONTACTS)
                .rationale(rationaleListener)
                .callback(permissionListener)
                .start();

    }
    private PermissionListener permissionListener=new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
            switch (requestCode){
                case 1:
                    startActivity(new Intent(mActivity, ContactsActivity.class));
                    break;
            }
        }

        @Override
        public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
            ToastUtils.showToast(mActivity,"请到设置-权限管理中开启");
            if (AndPermission.hasAlwaysDeniedPermission(mActivity, deniedPermissions)){
                // 第一种：用默认的提示语。
                AndPermission.defaultSettingDialog(mActivity,1).show();
            }
        }
    };
    private RationaleListener rationaleListener =new RationaleListener() {
        @Override
        public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
            AndPermission.rationaleDialog(mActivity,rationale).show();
        }
    };
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
}