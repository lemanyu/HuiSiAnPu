package com.hsap.huisianpu.utils;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.tu.loadingdialog.LoadingDailog;
import com.google.gson.Gson;
import com.hsap.huisianpu.activity.AnnouncementActivity;
import com.hsap.huisianpu.bean.HavePermissionBean;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhao on 2017/12/2.
 */

public class Utils {
    private static final String TAG = "Utils";

    /**
     * 判断电话号码是否符合格式.
     *
     * @param inputText the input text
     * @return true, if is phone
     */
    public static boolean isPhone(String inputText) {
        Pattern p = Pattern.compile("^((14[0-9])|(13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$");
        Matcher m = p.matcher(inputText);
        return m.matches();
    }

    private static int flag = -2;

    public static int is(final Context context) {
        final LoadingDailog 获取权限中 = ToastUtils.showDailog(context, "获取权限中");
        获取权限中.show();
        if (flag == -2) {
            OkGo.<String>post(NetAddressUtils.getJurisdiction).
                    params("id", SpUtils.getInt(ConstantUtils.UserId, context)).
                    execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            获取权限中.dismiss();
                            Log.d(TAG, "onSuccess: " + response.body());
                            HavePermissionBean bean =
                                    new Gson().fromJson(response.body().toString(), HavePermissionBean.class);
                            if (bean.isSuccess()) {
                                flag = bean.isData() ? 0 : 1;
                                if (!bean.isData()) {
                                    ToastUtils.showToast(context, "权限不足");
                                }
                            } else {
                                flag = -1;
                                ToastUtils.showToast(context, "网络不好，请重试");
                            }
                        }

                        @Override
                        public void onError(Response<String> response) {
                            super.onError(response);
                            获取权限中.dismiss();
                            ToastUtils.showToast(context, "网络不好，请重试");
                            flag = -1;
                        }
                    });
        }
        获取权限中.dismiss();
        return flag;
    }

    public static void down(View v) {
        String hint = ((Button) v).getHint().toString();
        OkGo.<File>post(hint).execute(new FileCallback() {
            @Override
            public void onSuccess(Response<File> response) {
                File body = response.body();
                EventBus.getDefault().post(body);
            }

            @Override
            public void onError(Response<File> response) {
                super.onError(response);

            }
        });
    }

    public static void getWordFileIntent(String param, Context context) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(param));
        intent.setDataAndType(uri, "application/msword");
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(context, "找不到可以打开该文件的程序", Toast.LENGTH_SHORT).show();
        }
    }
}