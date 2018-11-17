package com.example.initish.testapp.trainers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.initish.testapp.CoursesAdapter;
import com.example.initish.testapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Courses extends AppCompatActivity {

    RecyclerView courses_rcview;
    List<String> courses=new ArrayList<>();
    CoursesAdapter adapter;
    FirebaseFirestore db=FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Choose Course");
        }

        courses_rcview=findViewById(R.id.courses_rcview);
        courses_rcview.setLayoutManager(new LinearLayoutManager(this));

        final String nam=getIntent().getStringExtra("Name");

        db.collection("trainingcentre1").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot d:list){

                    Trainer trainer=d.toObject(Trainer.class);
                    if(trainer.getName().equals(nam)){

                        db.collection("trainingcentre1").document(trainer.getFileId()).collection("Course").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                                for(DocumentSnapshot d:list){
                                    TrainingProg trainingProg=d.toObject(TrainingProg.class);
                                    courses.add(trainingProg.getCourse());
                                }
                                adapter.notifyDataSetChanged();
                            }
                        });
                        break;
                    }

                }

            }
        });


        adapter = new CoursesAdapter(getApplicationContext(),courses);
        courses_rcview.setAdapter(adapter);
    }
}
