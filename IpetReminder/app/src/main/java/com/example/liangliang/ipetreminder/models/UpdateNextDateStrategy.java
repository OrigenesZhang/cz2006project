package com.example.liangliang.ipetreminder.models;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by caoliu on 9/4/18.
 */

public interface UpdateNextDateStrategy {
    public GregorianCalendar update(GregorianCalendar startDate, GregorianCalendar endDate,
                                            LocalTime time, int freqNum, Frequency frequency);
}

class updateWithEndDate implements UpdateNextDateStrategy {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public GregorianCalendar update(GregorianCalendar startDate, GregorianCalendar endDate,
                                            LocalTime time, int freqNum, Frequency frequency) {
        Calendar today = Calendar.getInstance();  // get today's date
        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH);
        int day = today.get(Calendar.DAY_OF_MONTH);
        GregorianCalendar nextDate = (GregorianCalendar)startDate.clone();
        int increment;
        switch (frequency) {
            case DAY:
                nextDate = new GregorianCalendar(year, month, day);

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
                nextDate = new GregorianCalendar(year, month, day);
                break;
        }

        nextDate.set(Calendar.HOUR_OF_DAY, time.getHour());
        nextDate.set(Calendar.MINUTE, time.getHour());
        return nextDate;
    }
}

class updateWithoutEndDateWithoutTime implements UpdateNextDateStrategy {

    @Override
    public GregorianCalendar update(GregorianCalendar nextDate, GregorianCalendar endDate,
                                            LocalTime time, int freqNum, Frequency frequency) {

        Calendar today = Calendar.getInstance();  // get today's date
        if (nextDate.after(today))    // no need to update
            return nextDate;

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

        return nextDate;
    }
}

class updateWithoutEndDateWithTime implements UpdateNextDateStrategy {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public GregorianCalendar update(GregorianCalendar nextDate, GregorianCalendar endDate, LocalTime time, int freqNum, Frequency frequency) {
        Calendar today = Calendar.getInstance();  // get today's date
        if (nextDate.after(today))    // no need to update
            return nextDate;

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

        nextDate.set(Calendar.HOUR_OF_DAY, time.getHour());
        nextDate.set(Calendar.MINUTE, time.getHour());
        return nextDate;

    }
}