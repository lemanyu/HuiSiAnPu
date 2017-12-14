package com.hsap.huisianpu.activity;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseActivity;
import com.hsap.huisianpu.view.PhotoViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zhao on 2017/12/13.
 */

public class PhotoViewPgaerActivity extends BaseActivity {
    @BindView(R.id.svp)
    PhotoViewPager svp;
    @BindView(R.id.main_linear)
    LinearLayout mainLinear;
    private ArrayList<String> imageList;
    private int position;
    private int size;
    private List<ImageView> mImageList = new ArrayList<ImageView>();
    @Override
    public int getLayoutId() {
        //取消标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_photo;
    }

    @Override
    public void initView() {
        //第几个
        position = getIntent().getIntExtra("position",0);
        //多少个
        size = getIntent().getIntExtra("size", 0);
        imageList = getIntent().getStringArrayListExtra("imageList");
        for (int i = 0; i <imageList.size(); i++) {
           ImageView  view = new ImageView(this);
             view.setBackgroundResource(R.drawable.background);
             view.setEnabled(false);
            //设置宽高
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(20, 20);
            //设置间隔
            if (i!=0) {
                layoutParams.leftMargin = 30;
            }
            //添加到LinearLayout
            mainLinear.addView(view, layoutParams);
        }
    }

    @Override
    public void initData() {

        MyAdapter adapter = new MyAdapter();
        svp.setAdapter(adapter);

        mainLinear.getChildAt(position).setEnabled(true);
        svp.setCurrentItem(position);
        svp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int mNum=position;
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                mainLinear.getChildAt(mNum).setEnabled(false);
                mainLinear.getChildAt(position).setEnabled(true);
                mNum = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void initListener() {

    }
   class MyAdapter extends PagerAdapter{

       @Override
       public int getCount() {
           return imageList.size();
       }

       @Override
       public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
           return view==object;
       }

       @NonNull
       @Override
       public Object instantiateItem(@NonNull ViewGroup container, int position) {
           View view=View.inflate(PhotoViewPgaerActivity.this,R.layout.item_details_photo,null);
           PhotoView iv_photo = view.findViewById(R.id.iv_photo);
           iv_photo.setOnPhotoTapListener(new OnPhotoTapListener() {
               @Override
               public void onPhotoTap(ImageView view, float x, float y) {
                   finish();
               }
           });
           LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
           iv_photo.setLayoutParams(layoutParams);
           Glide.with(PhotoViewPgaerActivity.this)
                   .load(imageList.get(position))
                   .into(iv_photo);
           container.addView(view);
           return view;
       }

       @Override
       public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
           container.removeView((View) object);
       }

   }
    @Override
    public void processClick(View v) {

    }
}
