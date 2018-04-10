package sg.edu.ntu.e.fang0074.ipet;


import java.util.ArrayList;
import java.util.List;

import sg.edu.ntu.e.fang0074.ipet.controlclasses.Clinic;

import static sg.edu.ntu.e.fang0074.ipet.R.color;
import static sg.edu.ntu.e.fang0074.ipet.R.drawable;
import static sg.edu.ntu.e.fang0074.ipet.R.id;
import static sg.edu.ntu.e.fang0074.ipet.R.layout;
import static sg.edu.ntu.e.fang0074.ipet.R.string;

public class SearchPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView listshowrcy;
    List<ClinicItem> clinicList = new ArrayList<>();
    private RecyclerView.LayoutManager mLayoutManager;
    SearchView searchView;
    SearchActivityAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_search_page);
        Toolbar toolbar = (Toolbar) findViewById(id.toolbar);
        setSupportActionBar(toolbar);
        int orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        setRequestedOrientation(orientation);

        FloatingActionButton fab = (FloatingActionButton) findViewById(id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, string.navigation_drawer_open, string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        createClinicList();

        // Set up the clinic search list
        listshowrcy = (RecyclerView)findViewById(id.clinic_list);
        listshowrcy.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listshowrcy.setLayoutManager(linearLayoutManager);
        adapter = new SearchActivityAdapter(clinicList,SearchPage.this);
        listshowrcy.setAdapter(adapter);
    }


    private void createClinicList(){
        //TODO: handle phone, rating and image
        /* Testing */////////////////////////////////////////////////////////////////////

        ArrayList<Clinic> clinics =  LogIn.clinicDAO.getAllClinics();
        /*
        Clinic new1 = new Clinic(1,0, "Happy Dog", "12345");
        clinics.add(new1);
        Clinic new2 = new Clinic(1,0, "Happy Cat", "12345");
        clinics.add(new2);
        Clinic new3 = new Clinic(1,0, "Happy Fish", "12345");
        clinics.add(new3);
        */

        for(Clinic cn : clinics){
            double avgRating = LogIn.rateDAO.getAvgRating(cn.getClinicID());
            clinicList.add(new ClinicItem(cn.getClinicName(), drawable.clinic_logo, "Singapore", Double.toString(avgRating)));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.log_in, menu);
        getMenuInflater().inflate(R.menu.searchfile, menu);
        final MenuItem myActionMenuItem = menu.findItem(id.search);
        searchView = (SearchView) myActionMenuItem.getActionView();
        changeSearchViewTextColor(searchView);
        ((EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)).setHintTextColor(getColor(color.white));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!searchView.isIconified()){
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final List<ClinicItem> filtermodelist = filter(clinicList, newText);
                adapter.setfilter(filtermodelist);
                return true;
            }
        });
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
            Intent startIntent = new Intent(getApplicationContext(), PetList.class);
            startActivity(startIntent);
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

    // Filter method: find clinic names that contain the query word.
    private List<ClinicItem> filter(List<ClinicItem> cList, String query){
        query = query.toLowerCase();
        final List<ClinicItem> filteredModeList = new ArrayList<>();

        for(ClinicItem vetClinic:cList){
            if(vetClinic.getName().toLowerCase().contains(query)){
                filteredModeList.add(vetClinic); }
        }
        return filteredModeList;
    }

    // for changing the color of the search view
    private void changeSearchViewTextColor(View view){
        if(view != null){
            if(view instanceof TextView){
                ((TextView) view).setTextColor(Color.WHITE);
                return;
            }else if(view instanceof ViewGroup){
                ViewGroup viewGroup = (ViewGroup) view;
                for(int i = 0; i<viewGroup.getChildCount(); i++){
                    changeSearchViewTextColor(viewGroup.getChildAt(i));
                }
            }
        }
    }
}