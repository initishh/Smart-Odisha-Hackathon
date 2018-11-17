package com.example.initish.testapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.initish.testapp.trainers.district_adapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SectorAdapter extends RecyclerView.Adapter<SectorAdapter.Viewholder> {

    Context context;
    List<Sector> sectors=new ArrayList<>();

    public SectorAdapter(Context context, List<Sector> sectors) {

        this.context = context;
        this.sectors = sectors;
    }

    @NonNull
    @Override
    public SectorAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater mInflater=LayoutInflater.from(context);
        View v=mInflater.inflate(R.layout.card_layout,viewGroup,false);
        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SectorAdapter.Viewholder viewholder, final int i) {

        viewholder.skill_title.setText(sectors.get(i).getName());
        viewholder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return sectors.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        TextView skill_title;
        CardView card_view;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            skill_title=itemView.findViewById(R.id.skill_title);
            card_view=itemView.findViewById(R.id.card_view);
        }
    }
}
