package com.example.jkb.myapplication;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by jkb on 18/3/2.
 */

public class MyViewModel extends ViewModel {
    private MutableLiveData<String> mCurrentName;

    private MutableLiveData<Person> personMutableLiveData;

    public MutableLiveData<Person> getPersonMutableLiveData() {
        if (personMutableLiveData ==null){
            personMutableLiveData = new MutableLiveData<Person>();
        }
        return personMutableLiveData;
    }



    public MutableLiveData<String> getCurrentName() {
        if (mCurrentName == null) {
            mCurrentName = new MutableLiveData<String>();
        }
        return mCurrentName;
    }

}
