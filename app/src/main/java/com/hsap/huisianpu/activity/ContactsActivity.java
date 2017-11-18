package com.hsap.huisianpu.activity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.android.tu.loadingdialog.LoadingDailog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.adapter.ContactRecycleAdapter;
import com.hsap.huisianpu.base.BaseActivity;
import com.hsap.huisianpu.bean.Bean;
import com.hsap.huisianpu.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhao on 2017/11/18.
 */

public class ContactsActivity extends BaseActivity {
    @BindView(R.id.bt_yaoqing)
    Button btYaoqing;
    @BindView(R.id.rlv_yaoqing)
    RecyclerView rlvYaoqing;
    @BindView(R.id.back)
    ImageButton back;

    @Override
    public int getLayoutId() {
        return R.layout.activity_contacts;
    }

    @Override
    public void initView() {
        back.setOnClickListener(this);
    }

    @Override
    public void initData() {
        LoadingDailog dailog = new LoadingDailog.Builder(this)
                .setMessage("正在获取联系人")
                .setCancelable(true)
                .setCancelOutside(true).create();
        dailog.show();
        List<Bean> list = getContacts(dailog);
        ContactRecycleAdapter adapter = new ContactRecycleAdapter(R.layout.item_contacts, list);
        rlvYaoqing.setLayoutManager(new LinearLayoutManager(this));
        rlvYaoqing.setAdapter(adapter);
        rlvYaoqing.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
    }

    @Override
    public void initListener() {

    }

    @Override
    public void processClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public List<Bean> getContacts(LoadingDailog dailog) {
        ContentResolver cr = getContentResolver();
        Uri uri=ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String [] projection={
          ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
          ContactsContract.CommonDataKinds.Phone.NUMBER
        };
        Cursor cursor = cr.query(uri, projection, null, null, null);
        if(cursor==null){
            dailog.dismiss();
            ToastUtils.showToast(ContactsActivity.this,"查找不到");
           return null;
        }
        ArrayList<Bean> list = new ArrayList<>();
        while (cursor.moveToNext()){
            String name = cursor.getString(0);
            String number = cursor.getString(1);
            Bean bean = new Bean();
            bean.setName(name);
            bean.setNumber(number);
            list.add(bean);
        }
        cursor.close();
        dailog.dismiss();
        return list;
    }
}
