package com.hsap.huisianpu.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hsap.huisianpu.R;
import com.hsap.huisianpu.adapter.ViewPagerFragmentAdapter;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.base.BaseFragmentPager;
import com.hsap.huisianpu.bean.EventDate;
import com.hsap.huisianpu.pager.mine.MineCCPager;
import com.hsap.huisianpu.pager.mine.MineDayPager;
import com.hsap.huisianpu.pager.mine.MineMonthPager;
import com.hsap.huisianpu.pager.mine.MinePerformancePager;
import com.hsap.huisianpu.pager.mine.MineWeekPager;
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

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的汇报
 */

public class MineReportActivity extends BaseBackActivity {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.mic_mine_report)
    MagicIndicator micMineReport;
    @BindView(R.id.vp_mine_report)
    MyViewPager vpMineReport;
    @BindView(R.id.tv_mine_huibao)
    TextView tvMineHuibao;
    @BindView(R.id.tv_checkreport_time)
    TextView tvCheckreportTime;
    @BindView(R.id.ll_checkreport_time)
    LinearLayout llCheckreportTime;
    private List<BaseFragmentPager> fragmentList = new ArrayList<>();

    @Override
    public int getLayoutId() {

        return R.layout.activity_mine_report;
    }

    @Override
    public void initView() {
        Calendar instance = Calendar.getInstance();
       int year = instance.get(Calendar.YEAR);
       int month = instance.get(Calendar.MONTH)+1;
       int day = instance.get(Calendar.DAY_OF_MONTH);
        tvCheckreportTime.setText(year+"."+month);
        fragmentList.add(new MineDayPager());
        fragmentList.add(new MineWeekPager());
        fragmentList.add(new MineMonthPager());
        fragmentList.add(new MineCCPager());
        fragmentList.add(new MinePerformancePager());
        initMic();
        vpMineReport.setOffscreenPageLimit(5);
        vpMineReport.setAdapter(new ViewPagerFragmentAdapter(getSupportFragmentManager(), fragmentList));
    }


    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
        llCheckreportTime.setOnClickListener(this);
        vpMineReport.addOnPageChangeListener(getListener());
    }

    private ViewPager.OnPageChangeListener getListener() {
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                micMineReport.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tvMineHuibao.setText("我的日报");
                        break;
                    case 1:
                        tvMineHuibao.setText("我的周报");
                        break;
                    case 2:
                        tvMineHuibao.setText("我的月报");
                        break;
                    case 3:
                        tvMineHuibao.setText("抄送信息");
                        break;
                    case 4:
                        tvMineHuibao.setText("绩效自评");
                        break;
                    default:
                }
                micMineReport.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                micMineReport.onPageScrollStateChanged(state);
            }
        };
    }

    @Override
    public void processClick(View v) {
           switch (v.getId()){
               case R.id.ll_checkreport_time:
                   Calendar instance = Calendar.getInstance();
                   int  year = instance.get(Calendar.YEAR);
                   int month = instance.get(Calendar.MONTH);
                   int day = instance.get(Calendar.DAY_OF_MONTH);
                   DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                       @Override
                       public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                           EventBus.getDefault().post(new EventDate(i,i1+1,i2));
                           int a=i1+1;
                           tvCheckreportTime.setText(i+"-"+a);
                       }
                   },year,month,day);
                   dialog.show();
                   break;
               default:
           }
    }

    private void initMic() {
        final ArrayList<String> list = new ArrayList<>();
        list.add("我的日报");
        list.add("我的周报");
        list.add("我的月报");
        list.add("我的抄送");
        list.add("我的绩效");
        micMineReport.setBackgroundColor(Color.WHITE);
        CommonNavigator navigator = new CommonNavigator(this);
        navigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return list == null ? 0 : list.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int position) {
                SimplePagerTitleView view = new ColorTransitionPagerTitleView(context);
                view.setTextAppearance(context,R.style.LeaveText);
                view.setText(list.get(position));
                view.setTextSize(18);
                view.setNormalColor(Color.parseColor("#b3b3b3"));
                view.setSelectedColor(Color.parseColor("#1296db"));
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vpMineReport.setCurrentItem(position);
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
        micMineReport.setNavigator(navigator);
        ViewPagerHelper.bind(micMineReport, vpMineReport);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
