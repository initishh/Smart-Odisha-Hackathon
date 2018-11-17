package com.example.initish.testapp.trainers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.initish.testapp.R;

public class TrackStudd extends AppCompatActivity {

    Button butto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_studd);

        butto=findViewById(R.id.butto);

        butto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),TrackProfile.class));
            }
        });

    }
}
