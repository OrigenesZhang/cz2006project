package sg.edu.ntu.e.fang0074.ipet;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewSwitcher;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar mToolbar;
    ListView mListView;
    ImageButton collectBtn;

    String[] promoName = {"Promotion1", "Promotion2", "Promotion3"};
    int[] promoPic = {R.drawable.pic_1,
            R.drawable.pic_2,
            R.drawable.png_3};

    int i = 0;  //count variable for image switcher

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //collectionButton onclick direct to collection list(DetailActicity.class)
        collectBtn = (ImageButton) findViewById(R.id.collectionButton);
        collectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(
                        MainActivity.this, PromotionList.class);
                startActivity(intentLoadNewActivity);
            }
        });

        //Image Swithcers
        ImageButton prv,nxt;
        final Integer[] images = new Integer[]{
                R.drawable.pic_1, R.drawable.pic_2, R.drawable.png_3};

        final ImageSwitcher imageSwitcher;

        imageSwitcher =(ImageSwitcher) findViewById((R.id.imageSwitcher));

        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
                                     @Override
                                     public View makeView() {
                                         ImageView imageView = new ImageView(getApplicationContext());
                                         imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                         imageView.setLayoutParams(
                                                 new ImageSwitcher.LayoutParams(
                                                         ViewGroup.LayoutParams.MATCH_PARENT,
                                                         ViewGroup.LayoutParams.MATCH_PARENT));
                                         return imageView;
                                     }
                                 }

        );
        prv=(ImageButton)findViewById(R.id.prev);
        nxt=(ImageButton)findViewById(R.id.next);

        prv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(i == 0){
                    i = images.length - 1;
                }
                else if(i>0){
                    i--;
                }
                imageSwitcher.setImageResource(images[i]);
            }
        });
        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i<images.length-1){
                    i++;
                }
                else{
                    i=0;
                }
                imageSwitcher.setImageResource(images[i]);
            }
        });

        //List with images and text
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(getResources().getString(R.string.activity_name));
        mListView = (ListView) findViewById(R.id.listview);
        MyAdapter myAdapter = new MyAdapter(MainActivity.this, promoName, promoPic);
        mListView.setAdapter(myAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent mIntent = new Intent(MainActivity.this, Detail2Activity.class);
                mIntent.putExtra("countryName", promoName[i]);
                mIntent.putExtra("countryFlag", promoPic[i]);
                startActivity(mIntent);
            }
        });}





        /*ImageButton collectionButton = (ImageButton) findViewById(R.id.collectionButton);
        collectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(MainActivity.this,DetailActivity.class);
                startActivity(intentLoadNewActivity);
            }
        });*/
}

