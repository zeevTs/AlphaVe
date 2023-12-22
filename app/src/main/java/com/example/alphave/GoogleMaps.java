package com.example.alphave;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class GoogleMaps extends AppCompatActivity {

    private EditText eTLoc,eTDes;
    private Intent toLog, toSign, toMain, toGal, toCam, iCamera,toNot,toMap,toBrod,toPref,toChat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);
        initViews();
    }
    private void initViews() {
        eTLoc = findViewById(R.id.eTLoc);
        eTDes = findViewById(R.id.eTDes);

        toLog = new Intent(GoogleMaps.this, LogIn.class);
        toGal = new Intent(GoogleMaps.this, Gallery1.class);
        toCam = new Intent(GoogleMaps.this, Camera.class);
        toMain = new Intent(GoogleMaps.this, Main.class);
        toSign = new Intent(GoogleMaps.this, MainActivity.class);
        toNot = new Intent(GoogleMaps.this, Notification.class);
        toMap = new Intent(GoogleMaps.this, GoogleMaps.class);
        toBrod = new Intent(GoogleMaps.this,NetworkBrodcast.class);
        toPref = new Intent(GoogleMaps.this,SharedPref.class);
        toChat = new Intent(GoogleMaps.this,ChatsActivity.class);
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
        }else if (s1.equals("SharedPref")) {
            startActivity(toPref);

        }else if (s1.equals("Chat")) {
            startActivity(toChat);
        }

        return super.onOptionsItemSelected(item);
    }
    public void googleMaps(View view) {
        String location = eTLoc.getText().toString();
        String destination = eTDes.getText().toString();
        if(location.equals("") || destination.equals("")){
            Toast.makeText(this, " please enter location and destination", Toast.LENGTH_SHORT).show();
        }else{
            getDirections(location,destination);
        }
    }

    private void getDirections(String location, String destination) {
        try {
            Uri uri = Uri.parse("https://www.google.com/maps/dir/" + location + "/" + destination);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri);
            mapIntent.setPackage("com.google.android.apps.maps");
            mapIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(mapIntent);
        }catch (ActivityNotFoundException exp){
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            Intent downloadIntent = new Intent(Intent.ACTION_VIEW, uri);
            downloadIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(downloadIntent);
        }
    }


}