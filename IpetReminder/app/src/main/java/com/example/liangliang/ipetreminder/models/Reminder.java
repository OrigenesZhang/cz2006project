package com.example.liangliang.ipetreminder.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * A parent class of reminder objects
 */
public abstract class Reminder implements Serializable {
    protected String name;
    protected int freqNum;
    protected Frequency frequency;
    private String note;
    protected UpdateNextDateStrategy updateNextDate;
    protected GregorianCalendar nextDate;

    public Reminder(String name, int freqNum, Frequency frequency, UpdateNextDateStrategy update) {
        this.name = name;
        this.freqNum = freqNum;
        this.frequency = frequency;
<<<<<<< HEAD
        this.note = "Note";
        this.updateNextDate = update;
=======
        this.note = null;
>>>>>>> parent of 36fd795... edit
    }

    public String getName() {
        return name;
    }

    public void setName(String itemName) {
        this.name = itemName;
    }

    public int getFreqNum() {
        return freqNum;
    }

    public void setFreqNum(int amount) {
        this.freqNum = amount;
    }

    public abstract GregorianCalendar getNextDate();

    public String getNextDateString() {
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        return (sdf.format(getNextDate().getTime()));
    }

    public String getFrequency() { return frequency.toString(); }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public int getFrequencyInex() {
        return frequency.ordinal();
    }

    public String getNote() { return this.note;}

    public void setNote(String note) { this.note = note;}
}
