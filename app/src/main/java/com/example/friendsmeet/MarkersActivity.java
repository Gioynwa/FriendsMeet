package com.example.friendsmeet;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MarkersActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Button backButton;
    DatabaseReference databaseLocation, databaseMeeting;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    Meeting meetingGlobal;
    double lat;
    double lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_markers);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), Mymeetings.class));
                finish();
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

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        databaseMeeting = FirebaseDatabase.getInstance().getReference("Meetings");
        databaseLocation = FirebaseDatabase.getInstance().getReference("location");

        databaseMeeting.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Meeting meeting = dataSnapshot1.getValue(Meeting.class);
                    if(meeting.getMembers().contains(firebaseUser.getEmail())) {
                        meetingGlobal = meeting;
                        databaseLocation.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                    LocationHelper loc = dataSnapshot1.getValue(LocationHelper.class);
                                    if(meetingGlobal.getMembers().contains(loc.getEmail())) {
                                        lat = loc.getLatitude();
                                        lon = loc.getLongitude();
                                        System.out.println("WTF" + lat);
                                        System.out.println("WTF" + lon);
                                        LatLng meet = new LatLng((int)loc.getLatitude(), (int)loc.getLongitude());
                                        mMap.addMarker(new MarkerOptions().position(meet).title(loc.getEmail()));
                                        mMap.moveCamera(CameraUpdateFactory.newLatLng(meet));
                                    }
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
         //Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
