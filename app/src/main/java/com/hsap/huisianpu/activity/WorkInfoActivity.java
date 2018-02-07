package com.hsap.huisianpu.activity;

import android.annotation.SuppressLint;
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
import com.hsap.huisianpu.bean.SelectApproverBean;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 修改他人信息
 */

public class WorkInfoActivity extends BaseBackActivity {
    private static final String TAG = "WorkInfoActivity";
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.tv_work_info_title)
    TextView tvWorkInfoTitle;
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
    @BindView(R.id.mine_info_main)
    LinearLayout mineInfoMain;
    @BindView(R.id.mine_info_jixiao)
    TextView mineInfoJixiao;
    @BindView(R.id.pll_jixiao)
    PercentLinearLayout pllJixiao;
    private int id;
    private int personId;
    @Override
    public int getLayoutId() {
        return R.layout.activity_work_info;
    }

    @Override
    public void initView() {
        id = getIntent().getIntExtra("id", 0);
        Log.e(TAG, "initView: " + id);
        String name = getIntent().getStringExtra("name");
        tvWorkInfoTitle.setText(name + "的信息");
    }

    @Override
    public void initData() {

        getJ(null);
        OkGo.<String>post(NetAddressUtils.getBaseInformation).
                params("id", id).execute(new StringCallback() {
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

    @Override
    public void initListener() {
        mineInfoJixiao.setOnClickListener(this);
        mineInfoCreatetime.setOnClickListener(this);
        mineInfoLevel.setOnClickListener(this);
        back.setOnClickListener(this);
        int childCount = mineInfoMain.getChildCount();
        for (int i = 0; i < childCount - 2; i += 2) {
            ((PercentLinearLayout) mineInfoMain.getChildAt(i)).getChildAt(1).setOnClickListener(this);
        }
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
        mineInfoCreatetime.setText(data.getCreateTime().getYear() + "-" + data.getCreateTime().getMonthValue() + "-" + data.getCreateTime().getDayOfMonth());
        OkGo.<String>post(NetAddressUtils.SelectDepartment).params("id", id).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.d(TAG, "onSuccess: " + response.body());
                mineInfoDepartmentname.setText(response.body());
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.mine_info_name:
            case R.id.mine_info_phone:
            case R.id.mine_info_iccard:
                createNoJAlertDialog(v);
                break;
            case R.id.mine_info_age:
                createAgeAlertDialog(v);
                break;
            case R.id.mine_info_birth:
                createDateAlertDialog(v);
                break;
            case R.id.mine_info_createtime:
                createDateAlertDialog(v);
                break;
            case R.id.mine_info_departmentname:
                createYesJAlertDialog(v);
                break;
            case R.id.mine_info_level:
                creatDialog(v);
                break;
            case R.id.mine_info_jixiao:
                creatjixiaoDialog(v);
                break;
            default:
        }
    }

    private void creatjixiaoDialog(View v) {
        OkGo.<String>post(NetAddressUtils.getWorkersList).params("id",
                SpUtils.getInt(ConstantUtils.UserId, this))
                .execute(new StringCallback() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onSuccess(Response<String> response) {
                        final int[] choose = {0};
                        final SelectApproverBean bean = new Gson().fromJson(response.body().toString(), SelectApproverBean.class);
                        final ArrayList<String> nameList = new ArrayList<>();
                        final ArrayList<Integer> idList = new ArrayList<>();
                        for (int i = 0; i < bean.getUsers().size(); i++) {
                            nameList.add(bean.getUsers().get(i).getUser().getName());
                            idList.add(bean.getUsers().get(i).getUser().getId());
                        }

                        String[] array = nameList.toArray(new String[nameList.size()]);
                        Log.e(TAG, "onSuccess: " + array.toString());

                        final AlertDialog.Builder builder = new AlertDialog.Builder(WorkInfoActivity.this);
                        builder.setTitle("请选择联系人");
                        builder.setSingleChoiceItems(array, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                choose[0] = which;
                            }
                        });
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mineInfoJixiao.setText(nameList.get(choose[0]));
                                personId=idList.get(choose[0]);
                                Log.e(TAG, "personId: "+personId );
                                OkGo.<String>post(NetAddressUtils.setBaseInformation).
                                        params("id",id).
                                        params("managerId",personId).
                                        execute(new StringCallback() {
                                            @Override
                                            public void onSuccess(Response<String> response) {
                                             ToastUtils.showToast(WorkInfoActivity.this,"修改成功");
                                            }

                                            @Override
                                            public void onError(Response<String> response) {
                                                super.onError(response);
                                                ToastUtils.showToast(WorkInfoActivity.this,"修改失败");
                                            }
                                        });

                            }
                        });
                        builder.show();
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);

                    }
                });

    }

    private void creatDialog(View v) {
        final int[] choose = {0};
        final String[] item = {"总监", "经理", "管理员", "员工", "实习员工"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择修改类型");
        builder.setSingleChoiceItems(item, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                choose[0] = i;
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mineInfoLevel.setText(item[choose[0]]);
                Log.e(TAG, "onClick: " + mineInfoAge.getText().toString());
                OkGo.<String>post(NetAddressUtils.setBaseInformation).
                        params("id", id).
                        params("newJurisdiction", mineInfoLevel.getText().toString()).execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ToastUtils.showToast(WorkInfoActivity.this, "修改成功");
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        ToastUtils.showToast(WorkInfoActivity.this, "修改失败");
                    }
                });

            }
        });
        builder.show();
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
                        params("id", id).
                        params("age", mineInfoAge.getText().toString()).execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        ToastUtils.showToast(WorkInfoActivity.this, "性别修改失败");
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
                        params("id", id).
                        params(paramKey, year + "-" + month + "-" + dayOfMonth).execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d(TAG, "onSuccess: " + response.body());
                        Gson gson = new Gson();
                        MineInfoBean mineInfoBean = gson.fromJson(response.body(), MineInfoBean.class);
                        setBaseInfo(mineInfoBean.getUser(), mineInfoBean);
                        ToastUtils.showDailog(WorkInfoActivity.this, "修改成功");
                    }

                    @Override
                    public void onError(Response<String> response) {
                        ToastUtils.showToast(WorkInfoActivity.this, "网络错误，请重试");
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
        OkGo.<String>post(NetAddressUtils.getJurisdiction).params("id", id).execute(new StringCallback() {
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
                    ToastUtils.showDailog(WorkInfoActivity.this, "请输入内容");
                    return;
                }
                OkGo.<String>post(NetAddressUtils.setBaseInformation).params("id", id).params(paramKey, editText.getText().toString()).execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d(TAG, "onSuccess: " + response.body());
                        Gson gson = new Gson();
                        MineInfoBean mineInfoBean = gson.fromJson(response.body(), MineInfoBean.class);
                        setBaseInfo(mineInfoBean.getUser(), mineInfoBean);
                        OkGo.<String>post(NetAddressUtils.setToken).
                                params("id", id).
                                params("token", "*").execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                TokenBena bena = new Gson().fromJson(response.body().toString(), TokenBena.class);
                                if (bena.isSuccess()) {
                                    dialog.dismiss();
                                    XGPushManager.registerPush(WorkInfoActivity.this.getApplicationContext(), "*");
                                    //XGPushManager.unregisterPush(mActivity.getApplicationContext());
                                    SpUtils.putBoolean(ConstantUtils.Login, false, WorkInfoActivity.this);
                                    SpUtils.putInt(ConstantUtils.UserId, 0, WorkInfoActivity.this);
                                    startActivity(new Intent(WorkInfoActivity.this, LoginActivity.class));
                                    ActivityManagerUtils.getInstance().finishActivityclass(MainActivity.class);
                                }
                            }
                        });

                    }

                    @Override
                    public void onError(Response<String> response) {
                        ToastUtils.showToast(WorkInfoActivity.this, "网络错误，请重试");
                    }
                });
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
