package com.example.vistormanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class requested_Visitor extends AppCompatActivity {

    RecyclerView recview1;
    requestedmyadapter requestedmyadapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requested__visitor);

        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + getString(R.string.requested_List)+ "</font>"));


        recview1 = (RecyclerView)findViewById(R.id.recview1);
        recview1.setLayoutManager(new LinearLayoutManager(this));

  /*      FirestoreRecyclerOptions<model> options = new FirestoreRecyclerOptions.Builder<model>()
                .setQuery(FirebaseFirestore.getInstance()
                        .collection("visitor"), model.class)
                .build();


        */

        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RequestedVisitor"), model.class)
                        .build();


        requestedmyadapter1 = new requestedmyadapter(options);
        recview1.setAdapter(requestedmyadapter1);

    }

    @Override
    protected void onStart() {
        super.onStart();
        requestedmyadapter1.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        requestedmyadapter1.stopListening();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView)item.getActionView();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                processearch(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                processearch(newText);

                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    private void processearch(String newText) {
        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RequestedVisitor").orderByChild("email").startAt(newText).endAt(newText+"\uf8ff"), model.class)
                        .build();

        requestedmyadapter1 = new requestedmyadapter(options);
        requestedmyadapter1.startListening();
        recview1.setAdapter(requestedmyadapter1);


    }
}