package com.hsap.huisianpu.push;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.bean.IdBean;
import com.hsap.huisianpu.bean.OneReportFromBean;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushManager;

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
    @BindView(R.id.tv_push_week_summary)
    TextView tvPushWeekSummary;
    @BindView(R.id.tv_push_week_plan)
    TextView tvPushWeekPlan;
    @BindView(R.id.tv_push_week_coordination)
    TextView tvPushWeekCoordination;


    @Override
    public int getLayoutId() {
        return R.layout.push_week_activity;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    private void dataFromNet(int id) {
        Log.e("getOneReportForm",":"+id);
        OkGo.<String>post(NetAddressUtils.getOneReportForm).
                params("id",id).execute(new StringCallback() {
                                            @Override
                                            public void onSuccess(Response<String> response) {
                                                OneReportFromBean bean = new Gson().fromJson(response.body().toString(), OneReportFromBean.class);
                                                tvPushWeekSummary.setText(bean.getData().getFinishWork());
                                                tvPushWeekPlan.setText(bean.getData().getWorkPlay());
                                                tvPushWeekCoordination.setText(bean.getData().getSummary());
                                            }

                                            @Override
                                            public void onError(Response<String> response) {
                                                super.onError(response);
                                                ToastUtils.showToast(PushWeekActivity.this,"当前网络不好，获取失败");
                                            }
                                        }

        );
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
            String title = clickedResult.getContent();
            tvPushZhoubao.setText(title);
            String content = clickedResult.getCustomContent();
            IdBean bean = new Gson().fromJson(content, IdBean.class);
           int id = bean.getId();
            dataFromNet(id);
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
