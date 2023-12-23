package com.example.englishg.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

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

public class InsertVocabLesson extends AppCompatActivity {
    final String allVocabLessons = "allvocablessons";
    final String vocabImoji = "vocabImoji";
    final String vocabLessonText = "vocabLessonText";
      String vocabLessonId = "vocabLessonId";
    FirebaseDatabase database;
    EditText insertVocabImoji,insertVocabLesson;
    Button insertVLBtn;
//    ArrayList<PhraseModal> vllist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_vocab_lesson);
        getSupportActionBar().hide();
        insertVocabImoji = findViewById(R.id.insertVocabImoji);
        insertVocabLesson = findViewById(R.id.insertVocabLesson);
        insertVLBtn = findViewById(R.id.insertVLBtn);
        database = FirebaseDatabase.getInstance();

        insertVLBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (insertVocabImoji.getText().toString().isEmpty()||insertVocabLesson.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "The text area is empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference myref = firebaseDatabase.getReference().child(allVocabLessons).push();
                    Map<String,Object> map = new HashMap<>();
                    map.put(vocabImoji,insertVocabImoji.getText().toString());
                    map.put(vocabLessonText,insertVocabLesson.getText().toString());
                    map.put(vocabLessonId,myref.getKey());
                    myref.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "lesson inserted 🤩 😎 😍", Toast.LENGTH_SHORT).show();
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