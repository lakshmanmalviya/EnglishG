package com.example.englishg.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.englishg.Activities.InsertPhrase;
import com.example.englishg.Activities.InsertSenToLesson;
import com.example.englishg.Activities.InsertVocabLesson;
import com.example.englishg.Activities.InsertVocabToLesson;
import com.example.englishg.MainActivity;
import com.example.englishg.R;
import com.google.firebase.auth.FirebaseAuth;

public class InsertOption extends Fragment {

    public InsertOption() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        TextView insertPhrases,insertSentences,logOut,insertVocabLessons,insertVocabs;
        FirebaseAuth myauth;
        View view =  inflater.inflate(R.layout.fragment_insert_option, container, false);
        insertPhrases = view.findViewById(R.id.insertPhrases);
        insertSentences = view.findViewById(R.id.insertSentences);
        insertVocabLessons = view.findViewById(R.id.insertVocabLessons);
        insertVocabs = view.findViewById(R.id.insertVocabs);
        myauth = FirebaseAuth.getInstance();
        logOut = view.findViewById(R.id.logOut);
        insertPhrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), InsertPhrase.class);
                startActivity(intent);
            }
        });
        insertSentences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), InsertSenToLesson.class);
                startActivity(intent);
            }
        });
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myauth.signOut();
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        insertVocabLessons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), InsertVocabLesson.class);
                startActivity(intent);
            }
        });
        insertVocabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), InsertVocabToLesson.class);
//                Intent intent = new Intent(getContext(), InsertPhrase.class);
                startActivity(intent);
            }
        });
        return  view;
    }
}