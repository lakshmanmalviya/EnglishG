package com.example.englishg.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.englishg.Modals.SentenceModal;
import com.example.englishg.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UpdateSentence extends AppCompatActivity {
  EditText updateEnglish,updateHindi;
  Button updateSenBtn;
    String lessonKey = "lessonKey";
    String FlessonKey = "lessonKey";
    final String allPhrases ="allphrases";
    final String lessonContent ="lessoncontent";
    String sentenceKey = "sentenceKey";
    final String Fenglish = "english";
    final String Fhindi = "hindi";
    String english = "english";
    String hindi = "hindi";
    ArrayList<SentenceModal> slist;
    FirebaseDatabase myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sentence);
        getSupportActionBar().hide();
        updateEnglish = findViewById(R.id.updateEnglish);
        updateHindi = findViewById(R.id.updateHindi);
        updateSenBtn = findViewById(R.id.updateSenBtn);
        lessonKey = getIntent().getStringExtra(lessonKey);
        sentenceKey = getIntent().getStringExtra(sentenceKey);
        english = getIntent().getStringExtra(english);
        hindi = getIntent().getStringExtra(hindi);
          updateEnglish.setText(english);
          updateHindi.setText(hindi);
          updateSenBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  if(updateEnglish.getText().toString().isEmpty()||updateHindi.getText().toString().isEmpty()){
                      Toast.makeText(getApplicationContext(), "The area is empty", Toast.LENGTH_SHORT).show();
                  }
                  else{
                      Map<String ,Object> map = new HashMap<>();
                      map.put(Fenglish,updateEnglish.getText().toString());
                      map.put(Fhindi,updateHindi.getText().toString());
                      FirebaseDatabase.getInstance().getReference().child(allPhrases).child(lessonKey)
                              .child(lessonContent).child(sentenceKey).updateChildren(map)
                              .addOnSuccessListener(new OnSuccessListener<Void>() {
                                  @Override
                                  public void onSuccess(Void unused) {
                                      Toast.makeText(getApplicationContext(), "Sentence Updated", Toast.LENGTH_SHORT).show();
                                  }
                              }).addOnFailureListener(new OnFailureListener() {
                          @Override
                          public void onFailure(@NonNull Exception e) {
                              Toast.makeText(getApplicationContext(), "updation Failed", Toast.LENGTH_SHORT).show();
                          }
                      });

                  }
              }
          });

    }
}