package com.example.friendsmeet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Mymeetings extends AppCompatActivity {

    DatabaseReference databaseReference;
    Button back, show;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    private List<Meeting> meetingList;
    private ListView listView;
    private NewCustomAdapter newCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mymeetings);

        databaseReference = FirebaseDatabase.getInstance().getReference("Meetings");

        back = findViewById(R.id.backButton);
        show = findViewById(R.id.showLoc);

        meetingList = new ArrayList();

        newCustomAdapter = new NewCustomAdapter(Mymeetings.this, meetingList);

        listView = findViewById(R.id.meetingListID);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), MarkersActivity.class));
                finish();
            }
        });


    }

    @Override
    protected void onStart() {

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                meetingList.clear();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Meeting meeting = dataSnapshot1.getValue(Meeting.class);
                    if(meeting.getMembers().contains(firebaseUser.getEmail())) {
                        meetingList.add(meeting);
                    }
                }

                listView.setAdapter(newCustomAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        super.onStart();

    }
}
