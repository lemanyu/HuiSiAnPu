package com.hsap.huisianpu.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hsap.huisianpu.R;

import butterknife.ButterKnife;

/**
 * Created by zhao on 2017/11/15.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initView();
        initData();
        initListener();

    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            default:
                processClick(v);
                break;
        }
    }
    public abstract int  getLayoutId();
    public abstract void initView();
    public abstract void initData();
    public abstract void initListener();
    public abstract void processClick(View v);
}
