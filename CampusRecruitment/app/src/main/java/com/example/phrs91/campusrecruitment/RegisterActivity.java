package com.example.phrs91.campusrecruitment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner mySpinner1,mySpinner2,mySpinner3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

         mySpinner1 = (Spinner)findViewById(R.id.spinner1);
         mySpinner2 = (Spinner)findViewById(R.id.spinner2);
         mySpinner3 = (Spinner)findViewById(R.id.spinner3);

        ArrayAdapter myadapter = ArrayAdapter.createFromResource(this, R.array.Category,android.R.layout.simple_spinner_item);
        ArrayAdapter myadapter1 = ArrayAdapter.createFromResource(this, R.array.Course,android.R.layout.simple_spinner_item);

        myadapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        mySpinner1.setAdapter(myadapter);
        mySpinner2.setAdapter(myadapter1);
        mySpinner2.setOnItemSelectedListener(this);

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
        else{
            ArrayAdapter myadapter2 = ArrayAdapter.createFromResource(this, R.array.MCA_array,android.R.layout.simple_spinner_item);
            mySpinner3.setAdapter(myadapter2);
        }
    }
    public void onNothingSelected(AdapterView<?> parent){

    }
    public void registeronClick(View view)
    {   boolean fake=false;
        UserProfile user=new UserProfile();
        try
        {
        EditText e1=(EditText)findViewById(R.id.editText);

        EditText e2=(EditText)findViewById(R.id.editText2);
        EditText e3=(EditText)findViewById(R.id.editText3);
        EditText e4=(EditText)findViewById(R.id.editText4);

        EditText e5=(EditText)findViewById(R.id.editText5);
        EditText e6=(EditText)findViewById(R.id.editText6);
        EditText e7=(EditText)findViewById(R.id.editText7);
        EditText e8=(EditText)findViewById(R.id.editText8);
        EditText e9=(EditText)findViewById(R.id.editText9);
        EditText e10=(EditText)findViewById(R.id.editText10);

        String name=e1.getText().toString();
        String RegisterNumber=e2.getText().toString();
        String Email=e3.getText().toString();
        String cpi=e4.getText().toString();
        String p10th=e5.getText().toString();
        String p12th=e6.getText().toString();
        String address=e7.getText().toString();
        String state=e8.getText().toString();
        String country=e9.getText().toString();
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
            Toast.makeText(RegisterActivity.this, "Please Add upper values first", Toast.LENGTH_LONG).show();
            ;
            fake=true;

        }
        if(password.length()<6)
        {
            Toast.makeText(RegisterActivity.this, "You can use that password if you want everyone to hack in your account i was kidding you can't", Toast.LENGTH_LONG).show();
            ;
            fake=true;
        }



        if(Float.parseFloat(cpi)>10 || Float.parseFloat(cpi)<0)

        {
            Toast.makeText(RegisterActivity.this, "Trust me on this one but you cant get this "+cpi, Toast.LENGTH_LONG).show();
            fake=true;
        }
            if(Float.parseFloat(p10th)>100 || Float.parseFloat(p10th)<0)
            {   fake=true;
                Toast.makeText(RegisterActivity.this, "Trust me on this one but you cant get this "+p10th, Toast.LENGTH_LONG).show();
            }
                if(Float.parseFloat(p12th)>100 || Float.parseFloat(p12th)<0) {

                    fake=true;
                    Toast.makeText(RegisterActivity.this, "Trust me on this one but you cant get this " + p12th, Toast.LENGTH_LONG).show();
                }
                user=new UserProfile(name,RegisterNumber,Email,cpi,p10th,p12th,address,state,country,password,cate,RegisterNumber+"resume",RegisterNumber+"Photo",course,branch,gender,phone,Dob);
        }
        catch(Exception e)
         {
             fake=true;
        Toast.makeText(RegisterActivity.this, "I can see  Something is wrong with above details", Toast.LENGTH_LONG).show();
        ;

        }
       // Toast.makeText(RegisterActivity.this,gender+cate+state+course+branch,Toast.LENGTH_LONG).show();
            if(!fake)
            {
                Intent t=new Intent(this,uploadimage.class);
                t.putExtra("user",(Serializable)user);
                startActivity(t);
            }

    }


}
