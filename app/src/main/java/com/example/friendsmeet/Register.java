package com.example.friendsmeet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Register extends AppCompatActivity {

    public static final String USER_NAME = "com.example.friendsmeet.name";
    public static final String USER_ID = "com.example.friendsmeet.id";

    public static final String TAG = "TAG";
    EditText userFullName, userEmail, userPassword, userPhone;
    Button userRegisterButton;
    TextView userLoginButton;
    FirebaseAuth fAuth;
    ProgressBar bar;
    DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userFullName = findViewById(R.id.FullName);
        userEmail = findViewById(R.id.Email);
        userPassword = findViewById(R.id.Password1);
        userPhone = findViewById(R.id.Phone);
        userRegisterButton = findViewById(R.id.RegisterButton);
        userLoginButton = findViewById(R.id.textView3);

        fAuth = FirebaseAuth.getInstance();
        bar = findViewById(R.id.progressBar);

        if(fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        userRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = userEmail.getText().toString().trim();
                String password = userPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)) {
                    userEmail.setError("Email is Required.");
                    return;
                }

                if(TextUtils.isEmpty(password)) {
                    userPassword.setError("Password is Required");
                    return;
                }

                if(password.length() < 6) {
                    userPassword.setError("Password must be >= 6 characters");
                    return;
                }

                bar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {

                            databaseUsers = FirebaseDatabase.getInstance().getReference("users");
                            addUser();

                            Toast.makeText(Register.this, "User Created.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else {
                            Toast.makeText(Register.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            bar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });

        userLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

    }

    private void addUser() {

        String name = userFullName.getText().toString();
        String email = userEmail.getText().toString();
        String password = userPassword.getText().toString();
        String phone = userPhone.getText().toString();

        if(!TextUtils.isEmpty(name)) {

            String id = databaseUsers.push().getKey();
            User user = new User(id, name, email, password, phone);

            databaseUsers.child(id).setValue(user);

            Toast.makeText(this, "User added in database", Toast.LENGTH_LONG).show();

        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }


    }

}
