package com.example.initish.testapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.initish.testapp.employer.candidates;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class StudentDetails extends AppCompatActivity {


    EditText name,dob,locality,pin,edu,train,skills,state,city,district,phone,expp;
    Button candidatebutton;

    FirebaseFirestore db=FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
        }

        name=findViewById(R.id.name);
        dob=findViewById(R.id.dob);
        phone=findViewById(R.id.phone);
        locality=findViewById(R.id.locality);
        pin=findViewById(R.id.pin);
        edu=findViewById(R.id.edu);
        train=findViewById(R.id.train);
        skills=findViewById(R.id.skills);
        state=findViewById(R.id.state);
        expp=findViewById(R.id.expp);
        city=findViewById(R.id.city);
        district=findViewById(R.id.district);

        findViewById(R.id.candidatebutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Exp=expp.getText().toString();
                String Name=name.getText().toString();
                String DOB=dob.getText().toString();
                String Phone=phone.getText().toString();
                String Locality=locality.getText().toString();
                String Pin=pin.getText().toString();
                String Edu=edu.getText().toString();
                String Training = train.getText().toString();
                String Skills=skills.getText().toString();
                String City=city.getText().toString();
                String State=state.getText().toString();
                String District=district.getText().toString();

                if(Name.isEmpty())
                {
                    name.setError("Please Enter the name");
                    name.requestFocus();
                }

                candidates candy=new candidates();
                candy.setCandidateId(FirebaseAuth.getInstance().getUid());
                candy.setCity(City);
                candy.setDob(DOB);
                candy.setEdu(Edu);
                candy.setPhone(Phone);
                candy.setState(State);
                candy.setName(Name);
                candy.setLocality(Locality);
                candy.setPin(Pin);
                candy.setExp(Exp);
                candy.setTrainings(Training);
                candy.setSkills(Skills);
                candy.setDistrict(District);
                candy.setCandidateId(FirebaseAuth.getInstance().getCurrentUser().getUid());
                candy.setEmail(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                candy.setPhotourl(FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl().toString());

                db.collection("candidates").document().set(candy).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(StudentDetails.this, "Details are saved", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getBaseContext(),Main2Activity.class));}
                        else
                            {
                                Toast.makeText(StudentDetails.this, "Problem occurred in saving", Toast.LENGTH_SHORT).show();
                            }

                    }
                });

            }
        });

    }
}
