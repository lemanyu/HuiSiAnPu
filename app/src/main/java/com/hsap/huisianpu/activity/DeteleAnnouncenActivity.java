package com.hsap.huisianpu.activity;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.google.gson.Gson;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.bean.AllGongGaoBean;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.SpUtils;
import com.hsap.huisianpu.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 删除公告
 * @author zhao
 */

public class DeteleAnnouncenActivity extends BaseBackActivity {
    private static final String TAG = "DeteleAnnouncenActivity";
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.bt_out_commit)
    Button btOutCommit;
    @BindView(R.id.lv_detele)
    ListView lvDetele;
    private MyAdapter adapter;
    private AllGongGaoBean bean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_detele_announcen;
    }

    @Override
    public void initView() {
        final LoadingDailog dailog = ToastUtils.showDailog(this, "正在获取公告");
        dailog.show();
        OkGo.<String>post(NetAddressUtils.selectAllNotice).
                params("workersId", SpUtils.getInt(ConstantUtils.UserId,getApplicationContext())).
                execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                dailog.dismiss();
                Log.e(TAG, "onSuccess: "+response.body().toString() );
                bean = new Gson().fromJson(response.body().toString(), AllGongGaoBean.class);
                if(adapter==null){
                    adapter = new MyAdapter(bean.getData());
                }
                lvDetele.setAdapter(adapter);
                lvDetele.setItemsCanFocus(false);
                lvDetele.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                lvDetele.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        MyAdapter.ViewHolder holder = (MyAdapter.ViewHolder) view.getTag();
                        holder.cb_delete.toggle();
                        adapter.isSelected.put(i,holder.cb_delete.isChecked());
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

            }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dailog.dismiss();
                        ToastUtils.showToast(getApplicationContext(),"获取失败");
                    }
                });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
        btOutCommit.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()){
            case R.id.bt_out_commit:
                delete();
                break;
                default:
        }
    }

    private void delete() {
        if (adapter.hasSelected.size()>0){
            ArrayList<Integer> idList = new ArrayList<>();
            idList.clear();
            for (int i = 0; i <adapter.hasSelected.size(); i++) {
                idList.add(bean.getData().get(adapter.hasSelected.get(i)).getId());
            }
            for (int i = 0; i <bean.getData().size(); i++) {
                if(adapter.getIsSelected().get(i)){
                    bean.getData().remove(bean.getData().get(i));
                }
            }
             OkGo.<String>post(NetAddressUtils.deleteNotice).
                     params("noticeId",new Gson().toJson(idList)).
                     execute(new StringCallback() {
                         @Override
                         public void onSuccess(Response<String> response) {
                             if (response.body().equals("true")){
                                 adapter.isCheck();
                                 adapter.notifyDataSetChanged();
                                 ToastUtils.showToast(getApplicationContext(),"删除成功");
                             }else {
                                 ToastUtils.showToast(getApplicationContext(),"删除失败");
                             }

                         }

                         @Override
                         public void onError(Response<String> response) {
                             super.onError(response);
                             ToastUtils.showToast(getApplicationContext(),"删除失败");
                         }
                     });

        }else {
            ToastUtils.showToast(getApplicationContext(),"请选择要删除的公告");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        setResult(Activity.RESULT_FIRST_USER);
    }

    class MyAdapter extends BaseAdapter{
        public  List<Integer> hasSelected;
        public  Map<Integer, Boolean> isSelected;
        private List<AllGongGaoBean.DataBean> list;
        public  Map<Integer, Boolean> getIsSelected() {
            return isSelected;
        }
        public void setIsSelected(Map<Integer, Boolean> isSelected) {
            this.isSelected = isSelected;
        }
      public MyAdapter( List<AllGongGaoBean.DataBean> list){
          isSelected = new HashMap<>();
          this.list = list;
          hasSelected=new LinkedList<>();
          isCheck();
      }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder=null;
            if(view==null){
                holder=new ViewHolder();
                view=View.inflate(getApplicationContext(),R.layout.item_delete,null);
                holder.tv_delete_name=view.findViewById(R.id.tv_delete_name);
                holder.cb_delete=view.findViewById(R.id.cb_delete);
                view.setTag(holder);
            }else {
                holder= (ViewHolder) view.getTag();
            }
            Log.d(TAG, "getView: "+list.get(i));
            holder.tv_delete_name.setText(list.get(i).getNoticeBody());
            holder.cb_delete.setChecked(getIsSelected().get(i));
            holder.cb_delete.setOnCheckedChangeListener(null);
            return view;
        }
        public class ViewHolder {
            public TextView tv_delete_name;

            public CheckBox cb_delete;
        }
        public void isCheck() {
            for (int i = 0; i <list.size(); i++) {
                isSelected.put(i,false);
            }

        }
    }


}
