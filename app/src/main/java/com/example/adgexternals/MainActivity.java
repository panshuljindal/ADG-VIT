package com.example.adgexternals;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    SharedPreferences pref,pref1;
    SharedPreferences.Editor editor;
    BottomNavigationView bottomNavigationView;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.nav_view);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new homefragment()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedfragment = null;

                switch (item.getItemId()){
                    case R.id.navigation_home:
                        selectedfragment = new homefragment();
                        break;
                    case R.id.navigation_recruitment:
                        selectedfragment = new recruitmentfragment();
                        break;
                    case R.id.navigation_faq:
                        selectedfragment = new faq_fragment();
                        break;
                    case R.id.navigation_settings:
                        selectedfragment = new settings_fragment();
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,selectedfragment).commit();
                return  true;
            }
        });
        pref = this.getSharedPreferences("com.adgexternals.com.token",MODE_PRIVATE);
        token = pref.getString("Token","");

        pref1 = this.getSharedPreferences("com.adgexternals.com.userdata",MODE_PRIVATE);
        editor=pref1.edit();


        sendNetworkRequest(token);
    }
    public void sendNetworkRequest(String t){
        HttpLoggingInterceptor logging  =new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpclient = new OkHttpClient.Builder();
        httpclient.addInterceptor(logging);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://adgrecruitments.herokuapp.com/user/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpclient.build())
                .build();
        userClient client = retrofit.create(userClient.class);
        Call<root> call = client.getUser(token);
        call.enqueue(new Callback<root>() {
            @Override
            public void onResponse(Call<root> call, Response<root> response) {
                if(response.isSuccessful())
                if(response.body().userDetails.getName().length()!=0){
                    try {
                        userDetails user = response.body().getUserDetails();
                        editor.putBoolean("attemptedTechnical", user.getAttemptedTechnical());
                        editor.putBoolean("attemptedManagement", user.getAttemptedManagement());
                        editor.putBoolean("attemptedDesign", user.getAttemptedDesign());
                        editor.putInt("yearofstudy", user.getYearofstudy());
                        editor.putString("id", user.getId());
                        editor.putString("name", user.getName());
                        editor.putString("regno", user.getRegno());
                        editor.putString("email", user.getEmail());
                        editor.apply();
                    }
                    catch (Exception e){

                    }
                }

            }

            @Override
            public void onFailure(Call<root> call, Throwable t) {

            }
        });

    }
}