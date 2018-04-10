package com.example.liangliang.ipetreminder.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * A hygiene reminder
 */
public class HygieneReminder extends Reminder {

    public HygieneReminder(String name, int freqNum, Frequency frequency,
                           GregorianCalendar nextDate) {
        super(name, freqNum, frequency);
        this.nextDate = nextDate;
        updateNextDate();
    }

    public void setNextDate(GregorianCalendar startDate) {
        this.nextDate = startDate;
    }

    public boolean isValidDate() {
        return !nextDate.before(Calendar.getInstance());
    }

    @Override
    protected void updateNextDate() {
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
                do {
                    nextDate.add(GregorianCalendar.DAY_OF_MONTH, increment);
                } while (nextDate.before(today));
                break;
            case MONTH:
                increment = (int)(30 / (freqNum));
                do {
                    nextDate.add(GregorianCalendar.DAY_OF_MONTH, increment);
                } while (nextDate.before(today));
                break;
            default:
                break;
        }
    }

    @Override
    public GregorianCalendar getNextDate() {
        updateNextDate();
        return nextDate;
    }
}
