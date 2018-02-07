package com.hsap.huisianpu.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.google.gson.Gson;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.adapter.AccompanyGvidViewAdapter;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.bean.Bean;
import com.hsap.huisianpu.bean.SelectApproverBean;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.SpUtils;
import com.hsap.huisianpu.utils.ToastUtils;
import com.hsap.huisianpu.view.MyGridView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhy.android.percent.support.PercentLinearLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 发布项目
 */

public class WorkPublishProjectActivity extends BaseBackActivity {
    private static final String TAG = "WorkPublishProjectActivity";
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.bt_publish_commit)
    Button btPublishCommit;
    @BindView(R.id.et_publish_name)
    EditText etPublishName;
    @BindView(R.id.et_publish_number)
    EditText etPublishNumber;
    @BindView(R.id.tv_publish_time)
    TextView tvPublishTime;
    @BindView(R.id.pll_publish_time)
    PercentLinearLayout pllPublishTime;
    @BindView(R.id.et_publish_days)
    EditText etPublishDays;
    @BindView(R.id.et_publish_content)
    EditText etPublishContent;
    @BindView(R.id.gv_project)
    MyGridView gvProject;
    @BindView(R.id.pll_fuzeren)
    PercentLinearLayout pllFuzeren;
    @BindView(R.id.tv_weiji)
    TextView tvWeiji;
    /* @BindView(R.id.tv_kaifa)
     TextView tvKaifa;
     @BindView(R.id.pll_kaifa)
     PercentLinearLayout pllKaifa;
     @BindView(R.id.tv_weiji)
     TextView tvWeiji;
     @BindView(R.id.pll_weiji)
     PercentLinearLayout pllWeiji;*/
    private StringBuilder time = new StringBuilder();
    private List<Bean> personList = new ArrayList<>();//陪同人
    private List<Integer> personIdList = new ArrayList<>();//陪同人id
    private int[] color = {R.mipmap.chengyuan, R.mipmap.fenyuan, R.mipmap.lanyuan,
            R.mipmap.luyuan, R.mipmap.ziyuan, R.mipmap.hongyuan};
    private AccompanyGvidViewAdapter accompanyGvidViewAdapter;
    private int personId;

    @Override
    public int getLayoutId() {

        return R.layout.activity_work_publish_project;
    }

    @Override
    public void initView() {
        accompanyGvidViewAdapter = new AccompanyGvidViewAdapter(this, personList);
        gvProject.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gvProject.setAdapter(accompanyGvidViewAdapter);
        gvProject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == personList.size()) {
                    //跳转到选择陪同人
                    Intent intent = new Intent(WorkPublishProjectActivity.this, ChooseAccompanyActivity.class);
                    startActivityForResult(intent, 200);
                } else {
                    personList.remove(position);
                    personIdList.remove(position);
                    accompanyGvidViewAdapter.notifyDataSetChanged();
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
        /*pllKaifa.setOnClickListener(this);
        pllWeiji.setOnClickListener(this);*/
        pllFuzeren.setOnClickListener(this);
        btPublishCommit.setOnClickListener(this);
        pllPublishTime.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.bt_publish_commit:
                commit();
                break;
            case R.id.pll_publish_time:
                time();
                break;
            /*case R.id.pll_kaifa:
                kaifa();
                break;
            case R.id.pll_weiji:
                weiji();
                break;*/
            case R.id.pll_fuzeren:
                choicePerson();
                break;
            default:
        }
    }

    private void choicePerson() {
        OkGo.<String>post(NetAddressUtils.getWorkersList).params("id",
                SpUtils.getInt(ConstantUtils.UserId, this))
                .execute(new StringCallback() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onSuccess(Response<String> response) {
                        final int[] choose = {0};
                        final SelectApproverBean bean = new Gson().fromJson(response.body().toString(), SelectApproverBean.class);
                        final ArrayList<String> nameList = new ArrayList<>();
                        final ArrayList<Integer> idList = new ArrayList<>();
                        for (int i = 0; i < bean.getUsers().size(); i++) {
                            nameList.add(bean.getUsers().get(i).getUser().getName());
                            idList.add(bean.getUsers().get(i).getUser().getId());
                        }

                        String[] array = nameList.toArray(new String[nameList.size()]);
                        Log.e(TAG, "onSuccess: " + array.toString());

                        final AlertDialog.Builder builder = new AlertDialog.Builder(WorkPublishProjectActivity.this);
                        builder.setTitle("请选择联系人");
                        builder.setSingleChoiceItems(array, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                choose[0] = which;
                            }
                        });
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                tvWeiji.setText(nameList.get(choose[0]));
                                personId=idList.get(choose[0]);
                                Log.e(TAG, "personId: "+personId );
                            }
                        });
                        /*View view =View.inflate(WorkPublishProjectActivity.this,R.layout.person,null);
                        builder.setView(view);*/

                        //final ListView lv_person = view.findViewById(R.id.lv_person);
                       /* lv_person.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                        lv_person.setAdapter(new MyAdapter(bean.getData()));*/
                        builder.show();
                        //  builder.show();
                        /*lv_person.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                tvPublishTime.setText(bean.getData().get(i).getName());
                                personId=bean.getData().get(i).getId();
                                builder.;
                            }
                        });*/

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);

                    }
                });

    }

    private void weiji() {
        final int[] choose = {0};
        final String[] item = {"上位机", "下位机"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择位机类型");
        builder.setSingleChoiceItems(item, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                choose[0] = i;
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                /*tvWeiji.setText(item[choose[0]]);*/
            }
        });
        builder.show();
    }

    private void kaifa() {
        final int[] choose = {0};
        final String[] item = {"开发", "改进"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择类型");
        builder.setSingleChoiceItems(item, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                choose[0] = i;
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
           /* tvKaifa.setText(item[choose[0]]);*/
            }
        });
        builder.show();
    }

    private void time() {
        time.setLength(0);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1+=1;
                time.append(i + "-" + i1 + "-" + i2 + " ");
                tvPublishTime.setText(time);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void commit() {
        /*if(tvKaifa.getText().toString().equals("请选择（必填）")){
            ToastUtils.showToast(this,"请选择开发类型");
            return;
        }
        if(tvWeiji.getText().toString().equals("请选择（必填）")){
            ToastUtils.showToast(this,"请选择位机类型");
            return;
        }*/
        if (TextUtils.isEmpty(etPublishName.getText().toString().trim())) {
            ToastUtils.showToast(this, "请填写项目名称");
            return;
        }
        if (TextUtils.isEmpty(etPublishNumber.getText().toString().trim())) {
            ToastUtils.showToast(this, "请填写项目编号");
            return;
        }
        if ("请选择（必填）".equals(tvWeiji.getText().toString().trim())) {
            ToastUtils.showToast(this, "请选择项目负责人");
            return;
        }
        if (tvPublishTime.getText().toString().trim().equals("请选择（必填）")) {
            ToastUtils.showToast(this, "请选择开始时间");
            return;
        }
        if (TextUtils.isEmpty(etPublishDays.getText().toString().trim())) {
            ToastUtils.showToast(this, "请填写预计天数");
            return;
        }
        if (TextUtils.isEmpty(etPublishContent.getText().toString().trim())) {
            ToastUtils.showToast(this, "请填写项目内容");
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确定要提交嘛");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final LoadingDailog 提交中 = ToastUtils.showDailog(WorkPublishProjectActivity.this, "提交中");
                提交中.show();
                OkGo.<String>post(NetAddressUtils.insertProjectBase).
                        params("workersId", SpUtils.getInt(ConstantUtils.UserId, WorkPublishProjectActivity.this)).
                        params("projectWorkers",personId).
                        params("projectName", etPublishName.getText().toString()).
                        params("projectNumber", etPublishNumber.getText().toString().trim()).
                        params("startTime", tvPublishTime.getText().toString()).
                        params("esCycle", etPublishDays.getText().toString()).
                        params("body", etPublishContent.getText().toString()).
                        params("activity", "com.hsap.huisianpu.push.PushTirpActivity").
                        params("ids", new Gson().toJson(personIdList)).
                        execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                提交中.dismiss();
                                ToastUtils.showToast(WorkPublishProjectActivity.this, "提交成功");
                            }

                            @Override
                            public void onError(Response<String> response) {
                                super.onError(response);
                                提交中.dismiss();
                                ToastUtils.showToast(WorkPublishProjectActivity.this, "提交失败");
                            }
                        });
            }
        });
        builder.show();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200) {
            if (resultCode == 201) {
                ArrayList<String> namelist = data.getStringArrayListExtra("namelist");
                ArrayList<Integer> idlist = data.getIntegerArrayListExtra("idlist");
                for (int i = 0; i < namelist.size(); i++) {
                    Bean bean = new Bean();
                    bean.setName(namelist.get(i));
                    bean.setPic(color[(int) (Math.random() * 6)]);
                    personList.add(bean);
                }
                personIdList.addAll(idlist);
                accompanyGvidViewAdapter.notifyDataSetChanged();

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    class MyAdapter extends BaseAdapter {
        private List<SelectApproverBean.UsersBean> list;

        public MyAdapter(List<SelectApproverBean.UsersBean> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = View.inflate(WorkPublishProjectActivity.this, R.layout.item_person, null);
            TextView id_name = view.findViewById(R.id.id_name);
            id_name.setText(list.get(i).getUser().getName());
            return view;
        }
    }
}
