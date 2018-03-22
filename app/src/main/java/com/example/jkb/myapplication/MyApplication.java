package com.example.jkb.myapplication;

import android.app.Application;
import android.arch.persistence.room.Room;

import javax.inject.Inject;

/**
 * Created by jkb on 18/3/5.
 */

public class MyApplication extends Application {
    @Inject
    PersonRepository personRepository;
    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder().appModule(new AppModule(this)).build().inject(this);
    }

}
