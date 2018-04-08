package com.example.liangliang.ipetreminder.models;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.liangliang.ipetreminder.models.ExerciseReminder;
import com.example.liangliang.ipetreminder.models.Frequency;
import com.example.liangliang.ipetreminder.models.HygieneReminder;
import com.example.liangliang.ipetreminder.models.MedicineReminder;
import com.example.liangliang.ipetreminder.models.Pet;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by caoliu on 24/3/18.
 */

@RequiresApi(api = Build.VERSION_CODES.O)
public class DataBase {
    public static Pet pet;
    public static List<MedicineReminder> medicineItems;
    public static List<HygieneReminder> hygieneItems;
    public static List<ExerciseReminder> exerciseItems;

    static {
        pet = Pet.getInstance();
        medicineItems = new ArrayList<>();
        hygieneItems = new ArrayList<>();
        exerciseItems = new ArrayList<>();

        medicineItems.add(new MedicineReminder("Vitamin", LocalTime.of(13,20), (new GregorianCalendar(2018, Calendar.FEBRUARY, 12)),
                new GregorianCalendar(2018, 4, 2), 2, Frequency.DAY));
        medicineItems.add(new MedicineReminder("Antibiotic", LocalTime.of(15, 20), new GregorianCalendar(2018, Calendar.APRIL, 4),
                new GregorianCalendar(2018, 7, 14), 4, Frequency.MONTH));

        hygieneItems.add(new HygieneReminder("Bath",  2, Frequency.MONTH, new GregorianCalendar(2018, GregorianCalendar.FEBRUARY, 20)));
        hygieneItems.add(new HygieneReminder("Hair",  3, Frequency.DAY, new GregorianCalendar(2018, GregorianCalendar.FEBRUARY, 21)));
        hygieneItems.add(new HygieneReminder("Tooth",1, Frequency.WEEK, new GregorianCalendar(2018, GregorianCalendar.FEBRUARY, 2)));

        exerciseItems.add(new ExerciseReminder("Walk the dog", 1, Frequency.DAY, LocalTime.of(18, 20), new GregorianCalendar(2018, GregorianCalendar.APRIL, 1)));
        exerciseItems.add(new ExerciseReminder("Ball game", 2, Frequency.WEEK, LocalTime.of(16, 30), new GregorianCalendar(2018, GregorianCalendar.MARCH, 1)));
        exerciseItems.add(new ExerciseReminder("Swimming", 2, Frequency.MONTH, LocalTime.of(16,30), new GregorianCalendar(2018, GregorianCalendar.MAY, 1)));

    }

    public static Pet getPet() {
        return pet;
    }

    public static void updatePet(Pet p) {
        pet = p;
    }
}
