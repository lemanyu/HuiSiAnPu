package com.hsap.huisianpu.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.android.tu.loadingdialog.LoadingDailog;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 外出界面
 */

public class WorkOutActivity extends BaseBackActivity {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.bt_out_commit)
    Button btOutCommit;
    @BindView(R.id.tv_out_reason)
    TextView tvOutReason;
    @BindView(R.id.pll_out_reason)
    PercentLinearLayout pllOutReason;
    @BindView(R.id.tv_out_time)
    TextView tvOutTime;
    @BindView(R.id.pll_out_time)
    PercentLinearLayout pllOutTime;
    @BindView(R.id.et_out_articles)
    EditText etOutArticles;
    @BindView(R.id.pll_out_articles)
    PercentLinearLayout pllOutArticles;//是否为送货 提货
    @BindView(R.id.tv_return_time)
    TextView tvReturnTime;
    @BindView(R.id.pll_return_time)
    PercentLinearLayout pllReturnTime;
    /*@BindView(R.id.gv_out)
    MyGridView gvOut;*/
    private boolean isArticles;
    private ApproveGridViewAdapter adapter;
    private List<Bean> list = new ArrayList<>();
    private List<String> idList = new ArrayList<>();//存放 审批人的id
    private int[] color = {R.mipmap.chengyuan, R.mipmap.fenyuan, R.mipmap.lanyuan,
            R.mipmap.luyuan, R.mipmap.ziyuan, R.mipmap.hongyuan};

    @Override
    public int getLayoutId() {

        return R.layout.activity_wokr_out;
    }

    @Override
    public void initView() {
        adapter = new ApproveGridViewAdapter(this, list);
        /*gvOut.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gvOut.setAdapter(adapter);
        gvOut.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == list.size()) {
                    //跳到选择联系人页面
                    startActivityForResult(
                            new Intent(WorkOutActivity.this,
                                    SelectApproverActivity.class), 100);
                } else {
                    list.remove(position);
                    idList.remove(position);
                    adapter.notifyDataSetChanged();
                }
            }
        });*/
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
        btOutCommit.setOnClickListener(this);
        pllOutReason.setOnClickListener(this);
        pllOutTime.setOnClickListener(this);
        pllReturnTime.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.bt_out_commit:
                showcommit();
                break;
            case R.id.pll_out_reason:
                //选择事由
                showreason();
                break;
            case R.id.pll_out_time:
                //选择事由
                showtime();
                break;
            case R.id.pll_return_time:
                showend();
                break;
            default:
        }
    }



    private void showcommit() {
        if (tvOutReason.getText().toString().trim().equals("请选择（必填）")) {
            ToastUtils.showToast(this, "请选择外出事由");
            return;
        }
        if (tvOutTime.getText().toString().trim().equals("请选择（必填）")) {
            ToastUtils.showToast(this, "请选择出厂时间");
            return;
        }
        if (TextUtils.isEmpty(tvReturnTime.getText().toString().trim())) {
            ToastUtils.showToast(this, "请选择返厂时间");
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh-mm");
        try {
            Date begin = sdf.parse(tvOutTime.getText().toString().trim());
            Date end = sdf.parse(tvReturnTime.getText().toString().trim());
            if (end.getTime()-begin.getTime()<=0){
                ToastUtils.showToast(this, "请选择正确的返厂时间");
                return;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (pllOutArticles.getVisibility() == View.VISIBLE && TextUtils.isEmpty(etOutArticles.getText().toString().trim())) {
            ToastUtils.showToast(this, "请输入物品的名称和数量");
            return;
        }
        final LoadingDailog dailog = ToastUtils.showDailog(this, "提交中");
        dailog.show();
        //提交
        OkGo.<String>post(NetAddressUtils.insertIntegration).
                params("startTime", tvOutTime.getText().toString().trim()).
                params("reasion", tvOutReason.getText().toString().trim()).
                params("type", 1).
                params("endTime", tvReturnTime.getText().toString().trim()).
                params("type2", etOutArticles.getText().toString().trim()).
                params("workersId", SpUtils.getInt(ConstantUtils.UserId, this)).
                params("activity", "com.hsap.huisianpu.push.PushTirpActivity").
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dailog.dismiss();
                        ToastUtils.showToast(WorkOutActivity.this, "提交成功");
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dailog.dismiss();
                        ToastUtils.showToast(WorkOutActivity.this, "提交失败，当前网络不好");
                    }
                });
    }


    private void showreason() {
        final int[] choice = {0};
        final String item[] = {"请假", "其他", "送货", "提货"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择外出事由");
        builder.setSingleChoiceItems(item, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                choice[0] = i;
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (choice[0] == 0 || choice[0] == 1) {
                    pllOutArticles.setVisibility(View.GONE);
                } else {
                    pllOutArticles.setVisibility(View.VISIBLE);
                }
                tvOutReason.setText(item[choice[0]]);
            }
        });

        builder.show();
    }
    private void showend() {
        final StringBuilder string = new StringBuilder();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        final TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                string.append(" " + i + ":" + i1);
                tvReturnTime.setText(string);
            }
        }, hour, minute, true);
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                string.append(i + "-" + (i1 + 1) + "-" + i2);
                timePickerDialog.show();
            }
        }, year, month, day).show();
    }
    private void showtime() {
        final StringBuilder string = new StringBuilder();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        final TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                string.append(" " + i + ":" + i1);
                tvOutTime.setText(string);
            }
        }, hour, minute, true);
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                string.append(i + "-" + (i1 + 1) + "-" + i2);
                timePickerDialog.show();
            }
        }, year, month, day).show();

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
            if (resultCode == 101) {
                Bundle bundle = data.getExtras();
                String name = bundle.getString("name");
                String id = bundle.getString("id");
                Bean bean = new Bean();
                bean.setName(name);
                bean.setPic(color[(int) (Math.random() * 6)]);
                list.add(bean);
                idList.add(id);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
