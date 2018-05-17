package com.example.jkb.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.jkb.myapplication.MyApplication;
import com.example.jkb.myapplication.R;
import com.example.jkb.myapplication.data.AccountManager;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jkb on 18/5/11.
 */

public class SettingActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_setting);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_cache, R.id.rl_about_us, R.id.rl_version_information, R.id.rl_service_declaration, R.id.btn_exit_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_cache:
                break;
            case R.id.rl_about_us:
                break;
            case R.id.rl_version_information:
                break;
            case R.id.rl_service_declaration:
                break;
            case R.id.btn_exit_login:
                AccountManager.getInstance().exitAccount();
                break;
        }
    }
}
