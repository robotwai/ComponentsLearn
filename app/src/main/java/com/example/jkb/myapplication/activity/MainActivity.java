package com.example.jkb.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.jkb.myapplication.FeedFragment;
import com.example.jkb.myapplication.LogUtils;
import com.example.jkb.myapplication.MyApplication;
import com.example.jkb.myapplication.R;
import com.example.jkb.myapplication.User;
import com.example.jkb.myapplication.utils.GlideCircleTransform;
import com.google.gson.Gson;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingMenuLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.jkb.myapplication.DemoService.url;

/**
 * Created by jkb on 18/3/2.
 */

public class MainActivity extends BaseActivity {


    @BindView(R.id.iv_icon_s)
    ImageView ivIconS;
    @BindView(R.id.menulayout)
    FlowingMenuLayout menulayout;
    @BindView(R.id.drawerlayout)
    FlowingDrawer drawerlayout;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.tv_followed)
    TextView tvFollowed;
    @BindView(R.id.tv_follower)
    TextView tvFollower;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        String s = ((MyApplication) getApplication()).sharedPreferenceHelper.getString(USER_SP);
        LogUtils.log(s);
        User user = new Gson().fromJson(s, User.class);
        tvName.setText(user.getName());
        LogUtils.log(user.getIcon());
        Glide.with(this).
                load(url + user.getIcon()).dontAnimate().
                diskCacheStrategy(DiskCacheStrategy.ALL).
                centerCrop().transform(new GlideCircleTransform(this)).into(ivIcon);
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


    @OnClick({R.id.ll_info, R.id.ll_set, R.id.ll_mark, R.id.iv_icon_s, R.id.iv_pen, R.id.tv_followed, R.id.tv_follower})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_info:

                break;
            case R.id.ll_set:
                startActivity(new Intent(this, SettingActivity.class));
                break;
            case R.id.ll_mark:
                break;
            case R.id.iv_icon_s:
                drawerlayout.openMenu(true);
                break;
            case R.id.iv_pen:
                startActivity(new Intent(this, AddMicropostActivity.class));
                break;
            case R.id.tv_followed:
                break;
            case R.id.tv_follower:
                break;
        }
    }
}
