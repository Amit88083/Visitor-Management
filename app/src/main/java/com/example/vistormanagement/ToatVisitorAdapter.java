package com.example.vistormanagement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class ToatVisitorAdapter extends FirebaseRecyclerAdapter<model, ToatVisitorAdapter.myviewholder>
{
    public ToatVisitorAdapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder myviewholder, int i, @NonNull model model) {


        myviewholder.randId.setText(model.getRandId());
        myviewholder.name.setText(model.getName());
        myviewholder.phone.setText(model.getPhone());
        myviewholder.email.setText(model.getEmail());
        myviewholder.date.setText(model.getDate());
        myviewholder.time.setText(model.getTime());
        myviewholder.purpose.setText(model.getPurpose());

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow2,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{

        TextView name,randId,email,phone,date,time,purpose;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.nametext2);
            randId = (TextView)itemView.findViewById(R.id.randIdText2);
            email = (TextView)itemView.findViewById(R.id.emailtext2);
            phone = (TextView)itemView.findViewById(R.id.phoneno2);
            date = (TextView)itemView.findViewById(R.id.datetext2);
            time = (TextView)itemView.findViewById(R.id.timetext2);
            purpose = (TextView)itemView.findViewById(R.id.purposeText2);

        }
    }
}
