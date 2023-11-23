package com.example.alphave;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Notification extends AppCompatActivity {
    private Intent toLog, toSign, toMain, toGal, toCam, toNot,toMap,toBrod,toPref;
    private EditText etNot;
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    private int ALARM_RQST_CODE = 1;

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
        toNot = new Intent(Notification.this, Notification.class);
        toMap = new Intent(Notification.this,GoogleMaps.class);
        toBrod = new Intent(Notification.this,NetworkBrodcast.class);
        toPref = new Intent(Notification.this,SharedPref.class);
        etNot = findViewById(R.id.etNot);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
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

    public void notification(View view) {
        String text = etNot.getText().toString();
        NotificationHelper.showNotification(this, text);
    }

    public void notificationBtn(View view) {
        String text = etNot.getText().toString();
        NotificationHelper.showNotificationBtn(this, text);
    }

    public void notificationTime(View view) {
        openTimePickerDialog(true);
    }

    private void openTimePickerDialog(boolean is24r) {
        Calendar calendar = Calendar.getInstance();

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), is24r);
        timePickerDialog.setTitle("Choose time");
        timePickerDialog.show();
    }

    TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            Calendar calNow = Calendar.getInstance();
            Calendar calSet = (Calendar) calNow.clone();

            calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calSet.set(Calendar.MINUTE, minute);
            calSet.set(Calendar.SECOND, 0);
            calSet.set(Calendar.MILLISECOND, 0);

            if (calSet.compareTo(calNow) <= 0) {
                calSet.add(Calendar.DATE, 1);
            }
            setAlarm(calSet);
        }
    };

    private void setAlarm(Calendar calSet) {
        ALARM_RQST_CODE++;
        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra("msg",etNot.getText().toString()+"  "+ALARM_RQST_CODE);
        alarmIntent = PendingIntent.getBroadcast(this,
                ALARM_RQST_CODE, intent, PendingIntent.FLAG_IMMUTABLE);
        alarmMgr = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        alarmMgr.set(AlarmManager.RTC_WAKEUP,
                calSet.getTimeInMillis(), alarmIntent);
        Toast.makeText(this, "Alarm in "+ String.valueOf(calSet.getTime()), Toast.LENGTH_SHORT).show();
    }

}