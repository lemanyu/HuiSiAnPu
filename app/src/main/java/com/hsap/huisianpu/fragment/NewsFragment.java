package com.hsap.huisianpu.fragment;


import android.app.AlertDialog;
import android.content.ContentValues;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.activity.MainActivity;
import com.hsap.huisianpu.base.BaseFragment;
import com.hsap.huisianpu.bean.DataBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.litepal.crud.DataSupport;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by zhao on 2017/11/15.
 */

public class NewsFragment extends BaseFragment {
    @BindView(R.id.news_toolbar)
    Toolbar newsToolbar;
    @BindView(R.id.news_rlv)
    RecyclerView newsRlv;
    Unbinder unbinder;
     private ArrayList<DataBean> list=new ArrayList<>();
     private Myadapter adapter;
     private static String TAG="NewsFragment";
    @Override
    public View initView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_news, null);
        return view;
    }

    @Override
    public void initData() {

        List<DataBean> beanList = DataSupport.select("title", "content",
                "date","state","type","numberId","workId","check").
                order("date desc").limit(DataSupport.findAll(DataBean.class).size()).find(DataBean.class);
        list.addAll(beanList);
        setHasOptionsMenu(true);
     //   newsToolbar.setOverflowIcon(getResources().getDrawable(R.drawable.add));
        newsToolbar.setTitle("消息");
        ((AppCompatActivity)getActivity()).setSupportActionBar(newsToolbar);
        newsRlv.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new Myadapter();
        newsRlv.setAdapter(adapter);
    }

    @Override
    public void initListener() {
        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(newsRlv);
        adapter.enableSwipeItem();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter aadapter, View view, int position) {
                DataBean bean = list.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                builder.setTitle(bean.getTitle());
                builder.setMessage(bean.getContent());
                builder.setNegativeButton("知道了",null);
                builder.show();
                bean.setCheck(false);
                ContentValues values = new ContentValues();
                values.put("check",false);
                DataBean.updateAll(DataBean.class, values, "numberId = ?", list.get(position).getNumberId()+"");
                adapter.notifyDataSetChanged();

            }
        });
        adapter.setOnItemSwipeListener(new OnItemSwipeListener() {
            @Override
            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {
            }

            @Override
            public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {
                DataSupport.deleteAll(DataBean.class, "numberId = ?", list.get(pos).getNumberId()+"");
            }

            @Override
            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {

            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
      //  inflater.inflate(R.menu.news_menu,menu);
        setIconsVisiable(menu,true);

    }

    private void setIconsVisiable(Menu menu, boolean b) {
        //判断menu是否为空
        if(menu != null) {
            try {
                //如果不为空,就反射拿到menu的setOptionalIconsVisible方法
                Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                //暴力访问该方法
                method.setAccessible(true);
                //调用该方法显示icon
                method.invoke(menu, b);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                Toast.makeText(mActivity, "aaa", Toast.LENGTH_SHORT).show();
                break;

                default:
        }
        return true;
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDataBean(DataBean dataBean) {
           list.add(0,dataBean);
           Log.e(TAG, "onDataBean: "+dataBean );
           adapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    class Myadapter extends BaseItemDraggableAdapter<DataBean,Myadapter.MyViewHolder> {

        public Myadapter() {
            super(R.layout.item_rlv,list);
        }

        @Override
        protected void convert(MyViewHolder helper, DataBean item) {
               if (item.isCheck()){
                   helper.badge.setBadgeNumber(1).setShowShadow(true).
                           setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
                               @Override
                               public void onDragStateChanged(int dragState, Badge badge, View targetView) {

                               }
                           });
               }else {
                   helper.badge.setBadgeNumber(0);
               }
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            helper.tv_news_name.setText(item.getTitle());
               helper.tv_news_content.setText(item.getContent());
               helper.tv_news_time.setText(dateFormat.format(item.getDate()));
        }

        class MyViewHolder extends BaseViewHolder{
            private final LinearLayout ll_news;
            private final TextView tv_news_name;
            private final TextView tv_news_content;
            private final TextView tv_news_time;
            private final Badge badge;
            public MyViewHolder(View view) {
                super(view);
                ll_news = view.findViewById(R.id.ll_news);
                tv_news_name = view.findViewById(R.id.tv_news_name);
                tv_news_content = view.findViewById(R.id.tv_news_content);
                tv_news_time=view.findViewById(R.id.tv_news_time);
                badge = new QBadgeView(mActivity).bindTarget(ll_news);
                badge.setBadgeGravity(Gravity.CENTER|Gravity.END);
                badge.setBadgeTextSize(14, true);
                badge.setBadgePadding(5, true);

            }
        }
    }

}
