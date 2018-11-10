package com.example.initish.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.initish.testapp.employer.candidates;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ApplicantProfile extends AppCompatActivity {

    TextView name,dob,locality,pin,edu,train,skills,state,city,district,phone,email,exp;
    ImageView imageView;

    FirebaseFirestore db=FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicant_profile);

        final String ID=getIntent().getStringExtra("ApplicantID");

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        dob=findViewById(R.id.dob);
        phone=findViewById(R.id.phone);
        locality=findViewById(R.id.locality);
        pin=findViewById(R.id.pin);
        edu=findViewById(R.id.edu);
        imageView=findViewById(R.id.imageView);
        train=findViewById(R.id.train);
        skills=findViewById(R.id.skills);
        exp=findViewById(R.id.exp);
        state=findViewById(R.id.state);
        city=findViewById(R.id.city);
        district=findViewById(R.id.district);

        StudentDetails details=new StudentDetails();

        db.collection("candidates").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                if(!queryDocumentSnapshots.isEmpty()){
                    List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                    for(DocumentSnapshot d:list){

                        candidates candy=d.toObject(candidates.class);
                        if(candy.getCandidateId().equals(ID)){
                            exp.setText("Experience : "+candy.getExp());
                            name.setText("Name : "+candy.getName());
                            dob.setText("Date of Birth : "+candy.getDob());
                            locality.setText("Locality : " + candy.getLocality());
                            city.setText("City : "+candy.getCity());
                            district.setText("District : "+candy.getDistrict());
                            state.setText("State : "+candy.getState());
                            pin.setText("PIN : "+candy.getPin());
                            edu.setText("Education : "+candy.getPin());
                            train.setText("Trainings Done : "+candy.getTrainings());
                            skills.setText("Skills : "+candy.getSkills());
                            phone.setText("Phone : " +candy.getPhone());
                            email.setText("Email Address : "+candy.getEmail());
                            Picasso.get().load(candy.getPhotourl()).into(imageView);
                        }

                    }
                }


            }
        });


    }
}
