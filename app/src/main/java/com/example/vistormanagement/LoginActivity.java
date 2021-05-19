package com.example.vistormanagement;

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

public class LoginActivity extends AppCompatActivity {
    EditText passwordEditTextLogin,emailEditTextLogin;
    TextView signupTextViewLogin;
    Button loginButton;
    ProgressBar progressBar2;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       passwordEditTextLogin = findViewById(R.id.passwordEditTextLogin);
        emailEditTextLogin = findViewById(R.id.emailEditTextLogin);
        progressBar2 = findViewById(R.id.progressBar2);
        mAuth = FirebaseAuth.getInstance();
        loginButton = findViewById(R.id.LoginButton);
       // signupTextViewLogin = findViewById(R.id.signupTextViewLogin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailEditTextLogin.getText().toString().trim();
                String password = passwordEditTextLogin.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    emailEditTextLogin.setError("Email is required....");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    passwordEditTextLogin.setError("Password is required....");
                    return;
                }

                if(password.length()<6){
                    passwordEditTextLogin.setError("Password must be >=6 character.");
                    return;
                }
                progressBar2.setVisibility(View.VISIBLE);

                //authenticate the users

                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            if(mAuth.getCurrentUser().isEmailVerified()){
                                Toast.makeText(LoginActivity.this, "Logged in successfull..", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            }
                        }else {
                            Toast.makeText(LoginActivity.this, "Error! "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar2.setVisibility(View.GONE);
                        }
                    }
                });


            }
        });

       /* signupTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });*/

    }
}