package sg.edu.ntu.e.fang0074.ipet;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

import sg.edu.ntu.e.fang0074.ipet.controlclasses.LoginController;
import sg.edu.ntu.e.fang0074.ipet.controlclasses.Rating;

public class ClinicIntro extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ZoomControls zoom;
    TextView mapClinicName;
    TextView mapClinicAddress;
    TextView mapClinicTel;
    TextView mapClnicRating;


    RecyclerView listshowrcy;
    List<CommentItem> commentList = new ArrayList<>();
    private RecyclerView.LayoutManager mLayoutManager;
    CommentAdapter adapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_intro);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // fix the orientation of the screen
        int orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        setRequestedOrientation(orientation);

        // Zoom Controls
        zoom = (ZoomControls)findViewById(R.id.zcZoom);
        zoom.setOnZoomOutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.animateCamera(CameraUpdateFactory.zoomOut());
            }
        });
        zoom.setOnZoomInClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.animateCamera(CameraUpdateFactory.zoomIn());
            }
        });


        // Get Clinic Information
        mapClinicName = (TextView) findViewById(R.id.map_clinic_name);
        mapClinicAddress = (TextView) findViewById(R.id.map_clinic_addr);
        mapClinicTel = (TextView) findViewById(R.id.map_clinic_tel);
        mapClnicRating = (TextView) findViewById(R.id.map_clinic_rating);

        createCommentList();
        listshowrcy = (RecyclerView)findViewById(R.id.comment_list);
        listshowrcy.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listshowrcy.setLayoutManager(linearLayoutManager);
        adapter = new CommentAdapter(commentList,ClinicIntro.this);
        listshowrcy.setAdapter(adapter);


        // direct to the rating page
        Button ratingBtn = (Button) findViewById(R.id.rating_btn);
        ratingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!LoginController.currentrole.equals("rep")) {
                    Intent startIntent = new Intent(getApplicationContext(), RateComment.class);
                    startActivity(startIntent);
                }
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        GeoDataClient myGeoDataClient = Places.getGeoDataClient(this, null);

        myGeoDataClient.getPlaceById(LogIn.clinicDAO.currentClinic.getLocationID()).addOnCompleteListener(new OnCompleteListener<PlaceBufferResponse>() {
            @Override
            public void onComplete(@NonNull Task<PlaceBufferResponse> task) {
                if (task.isSuccessful()) {
                    PlaceBufferResponse places = task.getResult();
                    Place myPlace = places.get(0);

                    mMap.addMarker(new MarkerOptions().position(myPlace.getLatLng()).title("Marker in Target Clinic"));
                    mapClinicName.setText(myPlace.getName());
                    mapClinicAddress.setText(myPlace.getAddress());
                    mapClinicTel.setText(myPlace.getPhoneNumber());
                    int currentClinicID = LogIn.clinicDAO.currentClinic.getClinicID();
                    mapClnicRating.setText(Double.toString(LogIn.rateDAO.getAvgRating(currentClinicID)) + " / 5.0");
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPlace.getLatLng(), 14.0f));
                    places.release(); //IMPORTANT!! MUST FREE THE BUFFER

                } else {
                    Toast.makeText(ClinicIntro.this, "Clinic not found on Map", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void createCommentList(){

        // Filter again to ensure correctness
        int currentClinicID = LogIn.clinicDAO.currentClinic.getClinicID();

        for(Rating rt : LogIn.rateDAO.ratingByClinic){
            if(rt.getClinicID() == currentClinicID){
                commentList.add(new CommentItem(rt.getUsername(), rt.getComment(), rt.getDate(), Double.toString(rt.getScore())+" / 5.0"));
            }
        }

    }

}
