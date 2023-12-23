package com.example.englishg.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.englishg.Activities.DeleteVocabLesson;
import com.example.englishg.Activities.LessonSenToDelete;
import com.example.englishg.Activities.LessonToDelete;
import com.example.englishg.Activities.VocabTodelete;
import com.example.englishg.R;

public class DeleteOptions extends Fragment {
    public DeleteOptions()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        TextView deletePhrases,deleteSentences,deleteVocavLesson,deleteVocabs;
        View view =  inflater.inflate(R.layout.fragment_delete_options, container, false);
       deletePhrases = view.findViewById(R.id.deletePhrases);
        deleteSentences = view.findViewById(R.id.deleteSentences);
        deleteVocavLesson = view.findViewById(R.id.deleteVocavLesson);
        deleteVocabs = view.findViewById(R.id.deleteVocabs);
       deletePhrases.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getContext(), LessonToDelete.class);
               startActivity(intent);
           }
       });
        deleteSentences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LessonSenToDelete.class);
                startActivity(intent);
            }
        });
        deleteVocavLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DeleteVocabLesson.class);
                startActivity(intent);
            }
        });
        deleteVocabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), VocabTodelete.class);
                startActivity(intent);
            }
        });
        return  view;
    }
}