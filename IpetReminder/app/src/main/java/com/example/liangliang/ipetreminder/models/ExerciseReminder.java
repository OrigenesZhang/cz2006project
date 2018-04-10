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

    public ExerciseReminder(String name, int freqNum, Frequency frequency, LocalTime time,
                            GregorianCalendar nextDate, UpdateNextDateStrategy updateNextDate) {
        super(name, freqNum, frequency, updateNextDate);
        this.time = time;
        this.nextDate = updateNextDate.update(nextDate, null, time, freqNum, frequency);
    }

    public String getTime() { return this.time.toString();}

    public static ExerciseReminder getInstance() {
        return new ExerciseReminder(null, 0, null, null, null);
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public GregorianCalendar getNextDate() {
        nextDate = updateNextDate.update(nextDate, null, time, freqNum, frequency);
        return nextDate;
    }

    public void setNextDate(GregorianCalendar startDate) {
        this.nextDate = startDate;
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
