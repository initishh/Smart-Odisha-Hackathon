package com.example.initish.testapp.employer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.initish.testapp.AddQuestion;
import com.example.initish.testapp.EmployerProfile;
import com.example.initish.testapp.Main2Activity;
import com.example.initish.testapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class JobPostDiscussion extends AppCompatActivity {

    TextView com_name,jobrole,skill,salary,exp,vac,address,des;
    Button apply,comp_prof;

    List<PostItem> postItems=new ArrayList<>();
    FirebaseFirestore db=FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_post_discussion);

        com_name=findViewById(R.id.comp_name);
        jobrole=findViewById(R.id.jobrole);
        skill=findViewById(R.id.skill);
        salary=findViewById(R.id.salary);
        exp=findViewById(R.id.exp);
        vac=findViewById(R.id.vac);
        address=findViewById(R.id.address);
        des=findViewById(R.id.des);

        apply=findViewById(R.id.apply);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Company Details");
        }

        comp_prof=findViewById(R.id.comp_prof);
        final String ID=getIntent().getStringExtra("JobId");

        comp_prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.collection("jobposts").document(ID).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        PostItem postItem=documentSnapshot.toObject(PostItem.class);
                        Intent intent=new Intent(getApplicationContext(),EmployerProfile.class);
                        intent.putExtra("Emp_ID",postItem.getUserId());
                        startActivity(intent);
                    }
                });

            }
        });


        final DocumentReference documentReference=db.collection("jobposts").document(ID)
                .collection("candidates").document();

        apply.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View view) {

                                         db.collection("candidates").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                             @Override
                                             public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                                 List<DocumentSnapshot> documentSnapshots = queryDocumentSnapshots.getDocuments();

                                                 for (DocumentSnapshot d : documentSnapshots) {

                                                     candidates c = d.toObject(candidates.class);
                                                     if (c.getCandidateId().equals(FirebaseAuth.getInstance().getUid())) {

                                                         candidates cand = new candidates();

                                                         cand.setCandidateId(FirebaseAuth.getInstance().getUid());
                                                         cand.setName(c.getName());
                                                         cand.setTrainings(c.getTrainings());
                                                         cand.setSkills(c.getSkills());
                                                         cand.setState(c.getState());
                                                         cand.setExp(c.getExp());
                                                         cand.setLocality(c.getLocality());
                                                         cand.setPhotourl(c.getPhotourl());
                                                         cand.setEdu(c.getEdu());
                                                         cand.setDob(c.getDob());
                                                         cand.setPhone(c.getPhone());
                                                         cand.setPin(c.getPin());
                                                         cand.setEmail(c.getEmail());
                                                         cand.setDistrict(c.getDistrict());
                                                         cand.setCity(c.getCity());

                                                         documentReference.set(cand).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                             @Override
                                                             public void onSuccess(Void aVoid) {
                                                                 Toast.makeText(JobPostDiscussion.this, "You have successfully applied for the job.", Toast.LENGTH_LONG).show();
                                                                 Intent intent = new Intent(JobPostDiscussion.this, Main2Activity.class);
                                                                 intent.putExtra("selected_index", 1);
                                                                 startActivity(intent);
                                                             }
                                                         }).addOnFailureListener(new OnFailureListener() {
                                                             @Override
                                                             public void onFailure(@NonNull Exception e) {
                                                                 Toast.makeText(JobPostDiscussion.this, "Some error occurred.", Toast.LENGTH_LONG).show();
                                                             }
                                                         });

                                                         break;
                                                     }
                                                 }
                                             }
                                         });
                                     }
                                 });

                db.collection("jobposts").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                        for (DocumentSnapshot d : list) {

                            PostItem postItem = d.toObject(PostItem.class);
                            if (postItem.getFileId().equals(ID)) {
                                jobrole.setText(postItem.getTitle());
                                skill.setText(postItem.getSkill());
                                salary.setText(postItem.getSalary());
                                exp.setText(postItem.getExp());
                                vac.setText(postItem.getVacancy());
                                address.setText(postItem.getAddress());
                                des.setText(postItem.getDesc());
                                break;
                            }
                        }
                    }
                });
            }
        }
