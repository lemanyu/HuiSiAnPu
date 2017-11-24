package com.hsap.huisianpu.pager;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseFragmentPager;

/**
 * 待我审批
 */

public class WorkMyApprovalPager extends BaseFragmentPager {
    @Override
    public View initView() {

        View view = View.inflate(mActivity, R.layout.pager_work_my_approval, null);
        return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
