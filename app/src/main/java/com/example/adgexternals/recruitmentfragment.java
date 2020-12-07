package com.example.adgexternals;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class recruitmentfragment extends Fragment {
    SharedPreferences pref,pref1;
    SharedPreferences.Editor editor;
    String token;
    ConstraintLayout clTechnical,clManagement,clDesign;
    String type="null";
    Boolean attemptedTechnical,attemptedManagement,attemptedDesign;
    Button submit;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_recruitmentfragment, container, false);

        clTechnical = view.findViewById(R.id.clTechnical);
        clManagement = view.findViewById(R.id.clManagement);
        clDesign = view.findViewById(R.id.clDesign);
        submit = view.findViewById(R.id.buttonDomainSubmit);

        pref= view.getContext().getSharedPreferences("com.adgexternals.com.token", Context.MODE_PRIVATE);
        token = pref.getString("Token","");

        pref1= view.getContext().getSharedPreferences("com.adgexternals.com.userdata",Context.MODE_PRIVATE);
        editor=pref1.edit();
        attemptedManagement = pref1.getBoolean("attemptedManagement",false);
        attemptedDesign = pref1.getBoolean("attemptedDesign",false);
        attemptedTechnical = pref1.getBoolean("attemptedTechnical",false);
        onclicklisteners();

        if(token.length()==0){
            startActivity(new Intent(getContext(),recruitment_home.class));
        }
        else{
            sendNetworkRequest(token);
        }
        return view;
    }
    public void onclicklisteners(){
        clTechnical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "technical";

            }
        });
        clManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "management";
            }
        });
        clDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "design";
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNetworkAvailable(v.getContext())) {
                    if (type.equals("null")) {
                        Toast.makeText(v.getContext(), "Please select a domain", Toast.LENGTH_SHORT).show();
                    } else if (type.equals("technical")) {
                        if (attemptedTechnical.equals(false)) {
                            Toast.makeText(v.getContext(), "You have selected technical", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(v.getContext(), "You have attempted technical quiz before", Toast.LENGTH_SHORT).show();
                        }
                    } else if (type.equals("management")) {
                        if (attemptedManagement.equals(false)) {
                            Toast.makeText(v.getContext(), "You have selected management", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(v.getContext(), "You have attempted management quiz before", Toast.LENGTH_SHORT).show();
                        }
                    } else if (type.equals("design")) {
                        if (attemptedDesign.equals(false)) {
                            Toast.makeText(v.getContext(), "You have selected design", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(v.getContext(), "You have attempted design quiz before", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            else{
                    Toast.makeText(v.getContext(), "Please connect to the internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
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
                            editor.putBoolean("attemptedTechnical", user.getAttemptedTechnical()).commit();
                            editor.putBoolean("attemptedManagement", user.getAttemptedManagement()).commit();
                            editor.putBoolean("attemptedDesign", user.getAttemptedDesign()).commit();
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