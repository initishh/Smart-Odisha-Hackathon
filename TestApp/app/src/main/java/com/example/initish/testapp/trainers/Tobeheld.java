package com.example.initish.testapp.trainers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.initish.testapp.R;

public class Tobeheld extends Fragment {

    FloatingActionButton fab;
    String data;

    public Tobeheld() {
    }

    public Tobeheld(String data) {
        this.data = data;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.activity_tobeheld,container,false);

        fab=rootView.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getActivity(),PostTraining.class);
                intent.putExtra("Name",data);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
