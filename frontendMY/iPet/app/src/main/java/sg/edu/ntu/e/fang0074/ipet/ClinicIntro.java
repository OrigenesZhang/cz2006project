package sg.edu.ntu.e.fang0074.ipet;

import android.content.Intent;
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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class ClinicIntro extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ZoomControls zoom;
    TextView mapClinicName;
    TextView mapClinicAddress;
    TextView mapClinicTel;
    TextView mapClnicRating;
    //private final static int MY_PERMISSION_FINE_LOCATION = 101;
    //private final static int PLACE_PICKER_REQUEST = 1;
    private final static LatLngBounds bounds = new LatLngBounds(new LatLng(51.5152192,-0.1321900), new LatLng(51.5166013,-0.1299262));

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

        Button ratingBtn = (Button) findViewById(R.id.rating_btn);
        ratingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), RateComment.class);
                startActivity(startIntent);
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

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        /*
        geoLocationBt = (Button) findViewById(R.id.btSearch);
        geoLocationBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText searchText = (EditText) findViewById(R.id.gmap_entry);
                String search = searchText.getText().toString();
                //System.out.println(search);
                if(search != null && !search.equals("")) {
                    //System.out.println("valid string");
                    List<Address> addressList = null;
                    Geocoder geocoder = new Geocoder(ClinicIntro.this);
                    //System.out.println("Going to try");
                    try {
                        addressList = geocoder.getFromLocationName(search, 2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(addressList);
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng).title("from geocoder"));
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                }
            }
        });*/

        GeoDataClient myGeoDataClient = Places.getGeoDataClient(this, null);


        myGeoDataClient.getPlaceById("ChIJa0VT3f0Z2jERB36JatD_MFM").addOnCompleteListener(new OnCompleteListener<PlaceBufferResponse>() {
            @Override
            public void onComplete(@NonNull Task<PlaceBufferResponse> task) {
                if (task.isSuccessful()) {
                    PlaceBufferResponse places = task.getResult();
                    Place myPlace = places.get(0);
                    //myPlace.getLatLng();
                    mMap.addMarker(new MarkerOptions().position(myPlace.getLatLng()).title("Marker in Target Clinic"));

                    mapClinicName.setText(myPlace.getName());
                    mapClinicAddress.setText(myPlace.getAddress());
                    mapClinicTel.setText(myPlace.getPhoneNumber());

                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPlace.getLatLng(), 14.0f));

                    //System.out.println("Place found: " + myPlace.getName());
                    places.release(); //IMPORTANT!! MUST FREE THE BUFFER
                } else {
                    //Log.e(TAG, "Place not found.");
                    Toast.makeText(ClinicIntro.this, "Clinic not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void createCommentList(){
        //System.out.println("Create list..");
        commentList.add(new CommentItem("First dog", "Good", "02/03/2018", "4.0/5.0"));
        commentList.add(new CommentItem("Third dog", "I like this clinic", "02/03/2018", "3.7/5.0"));
        commentList.add(new CommentItem("Fourth dog", "Excellent Service", "02/03/2018", "4.5/5.0"));
        commentList.add(new CommentItem("Fifth dog", "Very good.", "02/03/2018", "3.5/5.0"));
        commentList.add(new CommentItem("Sixth dog", "Recommended", "02/03/2018", "3.9/5.0"));
    }
}
