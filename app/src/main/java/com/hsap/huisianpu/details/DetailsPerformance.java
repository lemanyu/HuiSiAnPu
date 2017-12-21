package com.hsap.huisianpu.details;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.google.gson.Gson;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.bean.EventDate;
import com.hsap.huisianpu.bean.PerformanceInfoBean;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.ToastUtils;
import com.hsap.huisianpu.view.CustomExpandableListView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */

public class DetailsPerformance extends BaseBackActivity {
    private static final String TAG = "DetailsPerformance";
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.bt_car_commit)
    Button btCarCommit;
    @BindView(R.id.item_details_performance)
    CustomExpandableListView itemDetailsPerformance;

    @BindView(R.id.tv_zongfen)
    TextView tvZongfen;
    @BindView(R.id.tv_zipingzongfen)
    TextView tvZipingzongfen;
    @BindView(R.id.tv_jinglizongfen)
    TextView tvJinglizongfen;
    private TreeMap<Integer, String> treeMap = new TreeMap<>();
    private MyExpandableListViewAdapter adapter;
    private int id;
    private int year;
    private int month;
    private int workid;
    private int zongfen;
    private int zipingzongfen;
    private int jinglizongfen;
    private boolean state;
    private int style;
    private int day;
    private Map<String, List<String>> map;
    public String[] groupStrings = {"研发过程的规范性（20）", "产品研发周期控制（20）",
            "工作内容饱和度（20）","工作积极主动性（10）","与其他部门沟通配合（10）",
            "解决问题（10）","工作日志（10）","工作失误","其他人员投诉","违反公司纪律"};
    @Override
    public int getLayoutId() {
        return R.layout.details_performance;
    }

    @Override
    public void initView() {
        View currentFocus = this.getCurrentFocus();
        if (currentFocus != null) {
            currentFocus.clearFocus();
        }

        ConstantUtils utils = new ConstantUtils();
        map = utils.getDataset();
        map.put(groupStrings[0],utils.getOneList());
        map.put(groupStrings[1],utils.getTwoList());
        map.put(groupStrings[2],utils.getThreeList());
        map.put(groupStrings[3],utils.getFourList());
        map.put(groupStrings[4],utils.getFiveList());
        map.put(groupStrings[5],utils.getSixList());
        map.put(groupStrings[6],utils.getSevenList());
        map.put(groupStrings[7],utils.getEightList());
        map.put(groupStrings[8],utils.getNineList());
        map.put(groupStrings[9],utils.getTenList());
        state = getIntent().getBooleanExtra("state", false);
        if (state) {
            btCarCommit.setVisibility(View.VISIBLE);
        } else {
            btCarCommit.setVisibility(View.GONE);
        }
        id = getIntent().getIntExtra("id", 0);
        year = getIntent().getIntExtra("year", 0);
        month = getIntent().getIntExtra("month", 0);
        day = getIntent().getIntExtra("day", 0);
        workid = getIntent().getIntExtra("workid", 0);
        style = getIntent().getIntExtra("style", 0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().post(new EventDate(year, month,day));
    }

    @Override
    public void initData() {
        itemDetailsPerformance.setGroupIndicator(null);
        itemDetailsPerformance.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
        final LoadingDailog 获取数据中 = ToastUtils.showDailog(DetailsPerformance.this, "获取数据中");
        获取数据中.show();
        OkGo.<String>post(NetAddressUtils.selectMySumScoreMonthInfo).
                params("id", id).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        获取数据中.dismiss();
                        Log.e(TAG, "onSuccess: " + response.body().toString());
                        PerformanceInfoBean bean = new Gson().fromJson(response.body().toString(), PerformanceInfoBean.class);
                        for (int i = 0; i < ConstantUtils.getGroupStrings().length; i++) {
                            Log.e(TAG, "onSuccess: " + bean.getData().getMyScore().get(i).getValue());
                            map.get(ConstantUtils.getGroupStrings()[i]).add("自评打分：" + bean.getData().getMyScore().get(i).getValue() + "分");
                            zipingzongfen+=bean.getData().getMyScore().get(i).getValue();
                            if (state) {

                            } else if ((style==0)){
                                treeMap.put(i, "经理打分：" + bean.getData().getManagerScore().get(i).getValue() + "分");
                                zipingzongfen+=bean.getData().getMyScore().get(i).getValue();
                                jinglizongfen+=bean.getData().getManagerScore().get(i).getValue();
                            }else {

                            }

                            adapter = new MyExpandableListViewAdapter();
                            itemDetailsPerformance.setAdapter(adapter);
                        }
                        zongfen+=zipingzongfen;
                        zongfen+=jinglizongfen;
                        tvZongfen.setText("总分："+zongfen);
                        tvZipingzongfen.setText("自评打分："+zipingzongfen);
                        tvJinglizongfen.setText("经理打分："+jinglizongfen);
                    }

                });
    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
        btCarCommit.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.bt_car_commit:
                commit();
                break;
            default:

        }
    }

    private void commit() {
        boolean flag = false;
        for (int i = 0; i < ConstantUtils.getGroupStrings().length; i++) {
            if (TextUtils.isEmpty(treeMap.get(i))) {
                ToastUtils.showToast(this, "请输入分数");
                flag = true;
                break;
            }else {
                jinglizongfen+=Integer.valueOf(treeMap.get(i));
            }
        }
        if (flag) {
            return;
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确认要提交嘛");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final LoadingDailog 提交中 = ToastUtils.showDailog(DetailsPerformance.this, "提交中");
                提交中.show();
                Log.e(TAG, "onClick: " + new Gson().toJson(treeMap).toString());
                OkGo.<String>post(NetAddressUtils.insertOneMonth).
                        params("workerId", workid).
                        params("json", new Gson().toJson(treeMap).toString()).
                        params("type", 1).
                        params("id", id).
                        params("activity", "com.hsap.huisianpu.push.PushPerformance").
                        execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                提交中.dismiss();
                                ToastUtils.showToast(DetailsPerformance.this, "提交成功");
                                btCarCommit.setVisibility(View.GONE);
                                state = false;
                                adapter.notifyDataSetChanged();
                                zongfen+=jinglizongfen;
                                tvJinglizongfen.setText("经理总分："+jinglizongfen);
                                tvZongfen.setText("总分："+zongfen);
                            }

                            @Override
                            public void onError(Response<String> response) {
                                super.onError(response);
                                提交中.dismiss();
                                ToastUtils.showToast(DetailsPerformance.this, "提交失败");
                            }
                        });
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

    class MyExpandableListViewAdapter extends BaseExpandableListAdapter {

        //  获得某个父项的某个子项
        @Override
        public Object getChild(int parentPos, int childPos) {
            return map.get(ConstantUtils.getGroupStrings()[parentPos]).get(childPos);
        }

        //  获得父项的数量
        @Override
        public int getGroupCount() {

            return map.size();
        }

        //  获得某个父项的子项数目
        @Override
        public int getChildrenCount(int parentPos) {
            return map.get(ConstantUtils.getGroupStrings()[parentPos]).size();
        }

        //  获得某个父项
        @Override
        public Object getGroup(int parentPos) {
            return map.get(ConstantUtils.getGroupStrings()[parentPos]);
        }

        //  获得某个父项的id
        @Override
        public long getGroupId(int parentPos) {

            return parentPos;
        }

        //  获得某个父项的某个子项的id
        @Override
        public long getChildId(int parentPos, int childPos) {

            return childPos;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(final int i, boolean b, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_father, null);
            view.setTag(R.layout.item_father, i);
            view.setTag(R.layout.item_son, -1);
            TextView tv_father = view.findViewById(R.id.tv_father);
            tv_father.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemDetailsPerformance.isGroupExpanded(i)) {
                        itemDetailsPerformance.collapseGroup(i);
                    } else {
                        itemDetailsPerformance.expandGroup(i);
                    }
                }
            });
            final EditText ettext = view.findViewById(R.id.ettext);
            if (state) {
                // ettext.setKeyListener(null);
                ettext.setEnabled(true);
            } else {
                ettext.setHint("经理尚未打分");
                ettext.setEnabled(false);
            }
            tv_father.setText(ConstantUtils.getGroupStrings()[i]);
            if (!TextUtils.isEmpty(treeMap.get(i))) {
                ettext.setText(treeMap.get(i));
            }
            ettext.clearFocus();
            ettext.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    treeMap.put(i, ettext.getText().toString());
                }
            });

            if (i == 0 || i == 1) {
                ettext.setFilters(new InputFilter[]{new InputFilterMinMax(0, 20)});
            } else {
                ettext.setFilters(new InputFilter[]{new InputFilterMinMax(0, 10)});
            }
            return view;
        }

        @Override
        public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.item_son, null);
            }
            Log.e(TAG, "getChildView: father " + i);
            Log.e(TAG, "getChildView: son" + i1);
            view.setTag(R.layout.item_father, i);
            TextView tv_son = view.findViewById(R.id.tv_son);
            tv_son.setText(map.get(ConstantUtils.getGroupStrings()[i]).get(i1));
            return view;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }
    }

    public class InputFilterMinMax implements InputFilter {

        private int min, max;

        public InputFilterMinMax(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public InputFilterMinMax(String min, String max) {
            this.min = Integer.parseInt(min);
            this.max = Integer.parseInt(max);
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            try {
                int input = Integer.parseInt(dest.toString() + source.toString());
                if (isInRange(min, max, input)) {
                    return null;
                }
            } catch (NumberFormatException nfe) {
            }
            return "";
        }

        private boolean isInRange(int a, int b, int c) {
            return b > a ? c >= a && c <= b : c >= b && c <= a;
        }
    }
}
