package com.example.vistormanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.text.HtmlCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStructure;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
   /* Button addVisitorButton;
    Button viewAllVisitorButton;

    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        finish();
    }
*/
    ImageView addVisitorsImg,viewVisitorsImg,searchDeleteImg,logoutImg,addAdminImg;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.iconmenu,menu);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_home_244);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId())
        {
            case R.id.menu_home:
                Toast.makeText(this, "home panel", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.menu_addVisitor:
                Toast.makeText(this, "add Visitor panel", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),AddVisitorAcitivity.class));
                return true;

            case R.id.menu_viewVisitor:
                Toast.makeText(this, "view Visitor panel", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),viewVisitorActivity.class));
                return true;

            case R.id.menu_logout:
                Toast.makeText(this, "Signed Out", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
                return true;

            case R.id.menu_addAdmin:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
                finish();
                return true;


            default:
                Toast.makeText(this, "home panel", Toast.LENGTH_SHORT).show();
                return false;


        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addVisitorsImg = findViewById(R.id.addVisitorsImg);
        viewVisitorsImg = findViewById(R.id.viewVisitorsImg);
        searchDeleteImg = findViewById(R.id.searchDeleteImg);
        logoutImg = findViewById(R.id.logoutImg);
        addAdminImg = findViewById(R.id.addAdminImg);

        addVisitorsImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddVisitorAcitivity.class));
            }
        });


        viewVisitorsImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),viewVisitorActivity.class));
            }
        });

        searchDeleteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),viewVisitorActivity.class));
            }
        });

        logoutImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
            }
        });

        addAdminImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
                finish();
            }
        });

     /*
        addVisitorButton = findViewById(R.id.addVisitorButton);
        viewAllVisitorButton = findViewById(R.id.viewAllVisitorButton);

        viewAllVisitorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),viewVisitorActivity.class));
            }
        });

        addVisitorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddVisitorAcitivity.class));
            }
        });*/

    }
}