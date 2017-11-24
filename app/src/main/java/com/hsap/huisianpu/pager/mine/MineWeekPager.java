package com.hsap.huisianpu.pager.mine;

import android.view.View;

import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseFragmentPager;

/**
 * Created by zhao on 2017/11/23.
 */

public class MineWeekPager extends BaseFragmentPager {
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.pager_mine_week, null);
        return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
