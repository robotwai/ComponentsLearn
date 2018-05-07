package com.example.jkb.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jkb on 18/3/2.
 */

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    String url = "http://192.168.45.47:3000";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        String s =((MyApplication) getApplication()).sharedPreferenceHelper.getString("user");
        User user = new Gson().fromJson(s,User.class);
        textView6.setText(user.getName());
        LogUtils.log(user.getIcon());
        Glide.with(this).
                load(url+user.getIcon()).dontAnimate().
                diskCacheStrategy(DiskCacheStrategy.ALL).
                centerCrop().into(imageView2);
    }


    @OnClick({R.id.button2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button2:
                startActivity(new Intent(this, ListActivity.class));
                break;
        }
    }

}
