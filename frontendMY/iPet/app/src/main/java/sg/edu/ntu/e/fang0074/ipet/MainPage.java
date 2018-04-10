package sg.edu.ntu.e.fang0074.ipet;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import sg.edu.ntu.e.fang0074.ipet.controlclasses.LoginController;
import sg.edu.ntu.e.fang0074.ipet.controllers.Profile;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //startActivity(startIntent);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        // fix the orientation of the screen
        int orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        setRequestedOrientation(orientation);



        CardView logInGrid = (CardView)findViewById(R.id.profileGrid);
        logInGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            // Launch an activity within the app
            public void onClick(View view) {
                if(!LoginController.currentrole.equals("rep")){
                    Intent startIntent = new Intent(getApplicationContext(), PetList.class);
                    startActivity(startIntent);
                }
            }
        });

        CardView searchGrid = (CardView)findViewById(R.id.searchGrid);
        searchGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            // Launch an activity within the app
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), SearchPage.class);
                startActivity(startIntent);
            }
        });

        CardView rmGrid = (CardView)findViewById(R.id.reminderGrid);
        searchGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            // Launch an activity within the app
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), Profile.class);
                startActivity(startIntent);
            }
        });

        CardView contactGrid = (CardView)findViewById(R.id.contactGrid);
        contactGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            // Launch an activity within the app
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), ContactUs.class);
                startActivity(startIntent);
            }
        });
    }
}
