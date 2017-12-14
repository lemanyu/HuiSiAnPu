package com.hsap.huisianpu.push;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.bean.PushAnnouncenBean;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 公告推送
 */

public class PushAnnouncenment extends BaseBackActivity {
    private static final String TAG = "PushAnnouncenment";
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.tv_push_announce)
    TextView tvPushAnnounce;
    @BindView(R.id.tv_push_announce_contant)
    TextView tvPushAnnounceContant;


    @Override
    public int getLayoutId() {
        return R.layout.push_announcenment;
    }

    @Override
    public void initView() {

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
        if (clickedResult != null) {
            PushAnnouncenBean bean = new Gson().fromJson(clickedResult.getCustomContent(), PushAnnouncenBean.class);
            tvPushAnnounce.setText(bean.getName()+"发起的"+clickedResult.getTitle());
            Log.e(TAG, "onResume: " + clickedResult.getContent());
            Log.e(TAG, "onResume: " + clickedResult.getCustomContent());
            tvPushAnnounceContant.setText(bean.getContent());
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
