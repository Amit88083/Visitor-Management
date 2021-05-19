package com.example.vistormanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Random;

public class AddVisitorAcitivity extends AppCompatActivity {
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    EditText nameEditTextVisitor,emailEditTextVisitor,phoneEditTextVisitor,purposeEditTextVisitor,periodEditTextVisitor;
    Button saveVisitorDetailsButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_visitor_acitivity);

        saveVisitorDetailsButton = findViewById(R.id.saveVisitorDetailsButton);
        emailEditTextVisitor = findViewById(R.id.emailEditTextVisitor);
        phoneEditTextVisitor = findViewById(R.id.phoneEditTextVisitor);
        nameEditTextVisitor = findViewById(R.id.nameEditTextVisitor);
        purposeEditTextVisitor = findViewById(R.id.purposeEditTextVisitor);
        periodEditTextVisitor = findViewById(R.id.periodEditTextVisitor);

        saveVisitorDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Visitors");

                String name = nameEditTextVisitor.getText().toString();
                String email = emailEditTextVisitor.getText().toString();
                String phone = phoneEditTextVisitor.getText().toString();
                String purpose = purposeEditTextVisitor.getText().toString();
                String period = periodEditTextVisitor.getText().toString();




                if(TextUtils.isEmpty(name)){
                    nameEditTextVisitor.setError("Username is required....");
                    return;
                }

                if(TextUtils.isEmpty(email)){
                    emailEditTextVisitor.setError("Email is required....");
                    return;
                }

                if(TextUtils.isEmpty(phone)){
                    phoneEditTextVisitor.setError("Contact no. is required....");
                    return;
                }

                if(TextUtils.isEmpty(purpose)){
                    purposeEditTextVisitor.setError("Purpose of visit is required....");
                    return;
                }

                if(TextUtils.isEmpty(period)){
                    periodEditTextVisitor.setError("period of stay is required....");
                    return;
                }

              /* HashMap<String,String> map = new HashMap<>();
                map.put("Name",name);
                map.put("Email",email);
                map.put("Contact no.",phone);
                map.put("Purpose Of Stay",purpose);
                map.put("Period Of Stay",period);

                reference.child(phone).setValue(map);*/
                int i = new Random().nextInt(900000) + 100000;
                String randId = String.valueOf(i);

               VisitorDetails visitorDetails = new VisitorDetails(randId,name,email,phone,purpose,period);

               reference.child(randId).setValue(visitorDetails);

                startActivity(new Intent(getApplicationContext(),MainActivity.class));

                





            }
        });


    }
}