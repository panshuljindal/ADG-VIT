package com.adgvit.externals;

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
import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class recruitment_login extends AppCompatActivity {
    private Button login;
    private EditText regNo1,password1;
    private String regNo,password;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
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
                regNo = regNo1.getText().toString().toUpperCase();
                password = password1.getText().toString();
                if(checkEmpty()){
                    loginrequest loginrequest = new loginrequest(regNo,password);
                    if(isNetworkAvailable(v.getContext())){
                        sendNetworkRequest(loginrequest);
                    }
                    else {
                        Toast.makeText(recruitment_login.this, "Please connect to the internet", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }

    boolean checkEmpty(){
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
    void sendNetworkRequest(loginrequest user){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://adgrecruitments.herokuapp.com/user/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userClient client = retrofit.create(userClient.class);
        Call<loginResponse> call = client.loginUser(user);
        call.enqueue(new Callback<loginResponse>() {
            @Override
            public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {
                if(response.code()==200) {
                    try {
                        Toast.makeText(recruitment_login.this, "You have successfully logged in!", Toast.LENGTH_SHORT).show();
                        editor.putString("Token",response.body().getToken());
                        editor.apply();
                        Intent intent = new Intent(recruitment_login.this,MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } catch (Exception e) {
                        Log.i("Exception", e.toString());
                    }
                }
                else if (response.code() == 403) {
                    Toast.makeText(recruitment_login.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                }
                else if (response.code() == 400) {
                    Toast.makeText(recruitment_login.this, "Registration number not found", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(recruitment_login.this, "Error occurred. Please try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<loginResponse> call, Throwable t) {
                Toast.makeText(recruitment_login.this,"Error occurred. Please try again",Toast.LENGTH_SHORT).show();
            }
        });
    }
    boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent i = new Intent(recruitment_login.this,recruitment_home.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}