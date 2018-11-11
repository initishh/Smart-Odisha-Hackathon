package com.example.initish.testapp.trainers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.initish.testapp.Adapter;
import com.example.initish.testapp.Comment;
import com.example.initish.testapp.CommentAdapter;
import com.example.initish.testapp.Post_discussion;
import com.example.initish.testapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TrainersHome extends AppCompatActivity {


    RecyclerView district_rcview;
    List<String> trainers=new ArrayList<>();
    Set<String> hs = new HashSet<>();
    district_adapter adapter;
    FirebaseFirestore db=FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainers_home);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Choose District");
        }

        district_rcview=findViewById(R.id.district_rcview);
        district_rcview.setLayoutManager(new LinearLayoutManager(this));


        db.collection("trainingcentre1").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                if(!queryDocumentSnapshots.isEmpty()) {
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot d : list) {

                        Trainer trainer = d.toObject(Trainer.class);
                        trainers.add(trainer.getDistrict());
                    }

                }
                adapter.notifyDataSetChanged();

                hs.addAll(trainers);
                trainers.clear();
                trainers.addAll(hs);
            }

        });

        adapter = new district_adapter(getApplicationContext(), trainers);
        district_rcview.setAdapter(adapter);

    }
}
