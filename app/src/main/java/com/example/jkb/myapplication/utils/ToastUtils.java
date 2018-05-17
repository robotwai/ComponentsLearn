package com.example.jkb.myapplication.utils;

import android.widget.Toast;

import com.example.jkb.myapplication.MyApplication;

/**
 * Created by liz on 16-12-21.
 * 这里可以对Toast做全局自定义
 */

public class ToastUtils {
    private static Toast toast = null;

    public static void Tip(String tips) {

        if (toast == null) {
            toast = Toast.makeText(MyApplication.getInstance(), tips, Toast.LENGTH_SHORT);
        } else {
            toast.setText(tips);
        }
        toast.show();
    }
}
