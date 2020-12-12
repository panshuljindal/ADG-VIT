package com.example.adgexternals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class landingActivity extends AppCompatActivity {
    private boolean count1 = false;
    private int count = 0;
    private Animation animationFadein;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private ImageView adglogo1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        pref = getSharedPreferences("com.adgexternals.com.count", MODE_PRIVATE);
        editor = pref.edit();
        count1 = pref.getBoolean("count", false);
        if (count1 == true) {
            startActivity(new Intent(landingActivity.this, MainActivity.class));
        } else if (count1 == false) {
            animationFadein = AnimationUtils.loadAnimation(getApplicationContext(),
                    R.anim.fadein1);

            adglogo1 = findViewById(R.id.adglogoLanding);
            adglogo1.setAnimation(animationFadein);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    /* Create an Intent that will start the Menu-Activity. */
                    Intent mainIntent = new Intent(getApplicationContext(), onboardingActivity.class);
                    landingActivity.this.startActivity(mainIntent);
                    landingActivity.this.finish();
                }
            }, 2900);
        }
    }


}