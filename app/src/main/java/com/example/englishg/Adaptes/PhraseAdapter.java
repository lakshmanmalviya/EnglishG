package com.example.englishg.Adaptes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishg.Modals.PhraseModal;
import com.example.englishg.R;

import java.util.ArrayList;

public class PhraseAdapter extends RecyclerView.Adapter<PhraseAdapter.PhraseHolder> {
      String lessonKey = "lessonKey";
    final String AllPhrases = "allphrases";
    ArrayList<PhraseModal> phlist;
    Context context;
    public PhraseAdapter(ArrayList<PhraseModal> phlist, Context context) {
        this.phlist = phlist;
        this.context = context;
    }

    @NonNull
    @Override
    public PhraseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.lesson_row,parent,false);
        return new PhraseHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull PhraseHolder holder, int position) {
        PhraseModal phraseModal = phlist.get(position);
         holder.imoji.setText(phraseModal.getImoji());
         holder.phrase.setText(phraseModal.getPhrase());
         holder.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
//                 Toast.makeText(v.getContext(), ""+holder.getAbsoluteAdapterPosition(), Toast.LENGTH_SHORT).show();

             }
         });
    }

    @Override
    public int getItemCount() {
        return phlist.size();
    }

    class PhraseHolder extends RecyclerView.ViewHolder{
      TextView imoji,phrase;
      public PhraseHolder(@NonNull View itemView) {
          super(itemView);
          imoji= itemView.findViewById(R.id.imojiText);
          phrase= itemView.findViewById(R.id.phraseText);
      }
  }
}
