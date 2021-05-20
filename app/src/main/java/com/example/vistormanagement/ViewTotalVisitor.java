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

public class ViewTotalVisitor extends AppCompatActivity {

    RecyclerView recview2;
    ToatVisitorAdapter Totaladapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_total_visitor);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + getString(R.string.Total_Visitor)+ "</font>"));

        recview2 = findViewById(R.id.recview2);
        recview2.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("TotalVisitors"), model.class)
                        .build();

        Totaladapter = new ToatVisitorAdapter(options);
        recview2.setAdapter(Totaladapter);

    }
    @Override
    protected void onStart() {
        super.onStart();
        Totaladapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        Totaladapter.stopListening();
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
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("TotalVisitors").orderByChild("date").startAt(newText).endAt(newText+"\uf8ff"), model.class)
                        .build();

        Totaladapter = new ToatVisitorAdapter(options);
        Totaladapter.startListening();
        recview2.setAdapter(Totaladapter);


    }

}