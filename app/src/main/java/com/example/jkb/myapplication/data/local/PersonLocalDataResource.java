package com.example.jkb.myapplication.data.local;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.jkb.myapplication.Person;
import com.example.jkb.myapplication.data.PersonDataResource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * Created by jkb on 18/3/7.
 */

public class PersonLocalDataResource {

    private PersonDao personDao;


    public PersonLocalDataResource(PersonDao personDao) {
        this.personDao = personDao;
    }

    // Prevent direct instantiation.


//    @Override
//    public void getPersons(MutableLiveData<List<Person>> listLiveData) {
//        listLiveData.setValue(personDao.getAll());
//    }
//
//    @Override
//    public void getPerson(int uid, MutableLiveData<Person> liveData) {
//        liveData.setValue(personDao.getPerson(uid));
//    }
//
//    @Override
//    public void savePerson(final Person person,MutableLiveData<List<Person>> listLiveData) {
//        personDao.insertAll(person);
//    }
//
//    @Override
//    public void removePerson(int id,MutableLiveData<List<Person>> listLiveData) {
//
//    }
}
