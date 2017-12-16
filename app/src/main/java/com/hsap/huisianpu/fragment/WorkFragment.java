package com.hsap.huisianpu.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.android.tu.loadingdialog.LoadingDailog;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.activity.AnnouncementActivity;
import com.hsap.huisianpu.activity.ContactsActivity;
import com.hsap.huisianpu.activity.DeteleAnnouncenActivity;
import com.hsap.huisianpu.activity.LeaveActivity;
import com.hsap.huisianpu.activity.PunchActivity;
import com.hsap.huisianpu.activity.WorkApprovalActivity;
import com.hsap.huisianpu.activity.WorkAttendanceActivity;
import com.hsap.huisianpu.activity.WorkCarActivity;
import com.hsap.huisianpu.activity.WorkCheckReportActivity;
import com.hsap.huisianpu.activity.WorkDayNewPaperActivity;
import com.hsap.huisianpu.activity.WorkModifyPermissionsActivity;
import com.hsap.huisianpu.activity.WorkMonthNewPaperActivity;
import com.hsap.huisianpu.activity.WorkOutActivity;
import com.hsap.huisianpu.activity.WorkOvertimeActivity;
import com.hsap.huisianpu.activity.WorkPublishProjectActivity;
import com.hsap.huisianpu.activity.WorkSeeProjectActivity;
import com.hsap.huisianpu.activity.WorkSummaryActivity;
import com.hsap.huisianpu.activity.WorkTripActivity;
import com.hsap.huisianpu.activity.WorkWeekNewPaperActivity;
import com.hsap.huisianpu.adapter.WorkRecycleAdapter;
import com.hsap.huisianpu.base.BaseFragment;
import com.hsap.huisianpu.bean.AllGongGaoBean;
import com.hsap.huisianpu.bean.Bean;
import com.hsap.huisianpu.bean.FalseBean;
import com.hsap.huisianpu.bean.HavePermissionBean;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.SpUtils;
import com.hsap.huisianpu.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;
import com.zhy.android.percent.support.PercentLinearLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

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
    private static final String TAG = "WorkFragment";
    @BindView(R.id.tv_notice)
    TextSwitcher tvNotice;

    private ArrayList<String> titleList = new ArrayList<String>();
    private LoadingDailog 获取权限中;
    private  int mSwitcherCount;
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                // 广告
                case 0:
                    tvNotice.setText(titleList.get(mSwitcherCount%titleList.size()));
                    tvNotice.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mHandler.removeMessages(0);
                            AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                            builder.setMessage("通知内容："+titleList.get(mSwitcherCount-1%titleList.size()));
                            builder.setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    mHandler.sendEmptyMessage(0);
                                }
                            });
                            builder.show();
                        }
                    });

                    mSwitcherCount++;
                    mHandler.sendEmptyMessageDelayed(0, 4000);
                    break;
                    default:
            }

        }
    };
    private Badge badge;
    private MyAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_work, null);
        return view;
    }

    @Override
    public void initData() {
        tvNotice.setInAnimation(mActivity, R.anim.slide_in_bottom);
        tvNotice.setOutAnimation(mActivity, R.anim.slide_out_up);
        tvNotice.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView tv = new TextView(mActivity);
                // 设置文字的显示单位以及文字的大小
                tv.setTextSize(20);
                tv.setTextColor(Color.parseColor("#1296db"));
                tv.setMaxLines(1);
                tv.setEllipsize(TextUtils.TruncateAt.END);
                return tv;
            }
        });
        获取权限中 = ToastUtils.showDailog(mActivity, "获取权限中");
        setHasOptionsMenu(true);
        workToolbar.setOverflowIcon(getResources().getDrawable(R.drawable.add));
        workToolbar.setTitle("工作");
        ((AppCompatActivity) getActivity()).setSupportActionBar(workToolbar);
        dataFormNet();
        initInternalField();
        initBusiness();
        initProject();
        initStatistics();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeMessages(0);
    }

    private void dataFormNet() {
        OkGo.<String>post(NetAddressUtils.selectAllNotice).
                params("workersId", SpUtils.getInt(ConstantUtils.UserId, mActivity)).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e(TAG, response.body().toString());
                        titleList.clear();
                        AllGongGaoBean bean = new Gson().fromJson(response.body().toString(), AllGongGaoBean.class);
                        for (int i = 0; i < bean.getData().size(); i++) {
                            titleList.add(bean.getData().get(i).getNoticeBody());
                        }
                        mHandler.sendEmptyMessage(0);

                    }
                });
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.work_menu, menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tianjia:
                获取权限中.show();
                OkGo.<String>post(NetAddressUtils.getJurisdiction).
                        params("id", SpUtils.getInt(ConstantUtils.UserId, mActivity)).
                        execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                HavePermissionBean bean = new Gson().fromJson(response.body().toString(), HavePermissionBean.class);
                                获取权限中.dismiss();
                                if (bean.isSuccess()) {
                                    startActivity(new Intent(mActivity, AnnouncementActivity.class));
                                } else {
                                    ToastUtils.showToast(mActivity, "您没有权限打开此服务");
                                }

                            }

                            @Override
                            public void onError(Response<String> response) {
                                super.onError(response);
                                获取权限中.dismiss();
                                ToastUtils.showToast(mActivity, "您没有权限打开此服务");

                            }
                        });
                break;
            case R.id.shanchu:
                获取权限中.show();
                OkGo.<String>post(NetAddressUtils.getJurisdiction).
                        params("id", SpUtils.getInt(ConstantUtils.UserId, mActivity)).
                        execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                HavePermissionBean bean = new Gson().fromJson(response.body().toString(), HavePermissionBean.class);
                                获取权限中.dismiss();
                                if (bean.isSuccess()) {
                                    startActivityForResult(new Intent(mActivity, DeteleAnnouncenActivity.class), Activity.RESULT_FIRST_USER);
                                } else {
                                    ToastUtils.showToast(mActivity, "您没有权限打开此服务");
                                }
                            }
                            @Override
                            public void onError(Response<String> response) {
                                super.onError(response);
                                获取权限中.dismiss();
                                ToastUtils.showToast(mActivity, "您没有权限打开此服务");

                            }
                        });
                break;
            default:
        }
        return true;
    }

    private void initInternalField() {
        ArrayList<Bean> list = new ArrayList<>();
        list.add(new Bean("打卡", R.drawable.dakai));
        list.add(new Bean("请假", R.drawable.qingjia));
        list.add(new Bean("外出", R.drawable.waichu));
        list.add(new Bean("出差", R.drawable.chuchai));
        list.add(new Bean("加班", R.drawable.jiaban));
        list.add(new Bean("用车", R.drawable.car));
        internalField.setLayoutManager(new GridLayoutManager(mActivity, 4));
        WorkRecycleAdapter adapter = new WorkRecycleAdapter(R.layout.item_work, list);
        internalField.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
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
                        default:
                }
            }
        });
    }

    private void initBusiness() {
        ArrayList<Bean> list = new ArrayList<>();
        list.add(new Bean("日报", R.drawable.day));
        list.add(new Bean("周报", R.drawable.week));
        list.add(new Bean("月报", R.drawable.month));
        list.add(new Bean("查看汇报", R.drawable.see));
        list.add(new Bean("出差总结", R.drawable.chuchaizongjie));
        business.setLayoutManager(new GridLayoutManager(mActivity, 4));
        WorkRecycleAdapter adapter = new WorkRecycleAdapter(R.layout.item_work, list);
        business.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        isReportFormState(0);
                        startActivity(new Intent(mActivity, WorkDayNewPaperActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(mActivity, WorkWeekNewPaperActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(mActivity, WorkMonthNewPaperActivity.class));
                        break;
                    case 3:
                        获取权限中.show();
                        OkGo.<String>post(NetAddressUtils.getJurisdiction).
                                params("id", SpUtils.getInt(ConstantUtils.UserId, mActivity)).
                                execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(Response<String> response) {
                                        获取权限中.dismiss();
                                        HavePermissionBean bean = new Gson().fromJson(response.body().toString(), HavePermissionBean.class);
                                        if (bean.isSuccess()) {
                                            startActivity(new Intent(mActivity, WorkCheckReportActivity.class));
                                        } else {
                                            ToastUtils.showToast(mActivity, "您没有权限打开此服务");
                                        }
                                    }

                                    @Override
                                    public void onError(Response<String> response) {
                                        super.onError(response);
                                        获取权限中.dismiss();
                                        ToastUtils.showToast(mActivity, "您没有权限打开此服务");

                                    }
                                });

                        break;
                    case 4:
                        startActivity(new Intent(mActivity, WorkSummaryActivity.class));
                        break;
                    default:
                }
            }
        });
    }

    private void initProject() {
        ArrayList<Bean> list = new ArrayList<>();
        list.add(new Bean("发布项目", R.drawable.fabuxiangmu));
        list.add(new Bean("查看项目", R.drawable.chaknaxiangmu));
        project.setLayoutManager(new GridLayoutManager(mActivity, 4));
        WorkRecycleAdapter adapter = new WorkRecycleAdapter(R.layout.item_work, list);
        project.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(mActivity, WorkPublishProjectActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(mActivity, WorkSeeProjectActivity.class));
                        break;
                        default:
                }
            }
        });

    }

    private void initStatistics() {
        ArrayList<Bean> list = new ArrayList<>();
        list.add(new Bean("考勤统计", R.drawable.kaoqin));
        list.add(new Bean("待我审批", R.drawable.shenpi));
        list.add(new Bean("修改权限", R.drawable.xiugaiquanxian));
        list.add(new Bean("邀请注册", R.drawable.yaoqingzhuce));
        statistics.setLayoutManager(new GridLayoutManager(mActivity, 4));
        if(adapter==null){
            adapter = new MyAdapter(R.layout.item_work, list);
            statistics.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        workAttendanceActivity();
                        break;
                    case 1:
                        workApprovalActivity();
                        break;
                    case 2:
                        workModifyPermissionsActivity();

                        break;
                    case 3:
                        contactsActivity();
                        break;
                    default:
                }
            }
        });

    }

    private void contactsActivity() {
        获取权限中.show();
        OkGo.<String>post(NetAddressUtils.getJurisdiction).
                params("id", SpUtils.getInt(ConstantUtils.UserId, mActivity)).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        获取权限中.dismiss();
                        HavePermissionBean bean = new Gson().fromJson(response.body().toString(), HavePermissionBean.class);
                        if (bean.isSuccess()) {
                            contactsPermission();
                        } else {
                            ToastUtils.showToast(mActivity, "您没有权限打开此服务");
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        获取权限中.dismiss();
                        ToastUtils.showToast(mActivity, "您没有权限打开此服务");

                    }
                });
    }

    private void workModifyPermissionsActivity() {
        获取权限中.show();
        OkGo.<String>post(NetAddressUtils.getJurisdiction).
                params("id", SpUtils.getInt(ConstantUtils.UserId, mActivity)).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        获取权限中.dismiss();
                        HavePermissionBean bean = new Gson().fromJson(response.body().toString(), HavePermissionBean.class);
                        if (bean.isSuccess()) {
                            startActivity(new Intent(mActivity, WorkModifyPermissionsActivity.class));
                        } else {
                            ToastUtils.showToast(mActivity, "您没有权限打开此服务");
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        获取权限中.dismiss();
                        ToastUtils.showToast(mActivity, "您没有权限打开此服务");

                    }
                });
    }

    private void workApprovalActivity() {
        获取权限中.show();
        OkGo.<String>post(NetAddressUtils.getJurisdiction).
                params("id", SpUtils.getInt(ConstantUtils.UserId, mActivity)).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        获取权限中.dismiss();
                        HavePermissionBean bean = new Gson().fromJson(response.body().toString(), HavePermissionBean.class);
                        if (bean.isSuccess()) {
                            Log.e(TAG, "workApprovalActivity ");
                            startActivity(new Intent(mActivity, WorkApprovalActivity.class));
                        } else {
                            ToastUtils.showToast(mActivity, "您没有权限打开此服务");
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        获取权限中.dismiss();
                        ToastUtils.showToast(mActivity, "您没有权限打开此服务");

                    }
                });
    }

    private void workAttendanceActivity() {
        获取权限中.show();
        OkGo.<String>post(NetAddressUtils.getJurisdiction).
                params("id", SpUtils.getInt(ConstantUtils.UserId, mActivity)).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        HavePermissionBean bean = new Gson().fromJson(response.body().toString(), HavePermissionBean.class);
                        获取权限中.dismiss();
                        if (bean.isSuccess()) {
                            startActivity(new Intent(mActivity, WorkAttendanceActivity.class));
                        } else {
                            ToastUtils.showToast(mActivity, "您没有权限打开此服务");
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        获取权限中.dismiss();
                        ToastUtils.showToast(mActivity, "您没有权限打开此服务");

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

    private Boolean isReportFormState(int type) {
        OkGo.<String>post(NetAddressUtils.getNowReportFormState).
                params("id", SpUtils.getInt(ConstantUtils.UserId, mActivity)).
                params("type", type).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.e("isReportFormState", response.body().toString());
            }
        });

        return false;
    }

    private void contactsPermission() {
        AndPermission.with(mActivity)
                .requestCode(1)
                .permission(Permission.CONTACTS)
                .rationale(rationaleListener)
                .callback(permissionListener)
                .start();

    }

    private PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
            switch (requestCode) {
                case 1:
                    startActivity(new Intent(mActivity, ContactsActivity.class));
                    break;
            }
        }

        @Override
        public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
            ToastUtils.showToast(mActivity, "请到设置-权限管理中开启");
            if (AndPermission.hasAlwaysDeniedPermission(mActivity, deniedPermissions)) {
                // 第一种：用默认的提示语。
                AndPermission.defaultSettingDialog(mActivity, 1).show();
            }
        }
    };
    private RationaleListener rationaleListener = new RationaleListener() {
        @Override
        public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
            AndPermission.rationaleDialog(mActivity, rationale).show();
        }
    };
   class MyAdapter extends BaseQuickAdapter<Bean,MyAdapter.MyViewHolder>{

       public MyAdapter(int layoutResId, @Nullable List<Bean> data) {
           super(layoutResId, data);
       }

       @Override
       protected void convert(MyViewHolder holder, Bean item) {
           if (holder.getAdapterPosition()==1){
               holder.badge.setBadgeNumber(SpUtils.getInt(ConstantUtils.Approve,mActivity)).setShowShadow(true)
                       .setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
                           @Override
                           public void onDragStateChanged(int dragState, Badge badge, View targetView) {

                           }
                       });

           }else {
               holder.badge.setBadgeNumber(0).setShowShadow(true);
           }
           holder.work_tv.setText(item.getName());
           Glide.with(mContext).load(item.getPic()).into((ImageView) holder.getView(R.id.work_iv));
       }
       class MyViewHolder extends BaseViewHolder{
           private final PercentLinearLayout pll_work;
           private final TextView work_tv;
           private final Badge badge;
           public MyViewHolder(View view) {
               super(view);
               pll_work=view.findViewById(R.id.pll_work);
               work_tv=view.findViewById(R.id.work_tv);
               badge= new QBadgeView(mActivity).bindTarget(pll_work);
               badge.setBadgeGravity(Gravity.END|Gravity.TOP)
                       .setBadgeTextSize(14, true);
           }

       }
   }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFalse(FalseBean event) {
        dataFormNet();
       initStatistics();
    }
}
