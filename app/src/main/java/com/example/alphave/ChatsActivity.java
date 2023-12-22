package com.example.alphave;

import static com.example.alphave.FBRefs.refChats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChatsActivity extends AppCompatActivity {
    private ListView lV1;
    private EditText eTdata;
    private String  str2,strtmp;
    private Intent toLog, toSign, toMain, toGal, toCam, iCamera,toNot,toMap,toBrod,toPref,toChat;
    private ArrayList<Chat> chatValues ;
    private ArrayList<String> chatList ;
    private ArrayAdapter<String> adp;
    private ValueEventListener chatListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);
        initviews();


    }


    @Override
    protected void onResume() {
        super.onResume();
        ValueEventListener chatListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dS) {
                chatList.clear();
                chatValues.clear();
                for(DataSnapshot data : dS.getChildren()) {
                    //str1 = (String) data.getKey();
                    Chat chatTmp = data.getValue(Chat.class);
                    chatValues.add(chatTmp);
                    //str2 = chatTmp.getText();
                    chatList.add(chatTmp.getNum() +"--"+ chatTmp.getText());
                }
                adp = new ArrayAdapter<String>(ChatsActivity.this, androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item, chatList);
                lV1.setAdapter(adp);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        };
        refChats.addValueEventListener(chatListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (chatListener!=null) {
            refChats.removeEventListener(chatListener);
        }
    }

    private void initviews() {
        lV1 = findViewById(R.id.lV1);
        eTdata = findViewById(R.id.eTdata);
        toLog = new Intent(ChatsActivity.this, LogIn.class);
        toGal = new Intent(ChatsActivity.this, Gallery1.class);
        toCam = new Intent(ChatsActivity.this, Camera.class);
        toMain = new Intent(ChatsActivity.this, Main.class);
        toSign = new Intent(ChatsActivity.this, MainActivity.class);
        toNot = new Intent(ChatsActivity.this, Notification.class);
        toMap = new Intent(ChatsActivity.this,GoogleMaps.class);
        toBrod = new Intent(ChatsActivity.this,NetworkBrodcast.class);
        toPref = new Intent(ChatsActivity.this,SharedPref.class);
        toChat = new Intent(ChatsActivity.this,ChatsActivity.class);

        chatValues = new ArrayList<>();
        chatList = new ArrayList<>();
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



    public void write(View view) {
        String text = eTdata.getText().toString();
        Chat chat = new Chat(text);
        refChats.child("msg " + chat.getNum()).setValue(chat);
        eTdata.setText("");
    }
}