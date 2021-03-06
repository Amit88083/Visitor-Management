package com.example.vistormanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;




import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class byUser extends AppCompatActivity {
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    int t1Hour,t1Minute;
    DatePickerDialog.OnDateSetListener setListener;


    EditText nameEditTextVisitor,emailEditTextVisitor,phoneEditTextVisitor,purposeEditTextVisitor,periodEditTextVisitor,timeEditTextVisitor,dateEditTextVisitor;
    Button saveVisitorDetailsButton;

   // private FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_user);


        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + getString(R.string.requested_title)+ "</font>"));
        //setTitle("hello");
        saveVisitorDetailsButton = findViewById(R.id.saveVisitorDetailsButton);
        emailEditTextVisitor = findViewById(R.id.emailEditTextVisitor);
        phoneEditTextVisitor = findViewById(R.id.phoneEditTextVisitor);
        nameEditTextVisitor = findViewById(R.id.nameEditTextVisitor);
        purposeEditTextVisitor = findViewById(R.id.purposeEditTextVisitor);
        periodEditTextVisitor = findViewById(R.id.periodEditTextVisitor);
        timeEditTextVisitor = findViewById(R.id.timeEditTextVisitor);
        dateEditTextVisitor = findViewById(R.id.dateEditTextVisitor);

/*
        saveVisitorDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditTextVisitor.getText().toString();
                String email = emailEditTextVisitor.getText().toString();
                String phone = phoneEditTextVisitor.getText().toString();
                String purpose = purposeEditTextVisitor.getText().toString();
                String period = periodEditTextVisitor.getText().toString();
                String date = dateEditTextVisitor.getText().toString();
                String time = timeEditTextVisitor.getText().toString();


                int i = new Random().nextInt(900000) + 100000;
                String randId = String.valueOf(i);


                Map<String, Object> visitor = new HashMap<>();
                visitor.put("randId",randId);
                visitor.put("name", name);
                visitor.put("email", email);
                visitor.put("phone", phone);
                visitor.put("purpose", purpose);
                visitor.put("period", period);
                visitor.put("date", date);
                visitor.put("time", time);

                db.collection("visitor").document().set(visitor)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(byUser.this, "visitor added..", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(byUser.this, "Error!", Toast.LENGTH_SHORT).show();
                               // Log.w(TAG, "Error adding document", e);
                            }
                        });

            }
        });


*/

        //////different part......................




        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        dateEditTextVisitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        byUser.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        dateEditTextVisitor.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();

            }
        });






        timeEditTextVisitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        byUser.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                t1Hour = hourOfDay;
                                t1Minute = minute;
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(0,0,0,t1Hour,t1Minute);
                                timeEditTextVisitor.setText(DateFormat.format("hh:mm aa",calendar));
                            }
                        },12,0,false
                );
                timePickerDialog.updateTime(t1Hour,t1Minute);
                timePickerDialog.show();
            }
        });

        saveVisitorDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("RequestedVisitor");

                String name = nameEditTextVisitor.getText().toString();
                String email = emailEditTextVisitor.getText().toString();
                String phone = phoneEditTextVisitor.getText().toString();
                String purpose = purposeEditTextVisitor.getText().toString();
                String period = periodEditTextVisitor.getText().toString();
                String date = dateEditTextVisitor.getText().toString();
                String time = timeEditTextVisitor.getText().toString();




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

                if(TextUtils.isEmpty(date)){
                    dateEditTextVisitor.setError("date of stay is required....");
                    return;
                }

                if(TextUtils.isEmpty(time)){
                    timeEditTextVisitor.setError("time of stay is required....");
                    return;
                }

                if(phone.length() != 10){
                    phoneEditTextVisitor.setError("Password must be of 10 character.");
                    return;
                }

//               HashMap<String,String> map = new HashMap<>();
//                map.put("Name",name);
//                map.put("Email",email);
//                map.put("Contact no.",phone);
//                map.put("Purpose Of Stay",purpose);
//                map.put("Period Of Stay",period);
//
//                reference.child(phone).setValue(map);
                int i = new Random().nextInt(900000) + 100000;
                String randId = String.valueOf(i);



                DatabaseReference ref=FirebaseDatabase.getInstance().getReference("RequestedVisitor");
                ref.orderByChild("randId").equalTo(randId).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            Toast.makeText(byUser.this, " user id exists....", Toast.LENGTH_SHORT).show();

                        }
                        else{
                            VisitorDetails visitorDetails = new VisitorDetails(randId,name,email,phone,purpose,period,date,time);

                            reference.child(randId).setValue(visitorDetails);


                            //email sending
                            final String username = "visitormanagementdepartment@gmail.com";
                            final String password = "Qwerty@123";

                            Properties props = new Properties();
                            props.put("mail.smtp.auth","true");
                            props.put("mail.smtp.starttls.enable","true");
                            props.put("mail.smtp.host","smtp.gmail.com");
                            props.put("mail.smtp.port","587");

                            Session session = Session.getInstance(props,
                                    new javax.mail.Authenticator(){
                                        @Override
                                        protected PasswordAuthentication getPasswordAuthentication() {
                                            return new PasswordAuthentication(username,password);
                                        }
                                    });

                            try {
                                Message message = new MimeMessage(session);
                                message.setFrom(new InternetAddress(username));
                                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailEditTextVisitor.getText().toString()));
                                message.setSubject("Visitor e-pass IIITA (confirmation-Pending)");
                                message.setText("Welcome "+name+" here is your visitor e-pass Id and Phone no."+"\n\n"+"ID                 : "+randId+"\n"+"Phone no.   : "+phone+"\n\n"+
                                        "At the time of entry you have to verify your e-visitor pass, then after you will allow in the campus.\nPlease make sure entering in the campus, without confirmation of e-pass is violation of rules and regulation of institute."+"\n\n"+"Thank you."+"\n\n\n"+"Visitor Management department"+"\n"+"Indian Institute of Information Technology,Allahabad"+
                                        "\n"+"Deoghat,Jhalwa,Prayagraj(U.P)-211015.");
                                Transport.send(message);
                                Toast.makeText(byUser.this, "sending e-pass..", Toast.LENGTH_SHORT).show();
                            }catch (MessagingException e)
                            {
                                throw new RuntimeException();
                            }


                            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


    }
}