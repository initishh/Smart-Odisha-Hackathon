package com.example.initish.testapp.trainers;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.initish.testapp.AddQuestion;
import com.example.initish.testapp.R;
import com.example.initish.testapp.SectorAdapter;
import com.example.initish.testapp.SkillAdapter;

public class Going extends Fragment {


    Context context;
    Button candbutton;
    FloatingActionButton fabon;
    String data;

    public Going() {
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.activity_going,container,false);

        candbutton=rootView.findViewById(R.id.candbutton);

        candbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getActivity(),TrackStud.class);
                startActivity(intent);
            }
        });

        fabon=rootView.findViewById(R.id.fabon);
        fabon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getActivity(),ongoingProg.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
