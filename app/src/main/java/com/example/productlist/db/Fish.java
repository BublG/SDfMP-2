package com.example.productlist.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Fish {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo
    private String imageSrc;
    @ColumnInfo
    private String scientificName;
    @ColumnInfo
    private String speciesName;
    @ColumnInfo(name = "harvest_type")
    private String harvestType;
    @ColumnInfo
    private String calories;

    public Fish() {
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public String getHarvestType() {
        return harvestType;
    }

    public void setHarvestType(String harvestType) {
        this.harvestType = harvestType;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "Fish{" +
                "imageSrc='" + imageSrc + '\'' +
                ", scientificName='" + scientificName + '\'' +
                ", speciesName='" + speciesName + '\'' +
                ", harvestType='" + harvestType + '\'' +
                '}';
    }
}
