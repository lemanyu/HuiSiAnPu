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
import com.hsap.huisianpu.pager.work.WorkHaveApprovalPager;
import com.hsap.huisianpu.pager.work.WorkMyApprovalPager;
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
 * 待我审批
 */

public class WorkApprovalActivity extends BaseBackActivity {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.mic_work_approval)
    MagicIndicator micWorkApproval;
    @BindView(R.id.vp_work_approval)
    MyViewPager vpWorkApproval;
    @BindView(R.id.tv_work_approval)
    TextView tvWorkApproval;
    @BindView(R.id.tv_checkreport_time)
    TextView tvCheckreportTime;
    @BindView(R.id.ll_checkreport_time)
    LinearLayout llCheckreportTime;
    private List<BaseFragmentPager> fragmentList = new ArrayList<>();

    @Override
    public int getLayoutId() {

        return R.layout.activity_work_approval;
    }

    @Override
    public void initView() {
        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH) + 1;
        tvCheckreportTime.setText(year + "-" + month);
        fragmentList.add(new WorkMyApprovalPager());
        fragmentList.add(new WorkHaveApprovalPager());
        initMic();
        vpWorkApproval.setAdapter(new ViewPagerFragmentAdapter(getSupportFragmentManager(), fragmentList));

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
        llCheckreportTime.setOnClickListener(this);
        vpWorkApproval.setOnPageChangeListener(getListener());
    }

    private ViewPager.OnPageChangeListener getListener() {
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                micWorkApproval.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        llCheckreportTime.setVisibility(View.GONE);
                        tvWorkApproval.setText("待我审批");
                        break;
                    case 1:
                        llCheckreportTime.setVisibility(View.VISIBLE);
                        tvWorkApproval.setText("我已审批");
                        break;
                    default:
                }
                micWorkApproval.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                micWorkApproval.onPageScrollStateChanged(state);
            }
        };
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.ll_checkreport_time:
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        //发送通知
                        EventBus.getDefault().post(new EventDate(i, i1 + 1));
                        int a = i1 + 1;
                        tvCheckreportTime.setText(i + "." + a);
                    }
                }, year, month, day);
                dialog.show();
                break;
            default:
        }
    }

    private void initMic() {
        final ArrayList<String> list = new ArrayList<>();
        list.add("待我审批");
        list.add("我已审批");
        micWorkApproval.setBackgroundColor(Color.WHITE);
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
                view.setTextAppearance(WorkApprovalActivity.this, R.style.LeaveText);
                view.setNormalColor(Color.parseColor("#b3b3b3"));
                view.setSelectedColor(Color.parseColor("#1296db"));
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vpWorkApproval.setCurrentItem(position);
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
        micWorkApproval.setNavigator(navigator);
        ViewPagerHelper.bind(micWorkApproval, vpWorkApproval);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
