package com.hsap.huisianpu.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hsap.huisianpu.R;
import com.hsap.huisianpu.bean.Bean;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zhao on 2017/11/22.
 */

public class ApproveGridViewAdapter extends BaseAdapter {
    private Context context;
    private List<Bean> nameList = new ArrayList<>();
     public ApproveGridViewAdapter(Context context,List<Bean>nameList){
         this.context=context;
         this.nameList=nameList;
     }


    @Override
    public int getCount() {
         if(nameList.size()==9){
             return 9;
         }
        return nameList.size()+1;
    }

    @Override
    public Object getItem(int i) {

         return i;
    }

    @Override
    public long getItemId(int i) {

         return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
         view=View.inflate(context, R.layout.item_approve,null);
        ImageView iv_approve_jiantou = view.findViewById(R.id.iv_approve_jiantou);
        TextView tv_approve_name = view.findViewById(R.id.tv_approve_name);
        if(position==nameList.size()){
            tv_approve_name.setBackground(ContextCompat.getDrawable(context,R.drawable.tianjia));
            iv_approve_jiantou.setVisibility(View.INVISIBLE);
            tv_approve_name.setText("");
            if (position==9){
                tv_approve_name.setVisibility(View.INVISIBLE);
            }
        }else {
            Bean bean = nameList.get(position);
            if(position==8){
                iv_approve_jiantou.setVisibility(View.INVISIBLE);
            }else {
                iv_approve_jiantou.setVisibility(View.VISIBLE);
            }
            tv_approve_name.setBackground(ContextCompat.getDrawable(context,bean.getPic()));
            tv_approve_name.setText(bean.getName());
        }

        return view;
    }
}
