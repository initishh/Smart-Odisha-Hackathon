package com.example.initish.testapp.trainers;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class GoingAdapter extends RecyclerView.Adapter<GoingAdapter.Viewholder> {
    @NonNull
    @Override
    public GoingAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull GoingAdapter.Viewholder viewholder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class Viewholder extends RecyclerView.ViewHolder {


        public Viewholder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
