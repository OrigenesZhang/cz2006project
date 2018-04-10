package sg.edu.ntu.e.fang0074.ipet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class PromotionList extends AppCompatActivity {

    Toolbar mToolbar1;
    ListView mListView1;

    String[] promoName = {"Promotion1"};
    int[] promoPic = {R.drawable.pic_1};


    @Override
    protected void onCreate(Bundle savedInstanceState1) {
        super.onCreate(savedInstanceState1);
        setContentView(R.layout.activity_promotion_list);
        mToolbar1 = (Toolbar) findViewById(R.id.toolbar4);
        mToolbar1.setTitle(getResources().getString(R.string.activity_name2));
        mListView1 = (ListView) findViewById(R.id.listView3);
        MyAdapter myAdapter2 = new MyAdapter(PromotionList.this, promoName, promoPic);
        mListView1.setAdapter(myAdapter2);
        mListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent mIntent1 = new Intent(PromotionList.this, Detail2Activity.class);
                mIntent1.putExtra("countryName", promoName[i]);
                mIntent1.putExtra("countryFlag", promoPic[i]);
                startActivity(mIntent1);
            }
        });
       }


}

