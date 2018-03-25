package sg.edu.ntu.e.fang0074.ipet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RatingBar;

public class RateComment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_comment);

        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);

    }
}
