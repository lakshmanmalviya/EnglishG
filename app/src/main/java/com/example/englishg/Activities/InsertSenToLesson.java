package com.example.englishg.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class InsertSenToLesson extends AppCompatActivity {
   RecyclerView insertSenRec;
    ArrayList<PhraseModal> list;
    FirebaseDatabase myDatabase;
    final String allPhrases = "allphrases";
    String lessonKey = "lessonKey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_sen_to_lesson);
        getSupportActionBar().hide();
        insertSenRec = findViewById(R.id.senToLessonRec);
        list = new ArrayList<>();
        myDatabase = FirebaseDatabase.getInstance();
        PhraseAdapter adapter = new PhraseAdapter(list,this);
        insertSenRec.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        insertSenRec.setLayoutManager(layoutManager);
        myDatabase.getReference().child(allPhrases).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    PhraseModal phrase = dataSnapshot.getValue(PhraseModal.class);
//                    PhraseModal phrase = dataSnapshot.get
                    list.add(phrase);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
             Toast.makeText(getApplicationContext(), ""+error, Toast.LENGTH_SHORT).show();
            }
        });
        insertSenRec.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), insertSenRec, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                PhraseModal pmodal = list.get(position);
                Intent intent = new Intent(getApplicationContext(),InsertSentece.class);
                intent.putExtra(lessonKey,pmodal.getLessonId());
//                Toast.makeText(getApplicationContext(), pmodal.getLessonId()+"clicked "+position, Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {
            }
        }));

    }
}