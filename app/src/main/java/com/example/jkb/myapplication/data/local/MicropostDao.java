package com.example.jkb.myapplication.data.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;


import com.example.jkb.myapplication.Micropost;

import java.util.List;

/**
 * Created by jkb on 18/5/10.
 */
@Dao
public interface MicropostDao {
    @Query("SELECT * FROM micropost ORDER BY id DESC")
    LiveData<List<Micropost>> getAll();

    @Query("SELECT * FROM micropost WHERE id IN (:id)")
    LiveData<Micropost> getMicropost(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Micropost... microposts);

    @Delete
    void delete(Micropost micropost);

    @Query("DELETE FROM micropost")
    void deleteAll();
}
