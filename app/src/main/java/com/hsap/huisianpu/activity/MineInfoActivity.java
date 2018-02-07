package com.hsap.huisianpu.activity;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.bean.BusinessMessage;
import com.hsap.huisianpu.bean.DepartmentBean;
import com.hsap.huisianpu.bean.HavePermissionBean;
import com.hsap.huisianpu.bean.MineInfoBean;
import com.hsap.huisianpu.bean.TokenBena;
import com.hsap.huisianpu.utils.ActivityManagerUtils;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.SpUtils;
import com.hsap.huisianpu.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tencent.android.tpush.XGPushManager;
import com.zhy.android.percent.support.PercentLinearLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhao on 2017/11/25.
 */

public class MineInfoActivity extends BaseBackActivity {
    private static final String TAG = "MineInfoActivity";
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.mine_info_name)
    TextView mineInfoName;
    @BindView(R.id.mine_info_phone)
    TextView mineInfoPhone;
    @BindView(R.id.mine_info_iccard)
    TextView mineInfoIccard;
    @BindView(R.id.mine_info_birth)
    TextView mineInfoBirth;
    @BindView(R.id.mine_info_age)
    TextView mineInfoAge;
    @BindView(R.id.mine_info_departmentname)
    TextView mineInfoDepartmentname;
    @BindView(R.id.mine_info_createtime)
    TextView mineInfoCreatetime;
    @BindView(R.id.mine_info_level)
    TextView mineInfoLevel;
    @BindView(R.id.mine_info_password)
    Button mineInfoPassword;
    @BindView(R.id.mine_info_main)
    LinearLayout mineInfoMain;
    @BindView(R.id.mine_info_jixiao)
    TextView mineInfoJixiao;
    @BindView(R.id.pll_jixiao)
    PercentLinearLayout pllJixiao;


    @Override
    public int getLayoutId() {
        return R.layout.activity_mine_info;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        getJ(null);
        OkGo.<String>post(NetAddressUtils.getBaseInformation).params("id", SpUtils.getInt(ConstantUtils.UserId, this)).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.d(TAG, "onSuccess: " + response.body());
                Gson gson = new Gson();
                MineInfoBean mineInfoBean = gson.fromJson(response.body(), MineInfoBean.class);
                MineInfoBean.UserBean data = mineInfoBean.getUser();
                setBaseInfo(data, mineInfoBean);
            }
        });
    }

    private void setBaseInfo(MineInfoBean.UserBean data, MineInfoBean bean) {
        mineInfoName.setText(data.getName());
        if (data.getBirthDate() != null) {
            mineInfoBirth.setText(data.getBirthDate().getYear() + "-" + data.getBirthDate().getMonthValue() + "-" + data.getBirthDate().getDayOfMonth());
        } else {
            mineInfoBirth.setText("未选择");
        }
        if ("0".equals(bean.getSName())){
            pllJixiao.setVisibility(View.GONE);
        }else {
            pllJixiao.setVisibility(View.VISIBLE);
            mineInfoJixiao.setText(bean.getSName());
        }
        mineInfoPhone.setText(data.getPhone());
        mineInfoIccard.setText(data.getIdCard());
        mineInfoAge.setText(data.getAge());
        mineInfoLevel.setText(bean.getJName());

            mineInfoCreatetime.setText(data.getCreateDate().getYear() + "-" + data.getCreateDate().getMonthValue() + "-" + data.getCreateDate().getDayOfMonth());


        OkGo.<String>post(NetAddressUtils.SelectDepartment).params("id", SpUtils.getInt(ConstantUtils.UserId, MineInfoActivity.this)).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.d(TAG, "onSuccess: " + response.body());
                mineInfoDepartmentname.setText(response.body());
            }
        });
    }

    @Override
    public void initListener() {
        mineInfoPassword.setOnClickListener(this);
        back.setOnClickListener(this);
        int childCount = mineInfoMain.getChildCount();
        for (int i = 0; i < childCount - 2; i += 2) {
            ((PercentLinearLayout) mineInfoMain.getChildAt(i)).getChildAt(1).setOnClickListener(this);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.mine_info_name:
            case R.id.mine_info_phone:
            case R.id.mine_info_iccard:
            case R.id.mine_info_password:
                createNoJAlertDialog(v);
                break;
            case R.id.mine_info_age:
                createAgeAlertDialog(v);
                break;
            case R.id.mine_info_birth:
                createDateAlertDialog(v);
                break;
            case R.id.mine_info_createtime:
                switch (flag) {
                    case -1:
                        getJ(v);
                        ToastUtils.showDailog(this, "网络不好，请稍后再试");
                        return;
                    case 0:
                        ToastUtils.showDailog(this, "您没有权限进行操作");
                        return;
                    default:
                }
                createDateAlertDialog(v);
                break;
            case R.id.mine_info_departmentname:
                createYesJAlertDialog(v);
                break;
            default:
        }
    }

    private void createAgeAlertDialog(View v) {
        final int[] choose = {0};
        final String[] item = {"男", "女"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择性别");
        builder.setSingleChoiceItems(item, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                choose[0] = i;
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mineInfoAge.setText(item[choose[0]]);
                Log.e(TAG, "onClick: " + mineInfoAge.getText().toString());
                OkGo.<String>post(NetAddressUtils.setBaseInformation).
                        params("id", SpUtils.getInt(ConstantUtils.UserId, MineInfoActivity.this)).
                        params("age", mineInfoAge.getText().toString()).execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        ToastUtils.showToast(MineInfoActivity.this, "性别修改失败");
                    }
                });

            }
        });
        builder.show();
    }

    private void createYesJAlertDialog(View v) {
        String title = ((TextView) ((LinearLayout) v.getParent()).getChildAt(0)).getText().toString();
        final String paramKey = ((TextView) v).getHint().toString();
        final String oldValue = ((TextView) ((LinearLayout) v.getParent()).getChildAt(1)).getText().toString();
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        String path = null;
        switch (v.getId()) {
            case R.id.mine_info_departmentname:
                path = NetAddressUtils.SelectDepartmentAll;
                break;
            default:
        }
        OkGo.<String>post(path).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.d(TAG, "onSuccess: " + response.body());
                Gson gson = new Gson();
                BusinessMessage<DepartmentBean> departmentBusiness = gson.fromJson(response.body(), new TypeToken<BusinessMessage<DepartmentBean>>() {
                }.getType());
                Log.d(TAG, "onSuccess: " + departmentBusiness);
                if (departmentBusiness.getSuccess()) {
                    List<DepartmentBean.ListBean> list = departmentBusiness.getData().getList();
                    Log.d(TAG, "onSuccess: " + list);

                    DepartmentBean.ListBean bean;
                    String[] values = new String[list.size()];
                    int valuei = 0;
                    for (int i = 0; i < list.size(); i++) {
                        bean = list.get(i);
                        values[i] = bean.getName();
                        if (oldValue.equals(bean.getName())) {
                            valuei = i;
                        }
                    }
                    builder.setSingleChoiceItems(values, valuei, null);
                }
            }
        });
        builder.setPositiveButton("确定", null);
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void createDateAlertDialog(View v) {
        String[] split = ((TextView) v).getText().toString().trim().split("-");
        final String paramKey = ((TextView) v).getHint().toString();

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, 0, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                OkGo.<String>post(NetAddressUtils.setBaseInformation).
                        params("id", SpUtils.getInt(ConstantUtils.UserId, MineInfoActivity.this)).
                        params(paramKey, year + "-" + month + "-" + dayOfMonth).execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d(TAG, "onSuccess: " + response.body());
                        Gson gson = new Gson();
                        MineInfoBean mineInfoBean = gson.fromJson(response.body(), MineInfoBean.class);
                        setBaseInfo(mineInfoBean.getUser(), mineInfoBean);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        ToastUtils.showToast(MineInfoActivity.this, "网络错误，请重试");
                    }
                });
            }
        }, Integer.valueOf(split[0]), Integer.valueOf(split[1]), Integer.valueOf(split[2]));
        datePickerDialog.setTitle(((TextView) ((LinearLayout) v.getParent()).getChildAt(0)).getText().toString());
        TextView title = new TextView(this);
        title.setText(((TextView) ((LinearLayout) v.getParent()).getChildAt(0)).getText().toString());
        title.setTextColor(Color.GREEN);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        title.setPadding(0, 10, 0, 10);
        title.setLayoutParams(layoutParams);
        title.setTextSize(25);
        int argb = Color.argb(255, 2, 151, 136);
        title.setTextColor(argb);
        datePickerDialog.setCustomTitle(title);
        datePickerDialog.show();
    }

    int flag = -1;

    private void getJ(final View v) {
        OkGo.<String>post(NetAddressUtils.getJurisdiction).params("id", SpUtils.getInt(ConstantUtils.UserId, this)).execute(new StringCallback() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSuccess(Response<String> response) {
                Gson gson = new Gson();
                HavePermissionBean havePermissionBean = gson.fromJson(response.body(), HavePermissionBean.class);
                if (havePermissionBean.isSuccess()) {
                    if (v != null) {
                        processClick(v);
                    }
                    if (havePermissionBean.isData()) {
                        flag = 1;
                    } else {
                        flag = 0;
                    }
                }
            }
        });
    }

    private void createNoJAlertDialog(View v) {
        String title = "修改" + ((TextView) ((LinearLayout) v.getParent()).getChildAt(0)).getText().toString();
        final String paramKey = ((TextView) v).getHint().toString();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        final EditText editText = new EditText(builder.getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 20);
        editText.setPadding(20, 5, 5, 20);
        editText.setHint(((TextView) v).getText().toString());
        editText.setLayoutParams(params);

        builder.setView(editText);
        builder.setPositiveButton("确定", null);
        final AlertDialog dialog = builder.create();
        dialog.show();
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled("".equals(editText.getText().toString()));
                return false;
            }
        });
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(editText.getText().toString())) {
                    ToastUtils.showDailog(MineInfoActivity.this, "请输入内容");
                    return;
                }
                OkGo.<String>post(NetAddressUtils.setBaseInformation).params("id", SpUtils.getInt(ConstantUtils.UserId, MineInfoActivity.this)).params(paramKey, editText.getText().toString()).execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d(TAG, "onSuccess: " + response.body());
                        Gson gson = new Gson();
                        MineInfoBean mineInfoBean = gson.fromJson(response.body(), MineInfoBean.class);
                        setBaseInfo(mineInfoBean.getUser(), mineInfoBean);
                        OkGo.<String>post(NetAddressUtils.setToken).
                                params("id", SpUtils.getInt(ConstantUtils.UserId, MineInfoActivity.this)).
                                params("token", "*").execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                TokenBena bena = new Gson().fromJson(response.body().toString(), TokenBena.class);
                                if (bena.isSuccess()) {
                                    dialog.dismiss();
                                    XGPushManager.registerPush(MineInfoActivity.this.getApplicationContext(), "*");
                                    //XGPushManager.unregisterPush(mActivity.getApplicationContext());
                                    SpUtils.putBoolean(ConstantUtils.Login, false, MineInfoActivity.this);
                                    SpUtils.putInt(ConstantUtils.UserId, 0, MineInfoActivity.this);
                                    startActivity(new Intent(MineInfoActivity.this, LoginActivity.class));
                                    ActivityManagerUtils.getInstance().finishActivityclass(MainActivity.class);
                                }
                            }
                        });

                    }

                    @Override
                    public void onError(Response<String> response) {
                        ToastUtils.showToast(MineInfoActivity.this, "网络错误，请重试");
                    }
                });
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
