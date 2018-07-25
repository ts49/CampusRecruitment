package com.example.phrs91.campusrecruitment;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.*;
import com.google.firebase.storage.*;
import com.google.firebase.auth.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.widget.*;
import java.io.Serializable;
import java.util.HashMap;

public class verify extends AppCompatActivity {

    private UserProfile user;
    private boolean failed;
    private FirebaseUser fuser;
    private FirebaseDatabase fdb;
    private DatabaseReference db;
    private FirebaseAuth auth;
    private FirebaseStorage fstrref;
    private  StorageReference strref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
           ;

            auth=FirebaseAuth.getInstance();
            fuser=auth.getCurrentUser();


        String Rno=fuser.getDisplayName();
        Toast.makeText(verify.this,Rno,Toast.LENGTH_LONG).show();
        DatabaseReference db=Utils.getDatabase().getReference();
        db=db.child("user").child(Rno);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<String ,String> hs=(HashMap<String, String>)dataSnapshot.getValue();
                user=new UserProfile(hs.get("name"),hs.get("registerNumber"),hs.get("email"),hs.get("cpi"),hs.get("p10th"),hs.get("p12th"),hs.get("address"),hs.get("course"),hs.get("branch"),hs.get("gender"),hs.get("state"),hs.get("country"),hs.get("password"),hs.get("phone"),hs.get("dobirth"),hs.get("cate"),hs.get("resume"),hs.get("photo"));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    public void  SendEmail(View v)
    {   Button b1=(Button)findViewById(R.id.button3);
        b1.setEnabled(false);

                fuser.sendEmailVerification().addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(verify.this, "Email Send", Toast.LENGTH_LONG).show();
                            while (!fuser.equals(null) && !fuser.isEmailVerified()) {
                                fuser.reload();

                            }



                            Intent i = new Intent(verify.this, uploadimage.class);
                            i.putExtra("user",(Serializable)user);
                            startActivity(i);
                        } else {
                            Toast.makeText(verify.this, " Please try Again", Toast.LENGTH_LONG);
                        }
                    }
                });


    }
    public void  SignOut(View v)
    {
        auth.signOut();
        Intent i=new Intent(this,login.class);

        startActivity(i);
    }
}
