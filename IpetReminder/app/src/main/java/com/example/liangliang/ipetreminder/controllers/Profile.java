package com.example.liangliang.ipetreminder.controllers;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liangliang.ipetreminder.models.DataBase;
import com.example.liangliang.ipetreminder.R;
import com.example.liangliang.ipetreminder.models.Pet;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Profile extends AppCompatActivity {
    @BindView(R.id.pet_name) TextView mName;
    @BindView(R.id.birthday) TextView mBirthday;
    @BindView(R.id.gender_image) ImageView genderImage;
    @BindView(R.id.pet_owner) TextView ownerName;
    @BindView(R.id.medicineTab) Button medicineTab;
    @BindView(R.id.hygieneTab) Button hygieneTab;
    @BindView(R.id.exerciseTab) Button exerciseTab;

    Pet pet;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);
        ButterKnife.bind(this);

        pet = DataBase.getPet();
        mName.setText(pet.getName());
        mBirthday.setText(pet.getBirthday());

        if (pet.isMale())
            genderImage.setImageResource(R.drawable.icon_male);
        else
            genderImage.setImageResource(R.drawable.icon_female);
        ownerName.setText(pet.getOwner());

        medicineTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int page = 0;
                Intent i = new Intent(Profile.this, RemindersMainTab.class);
                i.putExtra("Page", page);
                startActivity(i);
            }
        });

        hygieneTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int page = 1;
                Intent i = new Intent(Profile.this, RemindersMainTab.class);
                i.putExtra("Page", page);
                startActivity(i);
            }
        });

        exerciseTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int page = 2;
                Intent i = new Intent(Profile.this, RemindersMainTab.class);
                i.putExtra("Page", page);
                startActivity(i);
            }
        });

        FloatingActionButton myFab = (FloatingActionButton) this.findViewById(R.id.main_fab);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Profile.this, EditProfile.class);
                i.putExtra("petObj", pet);
                startActivity(i);
            }
        });
    }
}
