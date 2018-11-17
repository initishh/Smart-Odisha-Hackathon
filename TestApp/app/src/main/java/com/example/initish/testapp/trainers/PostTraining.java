package com.example.initish.testapp.trainers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.initish.testapp.AddQuestion;
import com.example.initish.testapp.Main2Activity;
import com.example.initish.testapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class PostTraining extends AppCompatActivity {

    FirebaseFirestore db=FirebaseFirestore.getInstance();
    EditText course_desc,course_seats,course_date,start;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_training);

        course_date=findViewById(R.id.course_date);
        course_desc=findViewById(R.id.course_desc);
        course_seats=findViewById(R.id.course_seats);
        start=findViewById(R.id.start);

        button=findViewById(R.id.post_submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String desc=course_desc.getText().toString();
                final String seats=course_seats.getText().toString();
                final String date=course_date.getText().toString();
                final String datestart=course_date.getText().toString();

                final String cnam=getIntent().getStringExtra("CourseName");

                db.collection("trainingcentre1").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot d:list){

                            final Trainer trainer=d.toObject(Trainer.class);
                            final String ID=trainer.getFileId();
                                db.collection("trainingcentre1").document(trainer.getFileId()).collection("Course").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                    @Override
                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                        List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                                        for(DocumentSnapshot d:list){
                                            TrainingProg trainingProg=d.toObject(TrainingProg.class);
                                            if(trainingProg.getCourse().equals(cnam)){

                                                DocumentReference documentReference=db.collection("trainingcentre1").document(ID).collection("Course").document(trainingProg.getFileId()).collection("programs").document();

                                                final Programs programs=new Programs();
                                                programs.setName(trainingProg.getCourse());
                                                programs.setDesc(desc);
                                                programs.setSeats(seats);
                                                programs.setDate(datestart);
                                                programs.setStatus("T");
                                                programs.setDuration(trainingProg.getDuration());
                                                programs.setElig(trainingProg.getEligibility());
                                                programs.setSub(date);
                                                programs.setFileId(ID);

                                                DocumentReference mDocref =db.collection("programs").document();
                                                mDocref.set(programs).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {


                                                    }
                                                });

                                                documentReference.set(programs).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {

                                                        Toast.makeText(PostTraining.this, "Successfully Posted", Toast.LENGTH_SHORT).show();
                                                        Intent intent=new Intent(PostTraining.this,TrainingCentreHome.class);
                                                        intent.putExtra("selected_index",0);
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            }
                                        }
                                    }
                                });
                        }
                    }
                });
            }
        });
    }
}
