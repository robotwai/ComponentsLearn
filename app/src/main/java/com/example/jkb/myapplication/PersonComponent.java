package com.example.jkb.myapplication;

import android.content.Context;

import dagger.Component;

/**
 * Created by lz on 18-3-15.
 */
@Component(modules = PersonMoudle.class)
public interface PersonComponent {
    void inject(MainActivity mainActivity);
}
