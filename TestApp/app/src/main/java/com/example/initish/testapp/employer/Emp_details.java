package com.example.initish.testapp.employer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.initish.testapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Emp_details extends AppCompatActivity {


    Button details;
    EditText comp_add,comp_mail,comp_name,comp_spec,comp_time,comp_con;

    FirebaseFirestore db=FirebaseFirestore.getInstance();
    DocumentReference documentReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_details);

        details=findViewById(R.id.details);
        documentReference=db.collection("employers").document();

        comp_add=findViewById(R.id.comp_add);
        comp_mail=findViewById(R.id.comp_mail);
        comp_name=findViewById(R.id.comp_name);
        comp_spec=findViewById(R.id.comp_spec);
        comp_time=findViewById(R.id.comp_time);
        comp_con=findViewById(R.id.comp_con);
    }

    public void submit(View view){

        String address=comp_add.getText().toString();
        String mail=comp_mail.getText().toString();
        String name=comp_name.getText().toString();
        String spec=comp_spec.getText().toString();
        String time=comp_time.getText().toString();
        String con=comp_con.getText().toString();
        String Id=FirebaseAuth.getInstance().getUid();

        employer emp=new employer();
        emp.setEmp_id(Id);
        emp.setComp_add(address);
        emp.setComp_mail(mail);
        emp.setComp_con(con);
        emp.setComp_spec(spec);
        emp.setComp_time(time);
        emp.setComp_name(name);
        emp.setComp_id(documentReference.getId());

        if(address!=null&&spec!=null&&con!=null){
            documentReference.set(emp).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if(task.isSuccessful()){
                        Toast.makeText(Emp_details.this, "Details of the company saved", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), emp_home.class));
                    }
                    else{
                        Toast.makeText(Emp_details.this,"Problem occurred in saving details",Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

    }
}

