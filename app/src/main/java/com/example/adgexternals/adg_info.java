package com.example.adgexternals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class adg_info extends AppCompatActivity {
    Button cancel;
    ImageView adgFb , adgTwitter , adgInsta , adgLinkedin;
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

    private void onClickListener() {

        adgLinkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        adgFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        adgTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        adgInsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

    }
}