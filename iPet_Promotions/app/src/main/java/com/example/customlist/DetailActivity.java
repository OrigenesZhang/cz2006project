package com.example.customlist;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

public class DetailActivity extends AppCompatActivity {

    Toolbar mToolbar;
    ListView listView2;

    String[] collectedPromoDesc = {"Promotion1", "Promotion2"};
    int[] collectedImage = {R.drawable.pic_1,
            R.drawable.pic_2};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(getResources().getString(R.string.activity_name));
        listView2 = (ListView) findViewById(R.id.listView2);
        MyAdapter myAdapter = new MyAdapter(DetailActivity.this, collectedPromoDesc, collectedImage);
        listView2.setAdapter(myAdapter);
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent mIntent = new Intent(DetailActivity.this, Detail2Activity.class);
                mIntent.putExtra("countryName", collectedPromoDesc[i]);
                mIntent.putExtra("countryFlag", collectedImage[i]);
                startActivity(mIntent);
            }
        });
    }

}
