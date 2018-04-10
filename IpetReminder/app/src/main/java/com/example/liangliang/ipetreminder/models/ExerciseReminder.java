package com.example.liangliang.ipetreminder.models;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
/**
 * This class defines a medicine reminder for Ipet application.
 * This class is a subclass of Reminder.
 * @see Reminder
 * @see Frequency
 */

@RequiresApi(api = Build.VERSION_CODES.O)
public class ExerciseReminder extends Reminder {
    private LocalTime time;

    /**
     * This method constructs an exercise reminder
     * @param name the name of the reminder
     * @param freqNum the frequency number of the reminder
     * @param frequency the frequency of the reminder
     * @param time the time of the reminder
     * @param nextDate the next date when the reminder will work
     *
     * @see Reminder
     */
    public ExerciseReminder(String name, int freqNum, Frequency frequency, LocalTime time,
                            GregorianCalendar nextDate) {
        // superclass's constructor
        super(name, freqNum, frequency);
        this.time = time;
        super.nextDate = nextDate;
        updateNextDate();
    }

    /**
     * Gets the time of the reminder
     * @return the time of the reminder
     */
    public String getTime() { return this.time.toString();}

    /**
     * Sets the time of the reminder
     * @param time the time of the reminder
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * Sets the next date when the reminder will work.
     * @param startDate the start date of the reminder
     */
    public void setNextDate(GregorianCalendar startDate) {
        this.nextDate = startDate;
    }

    /**
     * This method checks whether the date field in the class is valid.
     * The next date of the reminder should not be before today.
     * @return whether the class is valid
     */
    public boolean isValidDate() {
        updateNextDate();
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        return !nextDate.before(today);
    }

    /**
     * This method updates the next date that the reminder will work
     * based on the current nextDate along with the frequency.
     */
    @Override
    protected void updateNextDate() {
        Calendar today = Calendar.getInstance();  // get today's date
        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH);
        int day = today.get(Calendar.DAY_OF_MONTH);

        if (nextDate.after(today))    // no need to update
            return;

        int increment;
        switch (frequency) {
            // the frequency is set to DAY
            case DAY:
                nextDate = new GregorianCalendar(year, month, day);
                break;

            // the frequency is set to WEEK
            case WEEK:
                increment = (int)(7 / (freqNum));
                do {
                    nextDate.add(GregorianCalendar.DAY_OF_MONTH, increment);
                } while (nextDate.before(today));
                break;

            // the frequency is set to MONTH
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

        // set the minute and hour for the next date
        nextDate.set(Calendar.HOUR_OF_DAY, time.getHour());
        nextDate.set(Calendar.MINUTE, time.getMinute());
    }

    /**
     * This method gets the next date when the reminder will reminder the user.
     * @return the date when user will be reminded
     */
    public GregorianCalendar getNextDate() {
        updateNextDate();
        return nextDate;
    }
}
