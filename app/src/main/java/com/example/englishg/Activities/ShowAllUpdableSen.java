package com.example.englishg.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.englishg.Adaptes.SentenceAdapter;
import com.example.englishg.Classes.RecyclerItemClickListener;
import com.example.englishg.Modals.PhraseModal;
import com.example.englishg.Modals.SentenceModal;
import com.example.englishg.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowAllUpdableSen extends AppCompatActivity {
    RecyclerView updatableSenRec;
    String lessonKey = "lessonKey";
    String FlessonKey = "lessonKey";
    final String allPhrases ="allphrases";
    final String lessonContent ="lessoncontent";
     String sentenceKey = "sentenceKey";
     String english = "english";
     String hindi = "hindi";
    ArrayList<SentenceModal> slist;
    FirebaseDatabase myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_updable_sen);
        getSupportActionBar().hide();
        updatableSenRec = findViewById(R.id.UpdatableSenRec);
        slist = new ArrayList<>();
        lessonKey = getIntent().getStringExtra(lessonKey);
        myDatabase = FirebaseDatabase.getInstance();
        SentenceAdapter adapter = new SentenceAdapter(slist,this);
        updatableSenRec.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        updatableSenRec.setLayoutManager(layoutManager);
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
        updatableSenRec.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), updatableSenRec, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                SentenceModal smodal = slist.get(position);
                Intent intent = new Intent(getApplicationContext(),UpdateSentence.class);
                intent.putExtra(FlessonKey,lessonKey);
                intent.putExtra(sentenceKey,smodal.getSentenceid());
                intent.putExtra(english,smodal.getEnglish());
                intent.putExtra(hindi,smodal.getHindi());
//                Toast.makeText(getApplicationContext(), pmodal.getLessonId()+"clicked "+position, Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {
            }
        }));
    }
}