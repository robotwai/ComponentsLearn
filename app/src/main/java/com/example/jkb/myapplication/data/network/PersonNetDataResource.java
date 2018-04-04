package com.example.jkb.myapplication.data.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.jkb.myapplication.DemoService;
import com.example.jkb.myapplication.LogUtils;
import com.example.jkb.myapplication.Person;
import com.example.jkb.myapplication.data.BaseResponse;
import com.example.jkb.myapplication.data.PersonDataResource;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by jkb on 18/4/3.
 */

public class PersonNetDataResource {
    private Retrofit retrofit;


    public PersonNetDataResource(Retrofit retrofit) {
        this.retrofit = retrofit;
    }


//    public LiveData<List<Person>> getPersons() {
//        final MutableLiveData<List<Person>> listLiveData = new MutableLiveData<>();
//
//
//    }
//
//    @Override
//    public void getPerson(int uid,final MutableLiveData<Person> liveData) {
//
//        retrofit.create(DemoService.class).getPeople(uid).enqueue(
//                new Callback<BaseResponse<Person>>() {
//                    @Override
//                    public void onResponse(Call<BaseResponse<Person>> call, Response<BaseResponse<Person>> response) {
//                        liveData.setValue(response.body().getData());
//                    }
//
//                    @Override
//                    public void onFailure(Call<BaseResponse<Person>> call, Throwable t) {
//
//                    }
//                }
//        );
//
//    }
//
//    @Override
//    public void savePerson(Person person) {
//        retrofit.create(DemoService.class).savePeople(person.getName(),person.getAddress(),person.getPhone())
//                .enqueue(new Callback<BaseResponse>() {
//                    @Override
//                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
//                        if (response.body().getStatus()==0){
//                            LogUtils.log("ok");
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<BaseResponse> call, Throwable t) {
//
//                    }
//                });
//    }
//
//    @Override
//    public void removePerson(int id,MutableLiveData<List<Person>> listLiveData) {
//        retrofit.create(DemoService.class).deletePeople(id)
//                .enqueue(new Callback<BaseResponse>() {
//                    @Override
//                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
//                        if (response.body().getStatus()==0){
//                            LogUtils.log("ok");
//
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<BaseResponse> call, Throwable t) {
//
//                    }
//                });
//
//
//    }
}
