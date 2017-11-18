package com.hsap.huisianpu.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.bean.Bean;

import java.util.List;

/**
 * Created by zhao on 2017/11/17.
 */

public class MyAdapter extends BaseQuickAdapter<Bean,BaseViewHolder> {


    public MyAdapter(int layoutResId, @Nullable List<Bean> data) {
        super(layoutResId, data);
        this.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    @Override
    protected void convert(BaseViewHolder holder, Bean item) {
         holder.setText(R.id.work_tv,item.getName());
        Glide.with(mContext).load(item.getPic()).into((ImageView) holder.getView(R.id.work_iv));
    }
}
