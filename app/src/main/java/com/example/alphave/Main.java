package com.example.alphave;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Main extends AppCompatActivity {
    private Intent toLog, toSign, toMain, toGal, toCam,toNot,toMap,toBrod,toPref,toChat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initviews();
    }

    private void initviews() {
        toLog = new Intent(Main.this, LogIn.class);
        toGal = new Intent(Main.this, Gallery1.class);
        toCam = new Intent(Main.this, Camera.class);
        toSign = new Intent(Main.this, MainActivity.class);
        toMain = new Intent(Main.this, Main.class);
        toNot = new Intent(Main.this,Notification.class);
        toMap = new Intent(Main.this, GoogleMaps.class);
        toBrod = new Intent(Main.this,NetworkBrodcast.class);
        toPref = new Intent(Main.this, SharedPref.class);
        toChat = new Intent(Main.this,ChatsActivity.class);
    }


    @Override
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
        }else if (s1.equals("SharedPref")) {
            startActivity(toPref);
        }else if (s1.equals("Chat")) {
            startActivity(toChat);
        }

        return super.onOptionsItemSelected(item);
    }

    public void Camera(View view) {
    }
}