package com.example.englishg.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.englishg.Fragments.DeleteOptions;
import com.example.englishg.Fragments.InsertOption;
import com.example.englishg.Fragments.UpdateOptions;
import com.example.englishg.R;

public class AdminOps extends AppCompatActivity {
   Button insertBtn,deleteBtn,updateBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_ops);
        getSupportActionBar().hide();
        insertBtn = findViewById(R.id.insert);
        deleteBtn = findViewById(R.id.delete);
        updateBtn = findViewById(R.id.update);
         replaceFg(R.id.replaceLayout,new InsertOption());
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFg(R.id.replaceLayout,new InsertOption());
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 replaceFg(R.id.replaceLayout,new DeleteOptions());

            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            replaceFg(R.id.replaceLayout,new UpdateOptions());
            }
        });

    }
    public void replaceFg(int layout, Fragment fg){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(layout,fg);
        transaction.commit();
    }
}