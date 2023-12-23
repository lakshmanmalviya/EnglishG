package com.example.englishg.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.englishg.Adaptes.VocabLessonAdapter;
import com.example.englishg.Classes.RecyclerItemClickListener;
import com.example.englishg.Modals.VocabLessonModal;
import com.example.englishg.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class InsertVocabToLesson extends AppCompatActivity {
    RecyclerView insertVTLRc;
    ArrayList<VocabLessonModal> IVList;
    FirebaseDatabase database;
    final String allVocabLessons = "allvocablessons";
    final String vocabImoji = "vocabImoji";
    final String vocabLessonText = "vocabLessonText";
    String vocabLessonId = "vocabLessonId";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_vocab_to_lesson);
        getSupportActionBar().hide();
        insertVTLRc = findViewById(R.id.insertVTLRc);
        IVList = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        VocabLessonAdapter adapter = new VocabLessonAdapter(IVList,this);
        insertVTLRc.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        insertVTLRc.setLayoutManager(layoutManager);
        database.getReference().child(allVocabLessons).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                IVList.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    VocabLessonModal phrase = dataSnapshot.getValue(VocabLessonModal.class);
                    IVList.add(phrase);
                }
                adapter.notifyDataSetChanged();
//                progressBar2.setVisibility(View.GONE);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), ""+error, Toast.LENGTH_SHORT).show();
            }
        });
        insertVTLRc.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), insertVTLRc, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                VocabLessonModal vmodal = IVList.get(position);
                Intent intent = new Intent(getApplicationContext(),InsertVocab.class);
                intent.putExtra(vocabLessonId,vmodal.getVocabLessonId());
//                Toast.makeText(getApplicationContext(), pmodal.getLessonId()+" clicked "+position, Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
            @Override
            public void onLongItemClick(View view, int position) {
            }
        }));

    }
}