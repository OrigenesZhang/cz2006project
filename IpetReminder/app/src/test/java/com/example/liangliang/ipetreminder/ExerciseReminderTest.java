package com.example.liangliang.ipetreminder;

import android.text.format.DateUtils;

import com.example.liangliang.ipetreminder.models.ExerciseReminder;
import com.example.liangliang.ipetreminder.models.Frequency;
import com.example.liangliang.ipetreminder.models.MedicineReminder;
import com.example.liangliang.ipetreminder.models.ReminderFactory;

import org.junit.Before;
import org.junit.Test;

import java.io.FileReader;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * Created by caoliu on 10/4/18.
 */

public class ExerciseReminderTest {
    private final String TAG = "ExerciseReminder";
    private ExerciseReminder exerciseReminder;

    private Calendar today = Calendar.getInstance();
    private int year = today.get(GregorianCalendar.YEAR);
    private int month = today.get(GregorianCalendar.MONTH);
    private int day = today.get(GregorianCalendar.DAY_OF_MONTH);

    @Before
    public void setUp() {
        exerciseReminder = (ExerciseReminder) ReminderFactory.getReminder("exercise");
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
        // all the nextDate will be valid since it will be updated automatically

        d(TAG, exerciseReminder.getNextDateString());
        assertEquals(true, exerciseReminder.isValidDate());
    }

    @Test
    public void updateNextDate() throws Exception {
        exerciseReminder.setFreqNum(2);
        exerciseReminder.setFrequency(Frequency.MONTH);
        GregorianCalendar nextDate =  exerciseReminder.getNextDate();

        int predMonth = nextDate.get(Calendar.MONTH);
        int predDay = nextDate.get(Calendar.DAY_OF_MONTH);

        int expectMonth = Calendar.APRIL;
        int expectDAY = 25;

        d(TAG, exerciseReminder.getNextDateString());

        assertEquals(expectMonth, predMonth);
        assertEquals(expectDAY, predDay);
    }
}
