package com.example.phrs91.campusrecruitment.Fragments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.phrs91.campusrecruitment.R;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_view_notification);
        Intent i=getIntent();
        Notification n1=(Notification)i.getSerializableExtra("notification");
        TextView t1=(TextView)findViewById(R.id.topic);
        TextView t2=(TextView)findViewById(R.id.text);
        String s1=n1.getTopic();
        String s2=n1.getText();
        t1.setText(s1);
        t2.setText(s2);


    }
}
