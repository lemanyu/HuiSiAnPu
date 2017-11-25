package com.hsap.huisianpu.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hsap.huisianpu.R;
import com.hsap.huisianpu.adapter.ViewPagerFragmentAdapter;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.base.BaseFragmentPager;
import com.hsap.huisianpu.pager.work.WorkAbsencePager;
import com.hsap.huisianpu.pager.work.WorkAttendancePager;
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
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 考勤统计
 */

public class WorkAttendanceActivity extends BaseBackActivity {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.mic_work_attendance)
    MagicIndicator micWorkAttendance;
    @BindView(R.id.vp_work_attendance)
    MyViewPager vpWorkAttendance;
    @BindView(R.id.tv_attendance_time)
    TextView tvAttendanceTime;
    @BindView(R.id.ll_attendance_time)
    LinearLayout llAttendanceTime;
    private List<BaseFragmentPager> fragmentList = new ArrayList<>();
    private int year;
    private int month;
    private int day;

    @Override
    public int getLayoutId() {
        return R.layout.activity_work_attendance;
    }

    @Override
    public void initView() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH)+1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        tvAttendanceTime.setText(year +"."+ month);
        fragmentList.add(new WorkAttendancePager());
        fragmentList.add(new WorkAbsencePager());
        initMic();
        vpWorkAttendance.setAdapter(new ViewPagerFragmentAdapter(getSupportFragmentManager(), fragmentList));
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
        llAttendanceTime.setOnClickListener(this);
        vpWorkAttendance.setOnPageChangeListener(getListener());
    }

    @NonNull
    private ViewPager.OnPageChangeListener getListener() {
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                micWorkAttendance.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                micWorkAttendance.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                micWorkAttendance.onPageScrollStateChanged(state);
            }
        };
    }

    @Override
    public void processClick(View v) {
              switch (v.getId()){
                  case R.id.ll_attendance_time:
                      time();
                      break;
              }
    }

    private void time() {
      Dialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                       int month=i1+1;
                       tvAttendanceTime.setText(i+"."+month);

            }
        }, year, month, day);
        dialog.show();
    }

    private void initMic() {
        final ArrayList<String> list = new ArrayList<>();
        list.add("全勤");
        list.add("缺勤");
        micWorkAttendance.setBackgroundColor(Color.WHITE);
        CommonNavigator navigator = new CommonNavigator(this);
        navigator.setAdjustMode(true);
        navigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return list == null ? 0 : list.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int position) {
                SimplePagerTitleView view = new ColorTransitionPagerTitleView(context);
                view.setText(list.get(position));
                view.setTextSize(18);
                view.setTextAppearance(WorkAttendanceActivity.this, R.style.LeaveText);
                view.setNormalColor(Color.parseColor("#b3b3b3"));
                view.setSelectedColor(Color.parseColor("#1296db"));
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vpWorkAttendance.setCurrentItem(position);
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
        micWorkAttendance.setNavigator(navigator);
        ViewPagerHelper.bind(micWorkAttendance, vpWorkAttendance);
    }
    private DatePicker findDatePicker(ViewGroup group) {
                 if (group != null) {
                         for (int i = 0, j = group.getChildCount(); i < j; i++) {
                                 View child = group.getChildAt(i);
                               if (child instanceof DatePicker) {
                                     return (DatePicker) child;
                                    } else if (child instanceof ViewGroup) {
                    DatePicker result = findDatePicker((ViewGroup) child);
                                         if (result != null)
                                                 return result;
                                    }
                             }
                     }
        return null;
             }
}
