package com.hsap.huisianpu.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hsap.huisianpu.R;
import com.hsap.huisianpu.adapter.ApproveGridViewAdapter;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.bean.Bean;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.SpUtils;
import com.hsap.huisianpu.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhy.android.percent.support.PercentLinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 请假页面
 */

public class LeaveActivity extends BaseBackActivity {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.bt_learve_commit)
    Button btLearveCommit;
    @BindView(R.id.leavte_toobar)
    Toolbar leavteToobar;
    @BindView(R.id.pll_qingjia)
    PercentLinearLayout pllQingjia;
    @BindView(R.id.pll_begin)
    PercentLinearLayout pllBegin;
    @BindView(R.id.pll_end)
    PercentLinearLayout pllEnd;
    @BindView(R.id.et_leave)
    EditText etLeave;
    @BindView(R.id.tv_qingjialeixing)
    TextView tvQingjialeixing;
    @BindView(R.id.gv_leave)
    GridView gvLeave;
    private ApproveGridViewAdapter adapter;
    private List<Bean> list = new ArrayList<>();
    private List<String> idList=new ArrayList<>();//存放 审批人的id
    private int[] color = {R.mipmap.chengyuan, R.mipmap.fenyuan, R.mipmap.lanyuan,
            R.mipmap.luyuan, R.mipmap.ziyuan,R.mipmap.hongyuan};

    @Override
    public int getLayoutId() {

        return R.layout.activity_work_leave;
    }

    @Override
    public void initView() {

        adapter = new ApproveGridViewAdapter(LeaveActivity.this, list);
        gvLeave.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gvLeave.setAdapter(adapter);

        gvLeave.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == list.size()) {
                    //跳到选择联系人页面
                    startActivityForResult(new Intent(LeaveActivity.this, SelectApproverActivity.class), 100);
                } else {
                    list.remove(position);
                    idList.remove(position);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
        btLearveCommit.setOnClickListener(this);
        pllQingjia.setOnClickListener(this);
        pllBegin.setOnClickListener(this);
        pllEnd.setOnClickListener(this);

    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.bt_learve_commit:
                ToastUtils.showToast(LeaveActivity.this, "提交");
                commit();
                break;
            case R.id.pll_qingjia:
                showSingleChoiceDialog();
                break;
            case R.id.pll_begin:
                showBeginTime();
                break;
            case R.id.pll_end:
                ToastUtils.showToast(LeaveActivity.this, "结束");
                break;
        }
    }

    private void showBeginTime() {

    }

    private void commit() {
        OkGo.<String>post(NetAddressUtils.leave).params("workersId", SpUtils.getInt(ConstantUtils.UserId,LeaveActivity.this))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e("bt_learve_commit","Aa");
                    }
                });
    }

    private void showSingleChoiceDialog() {
        final int[] choose = {0};
        final String[] item = {"事假", "病假", "产假", "婚假", "丧假"};
        ToastUtils.showToast(LeaveActivity.this, "请假");
        AlertDialog.Builder builder = new AlertDialog.Builder(LeaveActivity.this);
        builder.setTitle("请选择请假事由");
        builder.setSingleChoiceItems(item, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                choose[0] = i;
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tvQingjialeixing.setText(item[choose[0]]);
            }
        });
        builder.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
               if(resultCode==101){
                   Bundle bundle = data.getExtras();
                   String name = bundle.getString("name");
                   String id = bundle.getString("id");
                   Bean bean = new Bean();
                   bean.setName(name);
                   bean.setPic(color[(int) (Math.random()*6)]);
                   list.add(bean);
                   idList.add(id);
                   adapter.notifyDataSetChanged();
               }
        }
    }
}
