package com.hsap.huisianpu.fragment;


import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseFragment;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zhao on 2017/11/15.
 */

public class NewsFragment extends BaseFragment {
    @BindView(R.id.news_toolbar)
    Toolbar newsToolbar;
    @BindView(R.id.news_rlv)
    RecyclerView newsRlv;
    Unbinder unbinder;

    @Override
    public View initView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_news, null);
        return view;
    }

    @Override
    public void initData() {
        setHasOptionsMenu(true);
        newsToolbar.setOverflowIcon(getResources().getDrawable(R.drawable.add));
        newsToolbar.setTitle("消息");
        ((AppCompatActivity)getActivity()).setSupportActionBar(newsToolbar);

    }

    @Override
    public void initListener() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.news_menu,menu);
        setIconsVisiable(menu,true);
    }

    private void setIconsVisiable(Menu menu, boolean b) {
        //判断menu是否为空
        if(menu != null) {
            try {
                //如果不为空,就反射拿到menu的setOptionalIconsVisible方法
                Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                //暴力访问该方法
                method.setAccessible(true);
                //调用该方法显示icon
                method.invoke(menu, b);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                Toast.makeText(mActivity, "aaa", Toast.LENGTH_SHORT).show();
                break;
            case R.id.create:
                Toast.makeText(mActivity, "bbb", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

}
