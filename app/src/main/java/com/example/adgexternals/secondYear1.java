package com.example.adgexternals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class secondYear1 extends AppCompatActivity {
    Button submit;
    EditText q1,q2;
    TextView heading;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_year1);

        q1= findViewById(R.id.secondq1A);
        q2 = findViewById(R.id.secondq2A);
        submit = findViewById(R.id.submit_button_secondTechnical);
        heading = findViewById(R.id.twoHeading);

        Intent intent=getIntent();
        type= intent.getStringExtra("type");
        if(type=="design"){
            heading.setText("Design Round");
        }
        else if(type=="technical"){
            heading.setText("Technical Round");
        }
        else{
            Toast.makeText(this, "Please try again", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(secondYear1.this,MainActivity.class));
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}