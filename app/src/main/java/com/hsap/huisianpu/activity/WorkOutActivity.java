package com.hsap.huisianpu.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.utils.ToastUtils;
import com.zhy.android.percent.support.PercentLinearLayout;

import java.util.Calendar;

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
    private boolean isArticles;
    @Override
    public int getLayoutId() {

        return R.layout.activity_wokr_out;
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
        btOutCommit.setOnClickListener(this);
        pllOutReason.setOnClickListener(this);
        pllOutTime.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {
         switch (v.getId()){
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
         }
    }

    private void showcommit() {
         if(tvOutReason.getText().toString().trim().equals("请选择（必填）")){
             ToastUtils.showToast(this,"请选择外出事由");
             return;
         }
         if(tvOutTime.getText().toString().trim().equals("请选择（必填）")){
             ToastUtils.showToast(this,"请选择出厂时间");
             return;
         }
         if(pllOutArticles.getVisibility()==View.VISIBLE&& TextUtils.isEmpty(etOutArticles.getText().toString().trim())){
             ToastUtils.showToast(this,"请输入物品的名称和数量");
             return;
         }

    }


    private void showreason() {
        final int[] choice={0};
        final String item[]={"请假","其他","送货","提货"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择外出事由");
        builder.setSingleChoiceItems(item, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                choice[0]=i;
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(choice[0]==0||choice[0]==1){
                    pllOutArticles.setVisibility(View.GONE);
                }else {
                    pllOutArticles.setVisibility(View.VISIBLE);
                }
                tvOutReason.setText(item[choice[0]]);
            }
        });

        builder.show();
    }
    private void showtime() {
        final StringBuilder string = new StringBuilder();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
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
       },year,month,day).show();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
