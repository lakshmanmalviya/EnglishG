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

import com.example.englishg.Adaptes.VocabLessonAdapter;
import com.example.englishg.Classes.RecyclerItemClickListener;
import com.example.englishg.Modals.VocabLessonModal;
import com.example.englishg.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VocabTodelete extends AppCompatActivity {
    RecyclerView vocabTDRec;
    ArrayList<VocabLessonModal> vllist;
    FirebaseDatabase myDatabase;
    final String allVocabLessons = "allvocablessons";
    //   final String vocabImoji = "vocabImoji";
//    final String vocabLessonText = "vocabLessonText";
    String vocabLessonId = "vocabLessonId";
    ProgressBar progressBar5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab_todelete);
        getSupportActionBar().hide();
        vocabTDRec = findViewById(R.id.vocabTDRec);
        progressBar5 = findViewById(R.id.progressBar5);
        vllist = new ArrayList<>();
        myDatabase = FirebaseDatabase.getInstance();
        VocabLessonAdapter adapter = new VocabLessonAdapter(vllist,this);
        vocabTDRec.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        vocabTDRec.setLayoutManager(layoutManager);
        myDatabase.getReference().child(allVocabLessons).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                vllist.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    VocabLessonModal phrase = dataSnapshot.getValue(VocabLessonModal.class);
                    vllist.add(phrase);
                }
                adapter.notifyDataSetChanged();
                progressBar5.setVisibility(View.GONE);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), ""+error, Toast.LENGTH_SHORT).show();
            }
        });
        vocabTDRec.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), vocabTDRec, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                VocabLessonModal vmodal = vllist.get(position);
                Intent intent = new Intent(getApplicationContext(),DeleteVocab.class);
                intent.putExtra(vocabLessonId,vmodal.getVocabLessonId());
                startActivity(intent);
//                Toast.makeText(getApplicationContext(), "Long Press to delete", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onLongItemClick(View view, int position) {
//                FirebaseDatabase.getInstance().getReference(allVocabLessons).child(vocabLessonId).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Toast.makeText(getApplicationContext(), "The Lesson is deleted", Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
        }));
    }
}