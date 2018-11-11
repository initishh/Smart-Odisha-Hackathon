package com.example.initish.testapp.employer;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.initish.testapp.ApplicantProfile;
import com.example.initish.testapp.CommentAdapter;
import com.example.initish.testapp.Profile;
import com.example.initish.testapp.R;
import com.example.initish.testapp.StudentDetails;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ApplicantAdapter extends RecyclerView.Adapter<ApplicantAdapter.myViewHolder> {

    Context context;
    List<candidates> list=new ArrayList<>();
    FirebaseFirestore db=FirebaseFirestore.getInstance();

    public ApplicantAdapter(Context context, List<candidates> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View v = mInflater.inflate(R.layout.applicant_layout, viewGroup, false);
        return new ApplicantAdapter.myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, final int i) {

        myViewHolder.post_train.setText(list.get(i).getTrainings());
        myViewHolder.post_skill.setText(list.get(i).getSkills());
        myViewHolder.post_name.setText(list.get(i).getName());
        Picasso.get().load(list.get(i).getPhotourl()).into(myViewHolder.applicant_photo);

        myViewHolder.post_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,ApplicantProfile.class);
                intent.putExtra("ApplicantID",list.get(i).getCandidateId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        Button post_button;
        ImageView applicant_photo;
        TextView post_name,post_skill,post_train;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            applicant_photo=itemView.findViewById(R.id.applicant_photo);
            post_name=itemView.findViewById(R.id.post_name);
            post_skill=itemView.findViewById(R.id.post_skill);
            post_train=itemView.findViewById(R.id.post_train);
            post_button=itemView.findViewById(R.id.post_button);
        }
    }
}
