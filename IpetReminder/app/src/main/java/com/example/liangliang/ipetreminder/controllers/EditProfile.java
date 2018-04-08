package com.example.liangliang.ipetreminder.controllers;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangliang.ipetreminder.models.DataBase;
import com.example.liangliang.ipetreminder.R;
import com.example.liangliang.ipetreminder.models.Pet;

import java.util.Calendar;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by caoliu on 24/3/18.
 */

public class EditProfile extends AppCompatActivity {
    @BindView(R.id.pet_name) EditText mName;
    @BindView(R.id.birthday) TextView mBirthday;
    @BindView(R.id.gender_image) ImageView genderImage;
    @BindView(R.id.pet_owner) EditText ownerName;
    @BindView(R.id.medicineTab) Button medicineTab;
    @BindView(R.id.hygieneTab) Button hygieneTab;
    @BindView(R.id.exerciseTab) Button exerciseTab;

    Pet pet;
    private DatePickerDialog.OnDateSetListener mBirthdayListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_reminders);
        ButterKnife.bind(this);

        // display the info based on the received object
        Intent intent = getIntent();
        pet = (Pet) intent.getSerializableExtra("petObj");

        mName.setText(pet.getName());
        mBirthday.setText(pet.getBirthday());

        // display the gender image
        if (pet.isMale())
            genderImage.setImageResource(R.drawable.icon_male);
        else
            genderImage.setImageResource(R.drawable.icon_female);
        ownerName.setText(pet.getOwner());


        // User select and edit the birthday
        mBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DATE);

                DatePickerDialog dialog = new DatePickerDialog(
                        EditProfile.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        mBirthdayListener,
                        year, month, day
                );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mBirthdayListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                int year = i;
                int month = i1 + 1;
                int day = i2;

                Calendar calendar = Calendar.getInstance();
                GregorianCalendar birthday = new GregorianCalendar(year, month-1, day);
                if (birthday.before(calendar)) {
                    String date = year + "-" + month + "-" + day;
                    mBirthday.setText(date);
                    pet.setBirthday(new GregorianCalendar(year, month-1, day));
                } else {
                    Toast.makeText(EditProfile.this, "You must enter a valid date",
                            Toast.LENGTH_LONG).show();
                }
            }
        };

        // click gender image to set gender
        genderImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int maleId = R.drawable.icon_male;
                int femaleId = R.drawable.icon_female;
                if (pet.isMale()) {
                    genderImage.setImageResource(femaleId);
                    pet.setMale(false);
                } else {
                    genderImage.setImageResource(maleId);
                    pet.setMale(true);
                }
            }
        });

        // click on Medicine, Hygiene or ExerciseTab
        medicineTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int page = 0;
                Intent i = new Intent(EditProfile.this, RemindersMainTab.class);
                i.putExtra("Page", page);
                startActivity(i);
            }
        });

        hygieneTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int page = 1;
                Intent i = new Intent(EditProfile.this, RemindersMainTab.class);
                i.putExtra("Page", page);
                startActivity(i);
            }
        });

        exerciseTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int page = 2;
                Intent i = new Intent(EditProfile.this, RemindersMainTab.class);
                i.putExtra("Page", page);
                startActivity(i);
            }
        });


        // save the change
        FloatingActionButton myFab = (FloatingActionButton) this.findViewById(R.id.main_fab);
        myFab.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onClick(View v) {

            pet.setName(mName.getText().toString());
            pet.setOwner(ownerName.getText().toString());
            DataBase.updatePet(pet);
            Intent i  = new Intent(EditProfile.this, Profile.class);
            startActivity(i);
            }
        });
    }
}