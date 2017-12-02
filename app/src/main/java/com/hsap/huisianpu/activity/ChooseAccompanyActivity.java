package com.hsap.huisianpu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.google.gson.Gson;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.adapter.ChooseAdapter;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.bean.SelectApproverBean;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.SpUtils;
import com.hsap.huisianpu.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 选择陪同人
 */

public class ChooseAccompanyActivity extends BaseBackActivity {
    private static final String TAG = "ChooseAccompanyActivity";
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.rlv_choose)
    ListView rlvChoose;
    @BindView(R.id.bt_choose)
    Button btChoose;
    private ChooseAdapter adapter;
    private SelectApproverBean bean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_choose;
    }

    @Override
    public void initView() {
        final LoadingDailog dailog = ToastUtils.showDailog(ChooseAccompanyActivity.this, "正在获取陪同人");
        dailog.show();
        OkGo.<String>post(NetAddressUtils.getWorkersList).params("id", SpUtils.getInt(ConstantUtils.UserId,ChooseAccompanyActivity.this))
        .execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                bean = new Gson().fromJson(response.body().toString(), SelectApproverBean.class);
                if (bean.isSuccess()){
                    dailog.dismiss();
                    adapter = new ChooseAdapter(ChooseAccompanyActivity.this, bean.getData());
                    rlvChoose.setItemsCanFocus(false);
                    rlvChoose.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    rlvChoose.setAdapter(adapter);
                    rlvChoose.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Log.e(TAG, "aaa");
                            ChooseAdapter.ViewHolder holder = (ChooseAdapter.ViewHolder) view.getTag();
                            holder.cb_choose.toggle();
                            adapter.isSelected.put(i,holder.cb_choose.isChecked());
                            //判断是否已经存在，如果已经，则移除，否则添加
                            if (adapter.hasSelected == null) {
                                adapter.hasSelected = new ArrayList<>();
                            }
                            if (adapter.hasSelected.contains(i)) {
                                adapter.hasSelected.remove((Integer) i);
                            } else {
                                adapter.hasSelected.add(i);
                            }
                        }
                    });
                }else{
                    dailog.dismiss();
                    ToastUtils.showToast(ChooseAccompanyActivity.this,"获取失败，当前网络不好");
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                dailog.dismiss();
                ToastUtils.showToast(ChooseAccompanyActivity.this,"获取失败，当前网络不好");
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
        btChoose.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {
             switch (v.getId()){
                 case R.id.bt_choose:
                     choose();
                     break;
             }
    }

    private void choose() {
         if (adapter.hasSelected.size()>0){
             ArrayList<Integer> idlist = new ArrayList<>();
             ArrayList<String> namelist = new ArrayList<>();
             for (int i = 0; i <adapter.hasSelected.size(); i++) {
                 idlist.add(bean.getData().get(adapter.hasSelected.get(i)).getId());
                 namelist.add(bean.getData().get(adapter.hasSelected.get(i)).getName());
             }
             Intent intent = new Intent();
             intent.putStringArrayListExtra("namelist",namelist);
             intent.putIntegerArrayListExtra("idlist",idlist);
             setResult(201,intent);
             finish();

         }else {
             Log.e(TAG, "bbb");
             ToastUtils.showToast(ChooseAccompanyActivity.this,"请选择陪同人");
         }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
