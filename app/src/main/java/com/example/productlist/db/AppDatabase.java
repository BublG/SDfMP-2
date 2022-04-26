package com.example.productlist.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Fish.class, Result.class}, version = 5)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FishDao fishDao();
    public abstract ResultDao resultDao();
}
