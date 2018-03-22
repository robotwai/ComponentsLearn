package com.example.jkb.myapplication;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by lz on 18-3-5.
 */
@Singleton
public class PersonRepository{
    private PersonDao personDao;


    private final Executor executor;


    @Inject
    public PersonRepository(PersonDao personDao, DiskIOThreadExecutor executor) {
        this.personDao = personDao;
        this.executor = executor;
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
