package com.example.alphave;

import static com.example.alphave.FBRefs.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Firebase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Gallery1 extends AppCompatActivity {

    private ImageView iV1;
    private Button bTnG;
    private final int GALLERY_REQ_CODE= 1000;
    private Intent toLog, toSign, toMain, toGal, toCam,iGallery;
    private Uri imageUri;
    private ProgressDialog prog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery1);
        initViews();
    }

    private void initViews() {
        iGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        iV1 = findViewById(R.id.iV1);
        bTnG = findViewById(R.id.bTnG);
        toLog = new Intent(Gallery1.this, LogIn.class);
        toSign = new Intent(Gallery1.this, MainActivity.class);
        toCam = new Intent(Gallery1.this, Camera.class);
        toMain = new Intent(Gallery1.this, Main.class);
        toGal = new Intent(Gallery1.this, Gallery1.class);
    }

    public void gallery(View view) {
        startActivityForResult(iGallery, GALLERY_REQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data!=null && requestCode == GALLERY_REQ_CODE){
            imageUri = data.getData();
            iV1.setImageURI(imageUri);
        }
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
        }

        return super.onOptionsItemSelected(item);
    }

    public void upload(View view) {

        prog =new ProgressDialog(this);
        prog.setTitle("uploading file....");
        prog.show();
        SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.US);
        Date now = new Date();
        String fileName = format.format(now);
        StorageReference refImage = refImg.child(fileName+".jpg");
        refImage.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        iV1.setImageURI(null);
                        imageUri= null;
                        Toast.makeText(Gallery1.this, "Successfully Uploaded", Toast.LENGTH_LONG).show();
                        if(prog.isShowing()){
                            prog.dismiss();
                        }
                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if(prog.isShowing()){
                            prog.dismiss();
                        }
                        Toast.makeText(Gallery1.this, "Upload failed", Toast.LENGTH_LONG).show();
                    }
                });
    }
}