package com.hsap.huisianpu.pager.work;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseFragmentPager;
import com.hsap.huisianpu.utils.BarChartManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 全勤
 */

public class WorkAttendancePager extends BaseFragmentPager {
    @BindView(R.id.barchart)
    BarChart barChart;
    Unbinder unbinder;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.pager_work_attendance, null);
        return view;
    }

    @Override
    public void initData() {
        BarChartManager barChartManager2 = new BarChartManager(barChart);

        //设置x轴的数据
        ArrayList<String> xValues0 = new ArrayList<>();
        xValues0.add("早晨");
        xValues0.add("上午");
        xValues0.add("中午");
        xValues0.add("下午");
        xValues0.add("晚上");
        //设置y轴的数据()
        List<List<Float>> yValues = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            List<Float> yValue = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                yValue.add((float) (Math.random() * 8)+2);
            }
            yValues.add(yValue);
        }
        //颜色集合
        List<Integer> colors = new ArrayList<>();
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.CYAN);

        //线的名字集合
        List<String> names = new ArrayList<>();
        names.add("柱状一");
        names.add("柱状二");
        names.add("柱状三");
        names.add("柱状四");
        barChartManager2.showBarChart(xValues0, yValues,names);
    }

    @Override
    public void initListener() {

    }


}
