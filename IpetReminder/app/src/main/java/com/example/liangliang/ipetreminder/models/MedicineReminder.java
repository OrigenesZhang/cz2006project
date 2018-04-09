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
    private UpdateNextDateStrategy updateNextDate;

    public MedicineReminder(String name, LocalTime time, GregorianCalendar startDate, GregorianCalendar endDate,
                            int freqNum, Frequency freq, UpdateNextDateStrategy updateNextDate) {
        super(name, freqNum, freq);
        this.time = time;
        this.startDate = startDate;
        this.endDate = endDate;
        this.updateNextDate = updateNextDate;
        updateNextDate.update(startDate, endDate, time, freqNum, frequency);
    }

    public GregorianCalendar getNextDate(){
        GregorianCalendar nextDate = updateNextDate.update(startDate, endDate, time, freqNum, frequency);
        return nextDate;
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
