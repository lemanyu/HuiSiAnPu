package com.hsap.huisianpu.pager.work;

import android.view.View;

import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseFragmentPager;

/**
 * 全部项目
 */

public class WorkAllProjectPager extends BaseFragmentPager {
    @Override
    public View initView()
    {
        View view = View.inflate(mActivity, R.layout.pager_work_all_project, null);
        return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
