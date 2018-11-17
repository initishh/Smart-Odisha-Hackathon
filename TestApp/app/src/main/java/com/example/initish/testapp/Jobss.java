package com.example.initish.testapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.initish.testapp.employer.JobPostAdapter;
import com.example.initish.testapp.employer.PostItem;
import com.example.initish.testapp.employer.emp_home;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Jobss extends Fragment {

    private RecyclerView mRecyclerView;
    List<PostItem> itemList=new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    JobPostAdapter adapter;

    public Jobss() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_jobss,container,false);

        String id=FirebaseAuth.getInstance().getUid();

        db.collection("jobposts").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                itemList.clear();
                if(!queryDocumentSnapshots.isEmpty()){
                    List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                    for(DocumentSnapshot d:list)
                    {
                        PostItem postItem=d.toObject(PostItem.class);
                        itemList.add(postItem);
                    }
                }
                adapter.notifyDataSetChanged();
            }

        });

        return rootView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView=getView().findViewById(R.id.jobs_rcview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new JobPostAdapter(getActivity(),itemList);
        mRecyclerView.setAdapter(adapter);
    }
}
