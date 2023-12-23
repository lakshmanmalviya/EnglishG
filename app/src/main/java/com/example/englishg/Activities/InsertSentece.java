package com.example.englishg.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.englishg.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class InsertSentece extends AppCompatActivity {
    final  String AllPhrases = "allphrases";
    final String lessonContent = "lessoncontent";
      String lessonKey = "lessonKey";
      String english= "english";
      String hindi= "hindi";
      String sentenceid= "sentenceid";
    EditText englishSen,hindiSen;
    Button insertSen;
    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_senteces);
        getSupportActionBar().hide();
        englishSen = findViewById(R.id.englishSen);
        hindiSen = findViewById(R.id.hindiSen);
        insertSen = findViewById(R.id.insertSen);
        lessonKey = getIntent().getStringExtra(lessonKey);
        firebaseDatabase = FirebaseDatabase.getInstance();
        insertSen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (englishSen.getText().toString().isEmpty()||hindiSen.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "The area is empty", Toast.LENGTH_SHORT).show();
                }
                else{
//                    firebaseDatabase.getReference().child(AllPhrases).child(lessonKey).child(lessonContent);
                    // code goes here to insert sentences to a lesson
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference myref = firebaseDatabase.getReference().child(AllPhrases).child(lessonKey).child(lessonContent).push();
                    Map<String,Object> map = new HashMap<>();
                    map.put(english,englishSen.getText().toString());
                    map.put(hindi,hindiSen.getText().toString());
                    map.put(sentenceid,myref.getKey());
                    myref.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Sentence Inserted 😎 🤩 😊", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Insertion Failed 😋 🤔 🙄", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}