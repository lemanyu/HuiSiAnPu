package com.hsap.huisianpu.details;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hsap.huisianpu.R;
import com.hsap.huisianpu.adapter.MyAdapter;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.view.MyGridView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的出差
 */

public class DetailsMineTrip extends BaseBackActivity {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.et_trip_reason)
    TextView etTripReason;
    @BindView(R.id.et_trip_city)
    TextView etTripCity;
    @BindView(R.id.tv_trip_begin)
    TextView tvTripBegin;
    @BindView(R.id.tv_trip_end)
    TextView tvTripEnd;
    @BindView(R.id.tv_trip_day)
    TextView tvTripDay;
    @BindView(R.id.et_trip_remark)
    TextView etTripRemark;
    @BindView(R.id.gv_trip_person)
    MyGridView gvTripPerson;
    @BindView(R.id.gv_trip)
    MyGridView gvTrip;
    private ArrayList<String> accompanying;
    private ArrayList<String> verifier;

    @Override
    public int getLayoutId() {
        return R.layout.details_mine_trip;
    }

    @Override
    public void initView() {
        String reason = getIntent().getStringExtra("reason");
        etTripReason.setText(reason);
        String city = getIntent().getStringExtra("city");
        etTripCity.setText(city);
        String begin = getIntent().getStringExtra("begin");
        tvTripBegin.setText(begin);
        String end = getIntent().getStringExtra("end");
        tvTripEnd.setText(end);
        double total = getIntent().getDoubleExtra("total", 0);
        tvTripDay.setText(total+"");
        String comment = getIntent().getStringExtra("comment");
        etTripRemark.setText(comment);
        accompanying = getIntent().getStringArrayListExtra("accompanying");
        verifier = getIntent().getStringArrayListExtra("verifier");
    }

    @Override
    public void initData() {
        gvTripPerson.setAdapter(new MyAdapter(this,accompanying));
        gvTrip.setAdapter(new MyAdapter(this,verifier));
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
}
