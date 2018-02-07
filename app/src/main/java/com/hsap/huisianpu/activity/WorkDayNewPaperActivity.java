package com.hsap.huisianpu.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.android.tu.loadingdialog.LoadingDailog;
import com.google.gson.Gson;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.adapter.CheckPhotoAdapter;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.bean.ReportFormStateBean;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.SpUtils;
import com.hsap.huisianpu.utils.ToastUtils;
import com.hsap.huisianpu.view.MyGridView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.album.api.widget.Widget;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 工作日报
 */

public class WorkDayNewPaperActivity extends BaseBackActivity {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.bt_work_day_commit)
    Button btWorkDayCommit;
    @BindView(R.id.et_day_finish_work)
    EditText etDayFinishWork;
    @BindView(R.id.et_day_no_work)
    EditText etDayNoWork;
    @BindView(R.id.et_day_concert)
    EditText etDayConcert;
    @BindView(R.id.day_photo)
    MyGridView dayPhoto;
    private ArrayList<AlbumFile> mImageList=new ArrayList<>();
    private CheckPhotoAdapter adapter;
    private int number=9;
    @Override
    public int getLayoutId() {

        return R.layout.activity_work_day;

    }

    @Override
    public void initView() {

        adapter=new CheckPhotoAdapter(this,mImageList);
        dayPhoto.setAdapter(adapter);
        dayPhoto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==mImageList.size()){
                     permission();
                }else {
                      number++;
                      mImageList.remove(i);
                      adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void permission() {
        AndPermission.with(this).
                requestCode(100).
                permission(Permission.CAMERA,Permission.STORAGE).
                callback(permissionListener).
                rationale(rationaleListener).start();
    }


    private PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
            switch (requestCode) {
                case 100:
                    Widget 选择图片 = Widget.newDarkBuilder(WorkDayNewPaperActivity.this)
                            .title("请选择图片")
                            .statusBarColor(Color.parseColor("#303F9F"))
                            .toolBarColor(Color.parseColor("#1296db"))
                            .navigationBarColor(Color.parseColor("#1296db"))
                            .mediaItemCheckSelector(Color.WHITE, Color.parseColor("#1296db"))
                            .build();
                         Album.image(WorkDayNewPaperActivity.this).multipleChoice().requestCode(200).
                            widget(选择图片).selectCount(number).
                            camera(true).columnCount(3).
                            onResult(new Action<ArrayList<AlbumFile>>() {
                        @Override
                        public void onAction(int requestCode, @NonNull ArrayList<AlbumFile> result) {
                                if (requestCode==200){
                                    number=number-result.size();
                                    mImageList.addAll(result);
                                    adapter.notifyDataSetChanged();
                                }

                        }
                    }).start();


                   break;
                default:
            }
        }

        @Override
        public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
            ToastUtils.showToast(getApplicationContext(), "请到设置-权限管理中开启");
            if (AndPermission.hasAlwaysDeniedPermission(getApplicationContext(), deniedPermissions)) {
                // 第一种：用默认的提示语。
                AndPermission.defaultSettingDialog(WorkDayNewPaperActivity.this, 100).show();
            }
        }
    };
    private RationaleListener rationaleListener = new RationaleListener() {
        @Override
        public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
            AndPermission.rationaleDialog(getApplicationContext(), rationale).show();
        }
    };
    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
        btWorkDayCommit.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.bt_work_day_commit:
                commit();
                break;
                default:
        }
    }

    private void commit() {
        if (TextUtils.isEmpty(etDayFinishWork.getText().toString().trim())) {
            ToastUtils.showToast(this, "请填写完成工作信息");
            return;
        }
        if (TextUtils.isEmpty(etDayNoWork.getText().toString().trim())){
            ToastUtils.showToast(this, "请填写未完成工作信息");
            return;
        }
        if (TextUtils.isEmpty(etDayConcert.getText().toString().trim())){
            ToastUtils.showToast(this, "请填写需协调工作信息");
            return;
        }

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确定要提交吗？");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final LoadingDailog 提交中 = ToastUtils.showDailog(WorkDayNewPaperActivity.this, "提交中");
                提交中.show();
                OkGo.<String>post(NetAddressUtils.getNowReportFormState).
                        params("id", SpUtils.getInt(ConstantUtils.UserId, WorkDayNewPaperActivity.this)).
                        params("type", 0).execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ReportFormStateBean bean = new Gson().fromJson(response.body().toString(), ReportFormStateBean.class);
                        if (bean.isSuccess()) {
                            ArrayList<File> fileArrayList = new ArrayList<>();
                            for(int i=0;i<mImageList.size();i++){
                                fileArrayList.add(new File(mImageList.get(i).getPath()));
                            }
                            OkGo.<String>post(NetAddressUtils.setReportForm).
                                    params("id", SpUtils.getInt(ConstantUtils.UserId, WorkDayNewPaperActivity.this)).
                                    params("type", Integer.valueOf(0)).
                                    params("finishWork", etDayFinishWork.getText().toString().trim()).
                                    params("unWork",etDayNoWork.getText().toString().trim()).
                                    params("coordinateWork",etDayConcert.getText().toString().trim()).
                                    params("activity", "com.hsap.huisianpu.push.PushWeekActivity")
                                    .addFileParams("files",fileArrayList).
                                    execute(new StringCallback() {
                                        @Override
                                        public void onSuccess(Response<String> response) {
                                            提交中.dismiss();
                                            ToastUtils.showToast(WorkDayNewPaperActivity.this, "提交成功");
                                        }

                                        @Override
                                        public void onError(Response<String> response) {
                                            super.onError(response);
                                            提交中.dismiss();
                                            ToastUtils.showToast(WorkDayNewPaperActivity.this, "提交失败");
                                        }
                                    });
                        } else {
                            提交中.dismiss();
                            ToastUtils.showToast(WorkDayNewPaperActivity.this, "您今日已经提交过");
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        提交中.dismiss();
                        ToastUtils.showToast(WorkDayNewPaperActivity.this, "当前网络不稳，提交失败");
                    }
                });

            }
        });
        builder.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    
}
