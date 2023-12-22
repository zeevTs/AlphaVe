package com.example.alphave;

import static com.example.alphave.FBRefs.refAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity {
    private EditText eTLpas;
    private EditText eTLemail;
    private Intent toLog, toSign, toMain, toGal, toCam,toNot,toMap,toBrod,toPref,toChat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        initViews();
    }

    private void initViews() {
        eTLemail = findViewById(R.id.eTLemail);
        eTLpas = findViewById(R.id.eTLpas);
        toSign = new Intent(LogIn.this, MainActivity.class);
        toGal = new Intent(LogIn.this, Gallery1.class);
        toCam = new Intent(LogIn.this, Camera.class);
        toMain = new Intent(LogIn.this, Main.class);
        toNot = new Intent(LogIn.this, Notification.class);
        toMap = new Intent(LogIn.this, GoogleMaps.class);
        toBrod = new Intent(LogIn.this, NetworkBrodcast.class);
        toPref = new Intent(LogIn.this, SharedPref.class );
        toChat = new Intent(LogIn.this,ChatsActivity.class);
    }

    public void logIn(View view) {
        String email = eTLemail.getText().toString();
        String password = eTLpas.getText().toString();

        refAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = refAuth.getCurrentUser();
                            String uid = user.getUid();
                            //database
                            Toast.makeText(LogIn.this, "user created successfully "+""+ uid, Toast.LENGTH_SHORT).show();
                            Intent si = new Intent(LogIn.this,Main.class);
                            startActivity(si);
                        }else{
                                Toast.makeText(LogIn.this, "user creation failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

    public void register(View view) {
        Intent toLog = new Intent(LogIn.this, MainActivity.class);
        startActivity(toLog);
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
        } else if (s1.equals("Main")) {
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
}