package com.example.jkb.myapplication;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by jkb on 18/3/5.
 */
@Dao
public interface PersonDao {
    @Query("SELECT * FROM user")
    LiveData<List<Person>> getAll();

    @Insert
    void insertAll(Person... users);

    @Delete
    void delete(Person user);
}
