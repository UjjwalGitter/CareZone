package com.ujjwalsingh.carezone.DataBase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "note_table")
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int image;
    private String name;
    private String time;
    private String minbefore;
    private int foodpattern;
    private int arrow;
    private boolean expandable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Note(){
    }



    public Note(String name, String time, String minbefore, int foodpattern, int image) {
        this.image = image;
        this.name = name;
        this.time = time;
        this.minbefore = minbefore;
        this.foodpattern = foodpattern;
    }


    public Note(int id,int image, String name, String time, String minbefore, int foodpattern, int arrow, boolean expandable) {
        this.id=id;
        this.image = image;
        this.name = name;
        this.time = time;
        this.minbefore = minbefore;
        this.foodpattern = foodpattern;
        this.arrow = arrow;
        this.expandable = expandable;
    }
    public Note(int image, String name, String time, String minbefore, int foodpattern, int arrow, boolean expandable) {
        this.image = image;
        this.name = name;
        this.time = time;
        this.minbefore = minbefore;
        this.foodpattern = foodpattern;
        this.arrow = arrow;
        this.expandable = expandable;
    }


    public void setTime(String time) {
        this.time = time;
    }

    public void setMinbefore(String minbefore) {
        this.minbefore = minbefore;
    }

    public void setFoodpattern(int foodpattern) {
        this.foodpattern = foodpattern;
    }

    public int getArrow() {
        return arrow;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    public void setArrow(int arrow) {
        this.arrow = arrow;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }


    public String getTime() {
        return time;
    }

    public String getMinbefore() {
        return minbefore;
    }

    public String getName() {
        return name;
    }

    public int getFoodpattern() {
        return foodpattern;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", image=" + image +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", minbefore='" + minbefore + '\'' +
                ", foodpattern=" + foodpattern +
                ", arrow=" + arrow +
                ", expandable=" + expandable +
                '}';
    }
}
