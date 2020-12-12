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
    private Button signup,login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruitment_home);

        signup = findViewById(R.id.buttonSignupHome);
        login = findViewById(R.id.buttonLoginHome);
        onclicklisteners();
    }
    void onclicklisteners(){
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(recruitment_home.this,recruitment_signup.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(recruitment_home.this,recruitment_login.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(recruitment_home.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}