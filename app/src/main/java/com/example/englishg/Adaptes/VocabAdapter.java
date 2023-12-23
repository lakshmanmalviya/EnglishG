package com.example.englishg.Adaptes;

import static com.example.englishg.R.drawable.egtwo;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishg.Modals.VocabModal;
import com.example.englishg.R;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class VocabAdapter extends RecyclerView.Adapter<VocabAdapter.vocabHolder> {
//    Uri imgUri;
    ArrayList<VocabModal> vmlist;
    Context context;

    public VocabAdapter(ArrayList<VocabModal> vmlist, Context context) {
        this.vmlist = vmlist;
        this.context = context;
    }

    @NonNull
    @Override
    public vocabHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.vocab_row,parent,false);
        return  new vocabHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull vocabHolder holder, int position) {
        VocabModal vmodal = vmlist.get(position);
        holder.vocabName.setText(vmodal.getVocabName());
        holder.vocabMeaning.setText(vmodal.getVocabMeaning());
        Picasso.get().load(vmodal.getVocabimage()).placeholder(egtwo).into(holder.vocabImage);
       holder.vocabShare.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
               Context context = v.getContext();
//               imgUri = Uri.parse(vmodal.getVocabimage());
               String shareText = vmodal.getVocabName()+"\n"+vmodal.getVocabMeaning();
               Intent intent = new Intent(Intent.ACTION_SEND);
//               intent.putExtra(Intent.EXTRA_STREAM,imgUri);
               intent.putExtra(Intent.EXTRA_TEXT,shareText);
               intent.setType("text/*");
               context.startActivity(intent);
           }
       });
        holder.copy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData data = ClipData.newPlainText("copy",holder.vocabName.getText()+"\n"+holder.vocabMeaning.getText());
                clipboardManager.setPrimaryClip(data);
                Toast.makeText(v.getContext(), "Copied", Toast.LENGTH_SHORT).show();

            }
        });
    }
    @Override
    public int getItemCount() {
        return vmlist.size();
    }
    class vocabHolder extends RecyclerView.ViewHolder{
         TextView vocabName,vocabMeaning,vocabShare;
         ImageView vocabImage,copy;
        public vocabHolder(@NonNull View itemView) {
            super(itemView);
            vocabName = itemView.findViewById(R.id.vocabNameTv);
            vocabMeaning = itemView.findViewById(R.id.vocabMeaningTv);
            vocabShare = itemView.findViewById(R.id.vocabShare);
            vocabImage = itemView.findViewById(R.id.VocabImgV);
            copy = itemView.findViewById(R.id.copy);
        }
    }
}
