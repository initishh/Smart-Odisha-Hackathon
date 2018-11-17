package com.example.initish.testapp.trainers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.initish.testapp.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class ongoingProg extends AppCompatActivity {


    EditText coname,coenrol,codate;
    Button cosubmit;

    FirebaseFirestore db=FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ongoing_prog);

        coname=findViewById(R.id.coname);
        coenrol=findViewById(R.id.coenrol);
        codate=findViewById(R.id.codate);

        cosubmit=findViewById(R.id.cosubmit);

        String name=coname.getText().toString();
        String enrol=coenrol.getText().toString();
        String date=codate.getText().toString();

        cosubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Details are submitted!", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(ongoingProg.this,TrainingCentreHome.class);
                intent.putExtra("selected_index",1);
                startActivity(intent);
            }
        });

    }
}
