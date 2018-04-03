package com.example.jkb.myapplication.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.jkb.myapplication.Person;

/**
 * Created by jkb on 18/3/5.
 */
@Database(entities = {Person.class}, version = 2, exportSchema = false)
public abstract class PersonDatabase extends RoomDatabase{

    public abstract PersonDao personDao();

    private static PersonDatabase INSTANCE;

    private static final Object sLock = new Object();
    public static PersonDatabase getInstance(Context context){
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        PersonDatabase.class, "person.db")
                        .build();
            }
            return INSTANCE;
        }
    }
}
