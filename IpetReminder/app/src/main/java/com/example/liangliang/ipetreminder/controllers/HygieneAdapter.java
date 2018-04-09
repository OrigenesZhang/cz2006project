package com.example.liangliang.ipetreminder.controllers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.liangliang.ipetreminder.R;
import com.example.liangliang.ipetreminder.models.HygieneReminder;

import java.util.List;

/**
 * Created by caoliu on 8/4/18.
 */

public class HygieneAdapter extends ArrayAdapter<HygieneReminder> {
    public HygieneAdapter(Context context, int resource, List<HygieneReminder> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final HygieneReminder hygiene = getItem(position);

        final View oneItemView = LayoutInflater.from(getContext()).inflate(R.layout.hygiene_item, parent, false);

        TextView name = oneItemView.findViewById(R.id.hygieneName);
        TextView mFrequency = oneItemView.findViewById(R.id.frequency);
        TextView mStartDate = oneItemView.findViewById(R.id.nextDate);

        mStartDate.setText(hygiene.getNextDateString());
        Log.i("HygieneTab", "getView: " + hygiene.getNextDate());
        name.setText(hygiene.getName());

        switch (hygiene.getName()) {
            case "Hair":
                name.setCompoundDrawablesWithIntrinsicBounds(R.drawable.hair, 0, 0, 0);
                break;

            case "Bath":
                name.setCompoundDrawablesWithIntrinsicBounds(R.drawable.bath, 0, 0, 0);
                break;

            case "Tooth":
                name.setCompoundDrawablesWithIntrinsicBounds(R.drawable.tooth, 0, 0, 0);
                break;
            default:
                name.setCompoundDrawablesWithIntrinsicBounds(R.drawable.hygiene, 0, 0, 0);
                break;
        }

        String frequency;
        if (hygiene.getFreqNum() == 1)
            frequency = " Once a " + hygiene.getFrequency();
        else
            frequency = Integer.toString(hygiene.getFreqNum()) + " times a " + hygiene.getFrequency();
        mFrequency.setText(frequency);

        // Onclick
        oneItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), EditHygiene.class);
                i.putExtra("Position", position);
                getContext().startActivity(i);
            }
        });

        return oneItemView;
    }
}