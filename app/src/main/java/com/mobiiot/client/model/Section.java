package com.mobiiot.client.model;

import android.graphics.drawable.Drawable;

public class Section {

    private String sectionName;
    private Drawable sectionDrawable;
    private int color;

    public Section() {
    }

    public Section(String sectionName, Drawable sectionDrawable,int color) {
        this.sectionName = sectionName;
        this.sectionDrawable = sectionDrawable;
        this.color=color;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Drawable getSectionDrawable() {
        return sectionDrawable;
    }

    public void setSectionDrawable(Drawable sectionDrawable) {
        this.sectionDrawable = sectionDrawable;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Section{" +
                "sectionName='" + sectionName + '\'' +
                ", sectionDrawable=" + sectionDrawable.toString() +
                '}';
    }
}
