package com.hsap.huisianpu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.hsap.huisianpu.R;
import com.hsap.huisianpu.bean.SelectApproverBean;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhao on 2017/12/1.
 */

public class ChooseAdapter extends BaseAdapter {


    public  Map<Integer, Boolean> isSelected;
    private final List<SelectApproverBean.DataBean> list;
    private Context context;
    public List<Integer> hasSelected;
    public ChooseAdapter(Context context, List<SelectApproverBean.DataBean> list) {
        hasSelected = new LinkedList<>();
        isSelected = new HashMap<>();
        this.context = context;
        this.list = list;
        isCheck();
    }
    public Map<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Map<Integer, Boolean> isSelected) {
        this.isSelected = isSelected;
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
        ViewHolder holder;
        if(view==null){
            holder=new ViewHolder();
            view= View.inflate(context, R.layout.item_choose,null);
            holder.tv_choose_name = view.findViewById(R.id.tv_choose_name);
            holder.cb_choose = view.findViewById(R.id.cb_choose);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
           holder.tv_choose_name.setText(list.get(i).getName());
          holder.cb_choose.setChecked(getIsSelected().get(i));
        holder.cb_choose.setOnCheckedChangeListener(null);
        return view;
    }
    public class ViewHolder {
        public TextView tv_choose_name;

        public CheckBox cb_choose;
    }
    public void isCheck() {
        for (int i = 0; i <list.size(); i++) {
            isSelected.put(i,false);
        }

    }
}
