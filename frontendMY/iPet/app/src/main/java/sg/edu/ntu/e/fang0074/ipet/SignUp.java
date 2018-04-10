package sg.edu.ntu.e.fang0074.ipet;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // fix the orientation of the screen
        int orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        setRequestedOrientation(orientation);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Sign Up Completion
        Button signupBtn = (Button)findViewById(R.id.signupButton);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText newusername = (EditText)findViewById(R.id.newusername);
                EditText newphone = (EditText)findViewById(R.id.new_user_phone);
                EditText password1 = (EditText)findViewById(R.id.newpassword1);
                EditText password2 = (EditText)findViewById(R.id.newpassword2);

                String newpassword;
                String usernew = newusername.getText().toString();
                String phonenew = newphone.getText().toString();
                String newpwd1 = password1.getText().toString();
                String newpwd2 = password2.getText().toString();
                CheckBox clinicrep = (CheckBox)findViewById(R.id.clinicVerify);
                EditText clinicname = (EditText)findViewById(R.id.editText3);
                String cname = clinicname.getText().toString();

                // Ensure all the fields have been filled upon registration
                if((TextUtils.isEmpty(usernew))||(TextUtils.isEmpty(phonenew))||(TextUtils.isEmpty(newpwd1))||(TextUtils.isEmpty(newpwd2))){
                    Toast tst = Toast.makeText(SignUp.this,"Please fill in the first four fields.",Toast.LENGTH_SHORT);
                    tst.show();
                }
                // Check the two passwords entered match
                else if(newpwd1.equals(newpwd2)){
                    newpassword = newpwd1;
                    // Check if user intends to register as clinic representative
                    if(clinicrep.isChecked()){
                        // Ensure the clinic name is entered for verification
                        if(!TextUtils.isEmpty(cname)){
                            // Add the new user to the clinic rep database
                            LogIn.repDAO.addRep(usernew, "", newpassword, phonenew, 0);
                            Toast tst = Toast.makeText(SignUp.this,"Signing up as clinic rep. Verification pending.",Toast.LENGTH_SHORT);
                            tst.show();
                        }
                        else{
                            Toast tst = Toast.makeText(SignUp.this,"Please enter a valid clinic name.",Toast.LENGTH_SHORT);
                            tst.show();
                        }
                    }
                    // Add the new user to the pet owner database
                    else{
                        LogIn.userDAO.addUser(usernew, newpassword,phonenew);
                        Toast tst = Toast.makeText(SignUp.this,"You have signed up as a pet owner!",Toast.LENGTH_SHORT);
                        tst.show();
                        //redirect to the login page
                        Intent startIntent = new Intent(getApplicationContext(), LogIn.class);
                        startActivity(startIntent);
                    }
                }
                else{
                    Toast tst = Toast.makeText(SignUp.this,"Passwords entered did not match.",Toast.LENGTH_SHORT);
                    tst.show();
                }
            }
        });
    }

}
