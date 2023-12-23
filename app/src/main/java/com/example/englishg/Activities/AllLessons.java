package com.example.englishg.Activities;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.englishg.Adaptes.PhraseAdapter;
import com.example.englishg.Classes.RecyclerItemClickListener;
import com.example.englishg.Modals.PhraseModal;
import com.example.englishg.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllLessons extends AppCompatActivity {
    ProgressBar progressBar;
   RecyclerView lessonRec;
   ArrayList<PhraseModal> list;
   FirebaseDatabase myDatabase;
   final String allPhrases = "allphrases";
   final  String lessonContent = "lessoncontent";
   String lessonKey = "lessonKey";
   int cnt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_lessons);
        getSupportActionBar().hide();
        lessonRec = findViewById(R.id.lessonRec);
        progressBar = findViewById(R.id.progressBar);
        list = new ArrayList<>();
        myDatabase = FirebaseDatabase.getInstance();
        PhraseAdapter adapter = new PhraseAdapter(list,this);
        lessonRec.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        lessonRec.setLayoutManager(layoutManager);
        myDatabase.getReference().child(allPhrases).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    PhraseModal phrase = dataSnapshot.getValue(PhraseModal.class);
                    list.add(phrase);
                    adapter.notifyDataSetChanged();
                }
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), ""+error, Toast.LENGTH_SHORT).show();
            }
        });
        lessonRec.addOnItemTouchListener(new RecyclerItemClickListener(AllLessons.this, lessonRec, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                PhraseModal pmodal = list.get(position);
                Intent intent = new Intent(AllLessons.this,ReadSentences.class);
                intent.putExtra(lessonKey,pmodal.getLessonId());
//                Toast.makeText(getApplicationContext(), pmodal.getLessonId()+" clicked "+position, Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
            @Override
            public void onLongItemClick(View view, int position) {
            }
        }));

    }
}