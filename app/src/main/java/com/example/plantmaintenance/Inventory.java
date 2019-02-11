package com.example.plantmaintenance;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import android.util.Log;
public class Inventory extends AppCompatActivity {
private ListView mlistView;
   // public Firebase mref;
   ArrayAdapter<String> adapter;
   private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
private ArrayList mdata=new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

ListView listView=(ListView)findViewById(R.id.displayInventory);


        databaseReference= FirebaseDatabase.getInstance().getReference("Inventory");


       adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice,
                mdata);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


        databaseReference.addChildEventListener(new ChildEventListener() {
    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

       // mdata.add(teacherString);
        //String data=dataSnapshot.getValue(String.class);
        //Log.v("DataSayee",data);
        //mdata.add(data);
        //arrayAd.notifyDataSetChanged();

        /*Product teacher = (Product) dataSnapshot.getValue(Product.class);
        String teacherString = String.valueOf(teacher);
        adapter.add(teacherString);*/
        adapter.add(
                (String) dataSnapshot.child("supplier").getValue());
        //listKeys.add(dataSnapshot.getKey());
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});
    }
}
