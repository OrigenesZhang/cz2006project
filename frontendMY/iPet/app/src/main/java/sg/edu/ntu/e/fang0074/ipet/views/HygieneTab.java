package sg.edu.ntu.e.fang0074.ipet.views;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import sg.edu.ntu.e.fang0074.ipet.R;
import sg.edu.ntu.e.fang0074.ipet.controllers.HygieneAdapter;
import sg.edu.ntu.e.fang0074.ipet.models.DataBase;


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
