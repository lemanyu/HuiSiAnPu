package com.hsap.huisianpu.pager.work;

import android.util.Log;
import android.view.View;

import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseFragmentPager;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.SpUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * 待我审批
 */

public class WorkMyApprovalPager extends BaseFragmentPager {
    private static final String TAG="WorkMyApprovalPager";
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.pager_work_my_approval, null);
        return view;
    }

    @Override
    public void initData() {
        OkGo.<String>post(NetAddressUtils.getAuditList).
                params("managerId", SpUtils.getInt(ConstantUtils.UserId,mActivity)).
                params("opinion",0).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e(TAG,response.body().toString() );
                    }
                });
    }

    @Override
    public void initListener() {

    }
}
