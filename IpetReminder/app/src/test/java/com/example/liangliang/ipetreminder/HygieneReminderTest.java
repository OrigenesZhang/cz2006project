package com.example.liangliang.ipetreminder;

import com.example.liangliang.ipetreminder.models.ExerciseReminder;
import com.example.liangliang.ipetreminder.models.Frequency;
import com.example.liangliang.ipetreminder.models.HygieneReminder;
import com.example.liangliang.ipetreminder.models.MedicineReminder;
import com.example.liangliang.ipetreminder.models.ReminderFactory;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * Created by caoliu on 10/4/18.
 */

public class HygieneReminderTest {
    private final String TAG = "HygieneReminderTest";
    private HygieneReminder hygieneReminder;

    private Calendar today = Calendar.getInstance();
    private int year = today.get(GregorianCalendar.YEAR);
    private int month = today.get(GregorianCalendar.MONTH);
    private int day = today.get(GregorianCalendar.DAY_OF_MONTH);

    @Before
    public void setUp() {
        hygieneReminder = (HygieneReminder) ReminderFactory.getReminder("hygiene");
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

        GregorianCalendar nextDate = new GregorianCalendar(year, month, day);
        hygieneReminder.setNextDate(nextDate);

        assertEquals(true, hygieneReminder.isValidDate());
    }

    @Test
    public void updateNextDate() throws Exception {
        hygieneReminder.setFreqNum(2);
        hygieneReminder.setFrequency(Frequency.WEEK);
        GregorianCalendar nextDate =  hygieneReminder.getNextDate();
        System.out.println(nextDate);

        int predMonth = nextDate.get(Calendar.MONTH);
        int predDay = nextDate.get(Calendar.DAY_OF_MONTH);

        int expectMonth = Calendar.APRIL;
        int expectDAY = 13;

        assertEquals(expectMonth, predMonth);
        assertEquals(expectDAY, predDay);
    }
}
