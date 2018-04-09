package com.example.liangliang.ipetreminder.models;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by caoliu on 9/4/18.
 */

@RequiresApi(api = Build.VERSION_CODES.O)
public class ReminderFactory {
    public static Reminder getReminder(String reminderType) {
        if (reminderType == null) return null;

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        GregorianCalendar today = new GregorianCalendar(year, month, day);
        LocalTime time = LocalTime.of(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE));
        int freqNum = 1;
        Frequency frequency = Frequency.DAY;

        switch (reminderType) {
            case "medicine":
                return new MedicineReminder("Medicine Name", time,
                        today, new GregorianCalendar(year, month, day+1),
                        freqNum, frequency, new updateWithEndDate());
            case "hygiene":
                return new HygieneReminder("Hygiene Name", freqNum, frequency, today,
                        new updateWithoutEndDateWithoutTime());
            case "exercise":
                return new ExerciseReminder("Exercise Name", freqNum, frequency, time, today,
                        new updateWithoutEndDateWithTime());
        }

        return null;
    }
}
