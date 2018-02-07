package com.hsap.huisianpu.push;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.gson.Gson;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.bean.PushAnnounanceBean;
import com.hsap.huisianpu.bean.PushAnnouncenBean;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
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
    @BindView(R.id.pv_push_annoucen)
    PhotoView pvPushAnnoucen;


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
            tvPushAnnounce.setText(bean.getName() + "发起的" + clickedResult.getTitle());
            Log.e(TAG, "onResume: " + clickedResult.getContent());
            Log.e(TAG, "onResume: " + clickedResult.getCustomContent());
            dataformNet(bean.getId());
        }

    }

    private void dataformNet(int id) {
        OkGo.<String>post(NetAddressUtils.selectOneNotice).
                params("id",id).
                execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.e(TAG, "onSuccess: "+response.body().toString() );
                PushAnnounanceBean bean = new Gson().fromJson(response.body().toString(), PushAnnounanceBean.class);
                if (bean.getUrlList()!=null&&bean.getUrlList().size()!=0){
                    pvPushAnnoucen.setVisibility(View.VISIBLE);
                    Glide.with(PushAnnouncenment.this).
                            load(bean.getUrlList().get(0)).into(pvPushAnnoucen);
                }else {
                    tvPushAnnounceContant.setVisibility(View.VISIBLE);
                    tvPushAnnounceContant.setText("通知内容："+bean.getNotice().getNoticeBody());
                }
            }
        });
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
