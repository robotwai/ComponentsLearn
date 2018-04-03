package com.example.jkb.myapplication;

import android.arch.lifecycle.LiveData;

import com.example.jkb.myapplication.data.PersonDataResource;
import com.example.jkb.myapplication.data.local.PersonDao;
import com.example.jkb.myapplication.data.local.PersonLocalDataResource;
import com.example.jkb.myapplication.data.network.PersonNetDataResource;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by lz on 18-3-5.
 */
@Singleton
public class PersonRepository implements PersonDataResource{
    private PersonLocalDataResource personDataResourceLocal;
    private PersonNetDataResource personDataResourceNet;

    @Inject
    public PersonRepository(PersonLocalDataResource personDataResourceLocal, PersonNetDataResource personDataResourceNet) {
        this.personDataResourceLocal = personDataResourceLocal;
        this.personDataResourceNet = personDataResourceNet;
    }

    @Override
    public void savePerson(final Person person) {
        personDataResourceNet.savePerson(person);
    }

    @Override
    public LiveData<List<Person>> getPersons() {
        return personDataResourceNet.getPersons();
    }

    @Override
    public LiveData<Person> getPerson(int uid) {
        return personDataResourceNet.getPerson(uid);
    }
}
