package com.example.adgexternals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class recruitment_home extends AppCompatActivity {
    Button signup,login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruitment_home);

        signup = findViewById(R.id.buttonSignupHome);
        login = findViewById(R.id.buttonLoginHome);
        onclicklisteners();
    }
    public void onclicklisteners(){
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(recruitment_home.this,recruitment_signup.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(recruitment_home.this,recruitment_login.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(recruitment_home.this,MainActivity.class));
    }
}