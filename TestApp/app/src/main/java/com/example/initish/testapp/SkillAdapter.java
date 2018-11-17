package com.example.initish.testapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.initish.testapp.trainers.InstProf;
import com.example.initish.testapp.trainers.Programs;

import java.util.ArrayList;
import java.util.List;

public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.Viewholder> {


    Context mContext;
    List<Programs> courses = new ArrayList<>();

    public SkillAdapter(Context mContext, List<Programs> courses) {
        this.mContext = mContext;
        this.courses = courses;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
         LayoutInflater mInflater=LayoutInflater.from(mContext);
         View v=mInflater.inflate(R.layout.skillprog_layout,viewGroup,false);
         return new Viewholder(v);
        }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, final int i) {

        viewholder.co_elig.setText(courses.get(i).getElig());
        viewholder.co_date.setText(courses.get(i).getDate());
        viewholder.co_inst.setText(courses.get(i).getName());
        viewholder.co_name.setText(courses.get(i).getName());
        viewholder.co_dur.setText(courses.get(i).getDuration());

        viewholder.train_profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(mContext,InstProf.class);
                intent.putExtra("ID",courses.get(i).getFileId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder {

        TextView co_name,co_inst,co_date,co_elig,co_dur;
        Button train_profile_button;


        public Viewholder(@NonNull View itemView) {
            super(itemView);

            co_name=itemView.findViewById(R.id.co_name);
            co_dur=itemView.findViewById(R.id.co_dur);
            co_inst=itemView.findViewById(R.id.co_inst);
            co_date=itemView.findViewById(R.id.co_date);
            co_elig=itemView.findViewById(R.id.co_elig);

            train_profile_button=itemView.findViewById(R.id.train_profile_button);
        }
    }
}
