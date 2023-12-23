package com.example.englishg.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.englishg.Adaptes.SentenceAdapter;
import com.example.englishg.Adaptes.VocabAdapter;
import com.example.englishg.Modals.SentenceModal;
import com.example.englishg.Modals.VocabModal;
import com.example.englishg.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ReadVocabs extends AppCompatActivity {
    ProgressBar progressBar3;
    RecyclerView readVocabRec;
    final String allvocablessons= "allvocablessons";
    final String vocabcontent = "vocabcontent";
    String  vocabLessonId ="vocabLessonId";
    ArrayList<VocabModal> slist;
    FirebaseDatabase myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_vocabs);
        getSupportActionBar().hide();
        readVocabRec = findViewById(R.id.readVocabRec);
        progressBar3 = findViewById(R.id.progressBar3);
        slist = new ArrayList<>();
        vocabLessonId = getIntent().getStringExtra(vocabLessonId);
        myDatabase = FirebaseDatabase.getInstance();
        VocabAdapter adapter = new VocabAdapter(slist,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        readVocabRec.setAdapter(adapter);
        readVocabRec.setLayoutManager(layoutManager);
        myDatabase.getReference().child(allvocablessons).child(vocabLessonId).child(vocabcontent)
                .addValueEventListener(new ValueEventListener(){
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            slist.clear();
                            for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                                VocabModal vmodal = dataSnapshot.getValue(VocabModal.class);
                                slist.add(vmodal);
                            }
                            adapter.notifyDataSetChanged();
                            progressBar3.setVisibility(View.GONE);
                        }
                        else{
                            progressBar3.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "Not a single vocab is there", Toast.LENGTH_SHORT).show();
                        }


                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(), ""+error, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}