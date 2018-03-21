package sg.edu.ntu.e.fang0074.ipet;

import android.content.Intent;

/**
 * Created by user on 17/3/2018.
 */

public class NavigationOptions {

    NavigationOptions(){}


    public boolean navOptions(int id){
        // Handle navigation view item clicks here.
        MainPage mp = new MainPage();

        if (id == R.id.nav_profile) {
            // Handle the camera action
        } else if (id == R.id.nav_clinic) {
            Intent startIntent = new Intent(mp.getApplicationContext(), SearchPage.class);
            mp.startActivity(startIntent);
        } else if (id == R.id.nav_reminder) {

        } else if (id == R.id.nav_promo) {

        } else if (id == R.id.nav_tips) {

        } else if (id == R.id.nav_contacts) {

        } else if (id == R.id.nav_help) {

        }
        return true;

    }
}
