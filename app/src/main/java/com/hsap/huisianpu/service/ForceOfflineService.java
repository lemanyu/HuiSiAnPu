package com.hsap.huisianpu.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.hsap.huisianpu.receiver.ForceOfflineReceiver;

/**
 * 通知下线服务
 */

public class ForceOfflineService extends Service {
    ForceOfflineReceiver forceOfflineReceiver;
    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.hsap.huisianpu");
        if(forceOfflineReceiver==null){
            forceOfflineReceiver=new ForceOfflineReceiver();
        }
        registerReceiver(forceOfflineReceiver,intentFilter);
        Intent intent = new Intent("com.hsap.huisianpu");
        getApplication().sendBroadcast(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(forceOfflineReceiver);
    }
}
