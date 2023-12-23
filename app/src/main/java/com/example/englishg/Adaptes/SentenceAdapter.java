package com.example.englishg.Adaptes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishg.Modals.SentenceModal;
import com.example.englishg.R;

import java.util.ArrayList;

public class SentenceAdapter extends RecyclerView.Adapter<SentenceAdapter.SentenceHolder> {
   ArrayList<SentenceModal> slist;
   Context context;

    public SentenceAdapter(ArrayList<SentenceModal> slist, Context context) {
        this.slist = slist;
        this.context = context;
    }

    @NonNull
    @Override
    public SentenceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sentence_row,parent,false);
        return  new SentenceHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SentenceHolder holder, int position) {
        SentenceModal sentenceModal = slist.get(position);
          holder.english.setText(sentenceModal.getEnglish());
          holder.hindi.setText(sentenceModal.getHindi());
          holder.share.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Context context = v.getContext();
                  String share =  holder.english.getText().toString()+"\n"+holder.hindi.getText().toString();
//                  Toast.makeText(v.getContext(), share, Toast.LENGTH_SHORT).show();
                  Intent intent = new Intent(Intent.ACTION_SEND);
                  intent.setType("text/plain");
                  intent.putExtra(Intent.EXTRA_TEXT,share);
                  context.startActivity(intent);
              }
          });
    }

    @Override
    public int getItemCount() {
        return slist.size();
    }

    class SentenceHolder extends RecyclerView.ViewHolder{
       TextView english,hindi,share;
        public SentenceHolder(@NonNull View itemView) {
            super(itemView);
            english = itemView.findViewById(R.id.englishSenTv);
            hindi = itemView.findViewById(R.id.hindiSenTv);
            share = itemView.findViewById(R.id.shareTv);
        }
    }
}
