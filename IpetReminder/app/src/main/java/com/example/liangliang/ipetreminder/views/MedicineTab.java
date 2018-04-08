package com.example.liangliang.ipetreminder.views;

/**
 * Created by caoliu on 18/3/18.
 */
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.liangliang.ipetreminder.controllers.MedicineAdapter;
import com.example.liangliang.ipetreminder.models.DataBase;
import com.example.liangliang.ipetreminder.R;
import com.example.liangliang.ipetreminder.models.MedicineReminder;

import java.util.List;

public class MedicineTab extends Fragment {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.listview, container, false);

        ListView lv = (ListView) rootView.findViewById(R.id.listView);

        MedicineAdapter adapter = new MedicineAdapter(getActivity(), R.layout.medicine_item, DataBase.medicineItems);

        lv.setAdapter(adapter);

        return rootView;
    }
}
