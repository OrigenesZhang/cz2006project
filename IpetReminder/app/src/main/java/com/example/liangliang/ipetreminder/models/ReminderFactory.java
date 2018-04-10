package com.example.liangliang.ipetreminder.models;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * ReminderFactory class implements factory method pattern. ReminderFactory
 * defines a object creation method for creating Reminder objects
 */

@RequiresApi(api = Build.VERSION_CODES.O)
public class ReminderFactory {
    /**
     * A object creation function which uses to create subclasses of Reminder
     * all the date field will be set to today and all the time field will
     * be set to current time
     *
     * @param reminderType a string which indicates the type of Reminder to create.
     *                     The value can be "medicine", "hygiene" or "exercise".
     *                     by default it returns null object.
     * @return a Reminder Object.
     */
    public static Reminder getReminder(String reminderType) throws NullPointerException {
        if (reminderType == null) throw new NullPointerException("No such type!");

        // Gets today
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);

        GregorianCalendar today = new GregorianCalendar(year, month, day);
        LocalTime time = LocalTime.of(hour, minute);

        int freqNum = 1; // default freqNum is 1
        Frequency frequency = Frequency.DAY;    // default frequency is DAY

        switch (reminderType) {
            // creating a medicine reminder
            case "medicine":
                return new MedicineReminder("Medicine Name", time,
                        today, new GregorianCalendar(year, month, day+1),
                        freqNum, frequency);
            // creating a hygiene reminder
            case "hygiene":
                return new HygieneReminder("Hygiene Name", freqNum, frequency, today);
            // creating an exercise reminder
            case "exercise":
                return new ExerciseReminder("Exercise Name", freqNum, frequency, time, today);
            default:
                throw new NullPointerException("No such type");
        }
    }
}
