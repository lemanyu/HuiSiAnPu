package com.hsap.huisianpu;

import android.app.Application;
import android.util.Log;

import com.hsap.huisianpu.activity.LoginActivity;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.SpUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpParams;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;

import java.util.logging.Level;

import okhttp3.OkHttpClient;

/**
 * Created by zhao on 2017/11/18.
 */

public class HsApApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initOkGo();
        initXGPush();
    }

    private void initXGPush() {
        XGPushConfig.enableDebug(getApplicationContext(),true);
        XGPushConfig.setAccessId(getApplicationContext(),2100271315);
        XGPushConfig.setAccessKey(getApplicationContext(),"A1T277UVH5JP");
        XGPushManager.registerPush(getApplicationContext(), new XGIOperateCallback() {
            @Override
            public void onSuccess(Object data, int i) {
                Log.e("TPush", "注册成功，设备token为：" + data);
                SpUtils.putString(ConstantUtils.Token,String.valueOf(data),getApplicationContext());

            }

            @Override
            public void onFail(Object data, int errCode, String msg) {
                Log.d("TPush", "注册失败，错误码：" + errCode + ",错误信息：" + msg);
            }
        });
    }

    private void initOkGo() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //配置log打印
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.WARNING);
        builder.addInterceptor(loggingInterceptor);
        //配置cookie  配置到sp中
        builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));
        HttpParams params = new HttpParams();
        params.put("devType", "phone");
        OkGo.getInstance()
                .init(this)
                .addCommonParams(params)
                .setOkHttpClient(builder.build())
                .setCacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                .setRetryCount(3);

    }
}
