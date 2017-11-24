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
import com.hsap.huisianpu.pager.work.WorkDayPager;
import com.hsap.huisianpu.pager.work.WorkMonthPager;
import com.hsap.huisianpu.pager.work.WorkWeekPager;
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
 * 查看汇报
 */

public class WorkCheckReportActivity extends BaseBackActivity {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.mic_work_check_peport)
    MagicIndicator micWorkCheckPeport;
    @BindView(R.id.vp_work_check_peport)
    MyViewPager vpWorkCheckPeport;
    private List<BaseFragmentPager> frgamentList=new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_work_checkreport;
    }

    @Override
    public void initView() {
        frgamentList.add(new WorkDayPager());
        frgamentList.add(new WorkWeekPager());
        frgamentList.add(new WorkMonthPager());
        initMic();
        vpWorkCheckPeport.setAdapter(new ViewPagerFragmentAdapter(getSupportFragmentManager(),frgamentList));

    }

    private void initMic() {
        final ArrayList<String> list = new ArrayList<>();
        list.add("日报");list.add("周报");list.add("月报");
        micWorkCheckPeport.setBackgroundColor(Color.WHITE);
        CommonNavigator navigator = new CommonNavigator(this);
        navigator.setAdjustMode(true);
        navigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return list==null?0:list.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int position) {
                SimplePagerTitleView view = new ColorTransitionPagerTitleView(context);
                view.setText(list.get(position));
                view.setTextSize(18);
                view.setTextAppearance(WorkCheckReportActivity.this,R.style.LeaveText);
                view.setNormalColor(Color.parseColor("#b3b3b3"));
                view.setSelectedColor(Color.parseColor("#1296db"));
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vpWorkCheckPeport.setCurrentItem(position);
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
        micWorkCheckPeport.setNavigator(navigator);
        ViewPagerHelper.bind(micWorkCheckPeport, vpWorkCheckPeport);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
        vpWorkCheckPeport.setOnPageChangeListener(getListener());
    }

    @NonNull
    private ViewPager.OnPageChangeListener getListener() {
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                 micWorkCheckPeport.onPageScrolled(position,positionOffset,positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                  micWorkCheckPeport.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                 micWorkCheckPeport.onPageScrollStateChanged(state);
            }
        };
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
