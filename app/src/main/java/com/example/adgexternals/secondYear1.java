package com.example.adgexternals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class secondYear1 extends AppCompatActivity {
    Button submit;
    EditText q1,q2;
    TextView heading;
    String token;
    SharedPreferences pref;
    SharedPreferences pref1;
    SharedPreferences.Editor editor;
    boolean cheat=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_year1);

        q1= findViewById(R.id.secondq1A);
        q2 = findViewById(R.id.secondq2A);
        submit = findViewById(R.id.submit_button_secondTechnical);
        heading = findViewById(R.id.twoHeading);
        pref= getSharedPreferences("com.adgexternals.com.token", Context.MODE_PRIVATE);
        token = pref.getString("Token","");

        pref1= getSharedPreferences("com.adgexternals.com.userdata",Context.MODE_PRIVATE);
        editor = pref1.edit();
        sendQuestionsRequest();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNetworkAvailable(v.getContext())){
                    postQuestion2 user = new postQuestion2(q2.getText().toString(),q1.getText().toString());
                    sendPostQuestionRequest(user);
                }
                else {
                    Toast.makeText(secondYear1.this, "Please connect to the internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void sendPostQuestionRequest(postQuestion2 ques){
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
        Call<postQuestion2> call = client.postQuestionTechnical2(token,ques);
        call.enqueue(new Callback<postQuestion2>() {
            @Override
            public void onResponse(Call<postQuestion2> call, Response<postQuestion2> response) {
                if(response.code()==200){
                    Toast.makeText(secondYear1.this, "Thank you for your submission", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(secondYear1.this,MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                else if(response.code()==403){
                    Toast.makeText(secondYear1.this, "You have attempted the quiz before", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(secondYear1.this,MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(secondYear1.this, "Error. Please try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<postQuestion2> call, Throwable t) {
                Toast.makeText(secondYear1.this, "Network Error. Please try again", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void sendQuestionsRequest(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpclient = new OkHttpClient.Builder();
        httpclient.addInterceptor(logging);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://adgrecruitments.herokuapp.com/questions/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpclient.build()).build();
        userClient client = retrofit.create(userClient.class);
        Call<List<questionObjectTechnical>> call = client.getQuestionTechnical2(token);
        call.enqueue(new Callback<List<questionObjectTechnical>>() {
            @Override
            public void onResponse(Call<List<questionObjectTechnical>> call, Response<List<questionObjectTechnical>> response) {
                if(response.code()==200){
                    if(cheat==true){
                        editor.putBoolean("attemptedTechnical", true).commit();
                        editor.apply();
                        Toast.makeText(secondYear1.this, "Cheating", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(secondYear1.this,MainActivity.class));
                    }
                    else{
                        editor.putBoolean("attemptedTechnical", true).commit();
                        editor.apply();
                        Toast.makeText(secondYear1.this, "Start", Toast.LENGTH_SHORT).show();
                    }
                }
                else if(response.code()==400){
                    if(cheat==true){
                        editor.putBoolean("attemptedTechnical", true).commit();
                        editor.apply();
                        Toast.makeText(secondYear1.this, "Cheating", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(secondYear1.this,MainActivity.class));
                    }
                    else{
                        editor.putBoolean("attemptedTechnical", true).commit();
                        editor.apply();
                        Toast.makeText(secondYear1.this, "You cannot submit quiz more than once", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<questionObjectTechnical>> call, Throwable t) {

            }
        });
    }
    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        cheat =true;
        //cheating();
    }
}