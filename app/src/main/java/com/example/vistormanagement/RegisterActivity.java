package com.example.vistormanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    EditText usernameEditText,emailEditText,passwordEditText,phoneEditText;
    Button registrationButton;
    FirebaseAuth mAuth;
    TextView signupTextView;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        usernameEditText = findViewById(R.id.usernameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditTextLogin);
        phoneEditText = findViewById(R.id.phoneEditText);
        progressBar = findViewById(R.id.progressBar);
        signupTextView = findViewById(R.id.signupTextView);
        registrationButton = findViewById(R.id.registrationButton);

        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + getString(R.string.Add_Admin)+ "</font>"));



        if(mAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String username = usernameEditText.getText().toString().trim();
                String phone = phoneEditText.getText().toString().trim();

                if(TextUtils.isEmpty(username)){
                    usernameEditText.setError("Username is required....");
                    return;
                }

                if(TextUtils.isEmpty(email)){
                    emailEditText.setError("Email is required....");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    passwordEditText.setError("Password is required....");
                    return;
                }



                if(TextUtils.isEmpty(phone)){
                    phoneEditText.setError("Phone Number is required....");
                    return;
                }

                if(password.length()<6){
                    passwordEditText.setError("Password must be >=6 character.");
                    return;
                }

                if(phone.length() != 10){
                    phoneEditText.setError("Password must be of 10 character.");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                //Register the user in the firebase

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            //send email verification
                            FirebaseUser user = mAuth.getCurrentUser();
                            user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(RegisterActivity.this, "Verification Email has been sent...", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(RegisterActivity.this, "onFailure: Email is not sent "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });






                          //  Toast.makeText(RegisterActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }else{
                            Toast.makeText(RegisterActivity.this, "Error "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });

            }
        });
        signupTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));

            }
        });

    }
}