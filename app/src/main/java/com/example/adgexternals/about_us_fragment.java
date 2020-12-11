package com.example.adgexternals;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class about_us_fragment extends Fragment {
    List<aboutUs> list;
    View view;
    Button backaboutus;
    ImageView ritikL,ritikGit,ritikGm,krishnaL,krishnaGit,krishnaGm,arupamL,arupamGit,arupamGm,panshulL,panshulGit,panshulGm
    ,gokulL,gokulGit,gokulGm,prabaljitL,prabaljitGit,prabaljitGm;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_about_us_fragment, container, false);


        findViewByIds();
        list=new ArrayList<>();
        addData();
        onClickListener();
        return view;
    }
    public void onClickListener(){

        backaboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate();
            }
        });

        ritikL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                githubLinkedin(list.get(1).getLinkedin(),v);
            }
        });
        ritikGit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                githubLinkedin(list.get(1).getGithub(),v);
            }
        });
        ritikGm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email(list.get(1).getEmail(),v);
            }
        });
        krishnaL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                githubLinkedin(list.get(4).getLinkedin(),v);
            }
        });
        krishnaGit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                githubLinkedin(list.get(4).getGithub(),v);
            }
        });
        krishnaGm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email(list.get(4).getEmail(),v);
            }
        });
        arupamL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                githubLinkedin(list.get(2).getLinkedin(),v);
            }
        });
        arupamGit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                githubLinkedin(list.get(2).getGithub(),v);
            }
        });
        arupamGm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email(list.get(2).getEmail(),v);
            }
        });
        panshulL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                githubLinkedin(list.get(0).getLinkedin(),v);
            }
        });
        panshulGit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                githubLinkedin(list.get(0).getGithub(),v);
            }
        });
        panshulGm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email(list.get(0).getEmail(),v);
            }
        });
        gokulL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                githubLinkedin(list.get(3).getLinkedin(),v);
            }
        });
        gokulGit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                githubLinkedin(list.get(3).getGithub(),v);
            }
        });
        gokulGm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email(list.get(3).getEmail(),v);
            }
        });
        prabaljitL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                githubLinkedin(list.get(5).getLinkedin(),v);
            }
        });
        prabaljitGit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                githubLinkedin(list.get(5).getGithub(),v);
            }
        });
        prabaljitGm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email(list.get(5).getEmail(),v);
            }
        });
    }
    public void findViewByIds(){
        backaboutus = view.findViewById(R.id.buttonaboutusback);
        ritikL = view.findViewById(R.id.ritikLinkedin);
        ritikGit = view.findViewById(R.id.ritikGithub);
        ritikGm = view.findViewById(R.id.ritikEmail);
        krishnaL = view.findViewById(R.id.krishnaLinkedin);
        krishnaGit = view.findViewById(R.id.krishnaGithub);
        krishnaGm = view.findViewById(R.id.krishnaEmail);
        arupamL = view.findViewById(R.id.arupamLinkedin);
        arupamGit = view.findViewById(R.id.arupamGithub);
        arupamGm = view.findViewById(R.id.arupamEmail);
        panshulL =view.findViewById(R.id.panshulLinkedin);
        panshulGit = view.findViewById(R.id.panshulGithub);
        panshulGm =view.findViewById(R.id.panshulEmail);
        gokulL = view.findViewById(R.id.gokulLinkedin);
        gokulGit=view.findViewById(R.id.gokulGithub);
        gokulGm =view.findViewById(R.id.gokulEmail);
        prabaljitL = view.findViewById(R.id.prabaljitLinkedin);
        prabaljitGit =view.findViewById(R.id.prabaljitGithub);
        prabaljitGm = view.findViewById(R.id.prabaljitEmail);
    }
    public void email(String text,View view){

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