package com.example.initish.testapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.viewHolder> {

    Context mContext;
    List<Comment> comments=new ArrayList<>();

    java.text.DateFormat dateFormat = new SimpleDateFormat("hh:mm  dd-mm-yyyy ");

    public CommentAdapter(Context mContext, List<Comment> comments) {
        this.mContext = mContext;
        this.comments = comments;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater mInflater=LayoutInflater.from(mContext);
        View v=mInflater.inflate(R.layout.comments_layout,viewGroup,false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {


        viewHolder.comment.setText(comments.get(i).getComment());
        viewHolder.comment_user.setText(comments.get(i).getUsername());
        viewHolder.comment_time.setText(dateFormat.format(comments.get(i).getTime()));
        Picasso.get().load(comments.get(i).getPhoto_url()).into(viewHolder.person_pic);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder {

      ImageView person_pic;
      TextView comment_user,comment_time,comment;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            person_pic=itemView.findViewById(R.id.person_pic);
            comment=itemView.findViewById(R.id.comment);
            comment_time=itemView.findViewById(R.id.comment_time);
            comment_user=itemView.findViewById(R.id.comment_user);
        }
    }


}
