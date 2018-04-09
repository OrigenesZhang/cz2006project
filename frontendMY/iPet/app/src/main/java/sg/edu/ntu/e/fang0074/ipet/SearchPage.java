package sg.edu.ntu.e.fang0074.ipet;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sg.edu.ntu.e.fang0074.ipet.controlclasses.Clinic;

import static sg.edu.ntu.e.fang0074.ipet.R.color;
import static sg.edu.ntu.e.fang0074.ipet.R.drawable;
import static sg.edu.ntu.e.fang0074.ipet.R.id;
import static sg.edu.ntu.e.fang0074.ipet.R.layout;
import static sg.edu.ntu.e.fang0074.ipet.R.string;

public class SearchPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView listshowrcy;
    List<ClinicItem> clinicList = new ArrayList<>();
    private RecyclerView.LayoutManager mLayoutManager;
    SearchView searchView;
    SearchActivityAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_search_page);
        Toolbar toolbar = (Toolbar) findViewById(id.toolbar);
        setSupportActionBar(toolbar);

        int orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        setRequestedOrientation(orientation);

        FloatingActionButton fab = (FloatingActionButton) findViewById(id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, string.navigation_drawer_open, string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        createClinicList();

        // Set up the clinic search list
        listshowrcy = (RecyclerView)findViewById(id.clinic_list);
        listshowrcy.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listshowrcy.setLayoutManager(linearLayoutManager);
        adapter = new SearchActivityAdapter(clinicList,SearchPage.this);

        listshowrcy.setAdapter(adapter);
    }


    private void createClinicList(){
        //System.out.println("Create list..");

        //TODO: handle phone, rating and image
        /* Testing */////////////////////////////////////////////////////////////////////
        ArrayList<Clinic> clinics =  LogIn.clinicDAO.getAllClinics();
        Clinic new1 = new Clinic(1,0, "Happy Dog", "12345");
        clinics.add(new1);
        Clinic new2 = new Clinic(1,0, "Happy Cat", "12345");
        clinics.add(new2);
        Clinic new3 = new Clinic(1,0, "Happy Fish", "12345");
        clinics.add(new3);

        for(Clinic cn : clinics){
            clinicList.add(new ClinicItem(cn.getClinicName(), drawable.clinic_logo, "Singapore", "5.0"));
        }

        /*Testing*/ /////////////////////////////////////////////////////////////////////
        /*
        clinicList.add(new ClinicItem("Third dog", drawable.dog3, 12345687, "3.7"));
        clinicList.add(new ClinicItem("Fourth dog", drawable.dog4, 12345876, "4.5"));
        clinicList.add(new ClinicItem("Fifth dog", drawable.dog5, 12354768, "3.5"));
        clinicList.add(new ClinicItem("Eleventh dog", drawable.dog11, 35234345, "3.6"));
        */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.log_in, menu);
        getMenuInflater().inflate(R.menu.searchfile, menu);
        final MenuItem myActionMenuItem = menu.findItem(id.search);
        searchView = (SearchView) myActionMenuItem.getActionView();
        changeSearchViewTextColor(searchView);
        ((EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)).setHintTextColor(getColor(color.white));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!searchView.isIconified()){
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final List<ClinicItem> filtermodelist = filter(clinicList, newText);
                adapter.setfilter(filtermodelist);
                return true;
            }
        });
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_profile) {
            // Handle the camera action
        } else if (id == R.id.nav_clinic) {
            Intent startIntent = new Intent(getApplicationContext(), SearchPage.class);
            startActivity(startIntent);
        } else if (id == R.id.nav_reminder) {

        } else if (id == R.id.nav_promo) {

        } else if (id == R.id.nav_tips) {

        } else if (id == R.id.nav_contacts) {

        } else if (id == R.id.nav_help) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Filter method: find clinic names that contain the query word.
    private List<ClinicItem> filter(List<ClinicItem> cList, String query){
        query = query.toLowerCase();
        final List<ClinicItem> filteredModeList = new ArrayList<>();

        for(ClinicItem vetClinic:cList){
            if(vetClinic.getName().toLowerCase().contains(query)){
                filteredModeList.add(vetClinic);
            }
        }
        /*
        for(ClinicItem model:pl){
            final String text = model.getName().toLowerCase();
            if(text.startsWith(query)){
                filteredModeList.add(model);
            }
        }*/
        return filteredModeList;
    }

    // for changing the color of the search view
    private void changeSearchViewTextColor(View view){
        if(view != null){
            if(view instanceof TextView){
                ((TextView) view).setTextColor(Color.WHITE);
                return;
            }else if(view instanceof ViewGroup){
                ViewGroup viewGroup = (ViewGroup) view;
                for(int i = 0; i<viewGroup.getChildCount(); i++){
                    changeSearchViewTextColor(viewGroup.getChildAt(i));
                }
            }
        }
    }
}
