package com.example.jkb.myapplication;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

/**
 * Created by jkb on 18/3/8.
 */

public class PersonListViewModel extends ViewModel {
    private LiveData<Resource<List<Person>>> listLiveData;
    private PersonRepository repository;

    public PersonListViewModel(PersonRepository repository) {
        this.repository = repository;
        listLiveData = repository.getPersons();
    }

    public LiveData<Resource<List<Person>>> getListLiveData(){
        return listLiveData;
    }

    public void savePerson(Person person){
        repository.savePerson(person);
    }

    public void remove(int id){
        repository.removePerson(id);
    }
    public void clear(){
        repository.clear();
    }
}
