package com.example.englishg.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.englishg.Adaptes.VocabAdapter;
import com.example.englishg.Classes.RecyclerItemClickListener;
import com.example.englishg.Modals.VocabLessonModal;
import com.example.englishg.Modals.VocabModal;
import com.example.englishg.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DeleteVocab extends AppCompatActivity {
    ProgressBar progressBar6;
    RecyclerView deleteVocabRec;
    final String allvocablessons= "allvocablessons";
    final String vocabcontent = "vocabcontent";
    String  vocabLessonId ="vocabLessonId";
    String  vocabId ="vocabId";
    ArrayList<VocabModal> slist;
    FirebaseDatabase myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_vocab);
        getSupportActionBar().hide();
        deleteVocabRec = findViewById(R.id.deleteVocabRec);
        progressBar6 = findViewById(R.id.progressBar6);
        slist = new ArrayList<>();
        vocabLessonId = getIntent().getStringExtra(vocabLessonId);
        myDatabase = FirebaseDatabase.getInstance();
        VocabAdapter adapter = new VocabAdapter(slist,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        deleteVocabRec.setAdapter(adapter);
        deleteVocabRec.setLayoutManager(layoutManager);
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
                            progressBar6.setVisibility(View.GONE);
                        }
                        else{
                            progressBar6.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "Not a single vocab is there", Toast.LENGTH_SHORT).show();
                        }


                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(), ""+error, Toast.LENGTH_SHORT).show();
                    }
                });
        deleteVocabRec.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), deleteVocabRec, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getApplicationContext(), "Long Press to delete", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onLongItemClick(View view, int position) {
                VocabModal vmodal = slist.get(position);
                vocabId= vmodal.getVocabId();
                FirebaseDatabase.getInstance().getReference(allvocablessons).child(vocabLessonId).child(vocabcontent).child(vocabId)
                        .removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(), "The Vocab is deleted", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }));

    }
}