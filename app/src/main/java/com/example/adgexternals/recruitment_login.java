package com.example.adgexternals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class recruitment_login extends AppCompatActivity {
    Button login;
    EditText regNo1,password1;
    String regNo,password;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruitment_login);

        login = findViewById(R.id.buttonLogin);
        regNo1 = findViewById(R.id.loginRegNo);
        password1 = findViewById(R.id.loginPassword);
        pref = this.getSharedPreferences("com.adgexternals.com.token",MODE_PRIVATE);
        editor = pref.edit();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regNo = regNo1.getText().toString();
                password = password1.getText().toString();
                if(checkEmpty()){
                    loginrequest loginrequest = new loginrequest(regNo,password);
                    sendNetworkRequest(loginrequest);
                }

            }
        });

    }
    public boolean checkEmpty(){
        if(regNo1.getText().length()==0){
            Toast.makeText(this, "Please enter registration number", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password1.getText().length()==0){
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public void sendNetworkRequest(loginrequest user){
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
        Call<loginResponse> call = client.loginUser(user);
        call.enqueue(new Callback<loginResponse>() {
            @Override
            public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {
                if(response.isSuccessful()){
                try {
                    editor.putString("Token",response.body().getToken());
                    editor.apply();
                    Toast.makeText(recruitment_login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(recruitment_login.this,MainActivity.class));
                }
                    catch (Exception e){
                        //Log.i("Message",response.body().getMessage());
                        Log.i("Exception",e.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<loginResponse> call, Throwable t) {

            }
        });
    }
}