package com.example.initish.testapp.trainers;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.initish.testapp.Adapter;
import com.example.initish.testapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Tobeheld extends Fragment {

    RecyclerView program_rcview;
    FloatingActionButton fab;
    String data;
    ProgramAdapter adapter;
    List<Programs> programs=new ArrayList<>();
    FirebaseFirestore db=FirebaseFirestore.getInstance();

    public Tobeheld() {
    }

    public Tobeheld(String data) {
        this.data = data;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.activity_tobeheld,container,false);

        fab=rootView.findViewById(R.id.fab);
        program_rcview=rootView.findViewById(R.id.program_rcview);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getActivity(),Courses.class);
                intent.putExtra("Name",data);
                startActivity(intent);
            }
        });

       db.collection("trainingcentre1").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
           @Override
           public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

               programs.clear();
               List<DocumentSnapshot> documentSnapshots=queryDocumentSnapshots.getDocuments();
               for(DocumentSnapshot d:documentSnapshots){

                   Trainer trainer=d.toObject(Trainer.class);
                   final String ID=trainer.getFileId();
                   if(trainer.getName().equals(data))
                   {
                       db.collection("trainingcentre1").document(ID).collection("Course").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                           @Override
                           public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                               List<DocumentSnapshot> documentSnapshot=queryDocumentSnapshots.getDocuments();
                               for(DocumentSnapshot d:documentSnapshot){

                                   TrainingProg trainingProg=d.toObject(TrainingProg.class);
                                   String ProgId=trainingProg.getFileId();

                                   db.collection("trainingcentre1").document(ID).collection("Course").document(ProgId).collection("programs").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                       @Override
                                       public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                           if(!queryDocumentSnapshots.isEmpty()){

                                               List<DocumentSnapshot> doc=queryDocumentSnapshots.getDocuments();
                                               for(DocumentSnapshot d:doc){

                                                   Programs prog=d.toObject(Programs.class);
                                                   programs.add(prog);
                                               }
                                           }
                                           adapter.notifyDataSetChanged();
                                       }
                                   });
                               }

                           }
                       });
break;
                   }


               }

           }
       });

        return rootView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        program_rcview=getView().findViewById(R.id.program_rcview);
        program_rcview.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new ProgramAdapter(getActivity(),programs);
        program_rcview.setAdapter(adapter);
    }

}
