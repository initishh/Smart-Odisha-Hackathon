package com.example.initish.testapp.trainers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.initish.testapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TrainingCentre extends AppCompatActivity {


    RecyclerView train_rcview;
    List<String> inst=new ArrayList<>();
    TrainerAdapter adapter;
    FirebaseFirestore db=FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_centre);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Choose Training Centre ");
        }

        train_rcview=findViewById(R.id.train_rcview);
        train_rcview.setLayoutManager(new LinearLayoutManager(this));

        final String Dist_name=getIntent().getStringExtra("Dist_name");


        db.collection("trainingcentre1").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                if(!queryDocumentSnapshots.isEmpty()) {
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot d : list) {
                        Trainer trainer = d.toObject(Trainer.class);
                        if(trainer.getDistrict().equals(Dist_name))
                          inst.add(trainer.getName());
                    }
                   }
                adapter.notifyDataSetChanged();
            }
        });

        adapter = new TrainerAdapter(getApplicationContext(), inst);
        train_rcview.setAdapter(adapter);
    }
}
