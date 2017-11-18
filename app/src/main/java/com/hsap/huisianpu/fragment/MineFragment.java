package com.hsap.huisianpu.fragment;

import android.view.LayoutInflater;
import android.view.View;

import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseFragment;

/**
 * Created by zhao on 2017/11/15.
 */

public class MineFragment extends BaseFragment {
    @Override
    public View initView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_mine, null);
        return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
