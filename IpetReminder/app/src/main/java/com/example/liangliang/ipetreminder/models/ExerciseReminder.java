package com.example.liangliang.ipetreminder.models;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
/**
 * Created by caoliu on 19/3/18.
 */

public class ExerciseReminder extends Reminder {
    private LocalTime time;
    private GregorianCalendar nextDate;

    public ExerciseReminder(String name, int freqNum, Frequency frequency, LocalTime time, GregorianCalendar nextDate) {
        super(name, freqNum, frequency);
        this.time = time;
        this.nextDate = nextDate;
    }

    public String getTime() { return this.time.toString();}

    public static ExerciseReminder getInstance() {
        return new ExerciseReminder(null, 0, null, null, null);
    }

    public void setTime(LocalTime time) {
        this.time = time;
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public GregorianCalendar getNextDateInstance() {
        GregorianCalendar cal = (GregorianCalendar) nextDate.clone();
        cal.set(GregorianCalendar.HOUR_OF_DAY, time.getHour());
        cal.set(GregorianCalendar.MINUTE, time.getMinute());
        return cal;
    }

    public boolean isValidDate() {
        return !nextDate.before(Calendar.getInstance());
    }
}
