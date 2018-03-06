package com.example.jkb.myapplication;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.persistence.room.Room;

import javax.inject.Inject;

/**
 * Created by jkb on 18/3/2.
 */

public class MyViewModel extends ViewModel {


    private LiveData<Person> personMutableLiveData;

    private PersonRepository repository;

    @Inject
    public MyViewModel(PersonRepository repository) {
        this.repository = repository;
    }

    public void init(int uid){
        if (this.personMutableLiveData != null) {
            // ViewModel is created per Fragment so
            // we know the userId won't change
            return;
        }
        personMutableLiveData = repository.getPerson(uid);
    }

    public LiveData<Person> getPersonMutableLiveData() {

        return personMutableLiveData;
    }

    public void savePerson(Person person){
        repository.savePerson(person);
    }



}
