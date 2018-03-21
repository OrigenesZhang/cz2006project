package sg.edu.ntu.e.fang0074.ipet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Intent startIntent = new Intent(getApplicationContext(), LogIn.class);
        //startActivity(startIntent);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        CardView logInGrid = (CardView)findViewById(R.id.profileGrid);
        logInGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            // Launch an activity within the app
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), Profile.class);
                startActivity(startIntent);
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
    }
}
