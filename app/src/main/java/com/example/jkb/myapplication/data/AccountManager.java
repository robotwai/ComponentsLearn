package com.example.jkb.myapplication.data;

import android.content.Intent;
import android.util.Log;


import com.example.jkb.myapplication.MyApplication;
import com.example.jkb.myapplication.activity.LoginActivity;
import com.example.jkb.myapplication.utils.Constans;
import com.example.jkb.myapplication.utils.ToastUtils;


import retrofit2.Response;

/**
 * Created by lz on 2017/4/11.
 */

public class AccountManager implements Constans{
    static volatile AccountManager instance;


    //    此处使用双重检查锁定(DCL)避免多线程导致产生多实例
    public static AccountManager getInstance() {
        if (instance == null) {
            synchronized (AccountManager.class) {
                if (instance == null) {
                    instance = new AccountManager();
                }
            }
        }
        return instance;
    }



    //退出登录
    public void exitAccount() {

        MyApplication.getInstance().sharedPreferenceHelper.setString(TOKEN,"");
        MyApplication.getInstance().sharedPreferenceHelper.setString(USER_SP,"");
        Intent intent = new Intent(MyApplication.getInstance(),LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
        MyApplication.getInstance().startActivity(intent);
        ToastUtils.Tip("登录失效 请重新登录");
    }



}
