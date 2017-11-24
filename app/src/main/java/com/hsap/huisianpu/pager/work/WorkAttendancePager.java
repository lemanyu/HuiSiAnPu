package com.hsap.huisianpu.pager.work;

import android.view.View;

import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseFragmentPager;

/**
 * 全勤
 */

public class WorkAttendancePager extends BaseFragmentPager {
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.pager_work_attendance, null);
        return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
