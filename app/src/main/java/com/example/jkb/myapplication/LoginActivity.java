package com.example.jkb.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jkb.myapplication.data.BaseResponse;
import com.example.jkb.myapplication.utils.MapTransToRubyUtil;
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
                .enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        if (response.body() != null && response.body().getStatus() == 0) {
                            String s= response.body().getData().toString();
                            LogUtils.log(s);
                            User user = new Gson().fromJson(s, User.class);
                            helper.setString(USER_SP, response.body().getData().toString());
                            helper.setString(TOKEN, user.getToken());
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "账号密码错误", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "账号密码错误", Toast.LENGTH_SHORT).show();
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
