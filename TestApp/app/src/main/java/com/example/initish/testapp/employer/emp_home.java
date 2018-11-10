package com.example.initish.testapp.employer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.initish.testapp.MainActivity;
import com.example.initish.testapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class emp_home extends AppCompatActivity {

    Button button_post;
    RecyclerView recyclerView;
    List<PostItem> postItems=new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    JobPostAdapter adapter;

    public emp_home(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_home);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Dashboard");
        }

        button_post=findViewById(R.id.button_post);
        button_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(emp_home.this,JobPost.class));
            }
        });

        recyclerView=findViewById(R.id.job_rcview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db.collection("jobposts").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                postItems.clear();

                Toast.makeText(emp_home.this,"Job posts here",Toast.LENGTH_SHORT).show();
                if(!queryDocumentSnapshots.isEmpty()){
                    List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                    for(DocumentSnapshot d:list)
                    {
                        PostItem postItem=d.toObject(PostItem.class);

                        if(postItem.getUserId().equals(FirebaseAuth.getInstance().getUid()))
                           postItems.add(postItem);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });

        adapter = new JobPostAdapter(emp_home.this,postItems);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.sign_out) {
            FirebaseAuth.getInstance().signOut();
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.sign_out_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
