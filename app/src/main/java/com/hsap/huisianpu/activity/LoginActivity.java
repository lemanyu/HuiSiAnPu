package com.hsap.huisianpu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.tu.loadingdialog.LoadingDailog;
import com.google.gson.Gson;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseActivity;
import com.hsap.huisianpu.bean.LoginBean;
import com.hsap.huisianpu.utils.ActivityManagerUtils;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.SpUtils;
import com.hsap.huisianpu.utils.ToastUtils;
import com.hsap.huisianpu.utils.Utils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhao on 2017/11/16.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.bt_zhuce)
    Button btZhuce;
    @BindView(R.id.bt_denglu)
    Button btdenglu;
    private LoginBean bean;
    private LoadingDailog 登录中;

    @Override
    public int getLayoutId() {

        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        ActivityManagerUtils.getInstance().addActivity(this);
        btdenglu.setOnClickListener(this);
        btZhuce.setOnClickListener(this);


    }

    @Override
    public void initData() {
    }

    @Override
    public void initListener() {

    }

    @Override
    public void processClick(View v) {
           switch (v.getId()){
               case R.id.bt_zhuce:
                   startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
                   break;
               case R.id.bt_denglu:
                   String  username = etUsername.getText().toString().trim();
                   String  password = etPassword.getText().toString().trim();
                   initXGPush(username,password);

                   break;
                   default:
           }
    }

    private void denglu(final String username, String password) {
        if (TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
            ToastUtils.showToast(this,"账号或密码不能为空");
            return;
        }
       if (!Utils.isPhone(username)){
            ToastUtils.showToast(this, "请输入正确的手机号码");
            return;
       }
        登录中 = ToastUtils.showDailog(this, "登录中");
        登录中.show();
        OkGo.<String>post(NetAddressUtils.login).params("username", username).
                params("password", password).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                bean = new Gson().fromJson(response.body().toString(), LoginBean.class);
                if (bean.isSuccess()){
                    XGPushManager.bindAccount(getApplicationContext(),username);
                    SpUtils.putBoolean(ConstantUtils.Login,true,LoginActivity.this);
                    SpUtils.putInt(ConstantUtils.UserId, bean.getData(),LoginActivity.this);
                    SpUtils.putString(ConstantUtils.Username,username,LoginActivity.this);
                    setToken(bean.getData());
                }else {
                    登录中.dismiss();
                    ToastUtils.showToast(LoginActivity.this, bean.getMsg()+"");
                    return;
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                登录中.dismiss();
                ToastUtils.showToast(LoginActivity.this,"当前网络不好");
            }
        });
    }

    private void initXGPush(final String username, final String password) {
        XGPushManager.registerPush(getApplicationContext(),new XGIOperateCallback() {
            @Override
            public void onSuccess(Object o, int i) {
                Log.d("TPush","注册成功"+o+"|"+i);
                denglu(username,password);
            }

            @Override
            public void onFail(Object o, int i, String s) {
                Log.d("TPush","注册失败"+o+"|"+i+"|"+s);
                initXGPush(username,password);
            }
        });
    }

    private void setToken(int data) {
        OkGo.<String>post(NetAddressUtils.setToken)
                .params("id",data)
                .params("token",XGPushConfig.getToken(this)).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        登录中.dismiss();
                        ToastUtils.showToast(LoginActivity.this,"登录成功");
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        finish();
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            finish();
            System.exit(0);
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }
}


