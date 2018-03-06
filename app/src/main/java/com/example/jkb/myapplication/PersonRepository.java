package com.example.jkb.myapplication;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import javax.inject.Inject;

/**
 * Created by lz on 18-3-5.
 */

public class PersonRepository {
    private final PersonDao personDao;
    @Inject
    public PersonRepository(PersonDao personDao) {
        this.personDao = personDao;
    }

    public LiveData<Person> getPerson(int uid){
        return personDao.getPerson(uid);
    }

    public void savePerson(Person person){
        personDao.insertAll(person);
    }
}
