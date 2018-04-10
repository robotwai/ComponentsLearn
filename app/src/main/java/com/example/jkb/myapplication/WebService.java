package com.example.jkb.myapplication;

import android.arch.lifecycle.LiveData;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;

/**
 * Created by jkb on 18/4/10.
 */
@Singleton
public class WebService {
    Retrofit retrofit;
    @Inject
    public WebService(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public LiveData<ApiResponse<Person>> getPerson(int id){
        return retrofit.create(DemoService.class).getPerson(id);
    }
}
