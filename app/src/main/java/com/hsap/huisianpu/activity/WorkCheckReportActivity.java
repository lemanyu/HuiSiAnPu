package com.hsap.huisianpu.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
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
import com.hsap.huisianpu.pager.work.WorkDayPager;
import com.hsap.huisianpu.pager.work.WorkMonthPager;
import com.hsap.huisianpu.pager.work.WorkPerformancePager;
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

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;

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
    @BindView(R.id.tv_work_checkreport)
    TextView tvWorkCheckreport;
    @BindView(R.id.tv_checkreport_time)
    TextView tvCheckreportTime;
    @BindView(R.id.ll_checkreport_time)
    LinearLayout llCheckreportTime;
    private List<BaseFragmentPager> frgamentList = new ArrayList<>();
    private int year;
    private int month;
    private int day;

    @Override
    public int getLayoutId() {

        return R.layout.activity_work_checkreport;
    }


    @Override
    public void initView() {
        Calendar instance = Calendar.getInstance();
        year = instance.get(Calendar.YEAR);
        month = instance.get(Calendar.MONTH)+1;
        day = instance.get(Calendar.DAY_OF_MONTH);
        tvCheckreportTime.setText(year+"."+month+"."+day);
        frgamentList.add(new WorkDayPager());
        frgamentList.add(new WorkWeekPager());
        frgamentList.add(new WorkMonthPager());
        frgamentList.add(new WorkPerformancePager());
        initMic();
        vpWorkCheckPeport.setOffscreenPageLimit(3);
        vpWorkCheckPeport.setAdapter(new ViewPagerFragmentAdapter(getSupportFragmentManager(), frgamentList));
    }

    public void initMic() {
        final ArrayList<String> list = new ArrayList<>();
        list.add("查看日报");
        list.add("查看周报");
        list.add("查看月报");
        list.add("绩效自评");
        micWorkCheckPeport.setBackgroundColor(Color.WHITE);
        CommonNavigator navigator = new CommonNavigator(this);
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
                view.setTextAppearance(WorkCheckReportActivity.this, R.style.LeaveText);
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
        llCheckreportTime.setOnClickListener(this);
        vpWorkCheckPeport.addOnPageChangeListener(getListener());
    }

    @NonNull
    private ViewPager.OnPageChangeListener getListener() {
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                micWorkCheckPeport.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tvWorkCheckreport.setText("查看日报");
                        break;
                    case 1:
                        tvWorkCheckreport.setText("查看周报");
                        break;
                    case 2:
                        tvWorkCheckreport.setText("查看月报");
                        break;
                    case 3:
                        tvWorkCheckreport.setText("绩效自评");
                        break;
                    default:
                }
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
                            tvCheckreportTime.setText(i+"."+a+"."+i2);
                        }
                    },year,month,day);
                    dialog.show();
                    break;
                 default:
             }
    }

}
