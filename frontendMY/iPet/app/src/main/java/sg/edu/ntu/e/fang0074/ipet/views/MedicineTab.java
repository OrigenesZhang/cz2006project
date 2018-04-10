package sg.edu.ntu.e.fang0074.ipet.views;

/**
 * Created by caoliu on 18/3/18.
 */

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import sg.edu.ntu.e.fang0074.ipet.R;
import sg.edu.ntu.e.fang0074.ipet.controllers.MedicineAdapter;
import sg.edu.ntu.e.fang0074.ipet.models.DataBase;


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
