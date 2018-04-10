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

@RequiresApi(api = Build.VERSION_CODES.O)
public class ExerciseReminder extends Reminder {
    private LocalTime time;

    public ExerciseReminder(String name, int freqNum, Frequency frequency, LocalTime time,
                            GregorianCalendar nextDate) {
        super(name, freqNum, frequency);
        this.time = time;
        this.nextDate = nextDate;
        updateNextDate();
    }

    public String getTime() { return this.time.toString();}

    public void setTime(LocalTime time) {
        this.time = time;
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

        nextDate.set(Calendar.HOUR_OF_DAY, time.getHour());
        nextDate.set(Calendar.MINUTE, time.getHour());
    }
}
