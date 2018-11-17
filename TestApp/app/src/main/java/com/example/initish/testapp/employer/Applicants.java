package com.example.initish.testapp.employer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.initish.testapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Applicants extends AppCompatActivity {

    RecyclerView recyclerView;
    List<candidates> candy=new ArrayList<>();
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    ApplicantAdapter adapter;
    List<String> IDs=new ArrayList<>();
    public Applicants(){
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicants);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Candidates Applied for the job");
        }

        String ID=getIntent().getStringExtra("JobId");

        recyclerView=findViewById(R.id.applicant_rcview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db.collection("jobposts").document(ID).collection("candidates").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot d : list) {

                        candidates cand = d.toObject(candidates.class);
                        candy.add(cand);

                    }
                        adapter.notifyDataSetChanged();
            }
        });
        adapter = new ApplicantAdapter(Applicants.this,candy);
        recyclerView.setAdapter(adapter);
    }
}
