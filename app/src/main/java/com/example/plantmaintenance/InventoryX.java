package com.example.plantmaintenance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.*;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.os.AsyncTask;
import java.net.*;
import java.io.*;
import java.util.*;
import com.android.volley.*;
import com.android.volley.toolbox.*;

import org.json.JSONArray;
import org.json.JSONObject;
//import android.support.v7.RecyclerView.*;

public class InventoryX extends AppCompatActivity {
    private static final String URL_PRODUCTS = "http://10.0.2.2/test2/display.php";

    //a list to store all the products
    List<ProductGS> productList;

    //the recyclerview
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_x);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        productList = new ArrayList<>();

        //this method will fetch and parse json
        //to display it in recyclerview
        loadProducts();
        //Button b = (Button) findViewById(R.id.checkI);
        //b.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View v) {
                /*String link = "http://10.0.2.2/test2/hello.php?id=78"; //using this IP for Genymotion emulator
                new updateData().execute(link);
                Toast.makeText(getApplicationContext(), "Executed", Toast.LENGTH_LONG).show();*/
                /*recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                //initializing the productlist
                productList = new ArrayList<>();

                //this method will fetch and parse json
                //to display it in recyclerview
                loadProducts();*/
           // }
        //});

    }
    private void loadProducts() {

        /*
         * Creating a String Request
         * The request type is GET defined by first parameter
         * The URL is defined in the second parameter
         * Then we have a Response Listener and a Error Listener
         * In response listener we will get the JSON response as a String
         * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);
                            Log.v("JSONARRAY",array.toString());

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {
                                Log.v("looop",String.valueOf(i));

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);
Log.v("product",product.toString());
                                //adding the product to product list
                                productList.add(new ProductGS(
                                        product.getInt("id"),
                                        product.getInt("quantity")

                                ));
                                Log.v("ProductList",productList.toString());
                            }


                            //creating adapter object and setting it to recyclerview

                            ProductAdapter adapter = new ProductAdapter(InventoryX.this, productList);
                            recyclerView.setAdapter(adapter);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
        Log.v("Sayeeee",stringRequest.toString());
    }

  /*  public class updateData extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection conn = null;

            try {
                URL url;
                url = new URL(params[0]);
                conn = (HttpURLConnection) url.openConnection();
                if( conn.getResponseCode() == HttpURLConnection.HTTP_OK ){
                    InputStream is = conn.getInputStream();
                    System.out.println(is+"Virat");
                }else{
                    InputStream err = conn.getErrorStream();
                }
                return "Done";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(conn != null) {
                    conn.disconnect();
                }
            }
            return null;
        }
}*/}

