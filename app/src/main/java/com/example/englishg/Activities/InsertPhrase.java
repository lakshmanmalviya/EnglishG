package com.example.englishg.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.englishg.Modals.PhraseModal;
import com.example.englishg.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InsertPhrase extends AppCompatActivity {
    final String AllPhrases = "allphrases";
    final String imoji = "imoji";
    final String phrase = "phrase";
    final String lessonId="lessonid";
    FirebaseDatabase database;
    EditText insertImoji,insertPhrase;
    Button insertPhraseBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_phrase);
        getSupportActionBar().hide();
        insertImoji = findViewById(R.id.insertImoji);
        insertPhrase = findViewById(R.id.insertLesson);
        insertPhraseBtn = findViewById(R.id.insertPhraseBtn);
        database = FirebaseDatabase.getInstance();
        insertPhraseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (insertImoji.getText().toString().isEmpty()||insertPhrase.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "The text area is empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                   DatabaseReference myref = firebaseDatabase.getReference().child(AllPhrases).push();
                    Map<String,Object> map = new HashMap<>();
                    map.put(imoji,insertImoji.getText().toString());
                    map.put(phrase,insertPhrase.getText().toString());
                    map.put(lessonId,myref.getKey());
                    myref.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
;                                     Toast.makeText(getApplicationContext(), "lesson inserted 🤩 😎 😍", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(), "Insertion Failed 😫 🙄 😏", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                       }

            }
        });

    }
}