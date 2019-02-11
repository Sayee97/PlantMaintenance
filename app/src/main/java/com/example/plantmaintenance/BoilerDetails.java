package com.example.plantmaintenance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
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

    }

}
