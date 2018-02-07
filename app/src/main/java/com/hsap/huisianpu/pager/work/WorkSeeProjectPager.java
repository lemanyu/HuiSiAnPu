package com.hsap.huisianpu.pager.work;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.tu.loadingdialog.LoadingDailog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.activity.WorkSeeActivity;
import com.hsap.huisianpu.base.BaseFragmentLanPager;
import com.hsap.huisianpu.base.BaseFragmentPager;
import com.hsap.huisianpu.bean.SeeProjectBean;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.SpUtils;
import com.hsap.huisianpu.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * 查看项目
 */


public class WorkSeeProjectPager extends BaseFragmentLanPager {
    private static final String TAG="WorkSeeProjectPager";

    Unbinder unbinder;
    private int state;
    private RecyclerView rlvWorkSeeProject;


    public WorkSeeProjectPager state(int state){
        this.state=state;
        return this;
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.pager_see_project, null);
        rlvWorkSeeProject = view.findViewById(R.id.rlv_work_see_project);
        rlvWorkSeeProject.setLayoutManager(new LinearLayoutManager(mActivity));
        return view;
    }

    @Override
    public void initData() {
        final LoadingDailog 获取数据中 = ToastUtils.showDailog(mActivity, "获取数据中");
        获取数据中.show();
        OkGo.<String>post(NetAddressUtils.getDepartmentAllProject).
                params("workerId", SpUtils.getInt(ConstantUtils.UserId,mActivity)).
                params("state",state).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        获取数据中.dismiss();
                        Log.e(TAG,"onSuccess: "+response.body().toString() );
                        final SeeProjectBean bean = new Gson().fromJson(response.body().toString(), SeeProjectBean.class);
                        MyAdapeter adapeter = new MyAdapeter(R.layout.item_see_project, bean.getList());
                        rlvWorkSeeProject.setAdapter(adapeter);
                        adapeter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                Intent intent = new Intent(mActivity, WorkSeeActivity.class);
                                intent.putExtra("projectId",bean.getList().get(position).getId());
                                intent.putExtra("projectName",bean.getList().get(position).getProjectName());
                                intent.putExtra("name",bean.getList().get(position).getName());
                                intent.putExtra("now",bean.getList().get(position).getNow());
                                startActivity(intent);
                            }
                        });
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        获取数据中.dismiss();
                        ToastUtils.showToast(mActivity,"获取失败");
                    }
                });
    }

    @Override
    public void initListener() {

    }
 class MyAdapeter extends BaseQuickAdapter<SeeProjectBean.ListBean,BaseViewHolder>{

     public MyAdapeter(int layoutResId, @Nullable List<SeeProjectBean.ListBean> data) {
         super(layoutResId, data);
     }

     @Override
     protected void convert(BaseViewHolder helper, SeeProjectBean.ListBean item) {
         if (item.getNow()==8){
             helper.getView(R.id.tv_project_jindu).setVisibility(View.GONE);
             if (state==-1){
                 item.setNow(7);
             }
         }
                helper.setText(R.id.tv_project_name,"项目名称："+item.getProjectName()).
                        setText(R.id.tv_project_person,"负责人："+item.getName()).
                        setText(R.id.tv_project_time,"开始时间："+item.getStartTime().getYear()+"-"+
                         item.getStartTime().getMonthValue()+"-"+item.getStartTime().getDayOfMonth()).
                        setText(R.id.tv_project_jindu,"项目进度："+item.getNow()+"/"+(item.getMax()-1));
     }
 }
}
