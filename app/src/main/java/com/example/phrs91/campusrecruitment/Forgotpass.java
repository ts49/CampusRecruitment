package com.example.phrs91.campusrecruitment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgotpass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass);
        Button reset=(Button)findViewById(R.id.passwordreset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText e1=(EditText)findViewById(R.id.emailtosend);
                String email=e1.getText().toString();
                FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Forgotpass.this,"Email Sent Successfully",Toast.LENGTH_SHORT).show();
                            Intent t=new Intent(Forgotpass.this,login.class);
                            startActivity(t);
                        }
                        else
                        {
                            Toast.makeText(Forgotpass.this,"Email is not Valid or User Doesn't exists ",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
