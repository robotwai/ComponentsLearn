package com.example.jkb.myapplication.data.local;

import android.arch.lifecycle.LiveData;

import com.example.jkb.myapplication.Person;
import com.example.jkb.myapplication.data.PersonDataResource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * Created by jkb on 18/3/7.
 */
@Singleton
public class PersonLocalDataResource implements PersonDataResource {

    private PersonDao personDao;

    @Inject
    public PersonLocalDataResource(PersonDao personDao) {
        this.personDao = personDao;
    }

    // Prevent direct instantiation.



    @Override
    public LiveData<List<Person>> getPersons() {

        return personDao.getAll();

    }

    @Override
    public LiveData<Person> getPerson(final int uid) {

        return personDao.getPerson(uid);

    }

    @Override
    public void savePerson(final Person person) {
        personDao.insertAll(person);
    }
}
