package com.hsap.huisianpu.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;

import com.hsap.huisianpu.R;
import com.hsap.huisianpu.adapter.ViewPagerFragmentAdapter;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.base.BaseFragmentPager;
import com.hsap.huisianpu.pager.work.WorkAllProjectPager;
import com.hsap.huisianpu.pager.work.WorkCompleteProjectPager;
import com.hsap.huisianpu.pager.work.WorkCondutProjectPager;
import com.hsap.huisianpu.pager.work.WorkExamineProjectPager;
import com.hsap.huisianpu.pager.work.WorkSeeProjectPager;
import com.hsap.huisianpu.pager.work.WorkUploadProjectPager;
import com.hsap.huisianpu.view.MyViewPager;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.WrapPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 查看项目
 */

public class WorkSeeProjectActivity extends BaseBackActivity {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.mic_work_project)
    MagicIndicator micWorkProject;
    @BindView(R.id.vp_work_project)
    MyViewPager vpWorkProject;
    private List<WorkSeeProjectPager> frgamentList=new ArrayList<>();
    private ArrayList<String> list;

    @Override
    public int getLayoutId() {

        return R.layout.activity_work_see_project;
    }

    @Override
    public void initView() {
         /*  frgamentList.add(new WorkAllProjectPager());
           frgamentList.add(new WorkCondutProjectPager());
           frgamentList.add(new WorkCompleteProjectPager());
           frgamentList.add(new WorkUploadProjectPager());
           frgamentList.add(new WorkExamineProjectPager());*/

           initMic();

        frgamentList.add(new WorkSeeProjectPager().state(-1));
        frgamentList.add(new WorkSeeProjectPager().state(0));
        frgamentList.add(new WorkSeeProjectPager().state(4));
        vpWorkProject.setAdapter(new ViewPagerFragmentAdapter(getSupportFragmentManager(),frgamentList));
    }


    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
        vpWorkProject.setOnPageChangeListener(getListener());
    }

    @NonNull
    private ViewPager.OnPageChangeListener getListener() {
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                micWorkProject.onPageScrolled(position,positionOffset,positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                micWorkProject.onPageSelected(position);
                frgamentList.get(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            micWorkProject.onPageScrollStateChanged(state);
            }
        };
    }

    @Override
    public void processClick(View v) {

    }

    private void initMic() {
        list = new ArrayList<>();
        list.add("全部项目");
        list.add("进行项目");
        list.add("完成项目");
        micWorkProject.setBackgroundColor(Color.WHITE);
        CommonNavigator navigator = new CommonNavigator(this);
        navigator.setAdjustMode(true);
        navigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return list ==null?0: list.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int i) {
                SimplePagerTitleView view = new ColorTransitionPagerTitleView(context);
                view.setTextAppearance(context,R.style.LeaveText);
                view.setTextSize(18);
                view.setText(list.get(i));
                view.setNormalColor(Color.parseColor("#b3b3b3"));
                view.setSelectedColor(Color.parseColor("#1296db"));
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vpWorkProject.setCurrentItem(i);
                    }
                });

                return view;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                WrapPagerIndicator indicator = new WrapPagerIndicator(context);
                return indicator;
            }
        });
        micWorkProject.setNavigator(navigator);
        ViewPagerHelper.bind(micWorkProject,vpWorkProject);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
