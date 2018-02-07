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
import com.hsap.huisianpu.pager.mine.MineAllProjectPager;
import com.hsap.huisianpu.pager.mine.MineComPleteProjectPager;
import com.hsap.huisianpu.pager.mine.MineCondutProjectPager;
import com.hsap.huisianpu.pager.mine.MineExamineProjectPager;
import com.hsap.huisianpu.pager.mine.MineSeeProjectPager;
import com.hsap.huisianpu.pager.mine.MineUploadProjectPager;
import com.hsap.huisianpu.pager.work.WorkSeeProjectPager;
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
 * 我的项目
 */

public class MineProjectActivity extends BaseBackActivity {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.mic_mine_project)
    MagicIndicator micMineProject;
    @BindView(R.id.vp_mine_project)
    MyViewPager vpMineProject;
    private List<MineSeeProjectPager> frgamentList=new ArrayList<>();
    private ArrayList<String> list;

    @Override
    public int getLayoutId() {

        return R.layout.activity_mine_project;
    }

    @Override
    public void initView() {
        initMic();

       frgamentList.add(new MineSeeProjectPager().state(0));
       frgamentList.add(new MineSeeProjectPager().state(1));
       frgamentList.add(new MineSeeProjectPager().state(4));

    }

    @Override
    public void initData() {

        vpMineProject.setAdapter(new ViewPagerFragmentAdapter(getSupportFragmentManager(),frgamentList));

    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
        vpMineProject.setOnPageChangeListener(getListener());
    }

    @NonNull
    private ViewPager.OnPageChangeListener getListener() {
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                 micMineProject.onPageScrolled(position,positionOffset,positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                micMineProject.onPageSelected(position);
                frgamentList.get(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                micMineProject.onPageScrollStateChanged(state);
            }
        };
    }

    private void initMic() {
        list = new ArrayList<>();
        list.add("进行项目");
        list.add("等待审核");
        list.add("完成项目");
        micMineProject.setBackgroundColor(Color.WHITE);
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
                        vpMineProject.setCurrentItem(i);
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
        micMineProject.setNavigator(navigator);
        ViewPagerHelper.bind(micMineProject,vpMineProject);
    }
    @Override
    public void processClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
