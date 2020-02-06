package com.example.friendsmeet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddMembers extends AppCompatActivity {

    ArrayList<String> selectedItems = new ArrayList<>();
    ArrayList<String> databaseItems = new ArrayList<>();
    Button back;
    DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_members);

        back = findViewById(R.id.backButton);
        databaseUsers = FirebaseDatabase.getInstance().getReference("users");

        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                databaseItems.clear();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    User user = dataSnapshot1.getValue(User.class);
                    databaseItems.add(user.getEmail());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), Newmeeting.class));
                finish();
            }
        });
    }

    public void showSelectedItems(View view) {
        String items = "";
        for(String item:selectedItems) {
            items += "-"+item+"\n";
        }
        Toast.makeText(this, "You have selected \n"+items, Toast.LENGTH_LONG).show();

        //###############################pass the string array in Newmeeting activity######################################


        String value = items;

        Intent i = new Intent(AddMembers.this, Newmeeting.class);
        i.putExtra("key", value);
        startActivity(i);
    }

    public void showDatabaseItems(View view) {

        String items[] = new String[databaseItems.size()];

        ListView ch1 = findViewById(R.id.checkable_list);
        ch1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        // ArrayList to Array Conversion
        for (int j = 0; j < databaseItems.size(); j++) {

            // Assign each value to String array
            items[j] = databaseItems.get(j);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.rowlayout, R.id.txt_mem, items);
        ch1.setAdapter(adapter);

        ch1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = ((TextView)view).getText().toString();
                if(selectedItems.contains(selectedItem)) {
                    selectedItems.remove(selectedItem);
                }
                else {
                    selectedItems.add(selectedItem);
                }
            }
        });

    }


}
