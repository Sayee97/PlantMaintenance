package com.example.plantmaintenance;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button;
    private TextView login;
    private EditText emailId,pass;
private ProgressDialog progressDialog;
private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            Intent intent2 = new Intent(getApplicationContext(), Profile.class);

            startActivity(intent2);

        }
        button=(Button)findViewById(R.id.register);
        login=(TextView)findViewById(R.id.login);
        emailId=(EditText)findViewById(R.id.emailId);
        pass=(EditText)findViewById(R.id.pass);
        button.setOnClickListener(this);
        login.setOnClickListener(this);
        progressDialog=new ProgressDialog(this);


    }
private void registerUser(){
        String email=emailId.getText().toString().trim();
        String password= pass.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(),"Enter email pls",Toast.LENGTH_LONG).show();

            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(),"Pls enter pass",Toast.LENGTH_LONG).show();
            return;
        }

progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    finish();
                   // Toast.makeText(getApplicationContext(),"Successfully Registered",Toast.LENGTH_LONG).show();

                    startActivity(new Intent(getApplicationContext(), Profile.class));

                }
                else {
                    Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });



}
    @Override
    public void onClick(View v) {
        if(v==button){
            registerUser();
        }
        if(v==login){
            //open activity!
            startActivity(new Intent(this, Login.class));




        }

    }
}
