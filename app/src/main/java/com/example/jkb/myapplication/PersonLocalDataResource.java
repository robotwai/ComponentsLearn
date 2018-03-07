package com.example.jkb.myapplication;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * Created by jkb on 18/3/7.
 */

public class PersonLocalDataResource implements PersonDataResource {

    private static volatile PersonLocalDataResource INSTANCE;

    private PersonDao personDao;

    private AppExecutors mAppExecutors;

    // Prevent direct instantiation.
    private PersonLocalDataResource(@NonNull AppExecutors appExecutors,
                                 @NonNull PersonDao personDao) {
        mAppExecutors = appExecutors;
        this.personDao = personDao;
    }

    public static PersonLocalDataResource getInstance(@NonNull AppExecutors appExecutors,
                                                   @NonNull PersonDao personDao) {
        if (INSTANCE == null) {
            synchronized (PersonLocalDataResource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PersonLocalDataResource(appExecutors, personDao);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getPersons(final LoadAllPersonCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final LiveData<List<Person>> people = personDao.getAll();
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (people!=null&&!people.getValue().isEmpty()){
                            callback.onLoadPersons(people);
                        }else {
                            callback.onLoadFailed();
                        }

                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void getPerson(final int uid,final LoadPersonCallback callback) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final LiveData<Person> people = personDao.getPerson(uid);
                Log.i("lz","local");
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (people!=null&&!(people.getValue()!=null)){
                            callback.onLoadPerson(people);
                        }else {
                            callback.onLoadFailed();
                        }

                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);

    }

    @Override
    public void savePerson(final Person person) {
        Runnable saveRunnable = new Runnable() {
            @Override
            public void run() {
                personDao.insertAll(person);
            }
        };
        mAppExecutors.diskIO().execute(saveRunnable);
    }
}
