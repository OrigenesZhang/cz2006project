package com.example.liangliang.ipetreminder.models;

import java.io.Serializable;

public class Reminder implements Serializable {
    protected String name;
    protected int freqNum;
    protected Frequency frequency;
    protected String note;

    public Reminder(String name, int freqNum, Frequency frequency) {
        this.name = name;
        this.freqNum = freqNum;
        this.frequency = frequency;
        this.note = null;
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
