package com.example.adgexternals;

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

    EditText email1,name1,regNo1,phoneNo1,github1,password1;
    String email,name,regNo,phoneNo,github,password;
    Button continue1;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruitment_signup);
        findId();
        onclicklisteners();
    }
    public void findId(){
        email1 =findViewById(R.id.editProfileEmailid);
        name1 = findViewById(R.id.editProfileName);
        regNo1 = findViewById(R.id.editProfileRegNo);
        phoneNo1 = findViewById(R.id.editProfilePhoneNumber);
        github1 = findViewById(R.id.editProfileGithub);
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
                if (isNetworkAvailable(v.getContext())) {
                    if (checkEmpty()) {
                        if (checkMail()) {
                            if (regNo1.getText().toString().startsWith("20")) {
                                if (github1.getText().length() == 0) {
                                    user = new User(name, regNo, password, email, 1, "https://github.com/adgvit");
                                    sendNetworkRequest(user);
                                } else {
                                    if(checkGithub()) {
                                        user = new User(name, regNo, password, email, 1, github);
                                        sendNetworkRequest(user);
                                    }
                                    else {
                                        Toast.makeText(v.getContext(), "Please enter full github link", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } else if (regNo1.getText().toString().startsWith("19")) {
                                if (github1.getText().length() == 0) {
                                    Toast.makeText(recruitment_signup.this, "Github Link mandatory for second year", Toast.LENGTH_SHORT).show();
                                } else {
                                    if(checkGithub()) {
                                        User user1 = new User(name, regNo, password, email, 2, github);
                                        sendNetworkRequest(user1);
                                    }
                                    else{
                                        Toast.makeText(v.getContext(), "Please enter full github link", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            else{
                                Toast.makeText(v.getContext(), "Please enter a correct registration number", Toast.LENGTH_SHORT).show();
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
    public void sendNetworkRequest(User user1){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpclient = new OkHttpClient.Builder();
        httpclient.addInterceptor(logging);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://adgrecruitments.herokuapp.com/user/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpclient.build())
                .build();
        userClient client = retrofit.create(userClient.class);
        Call<User> call = client.signupuser(user1);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.i("Code",String.valueOf(response.code()));
                if(response.code()==201){
                    Toast.makeText(recruitment_signup.this, "Sign up Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(recruitment_signup.this,recruitment_login.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                if(response.code()==400){
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
    public boolean checkEmpty(){
        if(name1.getText().length()==0){
            Toast.makeText(this, "Please enter name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(regNo1.getText().length()==0){
            Toast.makeText(this, "Please enter a registration number", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(email1.getText().length()==0){
            Toast.makeText(this, "Please enter emailid", Toast.LENGTH_SHORT).show();
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
    public Boolean checkMail(){
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
    public boolean checkGithub(){
        String tempGit = github1.getText().toString();
        if(tempGit.startsWith("https://github.com/")){
            return true;
        }
        return false;
    }
    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}