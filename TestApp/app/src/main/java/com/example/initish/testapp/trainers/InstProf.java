package com.example.initish.testapp.trainers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.initish.testapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class InstProf extends AppCompatActivity {

    TextView Name,Add,Dist,Pin,Cont,EMail,Web;

    FirebaseFirestore db=FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inst_prof);

        Name=findViewById(R.id.Name);
        Add=findViewById(R.id.Add);
        Dist=findViewById(R.id.Dist);
        Pin=findViewById(R.id.Pin);
        Cont=findViewById(R.id.Cont);
        EMail=findViewById(R.id.EMail);
        Web=findViewById(R.id.web);

        final String ID=getIntent().getStringExtra("ID");

        db.collection("trainingcentre1").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();

                for(DocumentSnapshot d:list){

                    Trainer trainer=d.toObject(Trainer.class);
                    final String name=trainer.getName();
                    final String address=trainer.getAddress();
                    final String district=trainer.getDistrict();
                    final String pin=trainer.getPin();
                    final String contact=trainer.getPhone();
                    final String email=trainer.getEmail();
                    final String web=trainer.getWebsite();
                    Log.i("Tag",trainer.getName());

                    if(trainer.getFileId().equals(ID)){
                        Name.setText(name);
                        Add.setText(address);
                        Dist.setText(district);
                        Pin.setText(pin);
                        Cont.setText(contact);
                        EMail.setText(email);
                        Web.setText(web);
                        break;
                    }
                }

            }
        });
    }
}
