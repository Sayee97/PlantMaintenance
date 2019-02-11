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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private Button signIn;
    private TextView signUp;
    private EditText emailId,pass;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            //startActivity(new Intent(getApplicationContext(),Profile.class));

            startActivity(new Intent(getApplicationContext(), Profile.class));

        }
        signIn=(Button)findViewById(R.id.loginButton);
        signUp=(TextView)findViewById(R.id.signUp);
        emailId=(EditText)findViewById(R.id.emailId);
        pass=(EditText)findViewById(R.id.pass);
        signUp.setOnClickListener(this);
        signIn.setOnClickListener(this);
        progressDialog=new ProgressDialog(this);



    }
    private void logIn(){
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

        progressDialog.setMessage("Signing in User...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
if(task.isSuccessful()){
    finish();

    startActivity(new Intent(getApplicationContext(), Profile.class));





}
else {
    Toast.makeText(getApplicationContext(),"Enter valid cred",Toast.LENGTH_LONG).show();
}
            }
        });

    }

    @Override
    public void onClick(View v) {
        if(v==signUp){
            finish();
            startActivity(new Intent(this, MainActivity.class));

        }
        if(v==signIn){
            logIn();
        }

    }
}
