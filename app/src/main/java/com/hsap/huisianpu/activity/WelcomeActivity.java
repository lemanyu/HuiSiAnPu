package com.hsap.huisianpu.activity;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseActivity;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.SpUtils;

/**
 * Created by zhao on 2017/11/16.
 */

public class WelcomeActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        //取消标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_welcome;
    }

    @Override
    public void initView() {
        boolean islogin = SpUtils.getBoolean(ConstantUtils.IsLogin, WelcomeActivity.this);
        if(islogin){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                    finish();
                }
            },3000);

        }else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
                    finish();
                }
            },3000);

        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void processClick(View v) {

    }
}
