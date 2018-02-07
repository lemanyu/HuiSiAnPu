package com.hsap.huisianpu.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.bumptech.glide.Glide;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.bean.FalseBean;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.SpUtils;
import com.hsap.huisianpu.utils.ToastUtils;
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
import com.zhy.android.percent.support.PercentLinearLayout;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author zhao
 * @date 2017/12/9  发布公告
 */

public class AnnouncementActivity extends BaseBackActivity {
    private static final String TAG = "AnnouncementActivity";
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.bt_commit)
    Button btCommit;
    @BindView(R.id.et_gonggao)
    EditText etGonggao;
    @BindView(R.id.tv_annouc_reason)
    TextView tvAnnoucReason;
    @BindView(R.id.pll_annou_reason)
    PercentLinearLayout pllAnnouReason;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.ll_image)
    LinearLayout llImage;
    private int state = 0;
    private ArrayList<AlbumFile> mImageList=new ArrayList<>();
    @Override
    public int getLayoutId() {

        return R.layout.activity_announcement;
    }

    @Override
    public void initView() {
         etGonggao.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 if (TextUtils.isEmpty(etGonggao.getText().toString())) {
                     llImage.setVisibility(View.VISIBLE);
                 }
             }

             @Override
             public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 llImage.setVisibility(View.GONE);
             }

             @Override
             public void afterTextChanged(Editable editable) {
                 if (TextUtils.isEmpty(etGonggao.getText().toString())) {
                     llImage.setVisibility(View.VISIBLE);
                 }else {
                     llImage.setVisibility(View.GONE);
                 }
             }
         });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
        btCommit.setOnClickListener(this);
        llImage.setOnClickListener(this);
        pllAnnouReason.setOnClickListener(this);
    }


    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.bt_commit:
                if (tvAnnoucReason.getText().toString().trim().equals("请选择（必填）")) {
                    ToastUtils.showToast(getApplicationContext(), "请选择发布对象");
                    return;
                }
                if (etGonggao.getVisibility()==View.VISIBLE&&TextUtils.isEmpty(etGonggao.getText().toString().trim())) {
                    ToastUtils.showToast(getApplicationContext(), "公告内容不能为空");
                    return;
                }
                //TODO、提交数据
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("确定要发布此公告嘛？");
                builder.setNegativeButton("取消", null);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final LoadingDailog 发布中 = ToastUtils.showDailog(AnnouncementActivity.this, "发布中");
                        发布中.show();
                        ArrayList<File> fileArrayList = new ArrayList<>();
                        if(iv.getVisibility()==View.VISIBLE){
                            fileArrayList.add(new File(mImageList.get(0).getPath()));
                        }
                        OkGo.<String>post(NetAddressUtils.addNotice).
                                params("type ", 1).
                                params("body", etGonggao.getText().toString().trim()).
                                params("workersId", SpUtils.getInt(ConstantUtils.UserId, getApplicationContext())).
                                params("level", state).
                                params("activity", "com.hsap.huisianpu.push.PushAnnouncenment").
                                addFileParams("files",fileArrayList).
                                execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(Response<String> response) {
                                        发布中.dismiss();
                                        ToastUtils.showToast(getApplicationContext(), "发布成功");
                                        // setResult(201);
                                    }

                                    @Override
                                    public void onError(Response<String> response) {
                                        super.onError(response);
                                        发布中.dismiss();
                                        ToastUtils.showToast(getApplicationContext(), "发布失败，无网络");
                                    }
                                });
                    }

                });
                builder.show();
                break;
            case R.id.pll_annou_reason:
                final int[] choose = {0};
                final String[] item = {"部门员工", "全体经理", "全公司"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("请选择发布对象");
                dialog.setSingleChoiceItems(item, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        choose[0] = i;
                        state = i;
                        Log.e(TAG, "onClick: " + state);


                    }
                });
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        tvAnnoucReason.setText(item[choose[0]]);
                    }
                });
                dialog.show();
                break;
            case R.id.ll_image:
                permission();
                break;
            default:
        }
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
                    Widget 选择图片 = Widget.newDarkBuilder(AnnouncementActivity.this)
                            .title("请选择图片")
                            .statusBarColor(Color.parseColor("#303F9F"))
                            .toolBarColor(Color.parseColor("#1296db"))
                            .navigationBarColor(Color.parseColor("#1296db"))
                            .mediaItemCheckSelector(Color.WHITE, Color.parseColor("#1296db"))
                            .build();
                    Album.image(AnnouncementActivity.this).
                            multipleChoice().requestCode(200).
                            widget(选择图片).selectCount(1).
                            camera(true).columnCount(3).
                            onResult(new Action<ArrayList<AlbumFile>>() {
                                @Override
                                public void onAction(int requestCode, @NonNull ArrayList<AlbumFile> result) {
                                    etGonggao.setVisibility(View.GONE);
                                    iv.setVisibility(View.VISIBLE);
                                    mImageList.clear();
                                    mImageList.addAll(result);
                                    Glide.with(AnnouncementActivity.this).load(result.get(0).getPath()).into(iv);

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
                AndPermission.defaultSettingDialog(AnnouncementActivity.this, 100).show();
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
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().post(new FalseBean("aa"));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
