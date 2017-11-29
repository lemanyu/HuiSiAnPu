package com.hsap.huisianpu.receiver;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.hsap.huisianpu.activity.LoginActivity;
import com.hsap.huisianpu.activity.MainActivity;
import com.hsap.huisianpu.bean.TokenBena;
import com.hsap.huisianpu.service.ForceOfflineService;
import com.hsap.huisianpu.utils.ActivityManagerUtils;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.SpUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tencent.android.tpush.XGPushManager;

/**
 * 通知下线广播
 */

public class ForceOfflineReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {
              intent=new Intent(context, ForceOfflineService.class);
              context.stopService(intent);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("已下线");
        builder.setMessage("您的账户已在另一个设备登录,请尝试重新登陆");
        builder.setCancelable(false);
        builder.setPositiveButton("登录", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ActivityManagerUtils.getInstance().finishActivityclass(MainActivity.class);
                ActivityManagerUtils.getInstance().finishAll();
                XGPushManager.registerPush(context,"*");


                SpUtils.putBoolean(ConstantUtils.Login,false,context);
                SpUtils.putInt(ConstantUtils.UserId,0,context);
                Intent intent = new Intent(context, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
        builder.setNegativeButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                XGPushManager.registerPush(context,SpUtils.getString(ConstantUtils.Username,context));
                OkGo.<String>post(NetAddressUtils.setToken).
                        params("id",SpUtils.getInt(ConstantUtils.UserId,context)).
                        params("token",null).execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        TokenBena bena = new Gson().fromJson(response.body().toString(), TokenBena.class);
                        if(bena.isSuccess()){
                            SpUtils.putBoolean(ConstantUtils.Login,false,context);
                            SpUtils.putInt(ConstantUtils.UserId,0,context);
                        }
                    }
                });


                ActivityManagerUtils.getInstance().exit();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alertDialog.show();
    }
}
