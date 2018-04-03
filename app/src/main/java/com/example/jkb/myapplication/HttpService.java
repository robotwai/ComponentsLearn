package com.example.jkb.myapplication;

import android.arch.lifecycle.LiveData;

import java.util.List;

import javax.inject.Singleton;

import retrofit2.Retrofit;

/**
 * Created by jkb on 18/4/3.
 */
public class HttpService {
    private Retrofit retrofit;

    public HttpService(Retrofit retrofit) {
        this.retrofit = retrofit;
    }


}
