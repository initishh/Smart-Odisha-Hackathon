package com.example.initish.testapp.trainers;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.initish.testapp.CommentAdapter;
import com.example.initish.testapp.Item;
import com.example.initish.testapp.R;

import java.util.ArrayList;
import java.util.List;

public class district_adapter extends RecyclerView.Adapter<district_adapter.Viewholder> {


    Context mContext;
    List<String> districts = new ArrayList<>();

    public district_adapter(Context mContext, List<String> districts) {
        this.mContext = mContext;
        this.districts = districts;
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

        viewholder.dist_name.setText(districts.get(i));
        viewholder.dist_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,TrainingCentre.class);
                intent.putExtra("Dist_name",districts.get(i));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
   return districts.size();
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

