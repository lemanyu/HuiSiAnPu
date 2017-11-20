package com.hsap.huisianpu.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.bean.Bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * Created by zhao on 2017/11/18.
 */

public class ContactRecycleAdapter extends BaseAdapter {
    public static Map<Integer, Boolean> isSelected;
    private final List<Bean> list;
    private Context context;
    /**
     * 用来保存之前选中状态的位置
     */
    public List<Integer> hasSelected;

    public ContactRecycleAdapter(Context context, List<Bean> list) {
        hasSelected = new LinkedList<>();
        isSelected = new HashMap<>();
        this.context = context;
        this.list = list;
    }



    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_contacts, null);
            holder.tv_contact_name = convertView.findViewById(R.id.tv_contact_name);
            holder.tv_contact_number = convertView.findViewById(R.id.tv_contact_number);
            holder.cb_contact = convertView.findViewById(R.id.cb_contact);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_contact_name.setText("联系人：" + list.get(position).getName());
        holder.tv_contact_number.setText("联系电话：" + list.get(position).getNumber());

        return convertView;
    }

    public class ViewHolder {
        public TextView tv_contact_name;
        public TextView tv_contact_number;
        public CheckBox cb_contact;
    }
}
