package com.example.phrs91.campusrecruitment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
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

import java.io.Serializable;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner mySpinner1,mySpinner2,mySpinner3,mySpinner4,mySpinner5;
    private FirebaseAuth auth;
    private FirebaseUser fuser;
    private UserProfile user;
    private Button b;
    private boolean suc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

         mySpinner1 = (Spinner)findViewById(R.id.spinner1);
         mySpinner2 = (Spinner)findViewById(R.id.spinner2);
         mySpinner3 = (Spinner)findViewById(R.id.spinner3);
        mySpinner4 = (Spinner)findViewById(R.id.spinner4);
        mySpinner5 = (Spinner)findViewById(R.id.spinner5);


        ArrayAdapter myadapter = ArrayAdapter.createFromResource(this, R.array.Category,android.R.layout.simple_spinner_item);
        ArrayAdapter myadapter1 = ArrayAdapter.createFromResource(this, R.array.Course,android.R.layout.simple_spinner_item);
        ArrayAdapter myadapter4 = ArrayAdapter.createFromResource(this, R.array.Country,android.R.layout.simple_spinner_item);
        ArrayAdapter myadapter5 = ArrayAdapter.createFromResource(this, R.array.NoneState,android.R.layout.simple_spinner_item);
        ArrayAdapter myadapter3=ArrayAdapter.createFromResource(this,R.array.NoneBranch,android.R.layout.simple_spinner_item);
        myadapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        mySpinner1.setAdapter(myadapter);
        mySpinner2.setAdapter(myadapter1);
        mySpinner3.setAdapter(myadapter3);
        mySpinner4.setAdapter(myadapter4);
        mySpinner5.setAdapter(myadapter5);

        mySpinner2.setOnItemSelectedListener(this);
        mySpinner4.setOnItemSelectedListener(this);
        mySpinner1.setOnItemSelectedListener(this);

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view,int position ,long id){
        if(mySpinner2.getSelectedItem().equals("B.Tech")){
            ArrayAdapter myadapter2 = ArrayAdapter.createFromResource(this, R.array.B_Tech_array,android.R.layout.simple_spinner_item);
                mySpinner3.setAdapter(myadapter2);
        }
        else if(mySpinner2.getSelectedItem().equals("M.Tech")){
            ArrayAdapter myadapter2 = ArrayAdapter.createFromResource(this, R.array.M_Tech_array,android.R.layout.simple_spinner_item);
            mySpinner3.setAdapter(myadapter2);
        }
        if(mySpinner2.getSelectedItem().equals("M.C.A")){
            ArrayAdapter myadapter3 = ArrayAdapter.createFromResource(this, R.array.MCA_array,android.R.layout.simple_spinner_item);
            mySpinner3.setAdapter(myadapter3);

        }
        if(mySpinner2.getSelectedItem().equals("--Course--")){
            ArrayAdapter myadapter3 = ArrayAdapter.createFromResource(this, R.array.NoneBranch,android.R.layout.simple_spinner_item);
            mySpinner3.setAdapter(myadapter3);
        }
        if( mySpinner4.getSelectedItem().equals("India")){
            ArrayAdapter myadapter5=ArrayAdapter.createFromResource( this, R.array.State,android.R.layout.simple_spinner_item);
            mySpinner5.setAdapter(myadapter5);

        }
        else
        {
            ArrayAdapter myadapter5=ArrayAdapter.createFromResource( this, R.array.NoneState,android.R.layout.simple_spinner_item);
            mySpinner5.setAdapter(myadapter5);

        }



        if(view!=null) {
            TextView tv = (TextView) view;
            tv.setTextColor(Color.GRAY);
            tv.setTextSize(17);
        }


    }
    public void onNothingSelected(AdapterView<?> parent){

    }
    public void registeronClick(View view)
    {   boolean fake=false;
        user=new UserProfile();
         b=(Button)view;
        b.setEnabled(false);
        try
        {
        EditText e1=(EditText)findViewById(R.id.editText);

        EditText e2=(EditText)findViewById(R.id.editText2);
        EditText e3=(EditText)findViewById(R.id.editText3);
        EditText e4=(EditText)findViewById(R.id.editText4);

        EditText e5=(EditText)findViewById(R.id.editText5);
        EditText e6=(EditText)findViewById(R.id.editText6);
        EditText e7=(EditText)findViewById(R.id.editText7);

        EditText e10=(EditText)findViewById(R.id.editText10);

        String name=e1.getText().toString();
        String RegisterNumber=e2.getText().toString();
        String Email=e3.getText().toString();
        String cpi=e4.getText().toString();
        String p10th=e5.getText().toString();
        String p12th=e6.getText().toString();
        String address=e7.getText().toString();
        String state=mySpinner5.getSelectedItem().toString();
        String country=mySpinner4.getSelectedItem().toString();
        String password=e10.getText().toString();
        String cate=mySpinner1.getSelectedItem().toString();
        String course=mySpinner2.getSelectedItem().toString();
        String branch=mySpinner3.getSelectedItem().toString();
        RadioGroup r1=(RadioGroup)findViewById(R.id.radio1);
        String phone=((EditText)findViewById(R.id.editText12)).getText().toString();
        String Dob=((EditText)findViewById(R.id.editText11)).getText().toString();
        RadioButton radio =(RadioButton)findViewById(r1.getCheckedRadioButtonId());
        String gender=radio.getText().toString();

        if (name.equals("")|| RegisterNumber.equals("") || Email.equals("") || cpi.equals("") || p10th.equals("") || p12th.equals("") || address.equals("")|| state.equals("")|| country.equals("") || password.equals("") || cate.equals("") || course.equals("") || branch.equals("") || gender.equals("")||Dob.equals("")||phone.equals("")) {
          //  Toast.makeText(RegisterActivity.this, "Please Add upper values first", Toast.LENGTH_LONG).show();

            //;
	
            fake=true;
	throw new Exception("Missing values");
	

        }
            if(RegisterNumber.length()!=8&&!fake)
            {
             //
                //   Toast.makeText(RegisterActivity.this, "Invalid Registration number", Toast.LENGTH_LONG).show();
                fake=true;
                throw new Exception("Wrong Registration Number");


            }
            if(phone.length()!=10&&fake==false)
            {
             //   Toast.makeText(RegisterActivity.this, "Invalid Phone number", Toast.LENGTH_LONG).show();
                fake=true;
                throw new Exception("Invalid Phone number");
            }
        if(password.length()<6)
        { fake=true;
         throw new Exception("Too small password");
           
        }



        if(Float.parseFloat(cpi)>10 || Float.parseFloat(cpi)<0)

        { fake=true;
           throw new Exception("CPI cant be more than 10 or less than 0");
           
        }
            if(Float.parseFloat(p10th)>100 || Float.parseFloat(p10th)<0)
            {   fake=true;
                throw new Exception("Please Enter valid 10 %");
            }
                if(Float.parseFloat(p12th)>100 || Float.parseFloat(p12th)<0) {

                    fake=true;
                //    Toast.makeText(RegisterActivity.this, "Trust me on this one but you cant get this " + p12th, Toast.LENGTH_LONG).show();
		throw new Exception("Please Enter valid 12 %");	                
}
                user=new UserProfile(name,RegisterNumber,Email,cpi,p10th,p12th,address,course,branch,gender,state,country,password,phone,Dob,cate,RegisterNumber+"Resume",RegisterNumber+"Photo");
        }
        catch(Exception e)
         {
             fake=true;
        Toast.makeText(RegisterActivity.this,e.getMessage(), Toast.LENGTH_LONG).show();
        ;

        }
       // Toast.makeText(RegisterActivity.this,gender+cate+state+course+branch,Toast.LENGTH_LONG).show();
            if(!fake)
            {   //addindatabase(
                createuser();
            }
            b.setEnabled(true);

    }
    private boolean createuser()
    {   suc=false;
        auth= FirebaseAuth.getInstance();

        auth.createUserWithEmailAndPassword(user.getEmail(),user.getPassword()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    fuser=auth.getCurrentUser();
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName((user.getRegisterNumber()))
                            .build();

                    fuser.updateProfile(profileUpdates)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        //  Log.d(TAG, "User profile updated.");
                                        addindatabase();
                                      Toast.makeText(RegisterActivity.this,"Email",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                    suc=true;

                }
                else
                {
                    Toast.makeText(RegisterActivity.this," Wrong Email or Email already Exist ",Toast.LENGTH_LONG).show();
                    b.setEnabled(true);
                }
            }
        });
    return suc;

    }
    private boolean addindatabase()
    {   suc=false;
        DatabaseReference rootRef=Utils.getDatabase().getReference();


        final DatabaseReference userNameRef = rootRef.child("user").child(user.getRegisterNumber());
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()) {
                    //create new user
                    userNameRef.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Intent i=new Intent(RegisterActivity.this,verify.class);
                            //  i.putExtra("user",(Serializable)fuser);
                            startActivity(i);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            fuser.delete();
                            Toast.makeText(RegisterActivity.this,"Technical Error please try again ",Toast.LENGTH_LONG).show();
                            b.setEnabled(true);
                        }
                    });

                }
                else
                {
                    fuser.delete();
                    Toast.makeText(RegisterActivity.this,"User with Given Registration no exists ",Toast.LENGTH_LONG).show();
                    b.setEnabled(true);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        userNameRef.addListenerForSingleValueEvent(eventListener);
        return false;

    }


}
