package com.example.liangliang.ipetreminder.models;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MedicineReminder extends Reminder {
    private LocalTime time;
    private GregorianCalendar startDate;
    private GregorianCalendar endDate;
    private GregorianCalendar nextDate;

    public MedicineReminder(String name, LocalTime time, GregorianCalendar startDate, GregorianCalendar endDate, int freqNum, Frequency freq) {
        super(name, freqNum, freq);
        this.time = time;
        this.startDate = startDate;
        this.endDate = endDate;
        updateNextDate();
    }

    private void updateNextDate() {
        Calendar today = Calendar.getInstance();  // get today's date
        nextDate = (GregorianCalendar)startDate.clone();
        int increment;
        switch (frequency) {
            case DAY:
                int year = today.get(Calendar.YEAR);
                int month = today.get(Calendar.MONTH);
                int day = today.get(Calendar.DAY_OF_MONTH);
                nextDate = new GregorianCalendar(year, month, day);
                return;

            case WEEK:
                increment = (int)(7 / (freqNum));
                break;
            case MONTH:
                increment = (int)(30 / (freqNum));
                break;
            default:
                increment = 1;
                break;
        }
        do {
            nextDate.add(GregorianCalendar.DAY_OF_MONTH, increment);
        } while (nextDate.before(today));

        nextDate.set(Calendar.HOUR_OF_DAY, time.getHour());
        nextDate.set(Calendar.MINUTE, time.getHour());
    }

    public GregorianCalendar getNextDate(){
        updateNextDate();
        return (GregorianCalendar) nextDate.clone();
    }

    public static MedicineReminder getInstance() {
        return new MedicineReminder(null, null, null, null, 0, null);
    }

    public String getStartDate() {
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        return (sdf.format(startDate.getTime()));
    }

    public void setStartDate(GregorianCalendar startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        return (sdf.format(endDate.getTime()));
    }

    public void setEndDate(GregorianCalendar endDate) {
        this.endDate = endDate;
    }

    public void setTime(LocalTime time) { this.time = time; }

    public String getTime() {
        return this.time.toString();
    }

    public boolean isValidDate() {
        return startDate.before(endDate) &&
                endDate.after(Calendar.getInstance());
    }
}
