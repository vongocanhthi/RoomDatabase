package com.vnat.roomdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = User.class, exportSchema = false, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    public static String DB_NAME = "User_db";

    private static UserDatabase instance;

    public static UserDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context, UserDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract IUserDao iUserDao();

    public static void destroyInstance() {
        instance = null;
    }

}
