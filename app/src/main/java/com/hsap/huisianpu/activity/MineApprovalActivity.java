package com.hsap.huisianpu.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.hsap.huisianpu.R;
import com.hsap.huisianpu.adapter.ViewPagerFragmentAdapter;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.base.BaseFragmentPager;
import com.hsap.huisianpu.pager.mine.MineAgreedPager;
import com.hsap.huisianpu.pager.mine.MineOngoingPager;
import com.hsap.huisianpu.pager.mine.MineRefusedPager;
import com.hsap.huisianpu.view.MyViewPager;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.WrapPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的审批
 */

public class MineApprovalActivity extends BaseBackActivity {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.mic_mine_approval)
    MagicIndicator micMineApproval;
    @BindView(R.id.vp_mine_approval)
    MyViewPager vpMineApproval;
    private List<BaseFragmentPager> fragmentList=new ArrayList<>();
    @Override
    public int getLayoutId() {

        return R.layout.activity_mine_approval;

    }

    @Override
    public void initView() {
        fragmentList.add(new MineOngoingPager());
        fragmentList.add(new MineRefusedPager());
        fragmentList.add(new MineAgreedPager());
        initmic();
        vpMineApproval.setAdapter(new ViewPagerFragmentAdapter(getSupportFragmentManager(),fragmentList));
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void processClick(View v) {

    }

    private void initmic() {
        final ArrayList<String> list = new ArrayList<>();
        list.add("审批进行");list.add("审批拒绝");list.add("审批通过");
        micMineApproval.setBackgroundColor(Color.WHITE);
        CommonNavigator navigator = new CommonNavigator(this);
        navigator.setAdjustMode(true);
        navigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return list==null?0:list.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int position) {
                ColorTransitionPagerTitleView view = new ColorTransitionPagerTitleView(context);
                view.setText(list.get(position));
                view.setTextSize(18);
                view.setTextAppearance(context,R.style.LeaveText);
                view.setNormalColor(Color.parseColor("#b3b3b3"));
                view.setSelectedColor(Color.parseColor("#1296db"));
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vpMineApproval.setCurrentItem(position);
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
        micMineApproval.setNavigator(navigator);
        ViewPagerHelper.bind(micMineApproval,vpMineApproval);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
