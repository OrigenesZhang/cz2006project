package com.example.liangliang.ipetreminder.views;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.liangliang.ipetreminder.controllers.HygieneAdapter;
import com.example.liangliang.ipetreminder.models.DataBase;
import com.example.liangliang.ipetreminder.R;
import com.example.liangliang.ipetreminder.models.HygieneReminder;

import java.util.List;

/**
 * Created by caoliu on 18/3/18.
 */

public class HygieneTab extends Fragment{

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.listview, container, false);

        ListView lv = (ListView) rootView.findViewById(R.id.listView);

        HygieneAdapter adapter = new HygieneAdapter(getActivity(), R.layout.hygiene_item, DataBase.hygieneItems);

        lv.setAdapter(adapter);

        return rootView;
    }
}
