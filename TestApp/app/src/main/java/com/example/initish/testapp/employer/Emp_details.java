package com.example.initish.testapp.employer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.initish.testapp.R;

public class Emp_details extends AppCompatActivity {


    Button companyDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_details);

        companyDetails=findViewById(R.id.companyDetails);

        companyDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), emp_home.class));

            }
        });
    }
}
