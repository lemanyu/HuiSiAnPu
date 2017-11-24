package com.hsap.huisianpu.activity;

import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.hsap.huisianpu.R;
import com.hsap.huisianpu.fragment.MineFragment;
import com.hsap.huisianpu.fragment.NewsFragment;
import com.hsap.huisianpu.fragment.WorkFragment;
import com.hsap.huisianpu.utils.ActivityManagerUtils;
import com.hsap.huisianpu.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.rb_news)
    RadioButton rbNews;
    @BindView(R.id.rb_work)
    RadioButton rbWork;
    @BindView(R.id.rb_mine)
    RadioButton rbMine;
    @BindView(R.id.fl_main)
    FrameLayout flMain;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;
    private NewsFragment nf;
    private WorkFragment wf;
    private MineFragment mf;
    private long exitTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ActivityManagerUtils.getInstance().addActivity(this);
        initView();
        RadioGroup rg_main = findViewById(R.id.rg_main);
        RadioButton rb_news=findViewById(R.id.rb_news);
        rg_main.setOnCheckedChangeListener(this);
        rb_news.setChecked(true);
    }
   private void initView(){
       Drawable drawable = ContextCompat.getDrawable(this,R.drawable.news_selector);
       drawable.setBounds(0,0,70,70);
       rbNews.setCompoundDrawables(null,drawable,null,null);
       Drawable drawable1 = ContextCompat.getDrawable(this, R.drawable.work_selector);
       drawable1.setBounds(0,0,70,70);
       rbWork.setCompoundDrawables(null,drawable1,null,null);
       Drawable drawable2 = ContextCompat.getDrawable(this, R.drawable.mine_selector);
       drawable2.setBounds(0,0,80,80);
       rbMine.setCompoundDrawables(null,drawable2,null,null);
   }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        hideAllFragment(ft);
        switch (i) {
            case R.id.rb_news:
                if (nf == null) {
                    nf = new NewsFragment();
                    ft.add(R.id.fl_main, nf);
                } else ft.show(nf);
                break;
            case R.id.rb_work:
                if (wf == null) {
                    wf = new WorkFragment();
                    ft.add(R.id.fl_main, wf);
                } else ft.show(wf);
                break;
            case R.id.rb_mine:
                if (mf == null) {
                    mf = new MineFragment();
                    ft.add(R.id.fl_main, mf);
                } else ft.show(mf);
                break;
        }
        ft.commit();
    }
    private void hideAllFragment(FragmentTransaction ft) {
        if (nf != null) ft.hide(nf);
        if (wf != null) ft.hide(wf);
        if (mf != null) ft.hide(mf);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000)  //System.currentTimeMillis()无论何时调用，肯定大于2000
            {
               ToastUtils.showToast(MainActivity.this,"再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
