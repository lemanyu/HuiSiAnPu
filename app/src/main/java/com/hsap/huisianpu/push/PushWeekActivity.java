package com.hsap.huisianpu.push;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushManager;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 推送的周报
 */

public class PushWeekActivity extends BaseBackActivity {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.tv_push_zhoubao)
    TextView tvPushZhoubao;

    @Override
    public int getLayoutId() {
        return R.layout.push_week_activity;
    }

    @Override
    public void initView() {

        tvPushZhoubao.setText("我是推送过来的");
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

        back.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        XGPushClickedResult clickedResult = XGPushManager.onActivityStarted(this);
        if(clickedResult!=null){
            String title = clickedResult.getTitle();
            tvPushZhoubao.setText(title);
            String content = clickedResult.getCustomContent();
            try {
                int id = (int) new JSONObject(content).get("id");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.e("PushWeek",content);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        XGPushManager.onActivityStoped(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
