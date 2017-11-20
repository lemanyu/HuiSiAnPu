package com.hsap.huisianpu.activity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.adapter.ContactRecycleAdapter;
import com.hsap.huisianpu.base.BaseActivity;
import com.hsap.huisianpu.bean.Bean;
import com.hsap.huisianpu.bean.InvitationBean;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.SpUtils;
import com.hsap.huisianpu.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PostRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhao on 2017/11/18.
 */

public class ContactsActivity extends BaseActivity {
    private static final String TAG = "ContactsActivity";
    @BindView(R.id.bt_yaoqing)
    Button btYaoqing;
    @BindView(R.id.rlv_yaoqing)
    ListView rlvYaoqing;
    @BindView(R.id.back)
    ImageButton back;
    private Map<Integer, Boolean> map = new HashMap<>();// 存放已被选中的CheckBox
    private ContactRecycleAdapter adapter;
    private List<Bean> list;

    @Override
    public int getLayoutId() {
        return R.layout.activity_contacts;
    }

    @Override
    public void initView() {

        back.setOnClickListener(this);
        btYaoqing.setOnClickListener(this);
    }

    @Override
    public void initData() {
        LoadingDailog dailog = ToastUtils.showDailog(ContactsActivity.this, "正在获取联系人");
        list = getContacts(dailog);

        adapter = new ContactRecycleAdapter(ContactsActivity.this, list);

        rlvYaoqing.setAdapter(adapter);
        rlvYaoqing.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ContactRecycleAdapter.ViewHolder holder = (ContactRecycleAdapter.ViewHolder) view.getTag();
                holder.cb_contact.toggle();
                adapter.isSelected.put(i, holder.cb_contact.isChecked());
                //判断是否已经存在，如果已经，则移除，否则添加
                if (adapter.hasSelected == null) {
                    adapter.hasSelected = new LinkedList<>();
                }
                if (adapter.hasSelected.contains(i)) {
                    adapter.hasSelected.remove((Integer) i);
                } else {
                    adapter.hasSelected.add(i);
                }
            }
        });
    }

    @Override
    public void initListener() {

    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.bt_yaoqing:
                yaoqing();
                break;
        }
    }

    private void yaoqing() {
        if (adapter.hasSelected.size() > 0) {
            final LoadingDailog dailog = ToastUtils.showDailog(ContactsActivity.this, "正在邀请中");
            ArrayList<String> nameList = new ArrayList<>();
            for (int i = 0; i < adapter.hasSelected.size(); i++) {
                nameList.add(list.get(adapter.hasSelected.get(i)).getNumber());
            }
            Gson gson = new Gson();
            String json = gson.toJson(nameList);
            //提交到服务器上
            OkGo.<String>post(NetAddressUtils.invitation).params("id", SpUtils.getInt(ConstantUtils.UserId, ContactsActivity.this))
                    .params("phones", json).execute(new StringCallback() {
                @Override
                public void onSuccess(Response<String> response) {
                    dailog.dismiss();
                    InvitationBean bean = new Gson().fromJson(response.body().toString(), InvitationBean.class);
                    if (bean.isSuccess()) {
                        ToastUtils.showToast(ContactsActivity.this, bean.getMsg());
                    } else {
                        ToastUtils.showToast(ContactsActivity.this, "邀请失败");
                    }
                }
            });

        } else {
            ToastUtils.showToast(ContactsActivity.this, "请选择你要邀请的人");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public List<Bean> getContacts(LoadingDailog dailog) {
        ContentResolver cr = getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = {
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };
        Cursor cursor = cr.query(uri, projection, null, null, null);
        if (cursor == null) {
            dailog.dismiss();
            ToastUtils.showToast(ContactsActivity.this, "查找不到");
            return null;
        }
        ArrayList<Bean> list = new ArrayList<>();
        while (cursor.moveToNext()) {
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
