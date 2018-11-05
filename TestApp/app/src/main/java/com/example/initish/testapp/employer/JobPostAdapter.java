package com.example.initish.testapp.employer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.initish.testapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class JobPostAdapter extends RecyclerView.Adapter<JobPostAdapter.myViewHolder> {

    Context context;
    List<PostItem> postItems=new ArrayList<>();

    static java.text.DateFormat dateFormat = new SimpleDateFormat("hh:mm  dd-mm-yyyy ");

    public JobPostAdapter(Context context, List<PostItem> postItems) {
        this.context = context;
        this.postItems = postItems;
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        TextView job_time,job_title,job_salary,job_city,job_skill;
        ConstraintLayout job_post;
        public myViewHolder(@NonNull View itemView) {

            super(itemView);
            job_city=itemView.findViewById(R.id.job_city);
            job_post=itemView.findViewById(R.id.job_post);
            job_salary=itemView.findViewById(R.id.job_salary);
            job_skill=itemView.findViewById(R.id.job_skill);
            job_title=itemView.findViewById(R.id.job_title);
            job_post=itemView.findViewById(R.id.job_post);
            job_time=itemView.findViewById(R.id.job_time);
        }

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater mInflater = LayoutInflater.from(context);
        View v = mInflater.inflate(R.layout.jobposts, viewGroup, false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int i) {


        myViewHolder.job_title.setText(postItems.get(i).getTitle());
        myViewHolder.job_skill.setText("Skill: "+postItems.get(i).getSkill());
        myViewHolder.job_salary.setText("Salary: "+postItems.get(i).getSalary());
        myViewHolder.job_time.setText(dateFormat.format(postItems.get(i).getTime()));
        myViewHolder.job_city.setText("Place: "+postItems.get(i).getAddress());
    }

    @Override
    public int getItemCount() {
        return postItems.size();
    }

}
