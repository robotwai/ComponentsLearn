package com.example.jkb.myapplication.data;

import android.arch.lifecycle.LiveData;

import com.example.jkb.myapplication.Person;

import java.util.List;

/**
 * Created by jkb on 18/3/6.
 */

public interface PersonDataResource {

    LiveData<List<Person>> getPersons();

    LiveData<Person> getPerson(int uid);

    void savePerson(Person person);


}
