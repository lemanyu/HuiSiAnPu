package com.hsap.huisianpu.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.bean.SelectApproverBean;

import java.util.List;

/**
 * Created by zhao on 2017/11/22.
 */

public class SelectApproverRecycleAdapter extends BaseQuickAdapter<SelectApproverBean.UsersBean,BaseViewHolder> {

    public SelectApproverRecycleAdapter(int layoutResId, @Nullable List<SelectApproverBean.UsersBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectApproverBean.UsersBean item) {
        helper.setText(R.id.tv_select_name,item.getSName()).
                setText(R.id.tv_approve_guanliyuan,item.getJName());
    }
}
