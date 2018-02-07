package com.hsap.huisianpu.activity;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hsap.huisianpu.R;
import com.hsap.huisianpu.adapter.ViewPagerFragmentAdapter;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.pager.work.WorkManagerPager;
import com.hsap.huisianpu.view.MyDatePickerDialog;
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

public class WorkManagerActivity extends BaseBackActivity {

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
    private List<WorkManagerPager> fragmentList = new ArrayList<>();

    private int year;
    private int month;
    private int day;

    private ArrayList<String> list = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_work_manager;
    }

    @Override
    public void initView() {
        initMicData();

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        tvAttendanceTime.setText(year + "." + month);

        for (int i = 0; i < list.size(); i++) {
            fragmentList.add(new WorkManagerPager().setI(i));
        }
        initMic();
        vpWorkAttendance.setAdapter(new ViewPagerFragmentAdapter(getSupportFragmentManager(), fragmentList));
    }

    private void initMicData() {
        list.add("请假");
        list.add("外出");
        list.add("出差");
        list.add("加班");
        list.add("用车");
        list.add("采购");
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
        llAttendanceTime.setOnClickListener(this);
        vpWorkAttendance.addOnPageChangeListener(getListener());
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
                fragmentList.get(position).setYearAndMonth(year, month);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                micWorkAttendance.onPageScrollStateChanged(state);
            }
        };
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.ll_attendance_time:
                if (vpWorkAttendance.getCurrentItem() != 0) {
                    showDatePicker2();
                }
                break;
            default:
        }
    }

    private MyDatePickerDialog myDatePickerDialog;

    public void showDatePicker2() {
        myDatePickerDialog = new MyDatePickerDialog(this,
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                new MyDatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        int month = monthOfYear + 1;
                        Toast.makeText(WorkManagerActivity.this, year + "-" + month, Toast.LENGTH_SHORT).show();
                        tvAttendanceTime.setText(year + "." + month);

                        int i = vpWorkAttendance.getCurrentItem();
                        fragmentList.get(i).setYearAndMonth(year, month);

                       WorkManagerActivity.this.year = year;
                       WorkManagerActivity.this.month = month;
                    }
                }, year, month - 1, day);
        myDatePickerDialog.myShow();

        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = myDatePickerDialog.getWindow().getAttributes();
        lp.width = (int) (display.getWidth() * 0.8);

        myDatePickerDialog.getWindow().setAttributes(lp);

        // 去掉显示日  只显示年月
        ((ViewGroup) ((ViewGroup) myDatePickerDialog.getDatePicker().getChildAt(0)).getChildAt(0)).getChildAt(2).setVisibility(View.GONE);
    }


    private void initMic() {
        micWorkAttendance.setBackgroundColor(Color.WHITE);
        CommonNavigator navigator = new CommonNavigator(this);
        navigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return list == null ? 0 : list.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int position) {
                SimplePagerTitleView view = new ColorTransitionPagerTitleView(context);
                view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                view.setText(list.get(position));
                view.setTextSize(18);
                //字体加粗
                view.getPaint().setFakeBoldText(true);
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

}
