package com.hsap.huisianpu.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.google.gson.Gson;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.bean.Bean;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.SpUtils;
import com.hsap.huisianpu.utils.ToastUtils;
import com.hsap.huisianpu.view.CustomExpandableListView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;

/**
 * 绩效自评
 */

public class WorkPerformanceActivity extends BaseBackActivity {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.bt_work_day_commit)
    Button btWorkDayCommit;
    @BindView(R.id.elv)
    CustomExpandableListView elv;
    private static final String TAG = "WorkPerformanceActivity";
    private Map<String, List<String>> dataset = new HashMap<>();
    public String[] groupStrings = {"研发过程的规范性（20）", "产品研发周期控制（20）",
            "工作内容饱和度（20）", "工作积极主动性（10）", "与其他部门沟通配合（10）",
            "解决问题（10）", "工作日志（10）", "工作失误", "其他人员投诉", "违反公司纪律"};
    private Map<Integer, String> save = new TreeMap<>();

    @Override
    public int getLayoutId() {

        return R.layout.activity_work_performance;
    }

    @Override
    public void initView() {
     //   elv.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
        ArrayList<String> oneList = new ArrayList<>();
        ArrayList<String> twoList = new ArrayList<>();
        ArrayList<String> threeList = new ArrayList<>();
        ArrayList<String> fourList = new ArrayList<>();
        ArrayList<String> fiveList = new ArrayList<>();
        ArrayList<String> sixList = new ArrayList<>();
        ArrayList<String> sevenList = new ArrayList<>();
        ArrayList<String> eightList = new ArrayList<>();
        ArrayList<String> nineList = new ArrayList<>();
        ArrayList<String> tenList = new ArrayList<>();
        oneList.add("新产品研发过程标准、规范，工作纪律性强、无违规现象(20分)");
        oneList.add("新产品研发过程标准、规范，工作纪律性强、有个别违规现象（15~19分）");
        oneList.add("新产品研发过程比较规范，有违规现象，但不影响研发质量（8~14分）");
        oneList.add("新产品研发过程混乱、违规现象较多，影响研发质量（0~7分）");
        twoList.add("研发过程加班加点，每次均能提前或按时完成任务(20分)");
        twoList.add("研发过程每个任务延期5天内，每延期1天扣除1分,延期5~10天，每延期1天扣2分（0~19分）");
        threeList.add("工作内容十分饱满，工作安排有条理、经常加班（16~20分）");
        threeList.add("工作内容饱满，工作安排有条理，偶尔加班（11~15分）");
        threeList.add("工作内容基本饱满，工作安排合理（6~10分）");
        threeList.add("工作内容不饱满，工作安排不合理（0~5分）");
        fourList.add("工作积极性高，有预见性，能够主动及时进行工作(7~10分)");
        fourList.add("工作有一定积极性，工作需领导进行安排(4~6分)");
        fourList.add("工作无积极性，行为懒散，不能主动工作(0~3分)");
        fiveList.add("与其他部门沟通顺畅、高效(7~10分)");
        fiveList.add("与其他部门人员沟通偶尔会存在问题，造成沟通不畅(4~6分)");
        fiveList.add("与其他部门人员沟通经常出现问题，不能正常沟通(0~3)分");
        sixList.add("独立解决问题的能力较强，通过钻研，都能够给出最合理的问题解决方案(7~10分)");
        sixList.add("具有独立解决问题能力，偶尔需其他人员进行指导(4~6分)");
        sixList.add("独立解决问题的能力较差，基本需其他人进行指导（0~3分）");
        sevenList.add("工作日志缺少每1天扣1分");
        eightList.add("每次工作失误扣1~5分");
        nineList.add("每次遭其他人员投诉扣1~5分");
        tenList.add("违反公司纪律每次扣10分");
        dataset.put(groupStrings[0], oneList);
        dataset.put(groupStrings[1], twoList);
        dataset.put(groupStrings[2], threeList);
        dataset.put(groupStrings[3], fourList);
        dataset.put(groupStrings[4], fiveList);
        dataset.put(groupStrings[5], sixList);
        dataset.put(groupStrings[6], sevenList);
        dataset.put(groupStrings[7], eightList);
        dataset.put(groupStrings[8], nineList);
        dataset.put(groupStrings[9], tenList);

        for (int i = 0; i < groupStrings.length; i++) {
            save.put(i, "");
        }
    }

    @Override
    public void initData() {
        elv.setAdapter(new MyExpandableListViewAdapter());
        elv.setGroupIndicator(null);

    }

    @Override
    public void initListener() {

        back.setOnClickListener(this);
        btWorkDayCommit.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.bt_work_day_commit:
                commit();
                break;
            default:
        }
    }

    private void commit() {
        boolean flag = false;
        for (int i = 0; i < groupStrings.length; i++) {
            if (TextUtils.isEmpty(save.get(i))) {
                ToastUtils.showToast(this, "请输入分数");
                flag = true;
                break;
            }
            Log.e(TAG, "commit: " + save.get(i));
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
                final LoadingDailog 提交中 = ToastUtils.showDailog(WorkPerformanceActivity.this, "提交中");
                提交中.show();
                Log.e(TAG, "onClick: " + new Gson().toJson(save).toString());

                OkGo.<String>post(NetAddressUtils.isNowMonth).
                        params("workerId", SpUtils.getInt(ConstantUtils.UserId, WorkPerformanceActivity.this)).
                        execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                Bean bean = new Gson().fromJson(response.body().toString(), Bean.class);
                                if (bean.isSuccess()) {
                                    提交中.dismiss();
                                    ToastUtils.showToast(WorkPerformanceActivity.this, "本月已经提交过");
                                    return;
                                } else {
                                    OkGo.<String>post(NetAddressUtils.insertOneMonth).
                                            params("workerId", SpUtils.getInt(ConstantUtils.UserId, WorkPerformanceActivity.this)).
                                            params("json", new Gson().toJson(save).toString()).
                                            params("type", 0).
                                            params("activity", "com.hsap.huisianpu.push.PushPerformance").
                                            execute(new StringCallback() {
                                                @Override
                                                public void onSuccess(Response<String> response) {
                                                    提交中.dismiss();
                                                    ToastUtils.showToast(WorkPerformanceActivity.this, "提交成功");
                                                }

                                                @Override
                                                public void onError(Response<String> response) {
                                                    super.onError(response);
                                                    提交中.dismiss();
                                                    ToastUtils.showToast(WorkPerformanceActivity.this, "提交失败");
                                                }
                                            });
                                }

                            }

                            @Override
                            public void onError(Response<String> response) {
                                super.onError(response);
                                提交中.dismiss();
                                ToastUtils.showToast(WorkPerformanceActivity.this, "网络错误");
                            }
                        });
            }
        });
        builder.show();

    }

    class MyExpandableListViewAdapter extends BaseExpandableListAdapter {

        //  获得某个父项的某个子项
        @Override
        public Object getChild(int parentPos, int childPos) {
            return dataset.get(groupStrings[parentPos]).get(childPos);
        }

        //  获得父项的数量
        @Override
        public int getGroupCount() {
            return dataset.size();
        }

        //  获得某个父项的子项数目
        @Override
        public int getChildrenCount(int parentPos) {
            return dataset.get(groupStrings[parentPos]).size();
        }

        //  获得某个父项
        @Override
        public Object getGroup(int parentPos) {

            return dataset.get(groupStrings[parentPos]);
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
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    if (elv.isGroupExpanded(i)) {
                        elv.collapseGroup(i);
                    } else {
                        elv.expandGroup(i);
                    }
                }
            });
            final EditText ettext = view.findViewById(R.id.ettext);
            tv_father.setText(groupStrings[i]);
            if (!TextUtils.isEmpty(save.get(i))) {
                ettext.setText(save.get(i));
            }
         //   ettext.clearFocus();

            ettext.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    save.put(i, ettext.getText().toString());
                }
            });
            if (i > -1 && i < 3) {
                Log.d(TAG, "getGroupView: "+i);
                Log.d(TAG, "getGroupView: "+ettext);
                ettext.setFilters(new InputFilter[]{new InputFilterMinMax(0, 20)});
            } else if (i > 2 && i < 6) {
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
            tv_son.setText(dataset.get(groupStrings[i]).get(i1));
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

