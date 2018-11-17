package com.example.initish.testapp.trainers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.initish.testapp.R;

public class Held extends Fragment {

    Button butt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.activity_held,container,false);
        butt=rootView.findViewById(R.id.butt);

        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                butt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getActivity(),TrackStudd.class));
                    }
                });

            }
        });

        return rootView;
    }
}
