package com.example.jkb.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.BaseAdapter;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jkb on 18/5/7.
 */

public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_splash);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (((MyApplication) getApplication()).sharedPreferenceHelper.getString(TOKEN).isEmpty()){
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }else {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
            }
        }, 1000);//3秒后执行TimeTask的run方法
    }
}
