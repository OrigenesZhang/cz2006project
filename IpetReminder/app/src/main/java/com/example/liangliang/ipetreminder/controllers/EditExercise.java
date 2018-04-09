package com.example.liangliang.ipetreminder.controllers;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.provider.AlarmClock;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.liangliang.ipetreminder.models.DataBase;
import com.example.liangliang.ipetreminder.R;
import com.example.liangliang.ipetreminder.models.ExerciseReminder;
import com.example.liangliang.ipetreminder.models.Frequency;
import com.example.liangliang.ipetreminder.models.ReminderFactory;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditExercise extends AppCompatActivity {

    @BindView(R.id.nextDate) TextView mNextDate;
    @BindView(R.id.spinner) Spinner freqSpinner;
    @BindView(R.id.freqNum) TextView mFreqNum;
    @BindView(R.id.exerciseName) EditText mExerciseName;
    @BindView(R.id.note) EditText mNote;
    @BindView(R.id.time) TextView time;
    @BindView(R.id.setAlarmButton) Button alarmButton;

    ExerciseReminder exercise;
    int position;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_exercise_reminder);
        ButterKnife.bind(this);

        // Spinner setup
        ArrayAdapter<String> freqAdapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.frequency)
        );
        freqAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        freqSpinner.setAdapter(freqAdapter);


        Intent intent = getIntent();
        position = intent.getIntExtra("Position", -1);

        // If is to edit a reminder
        if (position == -1) {
            exercise = (ExerciseReminder) ReminderFactory.getReminder("exercise");

        } else {
            exercise = DataBase.exerciseItems.get(position);
        }

        mExerciseName.setText(exercise.getName());
        mFreqNum.setText(Integer.toString(exercise.getFreqNum()));
        // mNextDate.setText(exercise.getNextDate());
        time.setText(exercise.getTime());
        freqSpinner.setSelection(exercise.getFrequencyInex());
        mNote.setText(exercise.getNote());

        mExerciseName.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                exercise.setName(mExerciseName.getText().toString());
            }
        });

        mNote.addTextChangedListener(new TextWatcher () {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                exercise.setNote(mNote.getText().toString());
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
                    exercise.setFreqNum(amount);
                } catch (NumberFormatException e) {
                    Toast.makeText(EditExercise.this, "Invalid Input!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        freqSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int freqPosition = adapterView.getSelectedItemPosition();
                exercise.setFrequency(Frequency.byOrdinal(freqPosition));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minute = cal.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(EditExercise.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        exercise.setTime(LocalTime.of(selectedHour, selectedMinute));       // update time field
                        time.setText( String.format("%02d", selectedHour) + ":" + String.format("%02d", selectedMinute));
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        mNextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(EditExercise.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        exercise.setNextDate(new GregorianCalendar(i, i1, i2));
                        mNextDate.setText(exercise.getNextDate());
                    }
                }, year, month, day
                );
                dialog.show();
            }
        });

        alarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = exercise.getNextDateInstance();
                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                intent.putExtra(AlarmClock.EXTRA_HOUR, cal.get(Calendar.HOUR_OF_DAY));
                intent.putExtra(AlarmClock.EXTRA_MINUTES, cal.get(Calendar.MINUTE));
                intent.putExtra(AlarmClock.EXTRA_MESSAGE, "Have Medicine");
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivity(intent);
            }
        });

        // save the reminder
        FloatingActionButton myFab = (FloatingActionButton) this.findViewById(R.id.save_fab);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!exercise.isValidDate()) {
                    Toast.makeText(EditExercise.this, "Invalid Date Input", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(EditExercise.this, RemindersMainTab.class);
                    i.putExtra("Page", 0);
                    if (position == -1) {
                        DataBase.exerciseItems.add(exercise);
                    } else {
                        DataBase.exerciseItems.set(position, exercise);
                    }
                    startActivity(i);
                }
            }
        });

        // delete the reminder
        FloatingActionButton deleteFab = (FloatingActionButton) this.findViewById(R.id.delete_fab);
        deleteFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditExercise.this);
                builder.setMessage("You are about to delete this record. Are you sure you want to continue?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DataBase.exerciseItems.remove(position);
                        Intent i = new Intent(EditExercise.this, RemindersMainTab.class);
                        i.putExtra("Page", 2);
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
