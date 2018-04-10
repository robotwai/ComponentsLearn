package com.example.jkb.myapplication;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.persistence.room.Room;
import android.util.Log;
import android.widget.Toast;

import javax.inject.Inject;

/**
 * Created by jkb on 18/3/2.
 */

public class MyViewModel extends ViewModel {


    private LiveData<Resource<Person>> personMutableLiveData ;

    private PersonRepository repository;

    public MyViewModel(PersonRepository repository) {
        this.repository = repository;
    }

    public void init(int uid){
        personMutableLiveData =repository.getPerson(uid);
    }

    public LiveData<Resource<Person>> getPersonMutableLiveData() {

        return personMutableLiveData;
    }

    public void savePerson(Person person){
        repository.savePerson(person);
    }
    public void removePerson(int id){
        repository.removePerson(id);
    }

}
