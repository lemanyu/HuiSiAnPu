package com.hsap.huisianpu.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.bean.Bean;

import java.util.List;

/**
 * Created by zhao on 2017/11/18.
 */

public class ContactRecycleAdapter extends BaseQuickAdapter<Bean,BaseViewHolder> {
    public ContactRecycleAdapter(int layoutResId, @Nullable List<Bean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Bean item) {
        helper.setText(R.id.tv_contact_name,"联系人："+item.getName())
                .setText(R.id.tv_contact_number,"联系电话："+item.getNumber());
        CheckBox checkBox = helper.getView(R.id.cb_contact);

    }
}
