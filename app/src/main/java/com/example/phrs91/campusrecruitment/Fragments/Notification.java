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

}

