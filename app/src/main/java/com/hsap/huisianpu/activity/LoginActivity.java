package com.hsap.huisianpu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseActivity;
import com.hsap.huisianpu.bean.LoginBean;
import com.hsap.huisianpu.bean.RegistrationBean;
import com.hsap.huisianpu.utils.ActivityManagerUtils;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.SpUtils;
import com.hsap.huisianpu.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

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
                  denglu(username,password);
                   break;
           }
    }

    private void denglu(String username, String password) {
        if (TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
            ToastUtils.showToast(LoginActivity.this,"账号或密码不能为空");
            return;
        }

        OkGo.<String>post(NetAddressUtils.login).params("username", username).
                params("password", password).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //TODO 保存sp的值 跳转到主页
                LoginBean bean = new Gson().fromJson(response.body().toString(), LoginBean.class);
                if (bean.isSuccess()){
                    SpUtils.putInt(ConstantUtils.UserId,bean.getData(),LoginActivity.this);
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    finish();
                    SpUtils.putBoolean(ConstantUtils.Login,true,LoginActivity.this);
                }else {
                    ToastUtils.showToast(LoginActivity.this,bean.getMsg()+"");
                    return;
                }

            }

            @Override
            public void onError(Response<String> response) {
               ToastUtils.showToast(LoginActivity.this,"当前无网络");
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
