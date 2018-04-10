package com.example.jkb.myapplication.data.local;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.jkb.myapplication.Person;

import java.util.List;

/**
 * Created by jkb on 18/3/5.
 */
@Dao
public interface PersonDao {
    @Query("SELECT * FROM person")
    LiveData<List<Person>> getAll();

    @Query("SELECT * FROM person WHERE id IN (:userid)")
    LiveData<Person> getPerson(int userid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Person... users);

    @Delete
    void delete(Person user);

    @Query("DELETE FROM person")
    void deleteAll();
}
