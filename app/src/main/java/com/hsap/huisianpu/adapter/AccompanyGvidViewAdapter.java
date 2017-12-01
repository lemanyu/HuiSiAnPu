package com.hsap.huisianpu.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hsap.huisianpu.R;
import com.hsap.huisianpu.bean.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhao on 2017/12/1.
 */

public class AccompanyGvidViewAdapter extends BaseAdapter {
    private Context context;
    private List<Bean> nameList = new ArrayList<>();
    public AccompanyGvidViewAdapter(Context context, List<Bean> nameList){

        this.context=context;
        this.nameList=nameList;
    }
    @Override
    public int getCount() {
        return nameList.size()+1;
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        view= View.inflate(context, R.layout.approve_tv,null);
        TextView tv_approve = view.findViewById(R.id.tv_approve);
        if(position==nameList.size()){
            tv_approve.setBackground(ContextCompat.getDrawable(context,R.drawable.tianjia));
            tv_approve.setText("");
        }else {
            Bean bean = nameList.get(position);
            tv_approve.setBackground(ContextCompat.getDrawable(context,bean.getPic()));
            tv_approve.setText(bean.getName());
        }
        return view;
    }
}
