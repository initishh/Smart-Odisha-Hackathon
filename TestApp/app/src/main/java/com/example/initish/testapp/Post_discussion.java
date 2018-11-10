package com.example.initish.testapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Post_discussion extends AppCompatActivity {

    private RecyclerView comment_rcview;
    List<Comment> commentsList=new ArrayList<>();
    TextView title_text,desc_text,usertext;
    EditText comment_text;
    Context context;
    ImageView ask_pic;
    RecyclerView.Adapter adapter;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_discussion);

        title_text = findViewById(R.id.title_text);
        desc_text = findViewById(R.id.desc_text);
        usertext = findViewById(R.id.ask_name);
        ask_pic=findViewById(R.id.ask_pic);
        comment_text=findViewById(R.id.comment_text);

        comment_rcview=findViewById(R.id.comment_rcview);
        comment_rcview.setLayoutManager(new LinearLayoutManager(this));

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        final String title_id = getIntent().getStringExtra("TITLE_ID");
        db.collection("posts/"+title_id+"/comments").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                if(!queryDocumentSnapshots.isEmpty()){

                    List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                    for(DocumentSnapshot d:list){
                        Comment comment=d.toObject(Comment.class);
                        commentsList.add(comment);

                    }

                    adapter=new CommentAdapter(Post_discussion.this,commentsList);
                    comment_rcview.setAdapter(adapter);
                }

                adapter.notifyDataSetChanged();
            }
        });

        comment_rcview=findViewById(R.id.comment_rcview);
        final CommentAdapter adapter=new CommentAdapter(this,commentsList);
        comment_rcview.setAdapter(adapter);
        comment_rcview.setLayoutManager(new LinearLayoutManager(this));


        db.collection("posts").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot d:list)
                {
                    Item item=d.toObject(Item.class);
                    if(title_id.equals(item.getTitle_id())){

                        title_text.setText(item.getQuery());
                        desc_text.setText(item.getDesc());
                        usertext.setText(item.getUsername());
                        Picasso.get()
                                .load(item.getPhoto_url())
                                .into(ask_pic);

                    }
                }

            }

        });

        comment_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Post_discussion.this,Writ.class);
                intent.putExtra("TITLEID",title_id);
                startActivity(intent);
            }
        });

    }
}
