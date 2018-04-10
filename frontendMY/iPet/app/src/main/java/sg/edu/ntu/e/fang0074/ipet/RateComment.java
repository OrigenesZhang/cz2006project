package sg.edu.ntu.e.fang0074.ipet;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import sg.edu.ntu.e.fang0074.ipet.controlclasses.RateDAO;

public class RateComment extends AppCompatActivity {


    RateDAO ratedao = LogIn.rateDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_comment);

        // Checks performed upon submission
        Button submitrating = (Button)findViewById(R.id.submit_rating);
        submitrating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText reason = (EditText)findViewById(R.id.rating_reason);
                String reasontext;
                RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
                // Get the current date and time of the device for rating submission.
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

                // fix the orientation of the screen
                int orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                setRequestedOrientation(orientation);

                // Ensure both ratings and reasons are given upon submission
                reasontext = reason.getText().toString();
                if((ratingBar.getRating()== 0.0)&&(reasontext.isEmpty())){
                    Toast tst = Toast.makeText(RateComment.this,"Please enter your rating and comments.",Toast.LENGTH_SHORT);
                    tst.show();
                }
                else if(ratingBar.getRating() == 0.0){
                    Toast tst = Toast.makeText(RateComment.this,"Please give a rating.",Toast.LENGTH_SHORT);
                    tst.show();
                }
                else if(reasontext.isEmpty()){
                    Toast tst = Toast.makeText(RateComment.this,"Please give your reasons.",Toast.LENGTH_SHORT);
                    tst.show();
                }
                else{
                    Toast tst = Toast.makeText(RateComment.this,"Rating given: " + ratingBar.getRating() + ". Thank you for your feedback!",Toast.LENGTH_SHORT);
                    tst.show();
                    ratedao.addRating(ratingBar.getRating(), reasontext,timeStamp);
                    Intent startIntent = new Intent(getApplicationContext(), SearchPage.class);
                    startActivity(startIntent);
                }
            }
        });

    }

}
