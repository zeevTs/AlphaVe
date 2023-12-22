package com.example.alphave;

import static com.example.alphave.FBRefs.refImg;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Camera extends AppCompatActivity {
    private ImageView iV2;
    private Intent toLog, toSign, toMain, toGal, toCam, iCamera,toNot,toMap,toBrod,toPref,toChat;
    private final int CAMERA_PERMISSION_CODE = 100;
    private final int CAMERA_REQUEST_CODE = 101;
    private StorageReference storageReference;
    private ProgressDialog prog;
    private Uri imageUri;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        initViews();
    }

    private void initViews() {
        iV2 = findViewById(R.id.iV2);
        toLog = new Intent(Camera.this, LogIn.class);
        toGal = new Intent(Camera.this, Gallery1.class);
        toCam = new Intent(Camera.this, Camera.class);
        toMain = new Intent(Camera.this, Main.class);
        toSign = new Intent(Camera.this, MainActivity.class);
        toNot = new Intent(Camera.this, Notification.class);
        toMap = new Intent(Camera.this,GoogleMaps.class);
        toBrod = new Intent(Camera.this,NetworkBrodcast.class);
        toPref = new Intent(Camera.this,SharedPref.class);
        toChat = new Intent(Camera.this,ChatsActivity.class);
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


    public void camera(View view) {
        askCamPer();
    }

    private void askCamPer() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA},CAMERA_PERMISSION_CODE);

        }else{
            openCam();
        }
    }
    

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == CAMERA_PERMISSION_CODE){
            if(grantResults.length<0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openCam();
            }else{
                Toast.makeText(this, "Camera Permission is Required to Use camera ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openCam() {
        iCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(iCamera,CAMERA_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_REQUEST_CODE && resultCode==RESULT_OK ){
            bitmap = (Bitmap) data.getExtras().get("data");
            iV2.setImageBitmap(bitmap);
        }
    }


    public void upload(View view) {


        prog =new ProgressDialog(this);
        prog.setTitle("uploading file....");
        prog.show();
        SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.US);
        Date now = new Date();
        String fileName = format.format(now);
        StorageReference refImage = refImg.child(fileName+".jpg");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();
        refImage.putBytes(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        prog.dismiss();
                        iV2.setImageBitmap(null);
                        Toast.makeText(Camera.this, "Successfully Uploaded", Toast.LENGTH_LONG).show();
                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        prog.dismiss();
                        Toast.makeText(Camera.this, "Upload failed", Toast.LENGTH_LONG).show();
                    }
                });

    }
}