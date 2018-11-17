package com.example.initish.testapp.employer;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.initish.testapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class JobPostAdapter extends RecyclerView.Adapter<JobPostAdapter.myViewHolder> {

    Context context;
    List<PostItem> postItems=new ArrayList<>();
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    static java.text.DateFormat dateFormat = new SimpleDateFormat("hh:mm  dd-mm-yyyy ");

    public JobPostAdapter(Context context, List<PostItem> postItems) {
        this.context = context;
        this.postItems = postItems;
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        TextView job_time,job_title,textViewOptions,job_salary,job_city,job_skill;
        ConstraintLayout job_post;
        public myViewHolder(@NonNull View itemView) {

            super(itemView);
            textViewOptions=itemView.findViewById(R.id.textViewOptions);
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
    public void onBindViewHolder(@NonNull final myViewHolder myViewHolder, final int i) {


        myViewHolder.job_title.setText(postItems.get(i).getTitle());
        myViewHolder.job_skill.setText("Skill: "+postItems.get(i).getSkill());
        myViewHolder.job_salary.setText("Salary: "+postItems.get(i).getSalary());
        myViewHolder.job_time.setText(dateFormat.format(postItems.get(i).getTime()));
        myViewHolder.job_city.setText("Place: "+postItems.get(i).getAddress());

       final String ID=postItems.get(i).getFileId();
        myViewHolder.textViewOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //creating a popup menu
                PopupMenu popup = new PopupMenu(context, myViewHolder.textViewOptions);
                //inflating menu from xml resource
                popup.inflate(R.menu.remove_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.remove:

                                db.collection("jobposts").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                    @Override
                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                        List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                                        for(DocumentSnapshot d:list){

                                            PostItem postItem=d.toObject(PostItem.class);
                                            if(postItem.getFileId().equals(ID)){

                                                db.collection("jobposts").document(postItem.getFileId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {

                                                        Toast.makeText(context, "Job post removed successfully", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                            }
                                        }

                                    }
                                });

                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popup.show();

            }
        });

        myViewHolder.job_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.collection("employers").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        String userId=FirebaseAuth.getInstance().getUid();
                        List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot d:list){
                            employer emp=d.toObject(employer.class);
                            if(userId.equals(emp.getEmp_id()))
                            {
                                Intent intent=new Intent(context,Applicants.class);
                                intent.putExtra("JobId",postItems.get(i).getFileId());
                                context.startActivity(intent);
                                break;
                            }
                        }

                    }
                });

                db.collection("candidates").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        String userId=FirebaseAuth.getInstance().getUid();
                        List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot d:list){
                            candidates cand=d.toObject(candidates.class);
                            if(userId.equals(cand.getCandidateId())){

                                Intent intent=new Intent(context,JobPostDiscussion.class);
                                intent.putExtra("JobId",postItems.get(i).getFileId());
                                context.startActivity(intent);
                                break;
                            }
                        }

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return postItems.size();
    }

}
