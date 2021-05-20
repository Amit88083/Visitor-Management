package com.example.vistormanagement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import static com.squareup.okhttp.internal.Internal.instance;

public class requestedmyadapter extends FirestoreRecyclerAdapter<model,requestedmyadapter.myviewholder> {

    public requestedmyadapter(@NonNull FirestoreRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model model) {


        holder.randId.setText(model.getRandId());
        holder.name.setText(model.getName());
        holder.phone.setText(model.getPhone());
        holder.email.setText(model.getEmail());
        holder.date.setText(model.getDate());
        holder.time.setText(model.getTime());



        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.name.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                        .setExpanded(true,800)
                        .create();

                View myview1 = dialogPlus.getHolderView();

                EditText name = myview1.findViewById(R.id.nameUp);
                EditText phone = myview1.findViewById(R.id.phoneUp);
                EditText purpose = myview1.findViewById(R.id.purposeUp);
                EditText period = myview1.findViewById(R.id.periodUp);
                EditText email = myview1.findViewById(R.id.emailUp);
                EditText date = myview1.findViewById(R.id.dateUp);
                EditText time = myview1.findViewById(R.id.timeUp);



                Button submit=myview1.findViewById(R.id.usubmit);
                // Button sendEmail=myview.findViewById(R.id.sendEmail);

                name.setText(model.getName());
                phone.setText(model.getPhone());
                purpose.setText(model.getPurpose());
                period.setText(model.getPeriod());
                email.setText(model.getEmail());
                date.setText(model.getDate());
                time.setText(model.getTime());


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
                        map.put("date",date.getText().toString());
                        map.put("time",time.getText().toString());


                        FirebaseFirestore.getInstance().collection("visitor").document("574330")
                                .update(map)
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

                       // FirebaseFirestore.getInstance().collection("visitor").document(getItem(position)).update(map)


                    }
                });




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
        TextView name,randId,email,phone,date,time;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.nametext);
            randId = (TextView)itemView.findViewById(R.id.randIdText);
            email = (TextView)itemView.findViewById(R.id.emailtext);
            phone = (TextView)itemView.findViewById(R.id.phoneno);
            date = (TextView)itemView.findViewById(R.id.datetext);
            time = (TextView)itemView.findViewById(R.id.timetext);


            edit=(ImageView)itemView.findViewById(R.id.editicon);
            delete=(ImageView)itemView.findViewById(R.id.deleteicon);

        }
    }
}
