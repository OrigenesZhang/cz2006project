package sg.edu.ntu.e.fang0074.ipet;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import sg.edu.ntu.e.fang0074.ipet.controlclasses.ClinicDAO;
import sg.edu.ntu.e.fang0074.ipet.controlclasses.ClinicRepDAO;
import sg.edu.ntu.e.fang0074.ipet.controlclasses.ExeReminderDAO;
import sg.edu.ntu.e.fang0074.ipet.controlclasses.HygReminderDAO;
import sg.edu.ntu.e.fang0074.ipet.controlclasses.LoginController;
import sg.edu.ntu.e.fang0074.ipet.controlclasses.MedReminderDAO;
import sg.edu.ntu.e.fang0074.ipet.controlclasses.PetDAO;
import sg.edu.ntu.e.fang0074.ipet.controlclasses.PromotionDAO;
import sg.edu.ntu.e.fang0074.ipet.controlclasses.RateDAO;
import sg.edu.ntu.e.fang0074.ipet.controlclasses.TipsDAO;
import sg.edu.ntu.e.fang0074.ipet.controlclasses.UserDAO;

public class LogIn extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /* Initialize all control classes */
    static UserDAO userDAO = new UserDAO();
    static ClinicDAO clinicDAO = new ClinicDAO();

    // Subscriptions
    static PetDAO petDAO = new PetDAO(userDAO);
    static MedReminderDAO medRDAO = new MedReminderDAO(userDAO);
    static HygReminderDAO hygRDAO = new HygReminderDAO(userDAO);
    static ExeReminderDAO exeRDAO = new ExeReminderDAO(userDAO);
    static RateDAO rateDAO = new RateDAO(userDAO, clinicDAO);

    static ClinicRepDAO repDAO = new ClinicRepDAO(clinicDAO);
    static PromotionDAO promoDAO = new PromotionDAO(clinicDAO);
    static TipsDAO tipsDAO = new TipsDAO(clinicDAO);

    static LoginController logincontroller = new LoginController(clinicDAO);

    /* Initialize all control classes */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        /* test temp *////////////////////////////////////////////////////////////////////
        userDAO.addUser("Meiyi", "12345", "12345678");

        /* test temp *////////////////////////////////////////////////////////////////////


        // fix the orientation of the screen
        int orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        setRequestedOrientation(orientation);

        // floating button action
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        // set select action for the navigation view
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Login actions: verify
        Button loginButton = (Button)findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText username = (EditText)findViewById(R.id.username);
                EditText password = (EditText)findViewById(R.id.password);

                boolean verify = false;
                String user = username.getText().toString();
                String pwd = password.getText().toString();
                System.out.println(user);
                System.out.println(pwd);
                if(logincontroller.verify(user, pwd)){
                    verify = true;
                }
                System.out.println(verify);

                // Ensure that all the fields are filled in and the credentials keyed in are valid
                if(TextUtils.isEmpty(user)||(TextUtils.isEmpty(pwd))){
                    Toast tst = Toast.makeText(LogIn.this,"Login fields must be filled",Toast.LENGTH_SHORT);
                    tst.show();
                }else if(!verify){
                    Toast tst = Toast.makeText(LogIn.this,"Invalid username or password.",Toast.LENGTH_SHORT);
                    tst.show();
                }else {
                    Intent startIntent = new Intent(getApplicationContext(), MainPage.class);
                    startActivity(startIntent);
                }
            }
        });

        // direct to signup page
        TextView signUp = (TextView) findViewById(R.id.signup_link);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            // Launch an activity within the app
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(startIntent);
            }
        });

    }

    // Menu drawer actions
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

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            // Handle the camera action
        } else if (id == R.id.nav_clinic) {
            Intent startIntent = new Intent(getApplicationContext(), SearchPage.class);
            startActivity(startIntent);
        } else if (id == R.id.nav_reminder) {

        } else if (id == R.id.nav_promo) {

        } else if (id == R.id.nav_tips) {

        } else if (id == R.id.nav_contacts) {

        } else if (id == R.id.nav_help) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
}
