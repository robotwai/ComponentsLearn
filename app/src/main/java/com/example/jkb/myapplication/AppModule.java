package com.example.jkb.myapplication;

import android.content.Context;

import com.example.jkb.myapplication.data.local.PersonDao;
import com.example.jkb.myapplication.data.local.PersonDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

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
        return new Retrofit.Builder()
                .baseUrl("http://192.168.45.52:3000/")
                .build();
    }
}
