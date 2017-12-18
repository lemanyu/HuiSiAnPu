package com.hsap.huisianpu.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.bean.CaiGouBean;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.EditTextUtils;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.SpUtils;
import com.hsap.huisianpu.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhy.android.percent.support.PercentLinearLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;

/**
 * 采购申请
 */

public class WorkPurchaseActivity extends BaseBackActivity {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.bt_purchase_commit)
    Button btPurchaseCommit;
    @BindView(R.id.bt_leave_again)
    Button btLeaveAgain;
    @BindView(R.id.et_purchase_reason)
    EditText etPurchaseReason;
    @BindView(R.id.tv_purchase_type)
    TextView tvPurchaseType;
    @BindView(R.id.pll_purchase_type)
    PercentLinearLayout pllPurchaseType;
    @BindView(R.id.tv_purchase_time)
    TextView tvPurchaseTime;
    @BindView(R.id.pll_purchase_time)
    PercentLinearLayout pllPurchaseTime;
    @BindView(R.id.rlv_purchase)
    RecyclerView rlvPurchase;
    @BindView(R.id.ll_purchase_add)
    LinearLayout llPurchaseAdd;
    private ArrayList<CaiGouBean>flagList=new ArrayList<>();//假的
    private ArrayList<CaiGouBean> list=new ArrayList<>();//传送的数据
    private MyAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_work_purchase;
    }

    @Override
    public void initView() {
        rlvPurchase.setNestedScrollingEnabled(false);
        flagList.add(new CaiGouBean());
        adapter = new MyAdapter(R.layout.item_purchase,flagList);
        rlvPurchase.setLayoutManager(new LinearLayoutManager(this));
        rlvPurchase.setAdapter(adapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
        btPurchaseCommit.setOnClickListener(this);
        pllPurchaseType.setOnClickListener(this);//类型
        pllPurchaseTime.setOnClickListener(this);//日期
        llPurchaseAdd.setOnClickListener(this);//添加条目
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {
                     if (position==0){
                         ToastUtils.showToast(WorkPurchaseActivity.this,"采购申请必须填写");
                         return;
                     }
                switch (view.getId()){
                         case R.id.bt_purchase_delete:
                             AlertDialog.Builder builder = new AlertDialog.Builder(WorkPurchaseActivity.this);
                             builder.setMessage("确定要删除此条采购信息嘛？");
                             builder.setNegativeButton("取消",null);
                             builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                 @Override
                                 public void onClick(DialogInterface dialogInterface, int i) {
                                     flagList.remove(position);
                                     adapter.notifyDataSetChanged();
                                 }
                             });
                             builder.show();
                             break;
                         default:
                     }
            }
        });


    }

    @Override
    public void processClick(View v) {
            switch (v.getId()){
                case R.id.bt_purchase_commit:
                    commit();
                    break;
                case R.id.pll_purchase_type:
                    choiceType();
                    break;
                case R.id.pll_purchase_time:
                    choiceTime();
                    break;
                case R.id.ll_purchase_add:
                    addItem();
                    break;

                    default:
            }
    }

    private void addItem() {
        flagList.add(new CaiGouBean());
           //adapter.setNewData(list);
           adapter.notifyItemChanged(flagList.size());
    }

    private void choiceTime() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                int a = i1 + 1;
                tvPurchaseTime.setText(i + "-" + a + "-" + i2);
            }
        }, year, month, day);
        dialog.show();
    }

    private void choiceType() {
        final int[] choose = {0};
        final String[] item = {"办公用品", "生产原料", "其他"};
        AlertDialog.Builder builder = new AlertDialog.Builder(WorkPurchaseActivity.this);
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
                tvPurchaseType.setText(item[choose[0]]);
            }
        });
        builder.show();
    }

    private void commit() {
        boolean flag=false;
         if (TextUtils.isEmpty(etPurchaseReason.getText().toString().trim())){
             ToastUtils.showToast(this,"事由不能为空");
             return;
         }
         if(tvPurchaseType.getText().toString().trim().equals("请选择（必填）")){
             ToastUtils.showToast(this,"请选择采购类型");
             return;
         }
         if(tvPurchaseTime.getText().toString().trim().equals("请选择（必填）")){
             ToastUtils.showToast(this,"请选择交付日期");
             return;
         }
        list.clear();
        for (int i = 0; i <flagList.size(); i++) {
            CaiGouBean bean = new CaiGouBean();
            EditText et_mingcheng = (EditText) adapter.getViewByPosition(rlvPurchase,i,R.id.et_mingcheng);
            EditText et_xinghao = (EditText) adapter.getViewByPosition(rlvPurchase,i,R.id.et_xinghao);
            EditText et_shuliang = (EditText) adapter.getViewByPosition(rlvPurchase,i, R.id.et_shuliang);
            EditText et_danjia = (EditText) adapter.getViewByPosition(rlvPurchase,i, R.id.et_danjia);
            EditText et_piaoju = (EditText) adapter.getViewByPosition(rlvPurchase,i, R.id.et_piaoju);
            EditText et_yongtu = (EditText) adapter.getViewByPosition(rlvPurchase,i, R.id.et_yongtu);
            if(TextUtils.isEmpty(et_mingcheng.getText().toString().trim())){
                flag=true;
                ToastUtils.showToast(this,"名称不能为空");
                list.remove(i);
                break;
            }else {
                bean.setName(et_mingcheng.getText().toString().trim());
            }
            if(TextUtils.isEmpty(et_xinghao.getText().toString().trim())){
                flag=true;
                ToastUtils.showToast(this,"型号不能为空");
                list.remove(i);
                break;
            }else {
                bean.setGuige(et_xinghao.getText().toString().trim());
            }
            if (TextUtils.isEmpty(et_shuliang.getText().toString().trim())){
                flag=true;
                ToastUtils.showToast(this,"数量不能为空");
                list.remove(i);
                break;
            }else {
                bean.setShuliang(Integer.valueOf(et_shuliang.getText().toString().trim()));
            }
            if (TextUtils.isEmpty(et_danjia.getText().toString().trim())){
                flag=true;
                ToastUtils.showToast(this,"单价不能为空");
                list.remove(i);
                break;
            }else {
                bean.setDanjia(Double.valueOf(et_danjia.getText().toString().trim()));
            }
            if (TextUtils.isEmpty(et_piaoju.getText().toString().trim())){
                flag=true;
                ToastUtils.showToast(this,"票据类型不能为空");
                list.remove(i);
                break;
            }else {
                bean.setPiaoju(et_piaoju.getText().toString().trim());
            }
            if (TextUtils.isEmpty(et_yongtu.getText().toString().trim())){
                flag=true;
                ToastUtils.showToast(this,"用途说明不能为空");
                list.remove(i);
                break;
            }else {
                 bean.setYongtu(et_yongtu.getText().toString().trim());
            }
            list.add(bean);
         }
         if(flag){
             return;
         }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确定要提交吗？");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final LoadingDailog dailog = ToastUtils.showDailog(getApplicationContext(), "提交中");
                dailog.show();
                OkGo.<String>post(NetAddressUtils.insertIntegration).
                        params("endTime",tvPurchaseTime.getText().toString().trim()).
                        params("reasion",etPurchaseReason.getText().toString().trim()).
                        params("type",12).
                        params("type2",tvPurchaseType.getText().toString().trim()).
                        params("workersId", SpUtils.getInt(ConstantUtils.UserId,getApplicationContext())).
                        params("0",new Gson().toJson(list)).
                        params("activity","com.hsap.huisianpu.push.PushTirpActivity").
                        execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                dailog.dismiss();
                                ToastUtils.showToast(getApplicationContext(),"提交成功");
                            }
                            @Override
                            public void onError(Response<String> response) {
                                super.onError(response);
                                dailog.dismiss();
                                ToastUtils.showToast(getApplicationContext(),"提交失败");
                            }
                        });
            }
        });
         builder.show();
        ToastUtils.showToast(this,"提交成功");
    }

  class  MyAdapter extends BaseQuickAdapter<CaiGouBean,MyAdapter.MyViewHolder>{

      public MyAdapter(int layoutResId, @Nullable List<CaiGouBean> data) {
          super(layoutResId, data);
      }


      @Override
      protected void convert(MyViewHolder helper,CaiGouBean item) {
          final EditText et_danjia = helper.getView(R.id.et_danjia);
          final EditText et_shuliang = helper.getView(R.id.et_shuliang);
          final TextView jine = helper.getView(R.id.tv_jine);
          et_shuliang.addTextChangedListener(new TextWatcher() {
              @Override
              public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
              }

              @Override
              public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                  if(!TextUtils.isEmpty(et_shuliang.getText().toString().trim())&&!TextUtils.isEmpty(et_danjia.getText().toString().trim())){
                      if(!(et_shuliang.getText().toString().trim().length()==1&&et_shuliang.getText().toString().trim().equals("0"))){
                          double  total = Double.valueOf(et_shuliang.getText().toString().trim()) *
                                  Double.valueOf(et_danjia.getText().toString().trim());
                          jine.setText(total+"");
                      }
                  }
              }

              @Override
              public void afterTextChanged(Editable editable) {

              }
          });
          et_danjia.addTextChangedListener(new TextWatcher() {
              @Override
              public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

              }

              @Override
              public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                  if(!TextUtils.isEmpty(et_shuliang.getText().toString().trim())&&!TextUtils.isEmpty(et_danjia.getText().toString().trim())){
                      if(!(et_shuliang.getText().toString().trim().length()==1&&et_shuliang.getText().toString().trim().equals("0"))){
                          double  total = Double.valueOf(et_shuliang.getText().toString().trim()) *
                                  Double.valueOf(et_danjia.getText().toString().trim());
                          jine.setText(total+"");
                      }

                  }

              }

              @Override
              public void afterTextChanged(Editable editable) {

              }
          });
          helper.setIsRecyclable(false);
          EditTextUtils.afterDotTwo(et_danjia);
          if (helper.getAdapterPosition()==0){
              helper.getView(R.id.bt_purchase_delete).setVisibility(View.GONE);
          }
          helper.addOnClickListener(R.id.bt_purchase_delete);
      }

      class MyViewHolder extends BaseViewHolder{

            public MyViewHolder(View view) {
                super(view);

            }
        }
  }
}
