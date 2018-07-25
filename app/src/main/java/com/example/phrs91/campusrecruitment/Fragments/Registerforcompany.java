package com.example.phrs91.campusrecruitment.Fragments;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phrs91.campusrecruitment.R;
import com.example.phrs91.campusrecruitment.RegisterActivity;
import com.example.phrs91.campusrecruitment.UserProfile;
import com.example.phrs91.campusrecruitment.Utils;
import com.example.phrs91.campusrecruitment.verify;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.HashSet;

public class Registerforcompany extends AppCompatActivity {
    private Company company;
    private UserProfile user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerforcompany);
        Intent t=getIntent();
        company=(Company)t.getSerializableExtra("company");
        user=(UserProfile)t.getSerializableExtra("user");
        TextView t1=(TextView)findViewById(R.id.cmp_name);
        t1.setText(company.getName());
        t1=(TextView)findViewById(R.id.crs_name);
        StringBuffer s=new StringBuffer("");
        HashSet<String > hs=company.getCourses();
        for(String x:hs)
        {
            s.append(x+ " ");
        }
        t1.setText(s.toString());
        s=new StringBuffer();

        t1=(TextView)findViewById(R.id.branch40_name);
        hs=company.getBranches();
        for(String x:hs)
        {
            s.append(x+" ");

        }
        t1.setText(s.toString());
        t1=(TextView)findViewById(R.id.ctc_name);
        t1.setText(company.getCTC());
        t1=(TextView)findViewById(R.id.elg_name);
        t1.setText(Double.toString(company.getCpi()));
        t1=(TextView)findViewById(R.id.des_name);
        t1.setText(company.getDesc());
        final Button b1=(Button)findViewById(R.id.registerforcombutton);
        if(!(Double.parseDouble(user.getCpi())>=company.getCpi() ||
                !( company.getBranches().contains("all") || company.getBranches().contains("ALL") || company.getBranches().contains(user.getBranch()) )
                || !(company.getCourses().contains("all")|| company.getCourses().contains("ALL")||
                company.getCourses().contains(user.getCourse()))))
        {
            b1.setText("Your are not eligible for this company");
            b1.setEnabled(false);
        }
        Date d1=company.getDate();
        Date Current =new Date();
        if(Current.after(d1))
        {
            b1.setText("Registration Closed");
            b1.setEnabled(false);

        }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference rootRef= Utils.getDatabase().getReference();
                final DatabaseReference userNameRef = rootRef.child("userreg").child(user.getRegisterNumber()).child(company.getName());
                ValueEventListener eventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(!dataSnapshot.exists()) {
                            //create new user
                            userNameRef.setValue(company.getName()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    DatabaseReference cp=Utils.getDatabase().getReference().child("compreg").
                                            child(company.getName()).child(user.getRegisterNumber());
                                    cp.setValue(user.getRegisterNumber());
                                    Toast.makeText(Registerforcompany.this,"Successfully Registered",Toast.LENGTH_LONG).show();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Registerforcompany.this,"Techinical Error please try again",Toast.LENGTH_LONG).show();
                                }

                            });

                        }
                        else
                        {
                            b1.setText("You are already Registered");
                            b1.setEnabled(false);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                };
                userNameRef.addListenerForSingleValueEvent(eventListener);
            }
        });


    }
}
