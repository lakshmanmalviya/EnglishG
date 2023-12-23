package com.example.englishg.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.englishg.Activities.AllLessons;
import com.example.englishg.Activities.AllVocabLesson;
import com.example.englishg.R;

public class HomeUser extends Fragment {
    public HomeUser() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        TextView phraseTv,vocabularyTv;
        View view =  inflater.inflate(R.layout.fragment_home_user, container, false);
        phraseTv = view.findViewById(R.id.phrasesTv);
        vocabularyTv = view.findViewById(R.id.vocabularyTv);
      phraseTv.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(getContext(), AllLessons.class);
              startActivity(intent);
          }
      });
        vocabularyTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AllVocabLesson.class);
                startActivity(intent);
            }
        });
        return  view;
    }
}