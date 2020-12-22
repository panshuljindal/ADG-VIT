package com.adgvit.externals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class recruitment_signup extends AppCompatActivity {

    private EditText email1,name1,regNo1,phoneNo1,github1,password1;
    private String email,name,regNo,phoneNo,github,password;
    private Button continue1;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruitment_signup);
        findId();
        onclicklisteners();
    }
    void findId(){
        email1 =findViewById(R.id.editProfileEmailid);
        name1 = findViewById(R.id.editProfileName);
        regNo1 = findViewById(R.id.editProfileRegNo);
        phoneNo1 = findViewById(R.id.editProfilePhoneNumber);
        github1 = findViewById(R.id.editProfileGithub);
        password1 = findViewById(R.id.signupPassword);
        continue1 = findViewById(R.id.buttonSignupContinue);
    }
    void onclicklisteners(){
        continue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = email1.getText().toString();
                name = name1.getText().toString();
                regNo = regNo1.getText().toString().toUpperCase();
                phoneNo = phoneNo1.getText().toString();
                github = github1.getText().toString();
                password = password1.getText().toString();
                Log.i("regNo",regNo.toUpperCase());
                if (isNetworkAvailable(v.getContext())) {
                    if (checkEmpty()) {
                        if (checkMail()) {
                            if (checkPhone()) {
                                if (regNo1.getText().toString().startsWith("20") && email1.getText().toString().contains("2020")) {
                                    if (github1.getText().length() == 0) {
                                        user = new User(name, regNo, password, email, 1, "https://github.com/adgvit",phoneNo);
                                        sendNetworkRequest(user);
                                    } else {
                                        if (checkGithub()) {
                                            user = new User(name, regNo, password, email, 1, github,phoneNo);
                                            sendNetworkRequest(user);
                                        } else {
                                            Toast.makeText(v.getContext(), "Please enter in the format https://github.com/username", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } else if (regNo1.getText().toString().startsWith("19") && email1.getText().toString().contains("2019")) {
                                    if (github1.getText().length() == 0) {
                                        Toast.makeText(recruitment_signup.this, "Github Link mandatory for second year", Toast.LENGTH_SHORT).show();
                                    } else {
                                        if (checkGithub()) {
                                            User user1 = new User(name, regNo, password, email, 2, github,phoneNo);
                                            sendNetworkRequest(user1);
                                        } else {
                                            Toast.makeText(v.getContext(), "Please enter in the format https://github.com/username", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } else {
                                    Toast.makeText(v.getContext(), "Registration number and email id doesn't match", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                }
                else {
                    Toast.makeText(recruitment_signup.this, "Please connect to the internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    void sendNetworkRequest(User user1){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://adgrecruitments.herokuapp.com/user/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userClient client = retrofit.create(userClient.class);
        Call<User> call = client.signupuser(user1);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.i("Code",String.valueOf(response.code()));
                if(response.code()==201){
                    Toast.makeText(recruitment_signup.this, "Sign up Successful. Please check inbox or spam for verification", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(recruitment_signup.this,recruitment_login.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                else if(response.code()==400){
                    Toast.makeText(recruitment_signup.this, "Email id or registration number already exists", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(recruitment_signup.this, "Error occurred. Please try again", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(recruitment_signup.this,"Error occurred. Please try again",Toast.LENGTH_SHORT).show();
            }
        });
    }
    boolean checkEmpty(){
        if(name1.getText().length()==0){
            Toast.makeText(this, "Please enter name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(regNo1.getText().length()==0){
            Toast.makeText(this, "Please enter a registration number", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(email1.getText().length()==0){
            Toast.makeText(this, "Please enter email id", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(phoneNo1.getText().length()==0){
            Toast.makeText(this, "Please enter a phone number", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password1.getText().length()==0){
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    boolean checkPhone(){
        String tempEmail = phoneNo1.getText().toString();
        if(tempEmail.startsWith("6")  && tempEmail.length()==10){
            return true;
        }
        if(tempEmail.startsWith("7")  && tempEmail.length()==10){
            return true;
        }
        if(tempEmail.startsWith("8")  && tempEmail.length()==10){
            return true;
        }
        if(tempEmail.startsWith("9")  && tempEmail.length()==10){
            return true;
        }
        Toast.makeText(this, "Please enter a correct phone number", Toast.LENGTH_SHORT).show();
        return false;
    }
    boolean checkMail(){
            String tempEmail=email1.getText().toString().trim();
            Pattern emailPattern=Pattern.compile("^[a-z]+.[a-z]*[0-9]?20[0-9][0-9]@vitstudent.ac.in$");
            Matcher emailMatcher=emailPattern.matcher(tempEmail);
            if(emailMatcher.matches())
            {
                return true;
            }
            Toast.makeText(recruitment_signup.this, "Please enter Vit email id", Toast.LENGTH_SHORT).show();
            return false;
    }
    boolean checkGithub(){
        String tempGit = github1.getText().toString();
        if(tempGit.startsWith("https://github.com/")){
            return true;
        }
        Toast.makeText(this, "Please enter in the format https://github.com/username", Toast.LENGTH_SHORT).show();
        return false;
    }
    boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}