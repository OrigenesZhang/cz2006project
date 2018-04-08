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
import com.example.liangliang.ipetreminder.models.ExerciseReminder;

import java.util.List;

/**
 * Created by caoliu on 8/4/18.
 */

public class ExerciseAdapter extends ArrayAdapter<ExerciseReminder> {
    public ExerciseAdapter(Context context, int resource, List<ExerciseReminder> objects) {
        super(context, resource, objects);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        final ExerciseReminder exerciseReminder = getItem(position);

        final View oneItemView = LayoutInflater.from(getContext()).inflate(R.layout.exercise_item, parent, false);

        TextView nameTv = oneItemView.findViewById(R.id.exerciseName);
        TextView frequencyTv = oneItemView.findViewById(R.id.frequency);
        TextView timeTv = oneItemView.findViewById(R.id.time);

        assert exerciseReminder != null;
        nameTv.setText(exerciseReminder.getName());

        String frequency;
        if (exerciseReminder.getFreqNum() == 1)
            frequency = " Once a " + exerciseReminder.getFrequency();
        else
            frequency = Integer.toString(exerciseReminder.getFreqNum()) + " times a " + exerciseReminder.getFrequency();
        frequencyTv.setText(frequency);

        timeTv.setText(exerciseReminder.getTime());

        // Onclick
        oneItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ExerciseTab", "onClick: ");
                Intent i = new Intent(getContext(), EditExercise.class);
                i.putExtra("Position", position);
                getContext().startActivity(i);
            }
        });

        return oneItemView;
    }
}