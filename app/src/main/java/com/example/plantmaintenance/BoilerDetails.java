package com.example.plantmaintenance;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import android.app.*;
import java.sql.Statement;
import android.graphics.*;
import android.support.v4.app.NotificationCompat;
import android.content.*;

import java.sql.ResultSet;

public class BoilerDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boiler_details);
        String sessionId= getIntent().getStringExtra("boilerID");
        TextView t=(TextView)findViewById(R.id.boilerID);
        t.setText("Boiler: "+sessionId);

        Button checkInventory=(Button)findViewById(R.id.checkInventory);
        checkInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Inventory.class);

                startActivity(intent);
            }
        });
        Button checkHumidity=(Button)findViewById(R.id.checkHumidity);
        checkHumidity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Doregister doregister = new Doregister();
                doregister.execute("");

            }
        });

    }

    public class Doregister extends AsyncTask<String,String,String>
    {
        String bid;


        String z="";
        boolean isSuccess=false;
        com.example.plantmaintenance.Connection c=new com.example.plantmaintenance.Connection();
        //Connection con=connectionClass.CONN();

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {



                try {
                    java.sql.Connection con=c.CONN();
                    // com.example.plantmaintenance.Connection con=c.CONN();
                    /*Connection con=c.CONN();*/

                    if (con == null) {
                        z = "Sayee Please check your internet connection";
                    } else {
                        String quey2="select ID from sensors where Temperature>1200";
                        //String query="insert into personal values('"+n1+"','"+l1+"')";

                        Statement stmt = con.createStatement();
                        //stmt.executeUpdate(quey2);

                        ResultSet rs=stmt.executeQuery(quey2);

while(rs.next()){
     bid=rs.getString(1);

    Log.v("BID",bid);




}
                        z = bid;
                        isSuccess=true;


                    }
                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    z = "Exceptions"+ex;
                }

            return z;
        }

        @Override
        protected void onPostExecute(String s) {

            Toast.makeText(getBaseContext(),""+z,Toast.LENGTH_LONG).show();
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel("default",
                        "YOUR_CHANNEL_NAME",
                        NotificationManager.IMPORTANCE_DEFAULT);
                channel.setDescription("YOUR_NOTIFICATION_CHANNEL_DISCRIPTION");
                mNotificationManager.createNotificationChannel(channel);
            }
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "default")
                    .setSmallIcon(R.mipmap.ic_launcher) // notification icon
                    .setContentTitle("Check Level!") // title for notification
                    .setContentText("Boiler "+z+"is high")// message for notification
                    // set alarm sound for notification
                    .setAutoCancel(true); // clear notification after click

            mNotificationManager.notify(0, mBuilder.build());
            //mNotificationManager.notify(0, mBuilder.build());

            /*if(isSuccess) {
                startActivity(new Intent(signup.this,HomePage.class));
                Toast.makeText(getApplicationContext(),"SUCCESS REGISTER",Toast.LENGTH_LONG);
            }


            progressDialog.hide();*/
        }
    }

}
