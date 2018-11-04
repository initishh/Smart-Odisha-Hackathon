package com.example.initish.testapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Writ extends AppCompatActivity {

    EditText comment_add;
    Context context;
    String titleid;

    FirebaseFirestore db=FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writ);


        comment_add=findViewById(R.id.comment_add);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Your Answer");
        }

        titleid=getIntent().getStringExtra("TITLEID");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.submit_button, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.mybutton) {

            String ans=comment_add.getText().toString();
            String photoUrl=FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl().toString();
            String UserName=FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

            Comment comment=new Comment();
            comment.setComment(ans);
            comment.setPhoto_url(photoUrl);
            comment.setUsername(UserName);

            db.collection("posts").document(titleid).set(comment).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if(task.isSuccessful()){
                        startActivity(new Intent(getApplication(),Post_discussion.class));
                        Toast.makeText(getApplicationContext(),"Question has been posted",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Problem here!",Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }
        return super.onOptionsItemSelected(item);
    }
}
