package sg.edu.ntu.e.fang0074.ipet;

import android.content.Intent;
import android.content.pm.ActivityInfo;
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
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import sg.edu.ntu.e.fang0074.ipet.controlclasses.Pet;

import static sg.edu.ntu.e.fang0074.ipet.R.id;
import static sg.edu.ntu.e.fang0074.ipet.R.layout;
import static sg.edu.ntu.e.fang0074.ipet.R.string;

public class PetList extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView listshowrcy;
    List<PetItem> petList = new ArrayList<>();
    PetAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_pet_list);
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
        if(drawer != null){
            drawer.addDrawerListener(toggle);
            toggle.syncState();
        }


        NavigationView navigationView = (NavigationView) findViewById(id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        createPetList();

        // Set up the pet list
        listshowrcy = (RecyclerView)findViewById(id.petlist);
        listshowrcy.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listshowrcy.setLayoutManager(linearLayoutManager);
        adapter = new PetAdapter(petList,PetList.this);

        listshowrcy.setAdapter(adapter);
    }


    private void createPetList(){
        //System.out.println("Create list..");

        //TODO: handle phone, rating and image
        /* Testing */////////////////////////////////////////////////////////////////////
        ArrayList<Pet> pets =  LogIn.petDAO.getAllPets();
        Pet new1 = new Pet("Ah Dog","test", "dog","2", "Singapore", "F", "12");
        pets.add(new1);
        Pet new2 = new Pet("Ah Cat","test", "cat","2", "Singapore", "F", "12");
        pets.add(new2);
        Pet new3 = new Pet("Ah Fish","test", "fish","2", "Singapore", "F", "12");
        pets.add(new3);


        for(Pet pt : pets){
            petList.add(new PetItem(pt.getPetName()));
        }

        /*Testing*/ /////////////////////////////////////////////////////////////////////
        /*
        clinicList.add(new ClinicItem("Third dog", drawable.dog3, 12345687, "3.7"));
        clinicList.add(new ClinicItem("Eleventh dog", drawable.dog11, 35234345, "3.6"));
        */
    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.log_in, menu);
        //getMenuInflater().inflate(R.menu.edit_profile, menu);
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

        if(id == R.id.edit){
            Intent startIntent = new Intent(getApplicationContext(), EditPetProfile.class);
            startActivity(startIntent);
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

}
