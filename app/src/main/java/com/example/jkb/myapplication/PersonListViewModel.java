package com.example.jkb.myapplication;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

/**
 * Created by jkb on 18/3/8.
 */

public class PersonListViewModel extends ViewModel {
    private LiveData<List<Person>> listLiveData;
    private PersonRepository repository;

    public PersonListViewModel(PersonRepository repository) {
        this.repository = repository;
        listLiveData = repository.getPersons();
    }

    public LiveData<List<Person>> getListLiveData(){
        return listLiveData;
    }

    public void savePerson(Person person){
        repository.savePerson(person);
    }
}
