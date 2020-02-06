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
    Button getID;
    Button typeName;
    EditText meetingName;

    static String globalName = "man";
    static double latitudeGlobal = 0;
    static double longitudeGlobal = 0;
    static String members = "men";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newmeeting);

        back = findViewById(R.id.backButton);
        chooseLocation = findViewById(R.id.chooseLocationButton);
        addMember = findViewById(R.id.addMemberButton);
        getID = findViewById(R.id.getMeetingId);
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

        getID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddMembers.class));
                finish();
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
        Bundle extras = getIntent().getExtras();
        String value = "hi";
        if (extras != null) {

            value = extras.getString("key");
            members = value;
        }

        Bundle extrasLocation = getIntent().getExtras();
        if (extrasLocation != null) {
            int number;
            number = extras.getInt("LocationKey");

            //Volos Paralia
            if(number == 1) {

                latitudeGlobal = 39.36103;
                longitudeGlobal = 22.94248;
            }

            //Athens Syntagma
            else if(number == 2) {

                latitudeGlobal = 37.971996112;
                longitudeGlobal = 23.73416373;
            }

            //Thessaloniki Pyrgos
            else if(number == 3) {

                latitudeGlobal = 40.626527;
                longitudeGlobal = 22.948545;
            }
        }

        DatabaseReference databaseMeetings;
        databaseMeetings = FirebaseDatabase.getInstance().getReference("Meetings");
        String id = databaseMeetings.push().getKey();
        Meeting meeting = new Meeting(id, globalName, members, longitudeGlobal, latitudeGlobal);
        databaseMeetings.child(id).setValue(meeting);

        Toast.makeText(this, "Meeting added in database", Toast.LENGTH_LONG).show();

    }
}
