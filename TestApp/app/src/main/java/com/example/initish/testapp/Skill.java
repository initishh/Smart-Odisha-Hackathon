package com.example.initish.testapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.initish.testapp.trainers.ProgramAdapter;
import com.example.initish.testapp.trainers.Programs;
import com.example.initish.testapp.trainers.Trainer;
import com.example.initish.testapp.trainers.TrainingProg;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Skill extends Fragment {

    private RecyclerView mRecyclerView,skills_rcv;
    List<Programs> itemList=new ArrayList<>();
    List<Sector> sectors=new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Context context;
    SkillAdapter adapter;
    SectorAdapter s_adapter;

    public Skill() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.skill_layout,container,false);
        mRecyclerView=rootView.findViewById(R.id.prog_rcv);
        skills_rcv=rootView.findViewById(R.id.skills_rcv);

        if(itemList.isEmpty())
        {

            db.collection("programs").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot d : list) {

                        Programs programs = d.toObject(Programs.class);
                        itemList.add(programs);
                    }

                    adapter.notifyDataSetChanged();
                }
            });
        }

     db.collection("skills").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
         @Override
         public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

             List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
             for(DocumentSnapshot d:list) {

                 Sector sector=d.toObject(Sector.class);
                 sectors.add(sector);
             }

            s_adapter.notifyDataSetChanged();
         }
     });

     return rootView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView=getView().findViewById(R.id.prog_rcv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));

        skills_rcv=getView().findViewById(R.id.skills_rcv);
        skills_rcv.setLayoutManager(new GridLayoutManager(getActivity(),2));

        s_adapter=new SectorAdapter(getActivity(),sectors);
        skills_rcv.setAdapter(s_adapter);

        adapter = new SkillAdapter(getActivity(),itemList);
        mRecyclerView.setAdapter(adapter);

    }

}
