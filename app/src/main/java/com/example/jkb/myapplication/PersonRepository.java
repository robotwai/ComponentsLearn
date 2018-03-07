package com.example.jkb.myapplication;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

/**
 * Created by lz on 18-3-5.
 */

public class PersonRepository{
    private PersonDao personDao;


    private final Executor executor;

    private static PersonRepository INSTANCE = null;

    public PersonRepository(PersonDao personDao, Executor executor) {
        this.personDao = personDao;
        this.executor = executor;
    }

    public static PersonRepository getInstance(PersonDao personDao,Executor executor
                                              ) {
        if (INSTANCE == null) {
            INSTANCE = new PersonRepository(personDao,executor);
        }
        return INSTANCE;
    }

    public void savePerson(final Person person) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                personDao.insertAll(person);
            }
        });
    }

    public LiveData<List<Person>> getPersons() {
        return personDao.getAll();
    }


    public LiveData<Person> getPerson(int uid) {
        return personDao.getPerson(uid);
    }
}
