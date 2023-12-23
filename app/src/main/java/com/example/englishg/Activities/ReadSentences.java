package com.example.englishg.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.englishg.Adaptes.PhraseAdapter;
import com.example.englishg.Adaptes.SentenceAdapter;
import com.example.englishg.Modals.PhraseModal;
import com.example.englishg.Modals.SentenceModal;
import com.example.englishg.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReadSentences extends AppCompatActivity {
   RecyclerView  readSenRec;
    String lessonKey = "lessonKey";
    final String allPhrases ="allphrases";
    final String lessonContent ="lessoncontent";
    ArrayList<SentenceModal> slist;
    FirebaseDatabase myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_sentences);
        getSupportActionBar().hide();
        readSenRec = findViewById(R.id.readSenRec);
        slist = new ArrayList<>();
        lessonKey = getIntent().getStringExtra(lessonKey);
        myDatabase = FirebaseDatabase.getInstance();
        SentenceAdapter adapter = new SentenceAdapter(slist,this);
        readSenRec.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        readSenRec.setLayoutManager(layoutManager);
        myDatabase.getReference().child(allPhrases).child(lessonKey).child(lessonContent)
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                slist.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    SentenceModal smodal = dataSnapshot.getValue(SentenceModal.class);
                    slist.add(smodal);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), ""+error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}