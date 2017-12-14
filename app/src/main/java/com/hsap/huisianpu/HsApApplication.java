package com.hsap.huisianpu;

import android.app.Application;

import com.hsap.huisianpu.view.GlideAlbumLoader;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpParams;
import com.tencent.android.tpush.XGPushConfig;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;

import java.util.Locale;
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
        Album.initialize(
                AlbumConfig.newBuilder(this)
                        .setAlbumLoader(new GlideAlbumLoader()) // 设置Album加载器。
                        .setLocale(Locale.CHINA) // 比如强制设置在任何语言下都用中文显示。
                        .build()
        );
    }

    private void initXGPush() {
        XGPushConfig.enableDebug(getApplicationContext(), true);
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
        OkGo.getInstance()
                .init(this)
                .addCommonParams(params)
                .setOkHttpClient(builder.build())
                .setCacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                .setRetryCount(3);
    }
}
