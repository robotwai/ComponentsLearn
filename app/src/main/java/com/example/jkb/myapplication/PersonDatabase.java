package com.example.jkb.myapplication;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by jkb on 18/3/5.
 */
@Database(entities = {Person.class}, version = 1)
public abstract class PersonDatabase extends RoomDatabase{
    public abstract PersonDao personDao();
}
