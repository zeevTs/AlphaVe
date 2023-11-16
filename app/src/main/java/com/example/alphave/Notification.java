package com.example.alphave;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class Notification extends AppCompatActivity {
    private Intent toLog, toSign, toMain, toGal, toCam,toNot;
    private EditText etNot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        initviews();
    }

    private void initviews() {
        toLog = new Intent(Notification.this, LogIn.class);
        toGal = new Intent(Notification.this, Gallery1.class);
        toCam = new Intent(Notification.this, Camera.class);
        toMain = new Intent(Notification.this, Main.class);
        toSign = new Intent(Notification.this, MainActivity.class);
        toNot= new Intent(Notification.this,Notification.class);
        etNot=findViewById(R.id.etNot);
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
        }
        return super.onOptionsItemSelected(item);
    }
    public void notification(View view) {
        String text = etNot.getText().toString();
        NotificationHelper.showNotification(this,text);

    }

    public void notificationBtn(View view) {
        String text = etNot.getText().toString();
        NotificationHelper.showNotificationBtn(this,text);
    }
}