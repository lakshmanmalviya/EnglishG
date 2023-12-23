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
import com.example.englishg.Adaptes.VocabLessonAdapter;
import com.example.englishg.Classes.RecyclerItemClickListener;
import com.example.englishg.Modals.PhraseModal;
import com.example.englishg.Modals.VocabLessonModal;
import com.example.englishg.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllVocabLesson extends AppCompatActivity {
   RecyclerView vocabLessonRec;
    ArrayList<VocabLessonModal> vllist;
    FirebaseDatabase myDatabase;
    final String allVocabLessons = "allvocablessons";
    final String vocabImoji = "vocabImoji";
    final String vocabLessonText = "vocabLessonText";
    String vocabLessonId = "vocabLessonId";
    ProgressBar progressBar2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_vocab_lesson);
        getSupportActionBar().hide();
        vocabLessonRec = findViewById(R.id.vocabLessonRec);
        progressBar2 = findViewById(R.id.progressBar2);
        vllist = new ArrayList<>();
        myDatabase = FirebaseDatabase.getInstance();
        VocabLessonAdapter adapter = new VocabLessonAdapter(vllist,this);
        vocabLessonRec.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        vocabLessonRec.setLayoutManager(layoutManager);
        myDatabase.getReference().child(allVocabLessons).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                vllist.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    VocabLessonModal phrase = dataSnapshot.getValue(VocabLessonModal.class);
                    vllist.add(phrase);
                }
                adapter.notifyDataSetChanged();
                progressBar2.setVisibility(View.GONE);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), ""+error, Toast.LENGTH_SHORT).show();
            }
        });
        vocabLessonRec.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), vocabLessonRec, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                VocabLessonModal vmodal = vllist.get(position);
                Intent intent = new Intent(getApplicationContext(),ReadVocabs.class);
                intent.putExtra(vocabLessonId,vmodal.getVocabLessonId());
//               Toast.makeText(getApplicationContext(), vmodal.getVocabLessonId()+" clicked "+position, Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
            @Override
            public void onLongItemClick(View view, int position) {
            }
        }));

    }
}