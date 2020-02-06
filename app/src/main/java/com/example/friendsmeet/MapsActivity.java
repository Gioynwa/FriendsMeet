package com.example.friendsmeet;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LocationManager locationManager;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    Button mapBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //Back button
        mapBack = findViewById(R.id.backButton);
        mapBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }

        //check the network provider is enabled
        if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {

                    //######################################show my location and put a marker in the map###############################
                    double latitude = location.getLatitude();
                    double longtitude = location.getLongitude();
                    //instantiate the class, LatLng
                    LatLng latLng = new LatLng(latitude, longtitude);
                    //instantiate the class, Geocoder
                    Geocoder geocoder = new Geocoder(getApplicationContext());


                    //############################add new location into the database##############################
//                    firebaseAuth = FirebaseAuth.getInstance();
//                    firebaseUser = firebaseAuth.getCurrentUser();
//                    DatabaseReference databaseLocation = FirebaseDatabase.getInstance().getReference("Location");
//                    String id = databaseLocation.push().getKey();
//
//                    LocationHelper helper = new LocationHelper(id,
//                            location.getLongitude(),
//                            location.getLatitude(),
//                            firebaseUser.getEmail()
//                    );
//
//                    databaseLocation.child(id).setValue(helper);

                    //##########################update Location into the database#################################

//                    LocationHelper helper = new LocationHelper(
//                            location.getLongitude(),
//                            location.getLatitude(),
//                            firebaseUser.getEmail()
//                    );
//
//                    FirebaseDatabase.getInstance().getReference("Location").setValue(helper).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if(task.isSuccessful()) {
//                                Toast.makeText(MapsActivity.this, "Location Saved", Toast.LENGTH_SHORT);
//                            }
//                            else {
//                                Toast.makeText(MapsActivity.this, "Location Not Saved", Toast.LENGTH_SHORT);
//                            }
//                        }
//                    });

                    try {
                        List<Address> addressList = geocoder.getFromLocation(latitude, longtitude, 1);
                        String str = addressList.get(0).getLocality();
                        str += addressList.get(0).getCountryName();
                        mMap.addMarker(new MarkerOptions().position(latLng).title(str));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10.2f));
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });
        }
        else if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double latitude = location.getLatitude();
                    double longtitude = location.getLongitude();
                    //instantiate the class, LatLng
                    LatLng latLng = new LatLng(latitude, longtitude);
                    //instantiate the class, Geocoder
                    Geocoder geocoder = new Geocoder(getApplicationContext());

                    firebaseAuth = FirebaseAuth.getInstance();
                    firebaseUser = firebaseAuth.getCurrentUser();

                    DatabaseReference databaseLocation = FirebaseDatabase.getInstance().getReference("Location");
                    String id = databaseLocation.push().getKey();

                    LocationHelper helper = new LocationHelper(id,
                            location.getLongitude(),
                            location.getLatitude(),
                            firebaseUser.getEmail()
                    );

                    databaseLocation.child(id).setValue(helper);

//                    FirebaseDatabase.getInstance().getReference("Location").setValue(helper).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if(task.isSuccessful()) {
//                                Toast.makeText(MapsActivity.this, "Location Saved", Toast.LENGTH_SHORT);
//                            }
//                            else {
//                                Toast.makeText(MapsActivity.this, "Location Not Saved", Toast.LENGTH_SHORT);
//                            }
//                        }
//                    });

                    try {
                        List<Address> addressList = geocoder.getFromLocation(latitude, longtitude, 1);
                        String str = addressList.get(0).getLocality() + ",";
                        str += addressList.get(0).getCountryName();
                        mMap.addMarker(new MarkerOptions().position(latLng).title(str));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10.2f));
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });
        }
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
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10.2f));

    }
}