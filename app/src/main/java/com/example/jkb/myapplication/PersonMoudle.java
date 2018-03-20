package com.example.jkb.myapplication;

import android.content.Context;

import java.util.concurrent.Executor;

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

    @Provides
    PersonRepository providePeRep(PersonDao personDao,Executor executor){
        return PersonRepository.getInstance(personDao,executor);
    }

    @Provides
    PersonDao providePerson(){
        return PersonDatabase.getInstance(context).personDao();
    }
    @Provides
    Executor provideExe(){
        return new DiskIOThreadExecutor();
    }

}
