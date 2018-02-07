package com.hsap.huisianpu.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.bean.InviteBean;

import java.util.List;

/**
 * Created by zhao on 2017/11/21.
 */

public class InviteRecycleAdapter extends BaseQuickAdapter<InviteBean.DataBean,BaseViewHolder> {


    public InviteRecycleAdapter(int layoutResId, @Nullable List<InviteBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, InviteBean.DataBean item) {

    }
}
