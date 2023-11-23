package com.example.alphave;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class SharedPref extends AppCompatActivity {
    private Intent toLog, toSign, toMain, toGal, toCam, toNot,toMap,toBrod,toPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_pref);
        initViews();
    }

    private void initViews() {
        toLog = new Intent(SharedPref.this, LogIn.class);
        toGal = new Intent(SharedPref.this, Gallery1.class);
        toCam = new Intent(SharedPref.this, Camera.class);
        toMain = new Intent(SharedPref.this, Main.class);
        toSign = new Intent(SharedPref.this, MainActivity.class);
        toNot = new Intent(SharedPref.this, Notification.class);
        toMap = new Intent(SharedPref.this,GoogleMaps.class);
        toBrod = new Intent(SharedPref.this,NetworkBrodcast.class);
        toPref = new Intent(SharedPref.this,SharedPref.class);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String s1 = item.getTitle().toString();
        if (s1.equals("LogIn")) {
            startActivity(toLog);
        } else if (s1.equals("SignUp")) {
            startActivity(toSign);
        } else if (s1.equals("Camera")) {
            startActivity(toCam);
        } else if (s1.equals("Gallery")) {
            startActivity(toGal);
        } else if (s1.equals("Main")) {
            startActivity(toMain);
        } else if (s1.equals("Notification")) {
            startActivity(toNot);
        } else if (s1.equals("GoogleMaps")) {
            startActivity(toMap);
        }else if (s1.equals("BrodcastReceiver")) {
            startActivity(toBrod);
        }else if (s1.equals("SharedPref")) {
            startActivity(toPref);
        }
        return super.onOptionsItemSelected(item);
    }
}