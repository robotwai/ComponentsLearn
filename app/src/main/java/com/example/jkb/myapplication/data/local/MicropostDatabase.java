package com.example.jkb.myapplication.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.jkb.myapplication.Micropost;

/**
 * Created by jkb on 18/5/10.
 */
@Database(entities = {Micropost.class}, version = 1, exportSchema = false)
public abstract class MicropostDatabase extends RoomDatabase {
    public abstract MicropostDao micropostDatabase();

    private static MicropostDatabase INSTANCE;

    private static final Object sLock = new Object();
    public static MicropostDatabase getInstance(Context context){
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        MicropostDatabase.class, "micropost.db")
                        .build();
            }
            return INSTANCE;
        }
    }
}
