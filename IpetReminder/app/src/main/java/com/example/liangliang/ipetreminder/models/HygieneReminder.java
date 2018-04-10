package com.example.liangliang.ipetreminder.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by caoliu on 7/4/18.
 */

public class HygieneReminder extends Reminder {

    public HygieneReminder(String name, int freqNum, Frequency frequency,
                           GregorianCalendar nextDate, updateWithoutEndDateWithoutTime update) {
        super(name, freqNum, frequency, update);
    }

<<<<<<< HEAD
    @Override
    public GregorianCalendar getNextDate() {
        this.nextDate = updateNextDate.update(nextDate, null,
                null,  freqNum, frequency);
        return nextDate;
=======
    public static HygieneReminder getInctance() {
        return new HygieneReminder(null, 0, null, null);
    }

    public String getNextDate() {
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        return (sdf.format(nextDate.getTime()));
>>>>>>> parent of 36fd795... edit
    }

    public void setNextDate(GregorianCalendar startDate) {
        this.nextDate = startDate;
    }

    public boolean isValidDate() {
        return !nextDate.before(Calendar.getInstance());
    }
}
