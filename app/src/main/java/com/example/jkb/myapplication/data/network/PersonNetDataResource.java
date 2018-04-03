package com.example.jkb.myapplication.data.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.jkb.myapplication.AppExecutors;
import com.example.jkb.myapplication.DemoService;
import com.example.jkb.myapplication.LogUtils;
import com.example.jkb.myapplication.Person;
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
@Singleton
public class PersonNetDataResource implements PersonDataResource {
    private Retrofit retrofit;

    @Inject
    public PersonNetDataResource(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public LiveData<List<Person>> getPersons() {
        final MutableLiveData<List<Person>> listLiveData = new MutableLiveData<>();
        retrofit.create(DemoService.class).getPeople().enqueue(
                new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String s = response.body().string();
                            LogUtils.log(s);
                            Type type = new TypeToken<ArrayList<Person>>() {
                            }.getType();

                            List<Person> list = new Gson().fromJson(s,type);
                            listLiveData.setValue(list);
                        }catch (Exception e){

                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                }
        );
        return listLiveData;
    }

    @Override
    public LiveData<Person> getPerson(int uid) {
        final MutableLiveData<Person> listLiveData = new MutableLiveData<>();
        retrofit.create(DemoService.class).getPeople(uid).enqueue(
                new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String s = response.body().string();
                            LogUtils.log(s);
                            Type type = new TypeToken<Person>() {
                            }.getType();

                            Person list = new Gson().fromJson(s,type);
                            listLiveData.setValue(list);
                        }catch (Exception e){

                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                }
        );
        return listLiveData;
    }

    @Override
    public void savePerson(Person person) {

    }
}
