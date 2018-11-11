package com.example.initish.testapp;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.initish.testapp.employer.candidates;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Profile extends Fragment {

    Button logout;

    TextView name,dob,locality,pin,edu,train,skills,state,city,district,phone,email,exp;
    ImageView imageView;

    FirebaseFirestore db=FirebaseFirestore.getInstance();

    public Profile(){}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_profile,container,false);

        logout=rootView.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(),MainActivity.class));
            }
        });

        name=rootView.findViewById(R.id.name);
        email=rootView.findViewById(R.id.email);
        dob=rootView.findViewById(R.id.dob);
        phone=rootView.findViewById(R.id.phone);
        locality=rootView.findViewById(R.id.locality);
        pin=rootView.findViewById(R.id.pin);
        edu=rootView.findViewById(R.id.edu);
        imageView=rootView.findViewById(R.id.imageView);
        train=rootView.findViewById(R.id.train);
        skills=rootView.findViewById(R.id.skills);
        exp=rootView.findViewById(R.id.exp);
        state=rootView.findViewById(R.id.state);
        city=rootView.findViewById(R.id.city);
        district=rootView.findViewById(R.id.district);

        StudentDetails details=new StudentDetails();
        final String ID=FirebaseAuth.getInstance().getUid();

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

        return rootView;
    }
}
