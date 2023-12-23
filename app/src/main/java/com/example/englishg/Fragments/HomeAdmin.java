package com.example.englishg.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.englishg.Activities.AdminOps;
import com.example.englishg.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class HomeAdmin extends Fragment {
    public HomeAdmin() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final String adminRecord = "admin";
       Button signup;
       EditText email,possword;
        FirebaseAuth mauth;
        FirebaseDatabase firebaseDatabase;
        ProgressDialog dialog;
      View view = inflater.inflate(R.layout.fragment_home_admin, container, false);
        signup = view.findViewById(R.id.signup);
        email = view.findViewById(R.id.email);
        possword = view.findViewById(R.id.possword);
        mauth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        dialog = new ProgressDialog(getContext());
        dialog.setTitle("Signing you ");
        dialog.setMessage("We are signing you");
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().isEmpty() ||possword.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "The email or possword is not provided", Toast.LENGTH_SHORT).show();
                    return;
                }
                dialog.show();
                mauth.signInWithEmailAndPassword(email.getText().toString(),possword.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                dialog.dismiss();
                                if (task.isSuccessful()){
                                    Intent intent= new Intent(getContext(), AdminOps.class);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(getContext(), ""+task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
//                this was the creation part
//                 mauth.createUserWithEmailAndPassword(email.getText().toString(),possword.getText().toString())
//                         .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
//                             @Override
//                             public void onComplete(@NonNull Task<AuthResult> task) {
//                                dialog.dismiss();
//                                 if (task.isSuccessful()){
//                                     AdminModal admin = new AdminModal(email.getText().toString(),possword.getText().toString());
//                                     String id = task.getResult().getUser().getUid();
//                                     firebaseDatabase.getReference().child(adminRecord).child(id).push().setValue(admin);
//                                     Toast.makeText(getContext(), "creation is succeeded", Toast.LENGTH_SHORT).show();
//                                     Toast.makeText(getContext(), "Login is succeeded", Toast.LENGTH_SHORT).show();
//                                 }
//                                 else
//                                 {
//                                     Toast.makeText(getContext(), "Login is failed", Toast.LENGTH_SHORT).show();
//
//                                 }
//                             }
//                         });
            }
        });
       if(mauth.getCurrentUser()!=null){
           Intent intent = new Intent(getContext(),AdminOps.class);
           startActivity(intent);
       }
     return  view;
    }
}