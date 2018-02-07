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
import android.util.Log;
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
import com.hsap.huisianpu.bean.PurchaseBean;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.EditTextUtils;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.SpUtils;
import com.hsap.huisianpu.utils.ToastUtils;
import com.hsap.huisianpu.utils.Utils;
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
    private static final String TAG = "WorkPurchaseActivity";
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
    private ArrayList<PurchaseBean.DataBean.ObjectBean.ListBean>flagList=new ArrayList<>();//假的
    private ArrayList<PurchaseBean.DataBean.ObjectBean.ListBean> list=new ArrayList<>();//传送的数据
    private MyAdapter adapter;
    private int state;
    private PurchaseMyAdapter myAdapter;
    private int id;

    @Override
    public int getLayoutId() {

        return R.layout.activity_work_purchase;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        id = getIntent().getIntExtra("id", 0);
        state = getIntent().getIntExtra("state", 0);
        if (state == 1) {
            LoadingDailog 获取数据中 = ToastUtils.showDailog(this, "获取数据中");
            获取数据中.show();
            btPurchaseCommit.setVisibility(View.GONE);
            btLeaveAgain.setVisibility(View.VISIBLE);
            dataFormNet(id, 获取数据中);
        } else {
            btPurchaseCommit.setVisibility(View.VISIBLE);
            btLeaveAgain.setVisibility(View.GONE);
            rlvPurchase.setNestedScrollingEnabled(false);
            rlvPurchase.setLayoutManager(new LinearLayoutManager(this));


        }
    }

    private void dataFormNet(int id, final LoadingDailog 获取数据中) {
        OkGo.<String>post(NetAddressUtils.selectOneIntergration).
                params("id", id).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        PurchaseBean bean = new Gson().fromJson(response.body(), PurchaseBean.class);
                        if (bean.isSuccess()) {
                            获取数据中.dismiss();
                            tvPurchaseTime.setText(bean.getData().getWaIntegration().getEndTime().getYear() + "-" +
                                    bean.getData().getWaIntegration().getEndTime().getMonthValue() + "-"
                                    + bean.getData().getWaIntegration().getEndTime().getDayOfMonth());
                            list.addAll(bean.getData().getObject().getList());
                            tvPurchaseType.setText(bean.getData().getWaIntegration().getType2());
                            etPurchaseReason.setText(bean.getData().getWaIntegration().getReason()+"");
                            rlvPurchase.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            myAdapter = new PurchaseMyAdapter(R.layout.item_purchase,list,bean.getData().getWaIntegration().getType2());
                            rlvPurchase.setAdapter(myAdapter);
                            myAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
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
                                                   list.remove(position);
                                                   myAdapter.notifyDataSetChanged();
                                               }
                                           });
                                           builder.show();
                                           break;
                                       default:
                                   }
                               }
                           });
                        } else {
                            获取数据中.dismiss();
                            ToastUtils.showToast(WorkPurchaseActivity.this, "当前无网络");
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        获取数据中.dismiss();
                        ToastUtils.showToast(WorkPurchaseActivity.this, "当前无网络");
                    }
                });
    }
    class PurchaseMyAdapter extends BaseQuickAdapter<PurchaseBean.DataBean.ObjectBean.ListBean,BaseViewHolder>{

          private String type2;
        public PurchaseMyAdapter(int layoutResId, @Nullable List<PurchaseBean.DataBean.ObjectBean.ListBean> data, String type2) {
            super(layoutResId, data);
            this.type2=type2;
        }

        @Override
        protected void convert(BaseViewHolder helper, PurchaseBean.DataBean.ObjectBean.ListBean item) {
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
            if (type2.equals("生产原料")){
                helper.getView(R.id.ll_purchase_caigou).setVisibility(View.VISIBLE);
                helper.setText(R.id.et_fengzhuang,item.getFengzhuang())
                        .setText(R.id.et_pinpai,item.getPinpai())
                        .setText(R.id.et_fenlei,item.getFenlei())
                        .setText(R.id.et_lianxiren,item.getLianxiren())
                        .setText(R.id.et_dianhua,item.getDianhua());
                helper.getView(R.id.ll_piaoju).setVisibility(View.GONE);
                helper.getView(R.id.ll_beizhu).setVisibility(View.VISIBLE);
            }

            helper.addOnClickListener(R.id.bt_purchase_delete);
            helper.setText(R.id.et_mingcheng,item.getName())
                    .setText(R.id.et_xinghao,item.getGuige())
                    .setText(R.id.et_shuliang,item.getShuliang())
                    .setText(R.id.et_danjia,item.getDanjia())
                    .setText(R.id.et_piaoju,item.getPiaoju())
                    .setText(R.id.et_yongtu,item.getYongtu())
                     .setText(R.id.et_beizhu,item.getBeizhu());
            if (!TextUtils.isEmpty(et_danjia.getText().toString().trim())
                    &&!TextUtils.isEmpty(et_shuliang.getText().toString().trim())){
                helper.setText(R.id.tv_jine,Double.valueOf(et_shuliang.getText().toString())*Double.valueOf(et_danjia.getText().toString())+"");
            }
        }

    }
    @Override
    public void initListener() {
        back.setOnClickListener(this);
        btLeaveAgain.setOnClickListener(this);
        btPurchaseCommit.setOnClickListener(this);
        pllPurchaseType.setOnClickListener(this);//类型
        pllPurchaseTime.setOnClickListener(this);//日期
        llPurchaseAdd.setOnClickListener(this);//添加条目

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
                case R.id.bt_leave_again:
                    commit();
                    break;
                    default:
            }
    }

    private void addItem() {
           if (tvPurchaseType.getText().toString().equals("请选择（必填）")){
               ToastUtils.showToast(this,"请先选择采购类型");
               return;
           }
           //adapter.setNewData(list);
         if(state==0){
             flagList.add(new PurchaseBean.DataBean.ObjectBean.ListBean());
             adapter.notifyItemChanged(flagList.size()-1);
            // adapter.notifyDataSetChanged();
         }else {
             list.add(new PurchaseBean.DataBean.ObjectBean.ListBean());
             myAdapter.notifyItemChanged(list.size());
         }


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
                flagList.clear();
                tvPurchaseType.setText(item[choose[0]]);
                flagList.add(new PurchaseBean.DataBean.ObjectBean.ListBean());
                adapter = new MyAdapter(R.layout.item_purchase,flagList);
                rlvPurchase.setAdapter(adapter);
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
                                        adapter.notifyItemChanged(position);
                                    }
                                });
                                builder.show();
                                break;
                            default:
                        }
                    }
                });
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
            PurchaseBean.DataBean.ObjectBean.ListBean bean = new PurchaseBean.DataBean.ObjectBean.ListBean();
            EditText etMingCheng = (EditText) adapter.getViewByPosition(rlvPurchase,i,R.id.et_mingcheng);
            EditText etXinghao = (EditText) adapter.getViewByPosition(rlvPurchase,i,R.id.et_xinghao);
            EditText etShuliang = (EditText) adapter.getViewByPosition(rlvPurchase,i, R.id.et_shuliang);
            EditText et_danjia = (EditText) adapter.getViewByPosition(rlvPurchase,i, R.id.et_danjia);
            EditText et_piaoju = (EditText) adapter.getViewByPosition(rlvPurchase,i, R.id.et_piaoju);
            EditText et_yongtu = (EditText) adapter.getViewByPosition(rlvPurchase,i, R.id.et_yongtu);
            EditText et_fengzhuang = (EditText) adapter.getViewByPosition(rlvPurchase, i, R.id.et_fengzhuang);
            EditText et_pinpai = (EditText) adapter.getViewByPosition(rlvPurchase, i, R.id.et_pinpai);
            EditText et_fenlei = (EditText) adapter.getViewByPosition(rlvPurchase, i, R.id.et_fenlei);
            EditText et_lianxiren = (EditText) adapter.getViewByPosition(rlvPurchase, i, R.id.et_lianxiren);
            EditText et_dianhua = (EditText) adapter.getViewByPosition(rlvPurchase, i, R.id.et_dianhua);
            EditText et_beizhu = (EditText) adapter.getViewByPosition(rlvPurchase, i, R.id.et_beizhu);
            LinearLayout ll_piaoju = (LinearLayout) adapter.getViewByPosition(rlvPurchase, i, R.id.ll_piaoju);
            if(TextUtils.isEmpty(etMingCheng.getText().toString().trim())){
                flag=true;
                ToastUtils.showToast(this,"名称不能为空");
                break;
            }else {
                bean.setName(etMingCheng.getText().toString().trim());
            }
            if(TextUtils.isEmpty(etXinghao.getText().toString().trim())){
                flag=true;
                ToastUtils.showToast(this,"型号不能为空");
                break;
            }else {
                bean.setGuige(etXinghao.getText().toString().trim());
            }
            if (TextUtils.isEmpty(etShuliang.getText().toString().trim())){
                flag=true;
                ToastUtils.showToast(this,"数量不能为空");
                break;
            }else {
                bean.setShuliang(Integer.valueOf(etShuliang.getText().toString().trim())+"");
            }
            if (TextUtils.isEmpty(et_danjia.getText().toString().trim())){
                flag=true;
                ToastUtils.showToast(this,"单价不能为空");
                break;
            }else {
                bean.setDanjia(Double.valueOf(et_danjia.getText().toString().trim())+"");
            }
            if("生产原料".equals(tvPurchaseType.getText().toString())){
                if(TextUtils.isEmpty(et_fengzhuang.getText().toString().trim())){
                    flag=true;
                    ToastUtils.showToast(this,"封装不能为空");
                    break;
                }else {
                    bean.setFengzhuang(et_fengzhuang.getText().toString().trim());
                }
                if(TextUtils.isEmpty(et_pinpai.getText().toString().trim())){
                    flag=true;
                    ToastUtils.showToast(this,"品牌不能为空");
                    break;
                }else {
                    bean.setPinpai(et_pinpai.getText().toString().trim());
                }

                if(TextUtils.isEmpty(et_fenlei.getText().toString().trim())){
                    flag=true;
                    ToastUtils.showToast(this,"分类不能为空");
                    break;
                }else {
                    bean.setFenlei(et_fenlei.getText().toString().trim());
                }

                if(TextUtils.isEmpty(et_lianxiren.getText().toString().trim())){
                    flag=true;
                    ToastUtils.showToast(this,"联系人不能为空");
                    break;
                }else {
                    bean.setLianxiren(et_lianxiren.getText().toString().trim());
                }

                if(TextUtils.isEmpty(et_dianhua.getText().toString().trim())){
                    flag=true;
                    ToastUtils.showToast(this,"联系人不能为空");
                    break;
                }else if(Utils.isPhone(et_dianhua.getText().toString().trim())){
                    bean.setDianhua(et_dianhua.getText().toString().trim());
                } else {
                    flag=true;
                    ToastUtils.showToast(this,"请输入正确电话号码");
                    break;
                }
                if (TextUtils.isEmpty(et_beizhu.getText().toString().trim())){
                    flag=true;
                    ToastUtils.showToast(this,"备注不能为空");
                    break;
                }else {
                    bean.setBeizhu(et_beizhu.getText().toString().trim());
                }
            }else {
                if (TextUtils.isEmpty(et_piaoju.getText().toString().trim())){
                    flag=true;
                    ToastUtils.showToast(this,"票据类型不能为空");
                    break;
                }else {
                    bean.setPiaoju(et_piaoju.getText().toString().trim());
                }
            }

            if (TextUtils.isEmpty(et_yongtu.getText().toString().trim())){
                flag=true;
                ToastUtils.showToast(this,"用途说明不能为空");
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
                final LoadingDailog dailog = ToastUtils.showDailog(WorkPurchaseActivity.this, "提交中");
                dailog.show();
                OkGo.<String>post(NetAddressUtils.insertIntegration).
                        params("endTime",tvPurchaseTime.getText().toString().trim()).
                        params("reason",etPurchaseReason.getText().toString().trim()).
                        params("type",12).
                        params("reStart",id).
                        params("type2",tvPurchaseType.getText().toString().trim()).
                        params("workersId", SpUtils.getInt(ConstantUtils.UserId,getApplicationContext())).
                        params("o",new Gson().toJson(list)).
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
    }

  class  MyAdapter extends BaseQuickAdapter<PurchaseBean.DataBean.ObjectBean.ListBean,BaseViewHolder>{

      public MyAdapter(int layoutResId, @Nullable List<PurchaseBean.DataBean.ObjectBean.ListBean> data) {
          super(layoutResId, data);
      }

      @Override
      protected void convert(BaseViewHolder helper, PurchaseBean.DataBean.ObjectBean.ListBean item) {
          LinearLayout ll_purchase_caigou = helper.getView(R.id.ll_purchase_caigou);
          if(tvPurchaseType.getText().toString().equals("生产原料")){
              ll_purchase_caigou.setVisibility(View.VISIBLE);
              helper.getView(R.id.ll_piaoju).setVisibility(View.GONE);
              helper.getView(R.id.ll_beizhu).setVisibility(View.VISIBLE);
          }else {
              ll_purchase_caigou.setVisibility(View.GONE);
              helper.getView(R.id.ll_piaoju).setVisibility(View.VISIBLE);
              helper.getView(R.id.ll_beizhu).setVisibility(View.GONE);
          }
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


  }
}
