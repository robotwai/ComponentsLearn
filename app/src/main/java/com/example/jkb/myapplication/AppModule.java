package com.example.jkb.myapplication;

import android.content.Context;
import android.util.Log;

import com.example.jkb.myapplication.data.local.PersonDao;
import com.example.jkb.myapplication.data.local.PersonDatabase;

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
    Retrofit provideRetrofit(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("lzz","lzzz====message " + message);
            }
        });
        return new Retrofit.Builder()
                .baseUrl("http://192.168.45.52:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder()
                    .addInterceptor(new CommonInterceptor())
                    .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                        @Override
                        public void log(String message) {
                            Log.i("lzz","lzzz====message " + message);
                        }
                    }))
                    .build())
                .build();
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
                .baseUrl("http://192.168.45.52:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(DemoService.class);
//        return new Retrofit.Builder()
//                .baseUrl("http://192.168.45.52:3000/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(new OkHttpClient.Builder()
//                        .addInterceptor(new CommonInterceptor())
//                        .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//                            @Override
//                            public void log(String message) {
//                                Log.i("lzz","lzzz====message " + message);
//                            }
//                        }))
//                        .build())
//                ;
    }
}
