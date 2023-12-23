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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DeleteSentences extends AppCompatActivity {
//    not solve some problem yet
    RecyclerView deleteSenRec;
    String lessonKey = "lessonKey";
    final String allPhrases ="allphrases";
    final String lessonContent ="lessoncontent";
    ArrayList<SentenceModal> slist;
    FirebaseDatabase myDatabase;
    String lessonContentKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_sentences);
        getSupportActionBar().hide();
        deleteSenRec = findViewById(R.id.deleteSenRec);
        slist = new ArrayList<>();
        lessonKey = getIntent().getStringExtra(lessonKey);
//        Toast.makeText(getApplicationContext(), ""+lessonKey, Toast.LENGTH_SHORT).show();
        myDatabase = FirebaseDatabase.getInstance();
        SentenceAdapter adapter = new SentenceAdapter(slist,this);
        deleteSenRec.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        deleteSenRec.setLayoutManager(layoutManager);
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
        deleteSenRec.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), deleteSenRec, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getApplicationContext(), " Long Press to DELETE ", Toast.LENGTH_SHORT).show();
               }
            @Override
            public void onLongItemClick(View view, int position) {
                SentenceModal smodal = slist.get(position);
                lessonContentKey = smodal.getSentenceid();
                FirebaseDatabase.getInstance().getReference()
                        .child(allPhrases).child(lessonKey).child(lessonContent).child(lessonContentKey).removeValue()
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(), "Sentence is DELETED", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }));

    }
}