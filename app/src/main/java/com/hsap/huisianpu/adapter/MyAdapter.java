package com.hsap.huisianpu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hsap.huisianpu.R;

import java.util.List;

/**
 * 回显时的gridview
 */

public class MyAdapter extends BaseAdapter {
    private  Context context;
    private List<String> accompanying;
    private int[] color = {R.mipmap.chengyuan, R.mipmap.fenyuan, R.mipmap.lanyuan,
            R.mipmap.luyuan, R.mipmap.ziyuan, R.mipmap.hongyuan};
    public MyAdapter(Context context,List<String> accompanying){
           this.context=context;
           this.accompanying=accompanying;
       }
    @Override
    public int getCount() {
       return accompanying.size();
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
        view=View.inflate(context, R.layout.item_details_trip,null);
        TextView details_tv_name= view.findViewById(R.id.details_tv_name);
        details_tv_name.setText(accompanying.get(i));
        details_tv_name.setBackgroundResource(color[(int) (Math.random() * 6)]);
        return view;
    }
}
