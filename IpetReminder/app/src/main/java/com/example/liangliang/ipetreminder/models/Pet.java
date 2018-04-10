package com.example.liangliang.ipetreminder.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * A Pet class
 */

public class Pet implements Serializable{
    private String name;
    private GregorianCalendar birthday;
    private boolean isMale;
    private String owner;

    // need to edit in the future
    // replace owner string with a class
    public Pet(String name, GregorianCalendar birthday, boolean isMale, String owner) {
        this.name = name;
        this.birthday = birthday;
        this.isMale = isMale;
        this.owner = owner;
    }

    public static Pet getInstance() {
        return new Pet("Wang Cai", new GregorianCalendar(2014, 1, 28), true, "David");

    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getDefault());
        return (sdf.format(birthday.getTime()));
    }

    public void setBirthday(GregorianCalendar birthday) {
        this.birthday = birthday;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setName(String name) {
        this.name = name;
    }
}
