package com.hsap.huisianpu.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.bean.Bean;

import java.util.List;

/**
 * Created by zhao on 2017/11/18.
 */

public class MineRecycleAdapter extends BaseQuickAdapter<Bean,BaseViewHolder> {
    public MineRecycleAdapter(int layoutResId, @Nullable List<Bean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Bean item) {
        helper.setText(R.id.tv_mine,item.getName());
        Glide.with(mContext).load(item.getPic()).into((ImageView) helper.getView(R.id.iv_mine));
    }
}
