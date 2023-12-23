package com.example.englishg.Adaptes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishg.Modals.VocabLessonModal;
import com.example.englishg.R;

import java.util.ArrayList;

public class VocabLessonAdapter extends RecyclerView.Adapter<VocabLessonAdapter.VLHolder> {
   ArrayList<VocabLessonModal> vllist;
   Context context;

    public VocabLessonAdapter(ArrayList<VocabLessonModal> vllist, Context context) {
        this.vllist = vllist;
        this.context = context;
    }

    @NonNull
    @Override
    public VLHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.vocab_lesson_row,parent,false);
        return new VLHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VLHolder holder, int position) {
        VocabLessonModal vmodal = vllist.get(position);
        holder.vocabImoji.setText(vmodal.getVocabImoji());
        holder.vocabLessonText.setText(vmodal.getVocabLessonText());

    }

    @Override
    public int getItemCount() {
        return vllist.size();
    }

    class VLHolder extends RecyclerView.ViewHolder{
        TextView vocabImoji,vocabLessonText;
        public VLHolder(@NonNull View itemView) {
            super(itemView);
            vocabImoji = itemView.findViewById(R.id.vocabImoji);
            vocabLessonText = itemView.findViewById(R.id.vocabLessonTv);
        }
    }
}
