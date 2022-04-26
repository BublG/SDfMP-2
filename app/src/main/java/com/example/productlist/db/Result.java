package com.example.productlist.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Result {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo
    private String date;
    @ColumnInfo
    private String search;
    @ColumnInfo
    private int quantity;

    public Result() {
    }

    public Result(String date, String search, int quantity) {
        this.date = date;
        this.search = search;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}