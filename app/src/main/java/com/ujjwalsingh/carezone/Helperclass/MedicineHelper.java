package com.ujjwalsingh.carezone.Helperclass;

public class MedicineHelper {
    int image;
    String time,name, minbefore;
    int arrow;
    boolean expandable;

    public MedicineHelper(int image, String time, String name, String minbefore) {
        this.image = image;
        this.time = time;
        this.name = name;
        this.minbefore = minbefore;
        this.expandable = false;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMinbefore() {
        return minbefore;
    }

    public void setMinbefore(String minbefore) {
        this.minbefore = minbefore;
    }
}
