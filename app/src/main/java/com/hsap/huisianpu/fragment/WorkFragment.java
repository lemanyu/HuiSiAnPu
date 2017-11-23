package com.hsap.huisianpu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.activity.ContactsActivity;
import com.hsap.huisianpu.activity.LeaveActivity;
import com.hsap.huisianpu.activity.PunchActivity;
import com.hsap.huisianpu.activity.WorkApprovalActivity;
import com.hsap.huisianpu.activity.WorkCarActivity;
import com.hsap.huisianpu.activity.WorkCheckReportActivity;
import com.hsap.huisianpu.activity.WorkDayNewPaperActivity;
import com.hsap.huisianpu.activity.WorkMonthNewPaperActivity;
import com.hsap.huisianpu.activity.WorkOutActivity;
import com.hsap.huisianpu.activity.WorkOvertimeActivity;
import com.hsap.huisianpu.activity.WorkTripActivity;
import com.hsap.huisianpu.activity.WorkWeekNewPaperActivity;
import com.hsap.huisianpu.adapter.WorkRecycleAdapter;
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

public class WorkFragment extends BaseFragment {
    @BindView(R.id.work_toolbar)
    Toolbar workToolbar;
    @BindView(R.id.internalField)
    RecyclerView internalField;
    @BindView(R.id.business)
    RecyclerView business;
    @BindView(R.id.project)
    RecyclerView project;
    @BindView(R.id.statistics)
    RecyclerView statistics;
    Unbinder unbinder;
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_work, null);
        return view;
    }

    @Override
    public void initData() {
        setHasOptionsMenu(true);
        workToolbar.setTitle("工作");
        ((AppCompatActivity)getActivity()).setSupportActionBar(workToolbar);
        initInternalField();
        initBusiness();
        initProject();
        initStatistics();
    }

    @Override
    public void initListener() {

    }

    private void initInternalField() {
        ArrayList<Bean> list = new ArrayList<>();
        list.add(new Bean("打卡",R.drawable.dakai));
        list.add(new Bean("请假",R.drawable.qingjia));
        list.add(new Bean("外出",R.drawable.waichu));
        list.add(new Bean("出差",R.drawable.chuchai));
        list.add(new Bean("加班",R.drawable.jiaban));
        list.add(new Bean("用车",R.drawable.car));
        WorkRecycleAdapter adapter = new WorkRecycleAdapter(R.layout.item_work, list);
        internalField.setLayoutManager(new GridLayoutManager(mActivity,4));
        internalField.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                 switch (position){
                     case 0:
                       startActivity(new Intent(mActivity, PunchActivity.class));
                     break;
                     case 1:
                        startActivity(new Intent(mActivity, LeaveActivity.class));
                         break;
                     case 2:
                         startActivity(new Intent(mActivity, WorkOutActivity.class));
                         break;
                     case 3:
                         startActivity(new Intent(mActivity, WorkTripActivity.class));
                         break;
                     case 4:
                         startActivity(new Intent(mActivity, WorkOvertimeActivity.class));
                         break;
                     case 5:
                         startActivity(new Intent(mActivity, WorkCarActivity.class));
                         break;
                 }
            }
        });
    }

    private void initBusiness() {
        ArrayList<Bean> list = new ArrayList<>();
        list.add(new Bean("日报",R.drawable.day));
        list.add(new Bean("周报",R.drawable.week));
        list.add(new Bean("月报",R.drawable.month));
        list.add(new Bean("查看汇报",R.drawable.see));
        WorkRecycleAdapter adapter = new WorkRecycleAdapter(R.layout.item_work, list);
        business.setLayoutManager(new GridLayoutManager(mActivity,4));
        business.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                 switch (position){
                     case 0:
                         startActivity(new Intent(mActivity, WorkDayNewPaperActivity.class));
                         break;
                     case 1:
                         startActivity(new Intent(mActivity, WorkWeekNewPaperActivity.class));
                         break;
                     case 2:
                        startActivity(new Intent(mActivity, WorkMonthNewPaperActivity.class));
                         break;
                     case 3:
                        startActivity(new Intent(mActivity, WorkCheckReportActivity.class));
                         break;
                 }
            }
        });
    }

    private void initProject() {
        ArrayList<Bean> list = new ArrayList<>();
        list.add(new Bean("发布项目",R.drawable.fabuxiangmu));
        list.add(new Bean("查看项目",R.drawable.chaknaxiangmu));
        WorkRecycleAdapter adapter = new WorkRecycleAdapter(R.layout.item_work, list);
        project.setLayoutManager(new GridLayoutManager(mActivity,4));
        project.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                 switch (position){
                     case 0:
                            ToastUtils.showToast(mActivity,"发布项目");
                         break;
                     case 1:
                         ToastUtils.showToast(mActivity,"查看项目");
                         break;
                 }
            }
        });

    }

    private void initStatistics() {
        ArrayList<Bean> list = new ArrayList<>();
        list.add(new Bean("考勤统计",R.drawable.kaoqin));
        list.add(new Bean("待我审批",R.drawable.shenpi));
        list.add(new Bean("修改权限",R.drawable.xiugaiquanxian));
        list.add(new Bean("邀请注册",R.drawable.yaoqingzhuce));
        WorkRecycleAdapter adapter = new WorkRecycleAdapter(R.layout.item_work, list);
        statistics.setLayoutManager(new GridLayoutManager(mActivity,4));
        statistics.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position){
                    case 0:
                        ToastUtils.showToast(mActivity,"考勤统计");
                        break;
                    case 1:
                        startActivity(new Intent(mActivity, WorkApprovalActivity.class));
                        break;
                    case 2:
                        ToastUtils.showToast(mActivity,"修改权限");
                        break;
                    case 3:
                        contactsPermission();
                        break;
                }
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

}
