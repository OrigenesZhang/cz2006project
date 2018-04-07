package sg.edu.ntu.e.fang0074.ipet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Button saveEdit = (Button) findViewById(R.id.saveEdit);
        saveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editName = (EditText)findViewById(R.id.editName);
                EditText editBreed = (EditText) findViewById(R.id.editBreed);
                EditText editLocation = (EditText) findViewById(R.id.editLocation);
                EditText editAge = (EditText) findViewById(R.id.ageBox);
                EditText editWeight = (EditText) findViewById(R.id.weightBox);
                EditText editGender = (EditText) findViewById(R.id.genderBox);


            }
        });
    }

}
