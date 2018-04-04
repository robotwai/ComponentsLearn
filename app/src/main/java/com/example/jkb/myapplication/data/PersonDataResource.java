package com.example.jkb.myapplication.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.jkb.myapplication.Person;

import java.util.List;

/**
 * Created by jkb on 18/3/6.
 */

public interface PersonDataResource {

    void getPersons(MutableLiveData<List<Person>> listLiveData);

    void getPerson(int uid,MutableLiveData<Person> liveData);

    void savePerson(Person person);

    void removePerson(int id);
}
