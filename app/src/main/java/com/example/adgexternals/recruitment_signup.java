package com.example.adgexternals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
                if(checkEmpty()){
                    if(checkMail()){
                        if(regNo1.getText().toString().startsWith("19")){
                            if(github1.getText().length()==0){
                                Log.i("User","user1_1");
                                user  = new User(name,regNo,password,email,1,"https://github.com/panshuljindal");
                                sendNetworkRequest(user);
                            }
                            else{
                                Log.i("User","user1_2");
                                user = new User(name,regNo,password,email,1,github);
                                sendNetworkRequest(user);
                            }
                        }
                        else if(regNo1.getText().toString().startsWith("18")){
                            if(github1.getText().length()==0){
                                Log.i("User","user2_1");
                                Toast.makeText(recruitment_signup.this, "Github Link mandatory for second year", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Log.i("User","user2_2");
                                User user1 = new User(name,regNo,password,email,2,github);
                                sendNetworkRequest(user1);
                            }
                        }
                    }
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
                if(response.isSuccessful() && response.code()==200){
                    try{
                        Toast.makeText(recruitment_signup.this, "Signup Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(recruitment_signup.this,recruitment_login.class));
                    }
                    catch (Exception e){

                    }
                }
                else if(!response.isSuccessful()){
                    if(response.code()==400){
                        Toast.makeText(recruitment_signup.this, "Email or registration number already exists", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(recruitment_signup.this, "Error occurred. Please try again", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(recruitment_signup.this, "Error occurred. Please try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(recruitment_signup.this,"Error",Toast.LENGTH_SHORT).show();
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
    public Boolean checkMail()
        {
            String tempEmail=email1.getText().toString().trim();
            String tempReqNo=regNo1.getText().toString().trim();
            String subreg=tempReqNo.substring(1,2);
            Pattern emailPattern=Pattern.compile("^[a-z]+.[a-z]*[0-9]?201[0-9]@vitstudent.ac.in$");
            Matcher emailMatcher=emailPattern.matcher(tempEmail);
            if(emailMatcher.matches())
            {
                return true;
            }
            Toast.makeText(recruitment_signup.this, "Please enter Vit emailid", Toast.LENGTH_SHORT).show();
            return false;
        }
}