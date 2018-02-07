package com.hsap.huisianpu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hsap.huisianpu.base.BaseFragmentPager;

import java.util.List;

/**
 * Created by zhao on 2017/11/23.
 */

public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {
    private  List<BaseFragmentPager>list;

    public ViewPagerFragmentAdapter(FragmentManager fm, List list) {
        super(fm);
        this.list=list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

}
