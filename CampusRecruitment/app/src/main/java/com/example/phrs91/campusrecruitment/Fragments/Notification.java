package com.example.phrs91.campusrecruitment.Fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.phrs91.campusrecruitment.NotificationAdapter;
import com.example.phrs91.campusrecruitment.R;
import com.example.phrs91.campusrecruitment.notification.Config;
import com.example.phrs91.campusrecruitment.notification.NotificationUtils;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashish on 3/6/18.
 */

public class Notification implements Serializable
{
    String topic;
    String text;
   Notification()
   {

   }
   Notification(String topic,String text)
   {
       this.topic=topic;
       this.text=text;
   }
   public void setTopic(String t)
   {
    this.topic=t;
   }


   public void setText(String t)
   {
       this.text=t;
   }
   public String getTopic()
       {return topic;}
       public String getText()
       {
           return text;
       }

    public static class homeN extends AppCompatActivity {
        private List<Notification> movieList = new ArrayList<>();
        private RecyclerView recyclerView;
        private NotificationAdapter mAdapter;
        private BroadcastReceiver mRegistrationBroadcastReceiver;
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home_n);

            /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

            /*setSupportActionBar(toolbar);*/
            recyclerView =(RecyclerView)findViewById(R.id.recycler_view);
            mAdapter = new NotificationAdapter(movieList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
            recyclerView.setAdapter(mAdapter);
            String s=getIntent().getStringExtra("message");
            if(s!=null)
            {
                Notification n=new Notification("Topic",s);
                movieList.add(n);
                mAdapter.notifyDataSetChanged();
            }
            mRegistrationBroadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {

                    // checking for type intent filter
                    if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                        // gcm successfully registered
                        // now subscribe to `global` topic to receive app wide notifications
                        FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);



                    } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                        // new push notification is received

                        String message = intent.getStringExtra("message");

                        Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();
                        Notification n=new Notification("Topic",message);
                        movieList.add(n);
                        mAdapter.notifyDataSetChanged();

                    }
                }
            };
            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);



         //   simpleList = (ListView)findViewById(R.id.simpleListView);
           // ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_list, R.id.textV, countryList);
            //simpleList.setAdapter(arrayAdapter);
            Notification n1=new Notification("Change ","New timinng is 2 pm");
            movieList.add(n1);

            String msg=getIntent().getStringExtra("message");
            Notification n2=new Notification(" Delay",msg);

            mAdapter.notifyDataSetChanged();

        }

        @Override
        protected void onResume() {
            super.onResume();

            // register GCM registration complete receiver
            LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                    new IntentFilter(Config.REGISTRATION_COMPLETE));

            // register new push message receiver
            // by doing this, the activity will be notified each time a new message arrives
            LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                    new IntentFilter(Config.PUSH_NOTIFICATION));

            // clear the notification area when the app is opened
            NotificationUtils.clearNotifications(getApplicationContext());
        }

        @Override
        protected void onPause() {

            super.onPause();
        }
    }
}
