package com.example.liangliang.ipetreminder.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by caoliu on 7/4/18.
 */

public class HygieneReminder extends Reminder {
    private GregorianCalendar nextDate;
    private UpdateNextDateStrategy updateNextDate;

    public HygieneReminder(String name, int freqNum, Frequency frequency,
                           GregorianCalendar nextDate, updateWithoutEndDateWithoutTime updateNextDate) {
        super(name, freqNum, frequency);
        this.nextDate = nextDate;
        this.updateNextDate = updateNextDate;
        this.nextDate = updateNextDate.update(nextDate, null,
                null,  freqNum, frequency);
    }


    public String getNextDate() {
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        this.nextDate = updateNextDate.update(nextDate, null,
                null,  freqNum, frequency);
        return (sdf.format(nextDate.getTime()));
    }

    public void setNextDate(GregorianCalendar startDate) {
        this.nextDate = startDate;
    }

    public boolean isValidDate() {
        return !nextDate.before(Calendar.getInstance());
    }
}
