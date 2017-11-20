package com.hsap.huisianpu.utils;

import android.content.Context;
import android.widget.Toast;

import com.android.tu.loadingdialog.LoadingDailog;
import com.hsap.huisianpu.R;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

/**
 * Created by zhao on 2017/11/17.
 */

public class ToastUtils {
    public static void showToast(Context context,String text){
        StyleableToast.makeText(context,text, Toast.LENGTH_SHORT, R.style.MyToast).show();
    }
    public static LoadingDailog showDailog(Context context, String text){
        LoadingDailog dailog = new LoadingDailog.Builder(context)
                .setMessage(text)
                .setCancelable(false)
                .setCancelOutside(false).create();
        dailog.show();
        return dailog;
    }
}
