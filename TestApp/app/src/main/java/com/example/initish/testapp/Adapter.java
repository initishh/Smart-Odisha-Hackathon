package com.example.initish.testapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Adapter extends RecyclerView.Adapter<Adapter.myViewHolder> {

    Context mContext;
    List<Item> items = new ArrayList<>();

    static java.text.DateFormat dateFormat = new SimpleDateFormat("hh:mm  dd-mm-yyyy ");

    public Adapter(Context mContext, List<Item> items) {
        this.mContext = mContext;
        this.items = items;
    }

    
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        View v = mInflater.inflate(R.layout.posts, viewGroup, false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, final int i) {
        Picasso.get()
                .load(items.get(i).getPhoto_url())
                .into(myViewHolder.profile_pic);
        myViewHolder.query.setText(items.get(i).getQuery());
        myViewHolder.username.setText(items.get(i).getUsername());
        myViewHolder.time.setText(dateFormat.format(items.get(i).getTime()));

        myViewHolder.post_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, Post_discussion.class);
                intent.putExtra("TITLE_ID", items.get(i).getTitle_id());
                mContext.startActivity(intent);
            }
        });
    }

    public int getItemCount(){
        return items.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        TextView query, time, username;
        ImageView profile_pic;
        ConstraintLayout post_layout;

        public myViewHolder(@NonNull View itemView) {

            super(itemView);
            profile_pic = itemView.findViewById(R.id.profile_pic);
            query = itemView.findViewById(R.id.query);
            time = itemView.findViewById(R.id.time);
            username = itemView.findViewById(R.id.username);
            post_layout = itemView.findViewById(R.id.post_layout);
        }

    }
}

