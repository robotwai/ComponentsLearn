package com.example.jkb.myapplication;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by lz on 18-3-15.
 */
@Singleton
@Component(modules = PersonMoudle.class)
public interface PersonComponent {
    void inject(MainActivity mainActivity);

    PersonDao providePerson();
}
