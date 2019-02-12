package com.example.plantmaintenance;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
    String classs = "com.mysql.jdbc.Driver";

    String url = "jdbc:mysql://10.0.2.2/plantmaintenance";
    //127.0.0.1	 172.33.6.58 //10.0.2.2
    String un = "root";
    String password = "";



    @SuppressLint("NewApi")
    public java.sql.Connection CONN() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        java.sql.Connection conn = null;
        String ConnURL = null;
        try {

            Class.forName(classs);

            conn = DriverManager.getConnection(url, un, password);


            conn = DriverManager.getConnection(ConnURL);
        } catch (SQLException se) {
            Log.e("ERRO", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERRO", e.getMessage());
        } catch (Exception e) {
            Log.e("ERRO", e.getMessage());
        }
        return conn;
    }
}
