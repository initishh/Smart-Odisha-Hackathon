package com.example.initish.testapp.employer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.initish.testapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class JobPost extends AppCompatActivity {

    EditText jobDesc,jobTitle,jobSkill,jobAddress,jobSalary,jobExp,jobVacancy;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    DocumentReference mDocref=db.collection("jobposts").document();
    DocumentReference documentReference=db.collection("trainingcentre1").document();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_post);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Add job credentials");
        }

        jobAddress=findViewById(R.id.jobAddress);
        jobDesc=findViewById(R.id.jobDesc);
        jobExp=findViewById(R.id.jobExp);
        jobSalary=findViewById(R.id.jobSalary);
        jobVacancy=findViewById(R.id.jobVacancy);
        jobSkill=findViewById(R.id.jobSkill);
        jobTitle=findViewById(R.id.jobTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.submit_button, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        if(id==R.id.mybutton){

            String address=jobAddress.getText().toString(),desc=jobDesc.getText().toString(),exp=jobExp.getText().toString(),salary=jobSalary.getText().toString(),vacancy=jobVacancy.getText().toString(),title=jobTitle.getText().toString(),skill=jobSkill.getText().toString();
            String userId=FirebaseAuth.getInstance().getCurrentUser().getUid();
            PostItem postItem=new PostItem();

            postItem.setTitle(title);
            postItem.setAddress(address);
            postItem.setSalary(salary);
            postItem.setSkill(skill);
            postItem.setExp(exp);
            postItem.setDesc(desc);
            postItem.setVacancy(vacancy);
            postItem.setUserId(userId);
            postItem.setFileId(documentReference.getId());

            mDocref.set(postItem).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                     if(task.isSuccessful()){
                         DocumentReference documentReference=FirebaseFirestore.getInstance().document("jobposts/responses");
                         Toast.makeText(getApplicationContext(),"Job has been posted!",Toast.LENGTH_SHORT).show();
                         startActivity(new Intent(JobPost.this,emp_home.class));
                     }
                     else{
                         Toast.makeText(getApplicationContext(),"Job has been posted!",Toast.LENGTH_SHORT).show();
                     }
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }
}
