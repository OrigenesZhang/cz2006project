package com.example.liangliang.ipetreminder.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * This class defines a reminder. The reminder will be
 * implemented based on different type.
 */
public abstract class Reminder implements Serializable {
    protected String name;
    protected int freqNum;
    protected Frequency frequency;
    private String note;
    protected GregorianCalendar nextDate;

    /**
     * This method constructs a Reminder with name, frequency number and frequency
     *
     * @param name the name of the reminder
     * @param freqNum the frequency number like once, twice
     * @param frequency the frequency of the reminder like every day, every week or every month
     *
     * @see Frequency
     */
    public Reminder(String name, int freqNum, Frequency frequency) {
        this.name = name;
        this.freqNum = freqNum;
        this.frequency = frequency;
    }

    /**
     * This method updates the next date when the reminder will reminder the user.
     * This method should be implemented by all the subclasses.
     */
    protected abstract void updateNextDate();

    /**
     * This method gets the next date when the reminder will reminder the user.
     * @return the date when user will be reminded
     */
    public GregorianCalendar getNextDate() {
        updateNextDate();
        return nextDate;
    }

    /**
     * This methods returns String representation of next reminding date.
     * @return a formatted string representation of next reminding date
     */
    public String getNextDateString() {
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        return (sdf.format(getNextDate().getTime()));
    }

    /**
     * This method gets the name of this reminder
     * @return the name of this reminder
     */
    public String getName() {
        return name;
    }

    /**
     * This method sets the name of this reminder
     * @param itemName the name of this reminder
     */
    public void setName(String itemName) {
        this.name = itemName;
    }

    /**
     * This method returns the frequent number of this reminder
     * @return the frequency number
     */

    public int getFreqNum() {
        return freqNum;
    }

    /**
     * This method sets the frequency number of the reminder
     * @param amount the frequency number
     */
    public void setFreqNum(int amount) {
        this.freqNum = amount;
    }


    /**
     * This method returns the frequency of the reminder
     * @return the frequency of the reminder
     */
    public String getFrequency() { return frequency.toString(); }

    /**
     * This method sets the frequency of the reminder
     * @param frequency the frequency of the reminder
     */
    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    /**
     * This methods return the index of the frequency
     * @return the index of the frequency
     *
     * @see Frequency
     */
    public int getFrequencyIndex() {
        return frequency.ordinal();
    }

    public String getNote() { return this.note;}

    public void setNote(String note) { this.note = note;}
}
