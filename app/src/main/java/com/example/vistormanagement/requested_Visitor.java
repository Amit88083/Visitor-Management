package com.example.vistormanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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

        recview1 = (RecyclerView)findViewById(R.id.recview1);
        recview1.setLayoutManager(new LinearLayoutManager(this));

        FirestoreRecyclerOptions<model> options = new FirestoreRecyclerOptions.Builder<model>()
                .setQuery(FirebaseFirestore.getInstance()
                        .collection("visitor"), model.class)
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
}