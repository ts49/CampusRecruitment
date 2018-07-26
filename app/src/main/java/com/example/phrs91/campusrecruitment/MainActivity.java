package com.example.phrs91.campusrecruitment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.phrs91.campusrecruitment.Fragments.*;
import com.example.phrs91.campusrecruitment.notification.Config;
import com.example.phrs91.campusrecruitment.notification.NotificationUtils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private int selecteditem;
    private NavigationView navigationView;
    private FirebaseAuth mAuth;
    private UserProfile user;
    private String activityTitles[]={"","Home","Profile","Companies","Registered Companies","Feedback","Contacts"};
    private static final String TAG_HOME = "home";
    private static final String TAG_PROFILE = "profile";
    private static final String TAG_CONTACT = "contacts";
    private static final String TAG_INTERNSHIP = "internship";
    private static final String TAG_COMPANIES = "companies";
    public static String CURRENT_TAG = TAG_HOME;
    private Handler mHandler;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        setContentView(R.layout.activity_main);
        mHandler=new Handler();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

         navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        checklogin();
        donotificationstuff();
      //  loadnavheader();
        if (savedInstanceState == null) {
            selecteditem = 1;
            CURRENT_TAG = TAG_HOME;
            loadFragment();
        }






    }
    private void checklogin()
    {
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=mAuth.getCurrentUser();
        if(firebaseUser==null)
        {
            Intent t=new Intent(MainActivity.this,login.class);
            t.putExtra("Exit",false);
            startActivity(t);
        }
        else
        {
            if(!firebaseUser.isEmailVerified())
            {
                Intent i=new Intent(MainActivity.this,verify.class);
                startActivity(i);

            }
            else
            {
                String Rno=firebaseUser.getDisplayName();
                DatabaseReference db=Utils.getDatabase().getReference();
                db=db.child("user").child(Rno);
                db.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        HashMap<String ,String> hs=(HashMap<String, String>)dataSnapshot.getValue();
                        user=new UserProfile(hs.get("name"),hs.get("registerNumber"),hs.get("email"),hs.get("cpi"),hs.get("p10th"),hs.get("p12th"),hs.get("address"),hs.get("course"),hs.get("branch"),hs.get("gender"),hs.get("state"),hs.get("country"),hs.get("password"),hs.get("phone"),hs.get("dobirth"),hs.get("cate"),hs.get("resume"),hs.get("photo"));
                        loadnavheader();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }

        }
    }
    private void donotificationstuff()
    {
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


                }
            }
        };
    }
    private void loadFragment()
    {
        selectNavMenu();

        // set toolbar title
        setToolbarTitle();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawers();

            // show or hide the fab button

            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        // show or hide the fab button

        //Closing drawer on item click
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }
    private Fragment getHomeFragment()
    {

        switch (selecteditem)
        {
            case 1:
                homeFragment homefragment=new homeFragment();
                return homefragment;
            case 2:
                profileFragment profilefragment=profileFragment.newInstance(user,"");
                return profilefragment;
            case 3:
                companyFragment companyfragment= companyFragment.newInstance(user,"");
                return  companyfragment;
            case 4:
                internshipFragment internshipFragment=new internshipFragment();
                return internshipFragment;
            case 6:
                contactFragment contactFragment=new contactFragment();
                return contactFragment;
            default:
                homeFragment homefragment1=new homeFragment();
                return homefragment1;
        }
    }
    private void loadnavheader()

    {       View Header=navigationView.getHeaderView(0);

        TextView name=(TextView)Header.findViewById(R.id.nammmm);

        name.setText(user.getName());
        TextView gmail=(TextView)Header.findViewById(R.id.gmailofuser);
        gmail.setText(user.getEmail());
        FirebaseStorage firebaseStorage=FirebaseStorage.getInstance();
        StorageReference storageRef=firebaseStorage.getReference();
       // Toast.makeText(MainActivity.this,user.getPhoto(),Toast.LENGTH_LONG).show();
        storageRef.child(user.getPhoto()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Got the download URL for 'users/me/profil.e.png'
            //    Toast.makeText(MainActivity.this,uri.toString(),Toast.LENGTH_LONG).show();
                ImageView imgProfile=(ImageView)findViewById(R.id.img_header_bg);
                Glide.with(MainActivity.this).load(uri)
                        .crossFade()
                        .thumbnail(0.5f)
                        .bitmapTransform(new CircleTransform(MainActivity.this))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imgProfile);


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // File not found
            }
        });


    }


    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[selecteditem]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(selecteditem-1).setChecked(true);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
            Intent intent=new Intent(MainActivity.this,login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("Exit",true);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the homeNav/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.contacts) {
            selecteditem=6;
            CURRENT_TAG=TAG_CONTACT;
        } else if (id == R.id.home) {
            selecteditem=1;
            CURRENT_TAG=TAG_HOME;
        } else if (id == R.id.profile) {
            selecteditem=2;
            CURRENT_TAG=TAG_PROFILE;
        } else if (id == R.id.companies) {
            selecteditem=3;
            CURRENT_TAG=TAG_COMPANIES;
        } else if (id == R.id.internship) {
                selecteditem=4;
                CURRENT_TAG=TAG_INTERNSHIP;
        } else if (id == R.id.feedback) {
            Intent i=new Intent(MainActivity.this,feedback.class);
            startActivity(i);
        }
        else if(id==R.id.lg)
        {
            mAuth.signOut();
            Intent t=new Intent(MainActivity.this,login.class);
            startActivity(t);
        }
        else
        {
            selecteditem=1;
            CURRENT_TAG=TAG_HOME;
        }
        if (item.isChecked()) {
            item.setChecked(false);
        } else {
            item.setChecked(true);
        }
        item.setChecked(true);
        loadFragment();
        return true;
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
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }
}

