package com.example.phrs91.campusrecruitment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

public class feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Button b1=(Button) findViewById(R.id.PostButton);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText e1=(EditText)findViewById(R.id.feedback);
                String text=e1.getText().toString();
                if(text.length()==0)
                    Toast.makeText(feedback.this,"Cant be Empty",Toast.LENGTH_LONG).show();
               else {
                    DatabaseReference db = Utils.getDatabase().getReference();
                    db = db.child("Feedbacks");
                    db.push().setValue(text);
                    Toast.makeText(feedback.this, "Done", Toast.LENGTH_LONG).show();
                    e1.setText("");
                }
            }
        });
    }
}
