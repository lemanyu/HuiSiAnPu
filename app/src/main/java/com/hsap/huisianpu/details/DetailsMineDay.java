package com.hsap.huisianpu.details;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseBackActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的日报详情
 */

public class DetailsMineDay extends BaseBackActivity {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.tv_details_title)
    TextView tvDetailsTitle;
    @BindView(R.id.tv_details_day_finish)
    TextView tvDetailsDayFinish;
    @BindView(R.id.ll_details_day)
    LinearLayout llDetailsDay;
    @BindView(R.id.tv_details_week_summary)
    TextView tvDetailsWeekSummary;
    @BindView(R.id.tv_details_week_plan_next)
    TextView tvDetailsWeekPlanNext;
    @BindView(R.id.tv_details_week_coordination)
    TextView tvDetailsWeekCoordination;
    @BindView(R.id.ll_details_week)
    LinearLayout llDetailsWeek;
    @BindView(R.id.tv_details_month_summary)
    TextView tvDetailsMonthSummary;
    @BindView(R.id.tv_details_month_plan)
    TextView tvDetailsMonthPlan;
    @BindView(R.id.tv_details_month_coordination)
    TextView tvDetailsMonthCoordination;
    @BindView(R.id.ll_details_month)
    LinearLayout llDetailsMonth;
    private String finishwork;
    private String summary;
    private String workplay;
    private String coordinatecork;


    @Override
    public int getLayoutId() {
        return R.layout.details_mine_day;
    }

    @Override
    public void initView() {
        int type = getIntent().getIntExtra("type", 0);//日周月
        finishwork = getIntent().getStringExtra("finishwork");
        summary = getIntent().getStringExtra("summary");
        workplay = getIntent().getStringExtra("workplay");
        coordinatecork = getIntent().getStringExtra("coordinatecork");
        isVisiableLiearent(type);


    }

    @Override
    public void initData() {
        if(llDetailsDay.getVisibility()==View.VISIBLE){
            tvDetailsDayFinish.setText(finishwork);
        }
        if(llDetailsWeek.getVisibility()==View.VISIBLE){
            tvDetailsWeekSummary.setText(summary);
            tvDetailsWeekPlanNext.setText(workplay);
            tvDetailsWeekCoordination.setText(coordinatecork);
        }
        if(llDetailsMonth.getVisibility()==View.VISIBLE){
            tvDetailsMonthSummary.setText(summary);
            tvDetailsMonthPlan.setText(workplay);
            tvDetailsMonthCoordination.setText(coordinatecork);
        }
    }

    @Override
    public void initListener() {
           back.setOnClickListener(this);
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

    public void isVisiableLiearent(int type) {
          switch (type){
              case 0:
                  tvDetailsTitle.setText("日报详情");
                  llDetailsDay.setVisibility(View.VISIBLE);
                  llDetailsWeek.setVisibility(View.GONE);
                  llDetailsMonth.setVisibility(View.GONE);
                  break;
              case 1:
                  tvDetailsTitle.setText("周报详情");
                  llDetailsDay.setVisibility(View.GONE);
                  llDetailsWeek.setVisibility(View.VISIBLE);
                  llDetailsMonth.setVisibility(View.GONE);
                  break;
              case 2:
                  tvDetailsTitle.setText("月报详情");
                  llDetailsDay.setVisibility(View.GONE);
                  llDetailsWeek.setVisibility(View.GONE);
                  llDetailsMonth.setVisibility(View.VISIBLE);
                  break;
          }
    }
}
