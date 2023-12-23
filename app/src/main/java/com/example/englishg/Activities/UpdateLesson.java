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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class UpdateLesson extends AppCompatActivity {
    FirebaseDatabase myDatabase;
    final String allPhrases = "allphrases";
    String lessonKey = "lessonKey";
    String imoji = "imoji";
    String phrase = "phrase";
    String Fimoji = "imoji";
    String Fphrase = "phrase";
    EditText updateImoji,updatePhrase;
    Button updatePhraseBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_lesson);
        getSupportActionBar().hide();
        updateImoji = findViewById(R.id.updateImoji);
        updatePhrase = findViewById(R.id.updatePhrase);
        updatePhraseBtn = findViewById(R.id.updatePhraseBtn);
        lessonKey = getIntent().getStringExtra(lessonKey);
        imoji = getIntent().getStringExtra(imoji);
        phrase = getIntent().getStringExtra(phrase);
        updateImoji.setText(imoji);
        updatePhrase.setText(phrase);
        updatePhraseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (updateImoji.getText().toString().isEmpty()|| updatePhrase.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "The area is empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    Map<String, Object> map = new HashMap<>();
                    map.put(Fimoji,updateImoji.getText().toString());
                    map.put(Fphrase,updatePhrase.getText().toString());
                    FirebaseDatabase.getInstance().getReference(allPhrases).child(lessonKey).updateChildren(map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Updatation Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


    }
}