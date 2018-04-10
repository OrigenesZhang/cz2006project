package com.example.liangliang.ipetreminder.models;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * ReminderFactory class implements factory method pattern. ReminderFactory
 * defines a object creation method for creating Reminder instance
 */

@RequiresApi(api = Build.VERSION_CODES.O)
public class ReminderFactory {
    /**
     * A object creation function which uses to create subclasses of Reminder
     * all the date field will be set to today's date and all the time field will
     * be set to current time
     *
     * @param reminderType a string which indicates the type of Reminder to create
     *                     the value can be "medicine", "hygiene" or "exercise".
     *                     by default it returns null object.
     * @return a Reminder Object.
     */
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
                        freqNum, frequency);
            case "hygiene":
                return new HygieneReminder("Hygiene Name", freqNum, frequency, today);
            case "exercise":
                return new ExerciseReminder("Exercise Name", freqNum, frequency, time, today);
        }
        return null;
    }
}
