package com.example.jkb.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.jkb.myapplication.data.local.MicropostDao;
import com.example.jkb.myapplication.data.local.MicropostDatabase;
import com.example.jkb.myapplication.data.local.PersonDao;
import com.example.jkb.myapplication.data.local.PersonDatabase;
import com.example.jkb.myapplication.utils.ExtraGsonConverterFactory;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lz on 18-3-15.
 */
@Module
public class AppModule {
    Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    PersonDao providePerson(){
        return PersonDatabase.getInstance(context).personDao();
    }

    @Singleton
    @Provides
    MicropostDao provideMicropost(){
        return MicropostDatabase.getInstance(context).micropostDatabase();
    }

    @Singleton
    @Provides
    Executor provideExecutor(){
        return new DiskIOThreadExecutor();
    }

    @Singleton
    @Provides
    DemoService provideDemoService(){

        return new Retrofit.Builder()
                .baseUrl(DemoService.url)
                .addConverterFactory( ExtraGsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new CommonInterceptor())
                        .build())
                .build()
                .create(DemoService.class);

    }

    @Singleton
    @Provides
    SharedPreferences provideSharePreferences(){
        return PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    }

}
