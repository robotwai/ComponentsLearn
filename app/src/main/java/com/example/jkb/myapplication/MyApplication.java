package com.example.jkb.myapplication;

import android.app.Application;
import android.arch.persistence.room.Room;

import javax.inject.Inject;

/**
 * Created by jkb on 18/3/5.
 */

public class MyApplication extends Application {
    @Inject
    public PersonRepository personRepository;

    @Inject
    public SharedPreferenceHelper sharedPreferenceHelper;

    @Inject
    public MicropostRepository micropostRepository;

    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    public MyApplication() {
        instance = this;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder().appModule(new AppModule(this)).build().inject(this);
    }



}
