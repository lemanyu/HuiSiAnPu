package com.hsap.huisianpu.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.utils.ToastUtils;
import com.zhy.android.percent.support.PercentLinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 修改权限
 */

public class WorkModifyPermissionsActivity extends BaseBackActivity {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.tv_modify)
    TextView tvModify;
    @BindView(R.id.pll_modify)
    PercentLinearLayout pllModify;
    @BindView(R.id.bt_modify_commit)
    Button btModifyCommit;

    @Override
    public int getLayoutId() {

        return R.layout.activity_work_modify_permission;
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
        pllModify.setOnClickListener(this);
        btModifyCommit.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.pll_modify:
                choice();
                break;
            case R.id.bt_modify_commit:
                commit();
                break;
        }
    }

    private void commit() {
        if(tvModify.getText().toString().trim().equals("请选择（必填）")){
            ToastUtils.showToast(this,"请选择要修改的权限");
            return;
        }
    }

    private void choice() {
        final int[] choice = {0};
        final String[] items = {"经理", "管理员", "员工", "实习员工"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择要修改的权限");
        builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                choice[0] = i;
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tvModify.setText(items[choice[0]]);
            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
