package com.example.englishg.Activities;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.englishg.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class InsertVocab extends AppCompatActivity {
     ActivityResultLauncher<String> launcher;
     FirebaseDatabase database;
     final String allVocabLessons = "allvocablessons";
     String vocabLessonId = "vocabLessonId";
     final String vocabcontent = "vocabcontent";
     final String vocabimage = "vocabimage";
     final String vocabName = "vocabName";
     final String vocabMeaning = "vocabMeaning";
     String vocabId = "vocabId";
     EditText insertVocabName,insertVocabMean;
     Button browseImgBtn,insertVocabBtn;
     ImageView insertVocabImg;
     FirebaseStorage storage;
     DatabaseReference myref;
     String imageUrl ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_vocab);
        getSupportActionBar().hide();
        insertVocabName = findViewById(R.id.insertVocabName);
        insertVocabMean = findViewById(R.id.insertVocabMean);
        browseImgBtn = findViewById(R.id.browseImgBtn);
        insertVocabBtn = findViewById(R.id.insertVocabBtn);
        insertVocabImg = findViewById(R.id.insertVocabImg);
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();
        vocabLessonId = getIntent().getStringExtra(vocabLessonId);
        launcher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri uri) {
                insertVocabImg.setImageURI(uri);
                final StorageReference reference = storage.getReference().child(vocabimage).child(vocabimage+new Date().getTime());
                reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
//                             Toast.makeText(getApplicationContext(), "ImageURL Assigned", Toast.LENGTH_SHORT).show();
                                imageUrl = uri.toString();
                            }
                        });
                    }
                });
            }
        });
        browseImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher.launch("image/*");
            }
        });
        insertVocabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (insertVocabName.getText().toString().isEmpty()||insertVocabMean.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "The area is empty", Toast.LENGTH_SHORT).show();
                }

                else{
                    try {
                        Thread.sleep(3000);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // code goes here to insert sentences to a lesson
                    myref = database.getReference().child(allVocabLessons).child(vocabLessonId).child(vocabcontent).push();
                    Map<String,Object> map = new HashMap<>();
                    map.put(vocabName,insertVocabName.getText().toString());
                    map.put(vocabMeaning,insertVocabMean.getText().toString());
                    map.put(vocabId,myref.getKey());
                    map.put(vocabimage,imageUrl);
                    myref.setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                                Toast.makeText(getApplicationContext(), "Vocab Inserted 😎 🤩 😊", Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                                Toast.makeText(getApplicationContext(), "Insertion Failed 😋 🤔 🙄"+e, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}