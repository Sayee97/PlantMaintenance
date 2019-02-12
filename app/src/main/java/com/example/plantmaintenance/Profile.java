package com.example.plantmaintenance;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Connection;
import java.sql.SQLOutput;
import java.sql.Statement;
import android.os.AsyncTask;

public class Profile extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;

    //view objects
    private TextView textViewUserEmail;
    private Button buttonLogout,start,saveSQL;




    private DatabaseReference databaseReference;
    private EditText namePerson,loc;
    private Button saveInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();

        //if the user is not logged in
        //that means current user will return null
        if(firebaseAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, Login.class));
        }





// Attach a listener to read the data at our posts reference

        //getting current user
        FirebaseUser user = firebaseAuth.getCurrentUser();

        //initializing views
        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
start=(Button)findViewById(R.id.start);
        //displaying logged in user name
        textViewUserEmail.setText("Welcome "+user.getEmail());
namePerson=(EditText)findViewById(R.id.name);
loc=(EditText)findViewById(R.id.location);
saveInfo=(Button)findViewById(R.id.save);
databaseReference= FirebaseDatabase.getInstance().getReference();
saveSQL=(Button)findViewById(R.id.saveSQL);
        //adding listener to button
        buttonLogout.setOnClickListener(this);
        saveInfo.setOnClickListener(this);
        saveSQL.setOnClickListener(this);
start.setOnClickListener(this);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserInformation post = dataSnapshot.getValue(UserInformation.class);
                System.out.println(post.location);
                Toast.makeText(getApplicationContext(),post.name,Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }


        });


    }
private void saveInformation(){
        String n1=namePerson.getText().toString().trim();
        String l1=loc.getText().toString().trim();
        UserInformation userInformation=new UserInformation(n1,l1);
        FirebaseUser user=firebaseAuth.getCurrentUser();
    Log.v("Dataa_Sayeeeeeeee", userInformation.toString());
        databaseReference.child(user.getUid()).setValue(userInformation);
    Toast.makeText(getApplicationContext(),"Saved Values",Toast.LENGTH_LONG).show();
}


    @Override
    public void onClick(View v) {
        if(v == buttonLogout){
            //logging out the user
            firebaseAuth.signOut();
            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(this, Login.class));
        }
        if(v==saveInfo){
            saveInformation();
        }
        if(v==start){
            startActivity(new Intent(this, Detect.class));
        }
        if(v==saveSQL){

            saveInformationSQL();

        }
    }

    private void saveInformationSQL() {
        Doregister doregister = new Doregister();
        doregister.execute("");


}




    public class Doregister extends AsyncTask<String,String,String>
    {


        String n1=namePerson.getText().toString().trim();
        String l1=loc.getText().toString().trim();
        String z="";
        boolean isSuccess=false;
        com.example.plantmaintenance.Connection c=new com.example.plantmaintenance.Connection();
        //Connection con=connectionClass.CONN();

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {

            if(n1.trim().equals("")|| l1.trim().equals(""))
                z = "Please enter all fields....";
            else
            {
                try {
                    Connection con=c.CONN();
                   // com.example.plantmaintenance.Connection con=c.CONN();
                    /*Connection con=c.CONN();*/

                    if (con == null) {
                        z = "Sayee Please check your internet connection";
                    } else {

                        String query="insert into personal values('"+n1+"','"+l1+"')";

                        Statement stmt = con.createStatement();
                        stmt.executeUpdate(query);

                        z = "Register successfull";
                        isSuccess=true;


                    }
                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    z = "Exceptions"+ex;
                }
            }
            return z;
        }

        @Override
        protected void onPostExecute(String s) {

            Toast.makeText(getBaseContext(),""+z,Toast.LENGTH_LONG).show();


            /*if(isSuccess) {
                startActivity(new Intent(signup.this,HomePage.class));
                Toast.makeText(getApplicationContext(),"SUCCESS REGISTER",Toast.LENGTH_LONG);
            }


            progressDialog.hide();*/
        }
    }



}
