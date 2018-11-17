package com.example.initish.testapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.initish.testapp.trainers.PostTraining;
import com.example.initish.testapp.trainers.TrainingCentreHome;
import com.example.initish.testapp.trainers.district_adapter;

import java.util.ArrayList;
import java.util.List;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.Viewholder> {


    Context mContext;
    List<String> courses = new ArrayList<>();

    public CoursesAdapter(Context mContext, List<String> courses) {
        this.mContext = mContext;
        this.courses = courses;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater mInflater=LayoutInflater.from(mContext);
        View v=mInflater.inflate(R.layout.district_layout,viewGroup,false);
        return new Viewholder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, final int i) {

        viewholder.dist_name.setText(courses.get(i));
        viewholder.dist_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,PostTraining.class);
                intent.putExtra("CourseName",courses.get(i));
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        TextView dist_name;
        ConstraintLayout dist_layout;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            dist_name=itemView.findViewById(R.id.dist_name);
            dist_layout=itemView.findViewById(R.id.dist_layout);
        }
    }

}
