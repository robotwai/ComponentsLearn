package com.example.jkb.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jkb.myapplication.ApiResponse;
import com.example.jkb.myapplication.LogUtils;
import com.example.jkb.myapplication.MyApplication;
import com.example.jkb.myapplication.R;
import com.example.jkb.myapplication.SharedPreferenceHelper;
import com.example.jkb.myapplication.User;
import com.example.jkb.myapplication.data.BaseResponse;
import com.google.gson.Gson;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jkb on 18/4/23.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.et_phone)
    EditText editText;
    @BindView(R.id.et_password)
    EditText editText2;

    SharedPreferenceHelper helper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_login);
        ButterKnife.bind(this);
        helper = ((MyApplication) getApplication()).sharedPreferenceHelper;
    }


    public void login() {
        HashMap<String, String> map = new HashMap<>();
        map.put("email", editText.getText().toString());
        LogUtils.log(editText.getText().toString());
        LogUtils.log(editText2.getText().toString());
        map.put("password", editText2.getText().toString());
        ((MyApplication) getApplication()).personRepository.webService.norlogin(map)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.body()!=null&&response.body()!=null){
                            User user = response.body();
                            helper.setString(USER_SP, new Gson().toJson(user));
                            helper.setString(TOKEN, user.getToken());
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "账号密码错误", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });

    }

    @OnClick({R.id.btn_login, R.id.tv_register,R.id.tv_forget})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.tv_register:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
            case R.id.tv_forget:
                break;
        }
    }
}
