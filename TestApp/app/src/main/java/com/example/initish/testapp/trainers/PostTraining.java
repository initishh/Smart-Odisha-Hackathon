package com.example.initish.testapp.trainers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.initish.testapp.AddQuestion;
import com.example.initish.testapp.Main2Activity;
import com.example.initish.testapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class PostTraining extends AppCompatActivity {

    FirebaseFirestore db=FirebaseFirestore.getInstance();
    EditText course_name,course_duration,course_elig,course_sub,course_sector;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_training);

        course_name=findViewById(R.id.course_name);
        course_duration=findViewById(R.id.course_duration);
        course_elig=findViewById(R.id.course_elig);
        course_sector=findViewById(R.id.course_sector);
        course_sub=findViewById(R.id.course_sub);

        button=findViewById(R.id.post_submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name=course_name.getText().toString();
                String duration=course_duration.getText().toString();
                String elig=course_elig.getText().toString();
                String sector=course_sector.getText().toString();
                String sub=course_sub.getText().toString();

                final Programs programs=new Programs();
                programs.setName(name);
                programs.setDuration(duration);
                programs.setElig(elig);
                programs.setSector(sector);
                programs.setSub(sub);


                final String nam=getIntent().getStringExtra("Name");



                db.collection("trainingcentre1").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot d:list){

                            Trainer trainer=d.toObject(Trainer.class);
                            DocumentReference documentReference=db.collection("trainingcentre1").document(trainer.getFileId()).collection("programson").document();
                            programs.setFileId(documentReference.getId());
                            if(trainer.getName().equals(nam)){

                                documentReference.set(programs).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                        Toast.makeText(PostTraining.this, "Skill Training program is posted Successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(PostTraining.this,TrainingCentre.class);
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
        });

    }
}
