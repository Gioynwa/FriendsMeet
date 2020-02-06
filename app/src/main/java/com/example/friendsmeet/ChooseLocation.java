package com.example.friendsmeet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ChooseLocation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_location);
    }

    public void chooseVolos(View view) {

        Toast.makeText(this, "You have selected Volos Paralia \n", Toast.LENGTH_LONG).show();
        int value = 1;
        Intent i = new Intent(ChooseLocation.this, Newmeeting.class);
        i.putExtra("LocationKey", value);
        startActivity(i);

    }

    public void chooseAthens(View view) {

        Toast.makeText(this, "You have selected Athens Syntagma \n", Toast.LENGTH_LONG).show();
        int value = 2;
        Intent i = new Intent(ChooseLocation.this, Newmeeting.class);
        i.putExtra("LocationKey", value);
        startActivity(i);

    }

    public void chooseThessaloniki(View view) {

        Toast.makeText(this, "You have selected Thessaloniki Pyrgos \n", Toast.LENGTH_LONG).show();
        int value = 3;
        Intent i = new Intent(ChooseLocation.this, Newmeeting.class);
        i.putExtra("LocationKey", value);
        startActivity(i);

    }
}
