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


public class Register extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText userFullName, userEmail, userPassword, userPhone;
    Button userRegisterButton;
    TextView userLoginButton;
    FirebaseAuth fAuth;
    ProgressBar bar;

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
}
