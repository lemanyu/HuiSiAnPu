package com.hsap.huisianpu.activity;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.bean.Bean;
import com.hsap.huisianpu.fragment.MineFragment;
import com.hsap.huisianpu.fragment.NewsFragment;
import com.hsap.huisianpu.fragment.WorkFragment;
import com.hsap.huisianpu.utils.ActivityManagerUtils;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.NotificationsUtils;
import com.hsap.huisianpu.utils.SpUtils;
import com.hsap.huisianpu.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private static final String TAG = "MainActivity";
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
    private RadioButton [] rbs=new RadioButton[3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ActivityManagerUtils.getInstance().addActivity(this);
        OkGo.<String>post(NetAddressUtils.url+"getNoAuditSize").
                params("managerId", SpUtils.getInt(ConstantUtils.UserId,this)).
                execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.e(TAG, "onSuccess: "+response.body().toString() );
                Bean bean = new Gson().fromJson(response.body().toString(), Bean.class);
                bean.getData();
                Log.e(TAG, "onSuccess: "+bean.getData());
                SpUtils.putInt(ConstantUtils.Approve,bean.getData(),MainActivity.this);
            }
        });
        initView();
        boolean notificationEnabled = NotificationsUtils.isNotificationEnabled(this);
        if(!notificationEnabled){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("当前通知未打开");
            builder.setMessage("请手动打开，确保可以收到及时消息");
            builder.setPositiveButton("打开", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    toSetting();
                }
            });
            builder.setNegativeButton("取消",null);
            builder.show();
        }
        RadioGroup rg_main = findViewById(R.id.rg_main);
        RadioButton rb_news=findViewById(R.id.rb_news);
        rg_main.setOnCheckedChangeListener(this);
        rb_news.setChecked(true);
    }

    private void initView() {
        rbs[0]= findViewById(R.id.rb_news);
        rbs[1]=findViewById(R.id.rb_work);
        rbs[2]=findViewById(R.id.rb_mine);
        for (int i = 0; i < rbs.length; i++) {
            if (i==rbs.length-1){
                Drawable[] drawables = rbs[2].getCompoundDrawables();
                Rect rect = new Rect(0, 0,
                        drawables[1].getMinimumWidth()/5,
                        drawables[1].getMinimumHeight()/5);
                drawables[1].setBounds(rect);
                rbs[2].setCompoundDrawables(null,drawables[1],null,null);
            }else {
                Drawable[] drawables = rbs[i].getCompoundDrawables();
                Rect rect = new Rect(0, 0,
                        drawables[1].getMinimumWidth()/6,
                        drawables[1].getMinimumHeight()/6);
                drawables[1].setBounds(rect);
                rbs[i].setCompoundDrawables(null,drawables[1],null,null);
            }

        }
    }


    private void toSetting() {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.setting.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", getPackageName());
        }
        startActivity(localIntent);
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
                } else {ft.show(nf);}
                break;
            case R.id.rb_work:
                if (wf == null) {
                    wf = new WorkFragment();
                    ft.add(R.id.fl_main, wf);
                } else {ft.show(wf);}
                break;
            case R.id.rb_mine:
                if (mf == null) {
                    mf = new MineFragment();
                    ft.add(R.id.fl_main, mf);
                } else {ft.show(mf);}
                break;
                default:
        }
        ft.commit();
    }
    private void hideAllFragment(FragmentTransaction ft) {
        if (nf != null) {ft.hide(nf);}
        if (wf != null) {ft.hide(wf);}
        if (mf != null){ft.hide(mf);}
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
