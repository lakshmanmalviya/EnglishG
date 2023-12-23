package com.example.englishg.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.englishg.Activities.UpdateLessons;
import com.example.englishg.Activities.UpdateSentences;
import com.example.englishg.R;

public class UpdateOptions extends Fragment {

    public UpdateOptions() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        TextView updatePhrases,updateSentences;
        View view =  inflater.inflate(R.layout.fragment_update_options, container, false);
           updatePhrases = view.findViewById(R.id.updatePhrases);
           updateSentences = view.findViewById(R.id.updateSentences);
          updatePhrases.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(getContext(), UpdateLessons.class);
                  startActivity(intent);
              }
          });
        updateSentences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UpdateSentences.class);
                startActivity(intent);
            }
        });
      return view;
    }
}