package com.example.alphave;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPref extends AppCompatActivity {
    SharedPreferences sf ;
    private TextView tvShow;
    EditText etData;
    String st;
    private Intent toLog, toSign, toMain, toGal, toCam, toNot,toMap,toBrod,toPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_pref);
        initViews();
        sf =getSharedPreferences("prefName",MODE_PRIVATE);
        st = sf.getString("text","");
        tvShow.setText(st);
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
        etData = findViewById(R.id.eTData);
        tvShow = findViewById(R.id.tvShow) ;
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

    public void save(View view) {
        sf = getSharedPreferences("prefName",MODE_PRIVATE);
        SharedPreferences.Editor ed = sf.edit();
        st = etData.getText().toString();
        tvShow.setText(st);
        ed.putString("text",st);
        ed.commit();
    }
}