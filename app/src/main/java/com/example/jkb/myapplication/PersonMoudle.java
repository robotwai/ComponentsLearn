package com.example.jkb.myapplication;

import android.content.Context;

import com.example.jkb.myapplication.data.local.PersonDao;
import com.example.jkb.myapplication.data.local.PersonDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lz on 18-3-15.
 */
@Module
public class PersonMoudle {
    Context context;

    public PersonMoudle(Context context) {
        this.context = context;
    }


    @Singleton
    @Provides
    PersonDao providePerson(){
        return PersonDatabase.getInstance(context).personDao();
    }
//    @Provides
//    Executor provideExe(){
//        return new DiskIOThreadExecutor();
//    }

}
