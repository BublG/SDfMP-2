package com.example.productlist.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FishDao {
    @Insert
    void insert(Fish fish);

    @Insert
    void insertAll(Fish... fish);

    @Query("SELECT * FROM fish WHERE `harvest_type` = :harvestType")
    List<Fish> findAllByHarvestType(String harvestType);

    @Query("DELETE FROM fish")
    void deleteAll();

    @Query("SELECT COUNT(*) FROM fish WHERE `harvest_type` = :harvestType")
    int getHarvestTypeQuantity(String harvestType);

    @Query("SELECT * FROM fish WHERE `speciesName` LIKE '%' || :speciesName || '%'")
    List<Fish> searchBySpeciesName(String speciesName);
}