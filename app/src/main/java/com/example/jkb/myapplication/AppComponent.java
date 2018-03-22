package com.example.jkb.myapplication;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by lz on 18-3-15.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(MyApplication application);
}
