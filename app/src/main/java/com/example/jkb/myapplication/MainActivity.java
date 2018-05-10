package com.example.jkb.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.jkb.myapplication.utils.GlideCircleTransform;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.jkb.myapplication.DemoService.url;

/**
 * Created by jkb on 18/3/2.
 */

public class MainActivity extends BaseActivity {


    TextView textView6;

    ImageView imageView2;

    @BindView(R.id.nav)
    NavigationView nav;
    @BindView(R.id.iv_icon_s)
    ImageView ivIconS;
    @BindView(R.id.dl)
    DrawerLayout dl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        View headerView = nav.getHeaderView(0);//获取头布局
        imageView2 = headerView.findViewById(R.id.iv_icon);
        textView6 = headerView.findViewById(R.id.tv_name);
        String s = ((MyApplication) getApplication()).sharedPreferenceHelper.getString(USER_SP);
        LogUtils.log(s);
        User user = new Gson().fromJson(s, User.class);
        textView6.setText(user.getName());
        LogUtils.log(user.getIcon());
        Glide.with(this).
                load(url + user.getIcon()).dontAnimate().
                diskCacheStrategy(DiskCacheStrategy.ALL).
                centerCrop().transform(new GlideCircleTransform(this)).into(imageView2);
        Glide.with(this).
                load(url + user.getIcon()).dontAnimate().
                diskCacheStrategy(DiskCacheStrategy.ALL).
                centerCrop().transform(new GlideCircleTransform(this)).into(ivIconS);

        FeedFragment feedFragment = new FeedFragment();
        Bundle args = new Bundle();
        args.putInt(ID, user.getId());

        feedFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().add(R.id.container, feedFragment).commit();

    }


    @OnClick(R.id.iv_icon_s)
    public void onViewClicked() {
        if (dl.isDrawerOpen(nav)) {
            dl.closeDrawer(nav);
        } else {
            dl.openDrawer(nav);
        }
    }
}
