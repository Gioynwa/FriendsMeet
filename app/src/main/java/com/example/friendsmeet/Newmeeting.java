package com.example.friendsmeet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Newmeeting extends AppCompatActivity {

    Button back;
    Button chooseLocation;
    Button addMember;
    Button getID;
    Button typeName;
    EditText meetingName;

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
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
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

            }
        });
    }
}
