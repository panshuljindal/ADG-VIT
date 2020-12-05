package com.example.adgexternals;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class recruitmentfragment extends Fragment {
    SharedPreferences pref;
    String token;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_recruitmentfragment, container, false);

        pref= view.getContext().getSharedPreferences("com.adgexternals.com.token", Context.MODE_PRIVATE);
        token = pref.getString("Token","");

        if(token.length()==0){
            startActivity(new Intent(getContext(),recruitment_home.class));
        }
        else{

        }
        return view;
    }
}