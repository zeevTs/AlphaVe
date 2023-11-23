package com.example.alphave;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class NetworkBrodcast extends AppCompatActivity {
    private Button btn;
    private NetworkStateReceiver networkStateReceiver;
    private boolean checkData = false;
    private Intent toLog, toSign, toMain, toGal, toCam, toNot,toMap,toBrod,toPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_b);
        initViews();
    }

    private void initViews() {
        btn = findViewById(R.id.btnOnOff);
        toLog = new Intent(NetworkBrodcast.this, LogIn.class);
        toGal = new Intent(NetworkBrodcast.this, Gallery1.class);
        toCam = new Intent(NetworkBrodcast.this, Camera.class);
        toMain = new Intent(NetworkBrodcast.this, Main.class);
        toSign = new Intent(NetworkBrodcast.this, MainActivity.class);
        toNot = new Intent(NetworkBrodcast.this, Notification.class);
        toMap = new Intent(NetworkBrodcast.this,GoogleMaps.class);
        toBrod = new Intent(NetworkBrodcast.this,NetworkBrodcast.class);
        toPref = new Intent(NetworkBrodcast.this,SharedPref.class);
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (checkData) {
            unregisterReceiver(networkStateReceiver);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter connectFilter2 = new IntentFilter();
        connectFilter2.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkStateReceiver, connectFilter2);
    }

    public void turnOnOff(View view) {
        if (!checkData) {
            networkStateReceiver = new NetworkStateReceiver();

            IntentFilter connectFilter = new IntentFilter();
            connectFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

            registerReceiver(networkStateReceiver, connectFilter);
            checkData = true;
            btn.setText("Turn off Data Receiver");
        } else {
            unregisterReceiver(networkStateReceiver);
            checkData = false;
            btn.setText("Turn on Data Receiver");
        }
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