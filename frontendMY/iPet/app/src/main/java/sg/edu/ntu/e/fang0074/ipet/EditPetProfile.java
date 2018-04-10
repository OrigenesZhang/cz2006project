package sg.edu.ntu.e.fang0074.ipet;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import sg.edu.ntu.e.fang0074.ipet.controlclasses.PetDAO;

public class EditPetProfile extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pet_profile);

        final PetDAO petdao = LogIn.petDAO;
        // fix the orientation of the screen
        int orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        setRequestedOrientation(orientation);

        // Set the static owner info on the pet profile page
        TextView ownername = (TextView)findViewById(R.id.edit_profile_owner);
        TextView ownerphone = (TextView)findViewById(R.id.edit_profile_owner_tel);
        ownername.setText("Owner:  " + LogIn.userDAO.currentUser.getUserName());
        ownerphone.setText("Owner Tel:  " + LogIn.userDAO.currentUser.getPhone());

        // Edit and update the pet profile
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

                String newname = editName.getText().toString();
                petdao.updateCurrPetName(newname);
                String newbreed = editBreed.getText().toString();
                petdao.updateCurrPetBreed(newbreed);
                String newlocation = editLocation.getText().toString();
                petdao.updateCurrPetLocation(newlocation);
                String newage = editAge.getText().toString();
                petdao.updateCurrPetAge(newage);
                String newweight = editWeight.getText().toString();
                petdao.updateCurrPetWeight(newweight);
                String newgender = editGender.getText().toString();
                petdao.updateCurrPetGender(newgender);

                Intent startIntent = new Intent(getApplicationContext(), Profile.class);
                startActivity(startIntent);
            }
        });


        // Set the textboxes to empty once the editting is cancelled
        Button cancelEdit = (Button)findViewById(R.id.cancelEdit);
        cancelEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editName = (EditText)findViewById(R.id.editName);
                EditText editBreed = (EditText) findViewById(R.id.editBreed);
                EditText editLocation = (EditText) findViewById(R.id.editLocation);
                EditText editAge = (EditText) findViewById(R.id.ageBox);
                EditText editWeight = (EditText) findViewById(R.id.weightBox);
                EditText editGender = (EditText) findViewById(R.id.genderBox);

                editName.setText("");
                editBreed.setText("");
                editLocation.setText("");
                editAge.setText("");
                editWeight.setText("");
                editGender.setText("");
            }

        });
    }

}
