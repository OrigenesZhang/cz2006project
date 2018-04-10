package sg.edu.ntu.e.fang0074.ipet.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import sg.edu.ntu.e.fang0074.ipet.R;
import sg.edu.ntu.e.fang0074.ipet.views.ExerciseTab;
import sg.edu.ntu.e.fang0074.ipet.views.HygieneTab;
import sg.edu.ntu.e.fang0074.ipet.views.MedicineTab;


public class RemindersMainTab extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders_main_tab);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        // display fragment according to the button clicked
        int defaultPage = 0;
        int page = getIntent().getIntExtra("Page", defaultPage);
        mViewPager.setCurrentItem(page);

        FloatingActionButton myFab = (FloatingActionButton) this.findViewById(R.id.add_fab);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i;
                switch (mViewPager.getCurrentItem()) {
                    case 0:
                        i = new Intent(RemindersMainTab.this, EditMedicine.class);
                        i.putExtra("Position", -1);
                        startActivity(i);
                        break;
                    case 1:
                        i = new Intent(RemindersMainTab.this, EditHygiene.class);
                        i.putExtra("Position", -1);
                        startActivity(i);
                        break;
                    case 2:
                        i = new Intent(RemindersMainTab.this, EditExercise.class);
                        i.putExtra("Position", -1);
                        startActivity(i);
                        break;
                    default:
                        break;
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reminders_main_tab, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new MedicineTab();

                case 1:
                    return new HygieneTab();

                case 2:
                    return new ExerciseTab();

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        private String[] pageTitles = new String[] {"Medicine", "Hygiene", "Exercise"};
        @Override
        public CharSequence getPageTitle(int position) {
            return pageTitles[position];
        }
    }
}
