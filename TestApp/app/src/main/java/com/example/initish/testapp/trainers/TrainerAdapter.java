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

import com.example.initish.testapp.R;

import java.util.ArrayList;
import java.util.List;

public class TrainerAdapter extends RecyclerView.Adapter<TrainerAdapter.Viewholder> {



    Context mContext;
    List<String> inst = new ArrayList<>();

    public TrainerAdapter(Context mContext, List<String> inst) {
        this.mContext = mContext;
        this.inst = inst;
    }

    @NonNull
    @Override
    public TrainerAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater mInflater=LayoutInflater.from(mContext);
        View v=mInflater.inflate(R.layout.district_layout,viewGroup,false);
        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainerAdapter.Viewholder viewholder, final int i) {

        viewholder.dist_name.setText(inst.get(i));
        viewholder.dist_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,TrainingCentreHome.class);
                intent.putExtra("Inst_name",inst.get(i));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return inst.size();
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
