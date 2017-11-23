package com.hsap.huisianpu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseActivity;
import com.hsap.huisianpu.bean.RegistrationBean;
import com.hsap.huisianpu.utils.ActivityManagerUtils;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.SpUtils;
import com.hsap.huisianpu.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushManager;
import com.zhy.android.percent.support.PercentLinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhao on 2017/11/17.
 */

public class RegistrationActivity extends BaseActivity {
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_cipher)
    EditText etCipher;
    @BindView(R.id.et_confirm_cipher)
    EditText etConfirmCipher;
    @BindView(R.id.bt_registration)
    Button btRegistration;
    @BindView(R.id.fanhui)
    PercentLinearLayout fanhui;
    @BindView(R.id.rb_yuangong)
    RadioButton rbYuangong;
    @BindView(R.id.rb_jingli)
    RadioButton rbJingli;
    @BindView(R.id.rg_zhuce)
    RadioGroup rgZhuce;
    @BindView(R.id.et_yaoqingma)
    EditText etYaoqingma;
    @BindView(R.id.pll_zhuce)
    PercentLinearLayout pllZhuce;
    private String account;
    private String cipher;
    private String confirm;
    private String yaoqingma;

    @Override
    public int getLayoutId() {
        return R.layout.activity_registration;
    }

    @Override
    public void initView() {
        fanhui.setOnClickListener(this);
        btRegistration.setOnClickListener(this);

    }

    @Override
    public void initData() {
         rgZhuce.setOnCheckedChangeListener(checkListener());

    }

    @NonNull
    private RadioGroup.OnCheckedChangeListener checkListener() {
        return new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                       switch (i){
                           case R.id.rb_yuangong:
                               yaoqingma="";
                               pllZhuce.setVisibility(View.GONE);
                               break;
                           case R.id.rb_jingli:
                               pllZhuce.setVisibility(View.VISIBLE);
                               break;
                       }
            }
        };
    }

    @Override
    public void initListener() {

    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.bt_registration:
                account = etAccount.getText().toString().trim();
                cipher = etCipher.getText().toString().trim();
                confirm = etConfirmCipher.getText().toString().trim();
                if (pllZhuce.getVisibility()==View.VISIBLE){
                    yaoqingma = etYaoqingma.getText().toString().trim();
                }
                registration();
                break;
            case R.id.fanhui:
                finish();
                break;
        }
    }

    private void registration() {
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(cipher) || TextUtils.isEmpty(confirm)) {
            ToastUtils.showToast(RegistrationActivity.this, "账号密码不能为空");
            return;
        }
        if(!cipher.equals(confirm)) {
            ToastUtils.showToast(RegistrationActivity.this, "两次密码不一样");
            return;
        }
        if (!(pllZhuce.getVisibility()==View.GONE)) {
            if(pllZhuce.getVisibility()==View.VISIBLE&&TextUtils.isEmpty(yaoqingma)) {
                ToastUtils.showToast(RegistrationActivity.this, "邀请码不能为空");
                return;
            }
        }
            OkGo.<String>post(NetAddressUtils.registration).
                    params("username",account).params("password",cipher).params("activationCode",yaoqingma)
                    .execute(new StringCallback() {
                @Override
                public void onSuccess(Response<String> response) {
                    RegistrationBean bean = new Gson().fromJson(response.body().toString(), RegistrationBean.class);
                    if(bean.isSuccess()){
                        //TODO 保存sp的值 跳转到主页
                        SpUtils.putInt(ConstantUtils.UserId,bean.getData(),RegistrationActivity.this);
                        ActivityManagerUtils.getInstance().finishActivityclass(LoginActivity.class);
                        startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                        finish();
                        SpUtils.putBoolean(ConstantUtils.Login,true,RegistrationActivity.this);
                    }else {
                        ToastUtils.showToast(RegistrationActivity.this,bean.getMsg()+"");
                        return;
                    }

                }

                        @Override
                        public void onError(Response<String> response) {
                            ToastUtils.showToast(RegistrationActivity.this,"当前无网络");
                            return;
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
