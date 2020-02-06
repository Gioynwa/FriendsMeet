package com.example.friendsmeet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Newmeeting extends AppCompatActivity {

    Button back;
    Button chooseLocation;
    Button addMember;
    Button verifyMembers;
    Button typeName;
    EditText meetingName;

    static String globalName = "man";
    static double latitudeGlobal = 0;
    static double longitudeGlobal = 0;
    static String members = "men";
    static String destination = "Nowhere";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newmeeting);

//        Bundle extras = getIntent().getExtras();
//        String value = "hi";
//        if (extras != null) {
//
//            value = extras.getString("key");
//            members = value;
//        }

        back = findViewById(R.id.backButton);
        chooseLocation = findViewById(R.id.chooseLocationButton);
        addMember = findViewById(R.id.addMemberButton);
        verifyMembers = findViewById(R.id.verifyMembers);
        typeName = findViewById(R.id.insertMeetingName);
        meetingName = findViewById(R.id.MeetingName);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        chooseLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ChooseLocation.class));
                finish();
            }
        });

        addMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddMembers.class));
                finish();
            }
        });

        verifyMembers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = getIntent().getExtras();
                String value = "hi";
                if (extras != null) {

                    value = extras.getString("key");
                    members = value;
                }
            }
        });

        typeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = meetingName.getText().toString().trim();

                if(TextUtils.isEmpty(name)) {
                    meetingName.setError("Meeting Name is Required.");
                    return;
                }
                else {
                    globalName = name;
                }

            }
        });
    }

    public void meetingDatabase(View view) {

        //################################retreive string array members from AddMember activity###########################

        Bundle extrasLocation = getIntent().getExtras();
        if (extrasLocation != null) {
            int number;
            number = extrasLocation.getInt("LocationKey");

            //Volos Paralia
            if(number == 1) {

                latitudeGlobal = 39.36103;
                longitudeGlobal = 22.94248;
                destination = "Volos Paralia";
            }

            //Athens Syntagma
            else if(number == 2) {

                latitudeGlobal = 37.971996112;
                longitudeGlobal = 23.73416373;
                destination = "Athens Syntagma";
            }

            //Thessaloniki Pyrgos
            else if(number == 3) {

                latitudeGlobal = 40.626527;
                longitudeGlobal = 22.948545;
                destination = "Thessaloniki Pyrgos";
            }
        }

        DatabaseReference databaseMeetings;
        databaseMeetings = FirebaseDatabase.getInstance().getReference("Meetings");
        String id = databaseMeetings.push().getKey();
        Meeting meeting = new Meeting(id, globalName, members, longitudeGlobal, latitudeGlobal, destination);
        databaseMeetings.child(id).setValue(meeting);

        Toast.makeText(this, "Meeting added in database", Toast.LENGTH_LONG).show();

    }
}
