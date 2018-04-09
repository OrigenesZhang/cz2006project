package sg.edu.ntu.e.fang0074.ipet;

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


    //float rating = -1;
    RateDAO ratedao = LogIn.rateDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_comment);
        /*
        ratingBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating = ratingBar.getRating();
            }
        }); */


        Button submitrating = (Button)findViewById(R.id.submit_rating);
        submitrating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText reason = (EditText)findViewById(R.id.rating_reason);
                String reasontext;
                RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);

                //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                //Calendar cal = Calendar.getInstance();
                //System.out.println(dateFormat.format(cal)); //2016/11/16 12:08:43

                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
                System.out.println(timeStamp);

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
                }
            }
        });


    }


}
