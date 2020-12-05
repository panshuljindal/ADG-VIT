package com.example.adgexternals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class recruitment_signup extends AppCompatActivity {

    EditText email1,name1,regNo1,phoneNo1,github1,password1;
    String email,name,regNo,phoneNo,github,password;
    Button continue1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruitment_signup);
        findId();
        onclicklisteners();
    }
    public void findId(){
        email1 =findViewById(R.id.signupEmailid);
        name1 = findViewById(R.id.signupName);
        regNo1 = findViewById(R.id.signupRegNo);
        phoneNo1 = findViewById(R.id.signupPhoneNumber);
        github1 = findViewById(R.id.signupGithub);
        password1 = findViewById(R.id.signupPassword);
        continue1 = findViewById(R.id.buttonSignupContinue);
    }
    public void onclicklisteners(){
        continue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = email1.getText().toString();
                name = name1.getText().toString();
                regNo = regNo1.getText().toString();
                phoneNo = phoneNo1.getText().toString();
                github = github1.getText().toString();
                password = password1.getText().toString();
                startActivity(new Intent(recruitment_signup.this,recruitment_login.class));

            }
        });
    }
}