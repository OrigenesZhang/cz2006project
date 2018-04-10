package com.example.liangliang.ipetreminder;

import com.example.liangliang.ipetreminder.models.Frequency;
import com.example.liangliang.ipetreminder.models.MedicineReminder;
import com.example.liangliang.ipetreminder.models.ReminderFactory;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * A Unit Test on MedicineReminder Class
 */
public class MedicineReminderTest {
    private final String TAG = "MedicineReminderTest";
    private MedicineReminder medicineReminder;

    private Calendar today = Calendar.getInstance();
    private int year = today.get(GregorianCalendar.YEAR);
    private int month = today.get(GregorianCalendar.MONTH);
    private int day = today.get(GregorianCalendar.DAY_OF_MONTH);

    @Before
    public void setUp() {
        medicineReminder = (MedicineReminder) ReminderFactory.getReminder("medicine");
        Calendar today = Calendar.getInstance();
        year = today.get(GregorianCalendar.YEAR);
        month = today.get(GregorianCalendar.MONTH);
        day = today.get(GregorianCalendar.DAY_OF_MONTH);
    }

    public void d(String tag, String message) {
        System.out.println("D " + tag + ": " + message);
    }

    @Test
    public void validDateInput() throws Exception {
        month = month - 1;
        day = day - 3;

        GregorianCalendar endDate = new GregorianCalendar(year, month, day);
        medicineReminder.setEndDate(endDate);
        d(TAG, medicineReminder.getStartDate());
        d(TAG, medicineReminder.getEndDate());
        assertEquals(false, medicineReminder.isValidDate());
    }

    @Test
    public void updateNextDate() throws Exception {
        medicineReminder.setFreqNum(2);
        medicineReminder.setFrequency(Frequency.WEEK);
        GregorianCalendar nextDate =  medicineReminder.getNextDate();
        System.out.println(nextDate);

        int predMonth = nextDate.get(Calendar.MONTH);
        int predDay = nextDate.get(Calendar.DAY_OF_MONTH);

        int expectMonth = Calendar.APRIL;
        int expectDAY = 13;

        assertEquals(expectMonth, predMonth);
        assertEquals(expectDAY, predDay);
    }
}