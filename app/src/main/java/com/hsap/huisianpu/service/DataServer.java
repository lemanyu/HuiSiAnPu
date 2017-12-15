package com.hsap.huisianpu.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.hsap.huisianpu.bean.DataBean;
import com.hsap.huisianpu.bean.PushAnnouncenBean;

import org.greenrobot.eventbus.EventBus;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhao on 2017/12/14.
 */

public class DataServer extends IntentService {
    private static String TAG="DataServer";
    private ArrayList<DataBean> list=new ArrayList();
    private List<DataBean> all;

    public DataServer() {
        super("DataServer");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        all = DataSupport.findAll(DataBean.class,true);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        list.addAll(all);
        //写数据库
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");
        String customContent = intent.getStringExtra("customContent");
        PushAnnouncenBean bean = new Gson().fromJson(customContent, PushAnnouncenBean.class);
        DataBean dataBean = new DataBean();
        dataBean.setTitle(title);
        dataBean.setContent(content);
        int id=1;
        if(list.size()!=0){
           id= list.get(list.size() - 1).getNumberId() + 1;
        }
        dataBean.setNumberId(id);
        dataBean.setType(bean.getType());
        dataBean.setWorkId(bean.getId());
        dataBean.setDate(new Date());
        Log.e(TAG, "onHandleIntent: "+new Date() );
        if (bean.isState()){
            dataBean.setState(1);//1不能处理
        }else {
            dataBean.setState(0);//0不能处理
        }
        dataBean.setCheck(true);
        dataBean.save();
        //消息列表显示
        EventBus.getDefault().post(dataBean);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();


    }
}
