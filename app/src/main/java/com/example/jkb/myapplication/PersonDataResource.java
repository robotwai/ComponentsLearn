package com.example.jkb.myapplication;

import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * Created by jkb on 18/3/6.
 */

public interface PersonDataResource {

    void getPersons(LoadAllPersonCallback callback);

    void getPerson(int uid,LoadPersonCallback callback);

    void savePerson(Person person);

    interface LoadPersonCallback{
        void onLoadPerson(LiveData<Person> person);
        void onLoadFailed();
    }

    interface LoadAllPersonCallback{
        void onLoadPersons(LiveData<List<Person>> list);
        void onLoadFailed();
    }

}
