package com.example.adgexternals;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
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
    TextView tech,tech1,manage,manage1,design,design1;
    ConstraintLayout clTechnical,clManagement,clDesign;
    String type="null";
    Boolean attemptedTechnical,attemptedManagement,attemptedDesign;
    Button submit;
    int yearOfStudy;
    View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_recruitmentfragment, container, false);

        findViewByIds();

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
            startActivity(new Intent(view.getContext(),finishQuiz.class));
            sendNetworkRequest(token);
        }
        if(type=="null"){

        }
        else if(type=="technical"){
            clTechnical.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.recruitment_selectedback));
            tech.setTextColor(ContextCompat.getColor(view.getContext(),R.color.recruitment_white));
            tech1.setTextColor(ContextCompat.getColor(view.getContext(),R.color.recruitment_white));
            type = "technical";
        }
        else if(type=="management"){
            clManagement.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.recruitment_selectedback));
            manage.setTextColor(ContextCompat.getColor(view.getContext(),R.color.recruitment_white));
            manage1.setTextColor(ContextCompat.getColor(view.getContext(),R.color.recruitment_white));
            type = "management";
        }
        else if(type=="design"){
            clDesign.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.recruitment_selectedback));
            design.setTextColor(ContextCompat.getColor(view.getContext(),R.color.recruitment_white));
            design1.setTextColor(ContextCompat.getColor(view.getContext(),R.color.recruitment_white));
            type = "design";
        }
        else {
            type="null";
        }
        return view;
    }
    public void findViewByIds(){
        clTechnical = view.findViewById(R.id.clTechnical);
        clManagement = view.findViewById(R.id.clManagement);
        clDesign = view.findViewById(R.id.clDesign);
        submit = view.findViewById(R.id.buttonDomainSubmit);
        tech = view.findViewById(R.id.textViewTechnical);
        tech1 = view.findViewById(R.id.textViewTechnical1);
        manage = view.findViewById(R.id.textViewManagement);
        manage1 = view.findViewById(R.id.textViewManagement1);
        design = view.findViewById(R.id.textViewDesign);
        design1 = view.findViewById(R.id.textViewDesign1);
    }
    public void reset(){
        clTechnical.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.recruitment_back));
        clManagement.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.recruitment_back));
        clDesign.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.recruitment_back));
        tech.setTextColor(ContextCompat.getColor(view.getContext(),R.color.recruitment_black));
        tech1.setTextColor(ContextCompat.getColor(view.getContext(),R.color.recruitment_grey));
        manage.setTextColor(ContextCompat.getColor(view.getContext(),R.color.recruitment_black));
        manage1.setTextColor(ContextCompat.getColor(view.getContext(),R.color.recruitment_grey));
        design.setTextColor(ContextCompat.getColor(view.getContext(),R.color.recruitment_black));
        design1.setTextColor(ContextCompat.getColor(view.getContext(),R.color.recruitment_grey));
    }
    public void onclicklisteners(){
        clTechnical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
                clTechnical.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.recruitment_selectedback));
                tech.setTextColor(ContextCompat.getColor(view.getContext(),R.color.recruitment_white));
                tech1.setTextColor(ContextCompat.getColor(view.getContext(),R.color.recruitment_white));
                type = "technical";
            }
        });
        clManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
                clManagement.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.recruitment_selectedback));
                manage.setTextColor(ContextCompat.getColor(view.getContext(),R.color.recruitment_white));
                manage1.setTextColor(ContextCompat.getColor(view.getContext(),R.color.recruitment_white));
                type = "management";
            }
        });
        clDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
                clDesign.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.recruitment_selectedback));
                design.setTextColor(ContextCompat.getColor(view.getContext(),R.color.recruitment_white));
                design1.setTextColor(ContextCompat.getColor(view.getContext(),R.color.recruitment_white));
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
                            if(yearOfStudy==1) {
                                quiz_instruction fragment = new quiz_instruction();
                                FragmentManager manager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
                                FragmentTransaction transaction = manager.beginTransaction();
                                transaction.replace(R.id.frameLayout, fragment);
                                transaction.addToBackStack(null);
                                transaction.commit();
                            }
                            else if(yearOfStudy==2){
                                Toast.makeText(v.getContext(), "Second Year", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(v.getContext(), "Error. Please try again", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(v.getContext(), "You have attempted technical quiz before", Toast.LENGTH_SHORT).show();
                        }
                    } else if (type.equals("management")) {
                        if (attemptedManagement.equals(false)) {
                            management_instructions fragment = new management_instructions();
                            FragmentManager manager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
                            FragmentTransaction transaction = manager.beginTransaction();
                            transaction.replace(R.id.frameLayout, fragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                        } else {
                            Toast.makeText(v.getContext(), "You have attempted management quiz before", Toast.LENGTH_SHORT).show();
                        }
                    } else if (type.equals("design")) {
                        if (attemptedDesign.equals(false)) {
                            if(yearOfStudy==1){
                                design_instructions fragment = new design_instructions();
                                FragmentManager manager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
                                FragmentTransaction transaction = manager.beginTransaction();
                                transaction.replace(R.id.frameLayout, fragment);
                                transaction.addToBackStack(null);
                                transaction.commit();
                            }
                            else if (yearOfStudy==2){
                                Toast.makeText(v.getContext(), "Second Year", Toast.LENGTH_SHORT).show();
                            }
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
                            yearOfStudy = user.getYearofstudy();
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