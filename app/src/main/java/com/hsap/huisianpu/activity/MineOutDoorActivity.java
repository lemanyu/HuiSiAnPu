package com.hsap.huisianpu.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;

import com.hsap.huisianpu.R;
import com.hsap.huisianpu.adapter.ViewPagerFragmentAdapter;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.base.BaseFragmentPager;
import com.hsap.huisianpu.pager.mine.MineCarPager;
import com.hsap.huisianpu.pager.mine.MineLeavePager;
import com.hsap.huisianpu.pager.mine.MineTripPager;
import com.hsap.huisianpu.pager.mine.MineoutPager;
import com.hsap.huisianpu.pager.mine.MineoverTimePager;
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
 * 我的外勤
 */

public class MineOutDoorActivity extends BaseBackActivity {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.mic_mine_out_door)
    MagicIndicator micMineOutDoor;
    @BindView(R.id.vp_mine_out_door)
    MyViewPager vpMineOutDoor;
    private List<BaseFragmentPager> frgamentList=new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_mine_out_door;
    }

    @Override
    public void initView() {
        frgamentList.add(new MineLeavePager());
        frgamentList.add(new MineoutPager());
        frgamentList.add(new MineTripPager());
        frgamentList.add(new MineoverTimePager());
        frgamentList.add(new MineCarPager());
    }

    private void initMic() {
        final ArrayList<String> list = new ArrayList<>();
        list.add("我的请假");list.add("我的外出");list.add("我的出差");list.add("我的加班");list.add("我的用车");
        micMineOutDoor.setBackgroundColor(Color.WHITE);
        CommonNavigator navigator = new CommonNavigator(this);
        navigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {

                return list==null?0:list.size();
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
                        vpMineOutDoor.setCurrentItem(i);
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
        micMineOutDoor.setNavigator(navigator);
        ViewPagerHelper.bind(micMineOutDoor,vpMineOutDoor);
    }

    @Override
    public void initData() {
        initMic();
        vpMineOutDoor.setAdapter(new ViewPagerFragmentAdapter(getSupportFragmentManager(),frgamentList));
    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
        vpMineOutDoor.setOnPageChangeListener(getListener());
    }
    private ViewPager.OnPageChangeListener getListener() {
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                micMineOutDoor.onPageScrolled(position,positionOffset,positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                micMineOutDoor.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                micMineOutDoor.onPageScrollStateChanged(state);
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
