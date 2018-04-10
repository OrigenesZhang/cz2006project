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
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import sg.edu.ntu.e.fang0074.ipet.controlclasses.PetDAO;

public class PetProfile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    // set current pet at the pet list selection
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        // fix the orientation of the screen
        int orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        setRequestedOrientation(orientation);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        PetDAO petdao = LogIn.petDAO;

        TextView petnametv = (TextView)findViewById(R.id.dogName);
        TextView petbreedtv = (TextView)findViewById(R.id.dogBreed);
        TextView petlocationtv = (TextView)findViewById(R.id.dogLocation);
        TextView petagetv = (TextView)findViewById(R.id.petAge);
        TextView petweighttv = (TextView)findViewById(R.id.weight);
        TextView petgendertv = (TextView)findViewById(R.id.fm);

        // initialize the profile contents upon loading
        petnametv.setText(petdao.getCurrentPet().getPetName());
        petbreedtv.setText(petdao.getCurrentPet().getBreed());
        petlocationtv.setText(petdao.getCurrentPet().getLocation());
        petagetv.setText(petdao.getCurrentPet().getAge());
        petweighttv.setText(petdao.getCurrentPet().getWeight());
        petgendertv.setText(petdao.getCurrentPet().getGender());

        TextView ownername = (TextView)findViewById(R.id.ownerName);
        TextView ownerphone = (TextView)findViewById(R.id.ownerPhoneNumber);
        ownername.setText("Owner:  " + LogIn.userDAO.currentUser.getUserName());
        ownerphone.setText("Owner Tel:  " + LogIn.userDAO.currentUser.getPhone());
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
        getMenuInflater().inflate(R.menu.edit_profile, menu);
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

        if (id == R.id.nav_petlist) {
            Intent startIntent = new Intent(getApplicationContext(), PetList.class);
            startActivity(startIntent);
        } else if (id == R.id.nav_home) {
            Intent startIntent = new Intent(getApplicationContext(), MainPage.class);
            startActivity(startIntent);
        }else if (id == R.id.nav_clinic) {
            Intent startIntent = new Intent(getApplicationContext(), SearchPage.class);
            startActivity(startIntent);
        } else if (id == R.id.nav_reminder) {

        } else if (id == R.id.nav_promo) {

        } else if (id == R.id.nav_tips) {

        } else if (id == R.id.nav_contacts) {
            Intent startIntent = new Intent(getApplicationContext(), ContactUs.class);
            startActivity(startIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
