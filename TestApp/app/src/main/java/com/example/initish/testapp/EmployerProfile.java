package com.example.initish.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.initish.testapp.employer.employer;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class EmployerProfile extends AppCompatActivity {


    TextView comp_name,comp_spec,comp_time,comp_add,comp_cont,comp_mail;

    FirebaseFirestore db=FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_profile);

        final String UID=getIntent().getStringExtra("Emp_ID");

        comp_add=findViewById(R.id.comp_add);
        comp_cont=findViewById(R.id.comp_cont);
        comp_time=findViewById(R.id.comp_time);
        comp_spec=findViewById(R.id.comp_spec);
        comp_mail=findViewById(R.id.comp_mail);
        comp_name=findViewById(R.id.comp_name);

        db.collection("employers").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot d:list){
                    employer emp=d.toObject(employer.class);
                    if(emp.getEmp_id().equals(UID)){

                        comp_add.setText(emp.getComp_add());
                        comp_name.setText(emp.getComp_name());
                        comp_cont.setText(emp.getComp_con());
                        comp_mail.setText(emp.getComp_mail());
                        comp_spec.setText(emp.getComp_spec());
                        comp_time.setText(emp.getComp_time());
                        break;
                    }
                }

            }
        });


    }
}
