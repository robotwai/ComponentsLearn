package com.example.jkb.myapplication;

import dagger.Component;

/**
 * Created by lz on 18-3-15.
 */
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(MyApplication application);
}
