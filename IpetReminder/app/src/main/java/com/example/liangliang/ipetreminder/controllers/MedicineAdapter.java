package com.example.liangliang.ipetreminder.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.liangliang.ipetreminder.R;
import com.example.liangliang.ipetreminder.models.MedicineReminder;

import java.util.List;

/**
 * Created by caoliu on 8/4/18.
 */

public class MedicineAdapter extends ArrayAdapter<MedicineReminder> {
    public MedicineAdapter(Context context, int resource, List<MedicineReminder> objects) {
        super(context, resource, objects);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final MedicineReminder medicine = getItem(position);

        // View oneItemView = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.medicine_item,null);
        final View oneItemView = LayoutInflater.from(getContext()).inflate(R.layout.medicine_item, parent, false);

        TextView mMedicineName = (TextView) oneItemView.findViewById(R.id.medicineName);
        TextView mTime = (TextView) oneItemView.findViewById(R.id.time);
        TextView mStartDate = (TextView) oneItemView.findViewById(R.id.startDate);
        TextView mDosage = (TextView) oneItemView.findViewById(R.id.dosage);

        // display the content
        mMedicineName.setText(medicine.getName());
        mStartDate.setText(medicine.getStartDate());
        mTime.setText(medicine.getTime());

        String dosage;
        if (medicine.getFreqNum() == 1)
            dosage = " Once a " + medicine.getFrequency();
        else
            dosage = Integer.toString(medicine.getFreqNum()) + " times a " + medicine.getFrequency();
        mDosage.setText(dosage);

        // Onclick
        oneItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), EditMedicine.class);
                i.putExtra("Position", position);
                getContext().startActivity(i);
            }
        });

        return oneItemView;
    }
}