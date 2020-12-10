package com.example.adgexternals;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class about_us_fragment extends Fragment {
    List<aboutUs> list;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_about_us_fragment, container, false);
        return view;
    }
    public void githubLinkedin(String link, View v){
        Uri uri = Uri.parse(link);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        v.getContext().startActivity(intent);
    }
    public void addData(){
        list.add(new aboutUs("Panshul Jindal","https://github.com/panshuljindal","https://www.linkedin.com/in/panshul-jindal-392746199","panshuljindal@gmail.com"));
        list.add(new aboutUs("Ritik Suryawanshi","https://github.com/rajritik2607","https://www.linkedin.com/in/ritik-suryawanshi-7075441a6","mailto:ritik.suryawanshi@gmail.com"));
        list.add(new aboutUs("Arupam Kumar Saha","https://github.com/arupam","https://www.linkedin.com/in/arupam-kumar-saha-310653191/","sahaarupam37@gmail.com"));
        list.add(new aboutUs("Gokul Nair","https://github.com/gokulnair2001","https://www.linkedin.com/in/gokul-r-nair/","gokulnair.2001@gmail.com"));
        list.add(new aboutUs("Krishna Khanikar","https://github.com/krishnakhanikar","https://www.linkedin.com/in/krishna-khanikar-a0144a19a/","krishna.khanikar2019@vitstudent.ac.in"));
        list.add(new aboutUs("Prabaljit Walia","https://github.com/prabal4546","https://www.linkedin.com/in/prabaljit-walia-5800571a0","prabaljit.walia2019@vitstudent.ac.in"));
    }
}