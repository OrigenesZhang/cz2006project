package com.example.liangliang.ipetreminder.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * This class defines a hygiene reminder for Ipet application.
 * This class is a subclass of Reminder
 *
 * @see Reminder
 * @see Frequency
 */
public class HygieneReminder extends Reminder {
    private GregorianCalendar nextDate;
    /**
     * This methods constructs a hygiene reminder instance.
     * @param name the name of the hygiene reminder
     * @param freqNum the frequency number
     * @param frequency the frequency
     * @param nextDate the next date when the reminder will work
     *
     * @see Reminder
     */
    public HygieneReminder(String name, int freqNum, Frequency frequency,
                           GregorianCalendar nextDate) {
        super(name, freqNum, frequency);
        this.nextDate = nextDate;
        updateNextDate();
    }

    /**
     * Sets the start date of the reminder
     * @param startDate the start date
     */
    public void setNextDate(GregorianCalendar startDate) {
        this.nextDate = startDate;
    }

    /**
     * This method validates whether the reminder's date field is valid.
     * The next date should not before today.
     * @return whether the date is valid.
     */
    public boolean isValidDate() {
        this.updateNextDate();
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
     * This method overrides superclass's method.
     * @see Reminder
     */
    @Override
    protected void updateNextDate() {
        Calendar today = Calendar.getInstance();  // get today's date
        if (nextDate.after(today))    // no need to update
            return;

        int increment;
        switch (frequency) {
            // the frequency is DAY
            case DAY:
                int year = today.get(Calendar.YEAR);
                int month = today.get(Calendar.MONTH);
                int day = today.get(Calendar.DAY_OF_MONTH);
                // sets the nextDate to today.
                nextDate = new GregorianCalendar(year, month, day);
                break;
            // the frequency is WEEK
            case WEEK:
                increment = (int)(7 / (freqNum));
                do {
                    nextDate.add(GregorianCalendar.DAY_OF_MONTH, increment);
                } while (nextDate.before(today));
                break;
            // the frequency is MONTH
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

    public GregorianCalendar getNextDate() {
        updateNextDate();
        return nextDate;
    }
}
