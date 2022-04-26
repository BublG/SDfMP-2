package com.example.productlist.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ResultDao {
    @Query("SELECT * FROM result")
    List<Result> getAll();

    @Insert
    void insert(Result result);
}
