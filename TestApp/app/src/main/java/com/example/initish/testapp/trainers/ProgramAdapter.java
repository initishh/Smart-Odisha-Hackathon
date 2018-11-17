package com.example.initish.testapp.trainers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.initish.testapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.Viewholder> {


    Context mContext;
    List<Programs> programs = new ArrayList<>();
    FirebaseFirestore db=FirebaseFirestore.getInstance();

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater mInflater=LayoutInflater.from(mContext);
        View v=mInflater.inflate(R.layout.program_layout,viewGroup,false);
        return new Viewholder(v);

    }

    public ProgramAdapter(Context mContext, List<Programs> programs) {
        this.mContext = mContext;
        this.programs = programs;
    }

    @Override
    public void onBindViewHolder(@NonNull final Viewholder viewholder, final int i) {

        viewholder.c_name.setText(programs.get(i).getName());
        viewholder.c_date.setText(programs.get(i).getDate());
        viewholder.c_dur.setText(programs.get(i).getDuration());
        viewholder.c_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.collection("programs").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot d:list){

                            Programs program=d.toObject(Programs.class);
                            if(programs.get(i).getFileId().equals(program.getFileId())){



                            }

                        }

                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return programs.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder
    {
        TextView c_name,c_dur,c_date,dots;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            c_name=itemView.findViewById(R.id.c_name);
            c_date=itemView.findViewById(R.id.c_date);
            c_dur=itemView.findViewById(R.id.c_dur);
            }

    }
}
