package com.hsap.huisianpu.activity;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseBackActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by zhao on 2017/12/19.
 */

public class WorkPerformanceActivity extends BaseBackActivity {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.bt_work_day_commit)
    Button btWorkDayCommit;
    @BindView(R.id.elv)
    ExpandableListView elv;
    private static final String TAG="WorkPerformanceActivity";
    private Map<String, List<String>> dataset = new HashMap<>();
    public String[] groupStrings = { "我的好友", "我的家人"};

    @Override
    public int getLayoutId() {
        return R.layout.activity_work_performance;
    }

    @Override
    public void initView() {
        ArrayList<String> oneList = new ArrayList<>();
        ArrayList<String> twoList = new ArrayList<>();
        oneList.add("瓜瓜");
        oneList.add("太湖");
        oneList.add("太湖");
        oneList.add("太湖");
        oneList.add("太湖");
        twoList.add("大姐");
        twoList.add("肥肥");
        twoList.add("二姐");
        twoList.add("爸爸");
        twoList.add("爸爸");
        dataset.put(groupStrings[0],oneList);
        dataset.put(groupStrings[1],twoList);
        elv.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
    }

    @Override
    public void initData() {
       elv.setAdapter(new MyExpandableListViewAdapter());

    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
        btWorkDayCommit.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.bt_work_day_commit:
                commit();
                break;
            default:
        }
    }

    private void commit() {

    }

     class MyExpandableListViewAdapter extends BaseExpandableListAdapter {
         //  获得某个父项的某个子项
         @Override
         public Object getChild(int parentPos, int childPos) {
             return dataset.get(groupStrings[parentPos]).get(childPos);
         }

         //  获得父项的数量
         @Override
         public int getGroupCount() {
             return dataset.size();
         }

         //  获得某个父项的子项数目
         @Override
         public int getChildrenCount(int parentPos) {
             return dataset.get(groupStrings[parentPos]).size();
         }

         //  获得某个父项
         @Override
         public Object getGroup(int parentPos) {
             return dataset.get(groupStrings[parentPos]);
         }

         //  获得某个父项的id
         @Override
         public long getGroupId(int parentPos) {
             return parentPos;
         }

         //  获得某个父项的某个子项的id
         @Override
         public long getChildId(int parentPos, int childPos) {
             return childPos;
         }

         @Override
         public boolean hasStableIds() {
             return true;
         }

         @Override
         public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
             view=View.inflate(WorkPerformanceActivity.this,R.layout.father,null);
             TextView textView = view.findViewById(R.id.tv_father);
             textView.setText(groupStrings[i]);
             EditText et_father = view.findViewById(R.id.et_father);
             return textView;
         }

         @Override
         public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
             view=View.inflate(WorkPerformanceActivity.this,R.layout.item_elv,null);
             TextView rlv_item = view.findViewById(R.id.rlv_item);
             rlv_item.setText(dataset.get(groupStrings[i]).get(i1));
             return view;
         }

         @Override
         public boolean isChildSelectable(int i, int i1) {
             return true;
         }
     }
     class MyAdapter extends BaseQuickAdapter<String,BaseViewHolder>{


         public MyAdapter(int layoutResId, @Nullable List<String> data) {
             super(layoutResId, data);
         }

         @Override
         protected void convert(BaseViewHolder helper, String item) {
             helper.setText(R.id.tv_rlv_item,item);
         }


     }
}

