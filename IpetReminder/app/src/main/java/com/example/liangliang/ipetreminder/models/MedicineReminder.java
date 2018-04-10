package com.example.liangliang.ipetreminder.models;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

/**
 * This class defines a medicine reminder for Ipet application.
 * This class is a subclass of Reminder
 * @see Reminder
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class MedicineReminder extends Reminder {
    private LocalTime time;
    private GregorianCalendar startDate;
    private GregorianCalendar endDate;
    private GregorianCalendar nextDate;

    /**
     * This method constructs an object of medicine reminder
     *
     * <p> the nextDate will be calculated based on the the startDate and endDate
     * along with the frequency number and frequency.
     *</p>
     * @param name the name of the medicine reminder
     * @param time the time which is going to be reminded to take medicine
     * @param startDate when the reminder will start working
     * @param endDate when the reminder will end working
     * @param freqNum superclass's field
     * @param freq superclass's field
     *
     * @see Reminder
     */
    public MedicineReminder(String name, LocalTime time,
                            GregorianCalendar startDate, GregorianCalendar endDate,
                            int freqNum, Frequency freq) {
        // super class's constructor
        super(name, freqNum, freq);
        this.time = time;
        this.startDate = startDate;
        this.endDate = endDate;
        updateNextDate();
    }

    /**
     * Gets the start date
     * @return the date when the reminder starts working
     */
    public String getStartDate() {
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        return (sdf.format(startDate.getTime()));
    }

    /**
     * Sets the start date
     * @param startDate the date when the reminder starts working
     */
    public void setStartDate(GregorianCalendar startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the end date
     * @return the date when the reminder ends working
     */
    public String getEndDate() {
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        return (sdf.format(endDate.getTime()));
    }

    /**
     * Sets the end date
     * @param endDate the date when the reminder ends working.
     */
    public void setEndDate(GregorianCalendar endDate) {
        this.endDate = endDate;
    }

    /**
     * Sets the time of reminder
     * @param time should contains the field minutes and seconds
     */
    public void setTime(LocalTime time) { this.time = time; }

    /**
     * Gets the time of reminder
     * @return the time of reminder will remind
     */
    public String getTime() {
        return this.time.toString();
    }

    /**
     * This methods whether the current medicine reminder contains valid date
     * the start date should be before the end date.
     * the end date should be after today
     * @return
     */
    public boolean isValidDate() {
        return startDate.before(endDate) &&             // start date should before end date
                endDate.after(Calendar.getInstance()); // end date should after today

    }

    /**
     * This method overrides the superclass's method.
     * This method update the next date when the reminder will work
     * based on the start and end date set by the user along with the
     * frequency.
     *
     * @see Reminder
     */
    @Override
    protected void updateNextDate() {
        Calendar today = Calendar.getInstance();  // get today's date
        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH);
        int day = today.get(Calendar.DAY_OF_MONTH);

        // next date starts at startDate
        nextDate = (GregorianCalendar)startDate.clone();
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

        // sets the minute and hour
        nextDate.set(Calendar.HOUR_OF_DAY, time.getHour());
        nextDate.set(Calendar.MINUTE, time.getHour());
    }
}
