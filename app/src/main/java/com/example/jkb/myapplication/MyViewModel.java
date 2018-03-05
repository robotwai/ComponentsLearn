package com.example.jkb.myapplication;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.persistence.room.Room;

/**
 * Created by jkb on 18/3/2.
 */

public class MyViewModel extends ViewModel {


    private MutableLiveData<Person> personMutableLiveData;

    public MutableLiveData<Person> getPersonMutableLiveData() {
        if (personMutableLiveData==null){
            personMutableLiveData = new MutableLiveData<Person>();
        }
        return personMutableLiveData;
    }



}
