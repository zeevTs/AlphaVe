package com.example.alphave;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class GoogleMaps extends AppCompatActivity {
    private Intent toLog, toSign, toMain, toGal, toCam, iCamera,toNot,toMap,toBrod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);
    }
    private void initViews() {
        toLog = new Intent(GoogleMaps.this, LogIn.class);
        toGal = new Intent(GoogleMaps.this, Gallery1.class);
        toCam = new Intent(GoogleMaps.this, Camera.class);
        toMain = new Intent(GoogleMaps.this, Main.class);
        toSign = new Intent(GoogleMaps.this, MainActivity.class);
        toNot = new Intent(GoogleMaps.this, Notification.class);
        toMap = new Intent(GoogleMaps.this, GoogleMaps.class);
        toBrod = new Intent(GoogleMaps.this,NetworkBrodcast.class);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String s1 = item.getTitle().toString();
        if(s1.equals("LogIn")){
            startActivity(toLog);
        }else if(s1.equals("SignUp")){
            startActivity(toSign);
        }else if(s1.equals("Camera")){
            startActivity(toCam);
        }else if(s1.equals("Gallery")){
            startActivity(toGal);
        }else if(s1.equals("Main")){
            startActivity(toMain);
        }else if(s1.equals("Notification")){
            startActivity(toNot);
        }else if (s1.equals("GoogleMaps")) {
            startActivity(toMap);
        }else if (s1.equals("BrodcastReceiver")) {
            startActivity(toBrod);
        }

        return super.onOptionsItemSelected(item);
    }

    public void googleMAp(View view) {

    }
}