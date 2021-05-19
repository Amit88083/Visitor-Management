package com.example.vistormanagement;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;


public class myadapter extends FirebaseRecyclerAdapter<model,myadapter.myviewholder>
{
    public myadapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder myviewholder, int i, @NonNull model model) {
        myviewholder.randId.setText(model.getRandId());
        myviewholder.name.setText(model.getName());
        myviewholder.phone.setText(model.getPhone());
        myviewholder.email.setText(model.getEmail());


        myviewholder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DialogPlus dialogPlus = DialogPlus.newDialog(myviewholder.name.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                        .setExpanded(true,700)
                        .create();

                View myview = dialogPlus.getHolderView();

                EditText name = myview.findViewById(R.id.nameUp);
                EditText phone = myview.findViewById(R.id.phoneUp);
                EditText purpose = myview.findViewById(R.id.purposeUp);
                EditText period = myview.findViewById(R.id.periodUp);
                EditText email = myview.findViewById(R.id.emailUp);



                Button submit=myview.findViewById(R.id.usubmit);

                name.setText(model.getName());
                phone.setText(model.getPhone());
                purpose.setText(model.getPurpose());
                period.setText(model.getPeriod());
                email.setText(model.getEmail());


                dialogPlus.show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map=new HashMap<>();
                        map.put("name",name.getText().toString());
                        map.put("phone",phone.getText().toString());
                        map.put("purpose",purpose.getText().toString());
                        map.put("period",period.getText().toString());
                        map.put("email",email.getText().toString());



                        FirebaseDatabase.getInstance().getReference().child("Visitors")
                                .child(getRef(i).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialogPlus.dismiss();
                                    }
                                });

                    }
                });




            }
        });


        myviewholder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(myviewholder.name.getContext());
                builder.setTitle("Delete Panel ");
                builder.setMessage("Delete...?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("Visitors")
                                .child(getRef(i).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();


            }
        });



    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{
        //String img;
        ImageView edit,delete;
        TextView name,randId,email,phone;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.nametext);
            randId = (TextView)itemView.findViewById(R.id.randIdText);
            email = (TextView)itemView.findViewById(R.id.emailtext);
            phone = (TextView)itemView.findViewById(R.id.phoneno);


            edit=(ImageView)itemView.findViewById(R.id.editicon);
            delete=(ImageView)itemView.findViewById(R.id.deleteicon);

        }
    }


}
