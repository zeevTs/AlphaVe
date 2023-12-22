package com.example.alphave;

import static com.example.alphave.FBRefs.refAuth;
import static com.example.alphave.FBRefs.refUsers;

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

public class MainActivity extends AppCompatActivity {
    private EditText eTemail, eTpas, eTname,eTid;
    private Intent toLog, toSign, toMain, toGal, toCam,toNot,toMap,toBrod,toPref,toChat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initviews();
    }

    private void initviews() {
        eTemail = findViewById(R.id.eTLemail);
        eTpas = findViewById(R.id.eTLpas);
        eTname =findViewById(R.id.eTName);
        eTid = findViewById(R.id.eTid);
        toLog = new Intent(MainActivity.this, LogIn.class);
        toGal = new Intent(MainActivity.this, Gallery1.class);
        toCam = new Intent(MainActivity.this, Camera.class);
        toMain = new Intent(MainActivity.this, Main.class);
        toSign = new Intent(MainActivity.this, MainActivity.class);
        toNot = new Intent(MainActivity.this,Notification.class);
        toMap = new Intent(MainActivity.this, GoogleMaps.class);
        toBrod = new Intent(MainActivity.this, NetworkBrodcast.class);
        toPref= new Intent(MainActivity.this, SharedPref.class);
        toChat = new Intent(MainActivity.this,ChatsActivity.class);
    }


    public void signUp(View view) {
        String email = eTemail.getText().toString();
        String password = eTpas.getText().toString();
        String name = eTname.getText().toString();
        String id = eTid.getText().toString();
        refAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = refAuth.getCurrentUser();
                            String uid = user.getUid();
                            Users users = new Users(name,password,email,id);
                            refUsers.child(id).setValue(users);
                            //database
                            Toast.makeText(MainActivity.this, "user created successfully "+""+ uid, Toast.LENGTH_SHORT).show();
                            Intent si = new Intent(MainActivity.this,Main.class);
                            startActivity(si);
                        }else{
                            if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(MainActivity.this, "email is already used", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(MainActivity.this, "user creation failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
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
        }else if (s1.equals("Chat")) {
            startActivity(toChat);
        }

        return super.onOptionsItemSelected(item);
    }

    public void logIn(View view) {
        startActivity(toLog);
    }

}