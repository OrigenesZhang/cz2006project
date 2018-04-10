package sg.edu.ntu.e.fang0074.ipet.controllers;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
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

import java.time.LocalTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import sg.edu.ntu.e.fang0074.ipet.R;
import sg.edu.ntu.e.fang0074.ipet.models.DataBase;
import sg.edu.ntu.e.fang0074.ipet.models.Frequency;
import sg.edu.ntu.e.fang0074.ipet.models.MedicineReminder;
import sg.edu.ntu.e.fang0074.ipet.models.ReminderFactory;

/**
 * Created by caoliu on 19/3/18.
 */

public class EditMedicine extends AppCompatActivity{

    @BindView(R.id.selectStartDate) TextView mStartDate;
    @BindView(R.id.selectEndDate) TextView mEndDate;
    @BindView(R.id.spinner) Spinner freqSpinner;
    @BindView(R.id.dosage) TextView mDosage;
    @BindView(R.id.medicineName) EditText mMedicineName;
    @BindView(R.id.note) EditText note;
    @BindView(R.id.time) TextView time;
    @BindView(R.id.setAlarmButton) Button alarmButton;

    MedicineReminder medicine;
    int position;
    int index;

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_medicine_reminder);
        ButterKnife.bind(this);


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
            try {
                medicine = (MedicineReminder) ReminderFactory.getReminder("medicine");
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            medicine = DataBase.medicineItems.get(position);
            mMedicineName.setText(medicine.getName());
            mDosage.setText(Integer.toString(medicine.getFreqNum()));
            mStartDate.setText(medicine.getStartDate());
            mEndDate.setText(medicine.getEndDate());
            time.setText(medicine.getTime());
            freqSpinner.setSelection(medicine.getFrequencyIndex());

            if (medicine.getNote() != null)
                note.setText(medicine.getNote());
        }

        mMedicineName.addTextChangedListener(new TextWatcher () {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                medicine.setName(mMedicineName.getText().toString());
            }
        });

        note.addTextChangedListener(new TextWatcher () {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                medicine.setNote(note.getText().toString());
            }
        });

        mDosage.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String amountStr = mDosage.getText().toString();
                amountStr = amountStr.trim();
                try {
                    int amount = Integer.parseInt(amountStr);
                    medicine.setFreqNum(amount);
                } catch (NumberFormatException e) {
                    Toast.makeText(EditMedicine.this, "Invalid Input!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        freqSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int freqPosition = adapterView.getSelectedItemPosition();
                medicine.setFrequency(Frequency.byOrdinal(freqPosition));
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
                mTimePicker = new TimePickerDialog(EditMedicine.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        medicine.setTime(LocalTime.of(selectedHour, selectedMinute));       // update time field
                        time.setText( String.format("%02d", selectedHour) + ":" + String.format("%02d", selectedMinute));
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        mStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(EditMedicine.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        medicine.setStartDate(new GregorianCalendar(i, i1, i2));
                        mStartDate.setText(medicine.getStartDate());
                    }
                }, year, month, day
                );
                dialog.show();
            }
        });


        mEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(EditMedicine.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        medicine.setEndDate(new GregorianCalendar(i, i1, i2));
                        mEndDate.setText(medicine.getEndDate());
                    }
                }, year, month, day
                );
                dialog.show();
            }
        });

        alarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = medicine.getNextDate();
                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                intent.putExtra(AlarmClock.EXTRA_HOUR, cal.get(Calendar.HOUR_OF_DAY));
                intent.putExtra(AlarmClock.EXTRA_MINUTES, cal.get(Calendar.MINUTE));
                intent.putExtra(AlarmClock.EXTRA_MESSAGE, "Have Medicine");
                startActivity(intent);
            }
        });

        // save the reminder
        FloatingActionButton myFab = (FloatingActionButton) this.findViewById(R.id.save_fab);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!medicine.isValidDate()) {
                    Toast.makeText(EditMedicine.this, "Invalid Date Input", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(EditMedicine.this, RemindersMainTab.class);
                    i.putExtra("Page", 0);
                    if (position == -1) {
                        DataBase.medicineItems.add(medicine);
                    } else {
                        DataBase.medicineItems.set(position, medicine);
                    }
                    startActivity(i);
                }
            }
        });


        // delete the reminder
        FloatingActionButton deleteFab = (FloatingActionButton) this.findViewById(R.id.delete_fab);
        deleteFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditMedicine.this);
                builder.setMessage("You are about to delete this record. Are you sure you want to continue?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (position != -1)
                            DataBase.medicineItems.remove(position);
                        Intent i = new Intent(EditMedicine.this, RemindersMainTab.class);
                        i.putExtra("Page", 0);
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