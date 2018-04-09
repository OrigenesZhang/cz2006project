package com.example.liangliang.ipetreminder.controllers;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangliang.ipetreminder.models.DataBase;
import com.example.liangliang.ipetreminder.R;
import com.example.liangliang.ipetreminder.models.Frequency;
import com.example.liangliang.ipetreminder.models.HygieneReminder;
import com.example.liangliang.ipetreminder.models.ReminderFactory;

import java.util.Calendar;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditHygiene extends AppCompatActivity {

    @BindView(R.id.hygieneName) EditText mHygieneName;
    @BindView(R.id.note) EditText mNote;
    @BindView(R.id.freqNum) TextView mFreqNum;
    @BindView(R.id.spinner) Spinner freqSpinner;
    @BindView(R.id.nextDate) TextView mNextDate;

    HygieneReminder hygiene;
    int position;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_hygiene_reminder);
        ButterKnife.bind(this);

        // Spinner widget setup
        ArrayAdapter<String> freqAdapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.frequency)
        );
        freqAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        freqSpinner.setAdapter(freqAdapter);

        // Intent
        Intent intent = getIntent();
        position = intent.getIntExtra("Position", -1);

        // display model
        if (position == -1) {
            hygiene = (HygieneReminder) ReminderFactory.getReminder("hygiene");
        } else {
            hygiene = DataBase.hygieneItems.get(position);
        }

        mHygieneName.setText(hygiene.getName());
        mFreqNum.setText(Integer.toString(hygiene.getFreqNum()));
        mNextDate.setText(hygiene.getNextDate());
        freqSpinner.setSelection(hygiene.getFrequencyInex());

        mNote.setText(hygiene.getNote());

        // Edit hygiene name
        mHygieneName.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                hygiene.setName(mHygieneName.getText().toString());
            }
        });

        // Edit note
        mNote.addTextChangedListener(new TextWatcher () {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                hygiene.setNote(mNote.getText().toString());
            }
        });

        mFreqNum.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String amountStr = mFreqNum.getText().toString();
                amountStr = amountStr.trim();
                try {
                    int amount = Integer.parseInt(amountStr);
                    hygiene.setFreqNum(amount);
                } catch (NumberFormatException e) {
                    Toast.makeText(EditHygiene.this, "Invalid Input!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        freqSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int freqPosition = adapterView.getSelectedItemPosition();
                hygiene.setFrequency(Frequency.byOrdinal(freqPosition));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        mNextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(EditHygiene.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        hygiene.setNextDate(new GregorianCalendar(i, i1, i2));
                        mNextDate.setText(hygiene.getNextDate());
                    }
                }, year, month, day
                );
                dialog.show();
            }
        });

        // save the reminder
        FloatingActionButton myFab = (FloatingActionButton) this.findViewById(R.id.save_fab);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!hygiene.isValidDate()) {
                    Toast.makeText(EditHygiene.this, "Invalid Date Input", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(EditHygiene.this, RemindersMainTab.class);
                    i.putExtra("Page", 1);
                    if (position == -1) {
                        DataBase.hygieneItems.add(hygiene);
                    } else {
                        DataBase.hygieneItems.set(position, hygiene);
                    }
                    startActivity(i);
                }
            }
        });

        // delete the reminder
        FloatingActionButton deleteFab = (FloatingActionButton) this.findViewById(R.id.delete_fab);
        deleteFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditHygiene.this);
                builder.setMessage("You are about to delete this record. Are you sure you want to continue?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DataBase.hygieneItems.remove(position);
                        Intent i = new Intent(EditHygiene.this, RemindersMainTab.class);
                        i.putExtra("Page", 1);
                        startActivity(i);
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                });

                builder.show();
            }
        });
    }
}


















