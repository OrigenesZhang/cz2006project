package com.example.liangliang.ipetreminder.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by caoliu on 7/4/18.
 */

public class HygieneReminder extends Reminder {
    private GregorianCalendar nextDate;

    public HygieneReminder(String name, int freqNum, Frequency frequency, GregorianCalendar nextDate) {
        super(name, freqNum, frequency);
        this.nextDate = nextDate;
    }

    public static HygieneReminder getInctance() {
        return new HygieneReminder(null, 0, null, null);
    }

    public String getNextDate() {
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        return (sdf.format(nextDate.getTime()));
    }

    public void setNextDate(GregorianCalendar startDate) {
        this.nextDate = startDate;
    }

    private void updateNextDate() {
        Calendar today = Calendar.getInstance();  // get today's date
        if (nextDate.after(today))    // no need to update
            return;

        int increment;
        switch (frequency) {
            case DAY:
                int year = today.get(Calendar.YEAR);
                int month = today.get(Calendar.MONTH);
                int day = today.get(Calendar.DAY_OF_MONTH);
                nextDate = new GregorianCalendar(year, month, day);
                break;
            case WEEK:
                increment = (int)(7 / (freqNum));
                nextDate.add(GregorianCalendar.DAY_OF_MONTH, increment);
                break;
            case MONTH:
                increment = (int)(30 / (freqNum));
                nextDate.add(GregorianCalendar.DAY_OF_MONTH, increment);
                break;
            default:
                break;
        }
    }

    public boolean isValidDate() {
        return !nextDate.before(Calendar.getInstance());
    }
}
