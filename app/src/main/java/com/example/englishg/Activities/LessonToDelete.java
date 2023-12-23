package com.example.englishg.Activities;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LessonToDelete extends AppCompatActivity {
    RecyclerView deleteRec;
    ArrayList<PhraseModal> list;
    FirebaseDatabase myDatabase;
    final String allPhrases = "allphrases";
   final  String  lessonContent = "lessoncontent";
    String lessonId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_to_delete);
        getSupportActionBar().hide();
        deleteRec = findViewById(R.id.deleteRec);
        list = new ArrayList<>();
        myDatabase = FirebaseDatabase.getInstance();
        PhraseAdapter adapter = new PhraseAdapter(list,this);
        deleteRec.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        deleteRec.setLayoutManager(layoutManager);
        myDatabase.getReference().child(allPhrases).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    PhraseModal phrase = dataSnapshot.getValue(PhraseModal.class);
                    list.add(phrase);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), ""+error, Toast.LENGTH_SHORT).show();
            }
        });
        deleteRec.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), deleteRec, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
               Toast.makeText(getApplicationContext(), "Long Press To Delete", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onLongItemClick(View view, int position) {
                PhraseModal pmodal = list.get(position);
                lessonId = pmodal.getLessonId();
                FirebaseDatabase.getInstance().getReference().child(allPhrases).child(lessonId).removeValue();
                Toast.makeText(getApplicationContext(), "Deleted Successfully",Toast.LENGTH_SHORT).show();
              }
        }));

    }
}