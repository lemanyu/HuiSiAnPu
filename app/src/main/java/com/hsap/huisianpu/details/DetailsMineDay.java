package com.hsap.huisianpu.details;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.activity.ChooseAccompanyActivity;
import com.hsap.huisianpu.activity.PhotoViewPgaerActivity;
import com.hsap.huisianpu.adapter.AccompanyGvidViewAdapter;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.bean.Bean;
import com.hsap.huisianpu.bean.DetailsMineDayBean;
import com.hsap.huisianpu.bean.PerformanceInfoBean;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.ToastUtils;
import com.hsap.huisianpu.view.MyGridView;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhy.android.percent.support.PercentLinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;

/**
 * 日周月报详情
 */

public class DetailsMineDay extends BaseBackActivity {
    private static final String TAG = "DetailsMineDay";
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.tv_details_title)
    TextView tvDetailsTitle;
    @BindView(R.id.bt_cc)
    Button btCc;
    @BindView(R.id.vs_day)
    ViewStub vsDay;
    @BindView(R.id.vs_week)
    ViewStub vsWeek;
    @BindView(R.id.vs_month)
    ViewStub vsMonth;
    @BindView(R.id.gv_share)
    MyGridView gvShare;
    @BindView(R.id.pll_details_mine_day)
    PercentLinearLayout pllDetailsMineDay;
    @BindView(R.id.bt_commit)
    Button btCommit;
    @BindView(R.id.vs_performance)
    ViewStub vsPerformance;
    private ArrayList<String> imageList = new ArrayList();
    private List<Bean> personList = new ArrayList<>();//陪同人
    private List<Integer> personIdList = new ArrayList<>();//陪同人id
    private int[] color = {R.mipmap.chengyuan, R.mipmap.fenyuan, R.mipmap.lanyuan,
            R.mipmap.luyuan, R.mipmap.ziyuan, R.mipmap.hongyuan};
    private AccompanyGvidViewAdapter adapter;

    private ExpandableListView item_details_performance;
    private TreeMap<Integer,String> treeMap=new TreeMap<>();
    private boolean enable;
    private MyExpandableListViewAdapter adapter1;
    private Map<String, List<String>> map;
    private int id;

    @Override
    public int getLayoutId() {
        return R.layout.details_mine_day;
    }

    @Override
    public void initView() {


        id = getIntent().getIntExtra("id", 0);
        int type = getIntent().getIntExtra("type", 0);//日周月
        int style = getIntent().getIntExtra("style", 0);
        enable = getIntent().getBooleanExtra("enable", false);
        switch (style) {
            case 0:
                btCommit.setVisibility(View.GONE);
                btCc.setVisibility(View.VISIBLE);
                pllDetailsMineDay.setVisibility(View.VISIBLE);
                break;
            case 1:
                btCc.setVisibility(View.GONE);
                pllDetailsMineDay.setVisibility(View.GONE);
                break;
            case 2:
                btCc.setVisibility(View.GONE);
                pllDetailsMineDay.setVisibility(View.GONE);
                btCommit.setVisibility(View.VISIBLE);
                break;
            default:
        }
        isVisiableLiearent(id, type);
    }

    private void isVisiableLiearent(int id, int type) {
        switch (type) {
            case 0:
                tvDetailsTitle.setText("工作日报");
                vsDay.inflate();
                vsDay(id);
                break;
            case 1:
                tvDetailsTitle.setText("工作周报");
                vsWeek.inflate();
                vsWeek(id);
                break;
            case 2:
                tvDetailsTitle.setText("工作月报");
                vsMonth.inflate();
                vsMonth(id);
                break;
            default:
        }
    }

    private void vsPerformance(int id) {
        item_details_performance = findViewById(R.id.item_details_performance);
        item_details_performance.setGroupIndicator(null);
        item_details_performance.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
        adapter1 = new MyExpandableListViewAdapter();
        item_details_performance.setAdapter(adapter1);
        final LoadingDailog 获取数据中 = ToastUtils.showDailog(DetailsMineDay.this, "获取数据中");
        获取数据中.show();
        OkGo.<String>post(NetAddressUtils.selectMySumScoreMonthInfo).
                params("id",id).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        获取数据中.dismiss();
                        Log.e(TAG, "onSuccess: "+response.body().toString() );
                        PerformanceInfoBean bean = new Gson().fromJson(response.body().toString(), PerformanceInfoBean.class);
                        for (int i = 0; i <ConstantUtils.getGroupStrings().length; i++) {
                            Log.e(TAG, "onSuccess: "+bean.getData().getMyScore().get(i).getValue() );
                            map.get(ConstantUtils.getGroupStrings()[i]).add("自评打分："+bean.getData().getMyScore().get(i).getValue()+"分");

                        }
                    }

                });
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
                    if(item_details_performance.isGroupExpanded(i)){
                        item_details_performance.collapseGroup(i);
                    }else {
                        item_details_performance.expandGroup(i);
                    }
                }
            });
            final EditText ettext = view.findViewById(R.id.ettext);
            if (enable){
                ettext.setEnabled(true);
            }else {
                ettext.setEnabled(false);
            }
            tv_father.setText(ConstantUtils.getGroupStrings()[i]);
            if(!TextUtils.isEmpty(treeMap.get(i))){
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
                    treeMap.put(i,ettext.getText().toString());
                }
            });

            if(i==0||i==1){
                ettext.setFilters(new InputFilter[]{new InputFilterMinMax(0,20)});
            }else {
                ettext.setFilters(new InputFilter[]{new InputFilterMinMax(0,10)});
            }
            return view;
        }
        @Override
        public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.item_son, null);
            }
            Log.e(TAG, "getChildView: father "+i );
            Log.e(TAG, "getChildView: son"+i1);
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
                if (isInRange(min, max, input)){
                    return null;}
            } catch (NumberFormatException nfe) { }
            return "";
        }

        private boolean isInRange(int a, int b, int c) {
            return b > a ? c >= a && c <= b : c >= b && c <= a;
        }
    }
    private void vsMonth(int id) {
        final TextView et_month_work_content = findViewById(R.id.et_month_work_content);
        final TextView et_month_work_summary = findViewById(R.id.et_month_work_summary);
        final TextView et_month_plan_next = findViewById(R.id.et_month_plan_next);
        final TextView et_month_coordination_work = findViewById(R.id.et_month_coordination_work);
        final NineGridImageView niv = findViewById(R.id.niv);
        final LoadingDailog 获取数据中 = ToastUtils.showDailog(DetailsMineDay.this, "获取数据中");
        获取数据中.show();
        OkGo.<String>post(NetAddressUtils.getOneReportForm).
                params("id", id).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e(TAG, "onSuccess: " + response.body().toString());
                        DetailsMineDayBean bean = new Gson().fromJson(response.body().toString(), DetailsMineDayBean.class);
                        if (bean.isSuccess()) {
                            获取数据中.dismiss();
                            et_month_work_content.setText(bean.getData().getReportForm().getFinishWork());
                            et_month_work_summary.setText(bean.getData().getReportForm().getSummary());
                            et_month_coordination_work.setText(bean.getData().getReportForm().getCoordinateWork());
                            et_month_plan_next.setText(bean.getData().getReportForm().getWorkPlay());
                            if (bean.getData().getList() != null && bean.getData().getList().size() != 0) {
                                niv.setVisibility(View.VISIBLE);
                                for (int i = 0; i < bean.getData().getList().size(); i++) {
                                    imageList.add(NetAddressUtils.getPhoto + bean.getData().getList().get(i).getFileName() +
                                            "?type=" + bean.getData().getList().get(i).getType());
                                }
                                niv.setAdapter(mAdapter);
                                niv.setImagesData(imageList);
                            } else {
                                niv.setVisibility(View.GONE);
                            }
                        } else {
                            获取数据中.dismiss();
                            ToastUtils.showToast(getApplicationContext(), "获取失败");
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        获取数据中.dismiss();
                        ToastUtils.showToast(getApplicationContext(), "获取失败");
                    }
                });
    }

    private void vsWeek(int id) {
        final TextView et_week_work_content = findViewById(R.id.et_week_work_content);
        final TextView et_week_work_summary = findViewById(R.id.et_week_work_summary);
        final TextView et_week_plan_next = findViewById(R.id.et_week_plan_next);
        final TextView et_week_coordination_work = findViewById(R.id.et_week_coordination_work);
        final NineGridImageView niv = findViewById(R.id.niv);
        final LoadingDailog 获取数据中 = ToastUtils.showDailog(DetailsMineDay.this, "获取数据中");
        获取数据中.show();
        OkGo.<String>post(NetAddressUtils.getOneReportForm).
                params("id", id).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e(TAG, "onSuccess: " + response.body().toString());
                        DetailsMineDayBean bean = new Gson().fromJson(response.body().toString(), DetailsMineDayBean.class);
                        if (bean.isSuccess()) {
                            获取数据中.dismiss();
                            et_week_work_content.setText(bean.getData().getReportForm().getFinishWork());
                            et_week_work_summary.setText(bean.getData().getReportForm().getSummary());
                            et_week_coordination_work.setText(bean.getData().getReportForm().getCoordinateWork());
                            et_week_plan_next.setText(bean.getData().getReportForm().getWorkPlay());
                            if (bean.getData().getList() != null && bean.getData().getList().size() != 0) {
                                niv.setVisibility(View.VISIBLE);
                                for (int i = 0; i < bean.getData().getList().size(); i++) {
                                    imageList.add(NetAddressUtils.getPhoto + bean.getData().getList().get(i).getFileName() +
                                            "?type=" + bean.getData().getList().get(i).getType());
                                }
                                niv.setAdapter(mAdapter);
                                niv.setImagesData(imageList);
                            } else {
                                niv.setVisibility(View.GONE);
                            }
                        } else {
                            获取数据中.dismiss();
                            ToastUtils.showToast(getApplicationContext(), "获取失败");
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        获取数据中.dismiss();
                        ToastUtils.showToast(getApplicationContext(), "获取失败");
                    }
                });
    }

    private NineGridImageViewAdapter<String> mAdapter = new NineGridImageViewAdapter<String>() {
        @Override
        protected void onDisplayImage(Context context, ImageView imageView, String url) {

            Glide.with(context)
                    .load(url)
                    .into(imageView);
        }

        @Override
        protected ImageView generateImageView(Context context) {
            ImageView imageView = new ImageView(context);
            WindowManager wm = getWindowManager();
            int width = wm.getDefaultDisplay().getWidth();
            int height = wm.getDefaultDisplay().getHeight();
            imageView.setAdjustViewBounds(true);//设置图片自适应，只是这句话必须结合下面的setMaxWidth和setMaxHeight才能有效果。
//下面必须使用LinearLayout，如果使用ViewGroup的LayoutParams，则会报空指针异常。
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    width, height);
            imageView.setLayoutParams(layoutParams);
            imageView.setMinimumWidth(width / 3);
            imageView.setMinimumHeight(height / 3);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            return imageView;
        }

        @Override
        protected void onItemImageClick(Context context, int index, List list) {
            Intent intent = new Intent(context, PhotoViewPgaerActivity.class);
            intent.putExtra("position", index);
            intent.putExtra("size", list.size());
            intent.putStringArrayListExtra("imageList", imageList);
            startActivity(intent);
        }

    };

    private void vsDay(final int id) {
        final TextView et_day_finish_work = findViewById(R.id.et_day_finish_work);
        final TextView et_day_no_work = findViewById(R.id.et_day_no_work);
        final TextView et_day_concert = findViewById(R.id.et_day_concert);
        final NineGridImageView niv = findViewById(R.id.niv);
        final LoadingDailog 获取数据中 = ToastUtils.showDailog(DetailsMineDay.this, "获取数据中");
        获取数据中.show();
        OkGo.<String>post(NetAddressUtils.getOneReportForm).
                params("id", id).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        DetailsMineDayBean bean = new Gson().fromJson(response.body().toString(), DetailsMineDayBean.class);
                        if (bean.isSuccess()) {
                            获取数据中.dismiss();
                            et_day_finish_work.setText(bean.getData().getReportForm().getFinishWork());
                            et_day_no_work.setText(bean.getData().getReportForm().getUnWork());
                            et_day_concert.setText(bean.getData().getReportForm().getCoordinateWork());
                            if (bean.getData().getList() != null && bean.getData().getList().size() != 0) {
                                niv.setVisibility(View.VISIBLE);
                                for (int i = 0; i < bean.getData().getList().size(); i++) {
                                    imageList.add(NetAddressUtils.getPhoto + bean.getData().getList().get(i).getFileName() +
                                            "?type=" + bean.getData().getList().get(i).getType());
                                }
                                niv.setAdapter(mAdapter);
                                niv.setImagesData(imageList);
                            } else {
                                niv.setVisibility(View.GONE);
                            }
                        } else {
                            获取数据中.dismiss();
                            ToastUtils.showToast(getApplicationContext(), "获取失败");
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        获取数据中.dismiss();
                        ToastUtils.showToast(getApplicationContext(), "获取失败");
                    }
                });
    }


    @Override
    public void initData() {
        adapter = new AccompanyGvidViewAdapter(this, personList);
        gvShare.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gvShare.setAdapter(adapter);
        gvShare.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == personList.size()) {
                    //跳转到选择陪同人
                    Intent intent = new Intent(DetailsMineDay.this, ChooseAccompanyActivity.class);
                    startActivityForResult(intent, 200);
                } else {
                    personList.remove(position);
                    personIdList.remove(position);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
        btCc.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.bt_cc:
                share();
                break;
            case R.id.bt_commit:
                commit();
            default:
        }
    }

    private void commit() {


    }

    private void share() {

        if (personIdList.size() == 0) {
            ToastUtils.showToast(DetailsMineDay.this, "请选择抄送人");
            return;
        }
        final LoadingDailog 抄送中 = ToastUtils.showDailog(this, "抄送中");
        抄送中.show();
        OkGo.<String>post(NetAddressUtils.reportForward).
               params("id",id).
               params("ids",new Gson().toJson(personIdList)).
               params("activity","com.hsap.huisianpu.push.PushWeekActivity")
               .execute(new StringCallback() {
                   @Override
                   public void onSuccess(Response<String> response) {
                       抄送中.dismiss();
                       ToastUtils.showToast(DetailsMineDay.this,"抄送成功");
                   }

                   @Override
                   public void onError(Response<String> response) {
                       super.onError(response);
                       抄送中.dismiss();
                       ToastUtils.showToast(DetailsMineDay.this,"抄送失败");
                   }
               });
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
                adapter.notifyDataSetChanged();

            }
        }
    }
}
