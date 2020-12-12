package com.example.adgexternals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import static android.net.Uri.*;

public class adg_info extends AppCompatActivity {
    private Button cancel;
    private ImageView adgFb , adgTwitter , adgInsta , adgLinkedin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adg_info);

        adgFb = findViewById(R.id.adgFacebook);
        adgTwitter = findViewById(R.id.adgTwitter);
        adgInsta = findViewById(R.id.adgInstagram);
        adgLinkedin = findViewById(R.id.adgLinkedin);

        cancel = findViewById(R.id.buttonInfoCancel);

        onClickListener();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(adg_info.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    void githubLinkedin(String link, View v){
        Uri uri = parse(link);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        v.getContext().startActivity(intent);
    }

    void onClickListener() {

        adgLinkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                githubLinkedin("https://www.linkedin.com/company/adgvit/",v);
            }
        });

        adgFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                githubLinkedin("https://www.facebook.com/vitios/",v);
            }
        });

        adgTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                githubLinkedin("https://twitter.com/adgvit",v);
            }
        });

        adgInsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                githubLinkedin("https://www.instagram.com/adgvit/",v);
            }
        });

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent i = new Intent(adg_info.this,MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}