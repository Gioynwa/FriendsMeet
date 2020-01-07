package com.example.friendsmeet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView userEmail;
    Button userLogOutButton;
    Button myMeetingsButton;
    Button newMeetingButton;
    Button contactsButton;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userEmail = findViewById(R.id.currentAccount);
        userLogOutButton = findViewById(R.id.LogOutButton);
        myMeetingsButton = findViewById(R.id.myMeetingsBtn);
        newMeetingButton = findViewById(R.id.newMeetingBtn);
        contactsButton = findViewById(R.id.contactsBtn);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        userEmail.setText(firebaseUser.getEmail());

        userLogOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                FirebaseAuth.getInstance().signOut();//logout
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });

        newMeetingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), Newmeeting.class));
                finish();
            }
        });

        myMeetingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), Mymeetings.class));
                finish();
            }
        });

        contactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), Contacts.class));
                finish();
            }
        });
    }

}
