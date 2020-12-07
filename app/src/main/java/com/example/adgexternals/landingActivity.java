package com.example.adgexternals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class landingActivity extends AppCompatActivity {
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        Animation animationFadein = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fadein1);
        Animation animationFadein2 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fadein);
        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fadein2);
        Animation animation3 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.anim2);
        Animation animation4 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.anim3);


        LinearLayout tab1 = findViewById(R.id.landingTab1);
        LinearLayout tab2 = findViewById(R.id.landingTab2);
        LinearLayout btnTab = findViewById(R.id.btntab);
        Button tabBtn1 = findViewById(R.id.tabBtn1);
        Button tabBtn2 = findViewById(R.id.tabBtn2);

        ImageView adglogo1 = findViewById(R.id.adglogoLanding);
        adglogo1.setAnimation(animationFadein);
        Button nextBtn = findViewById(R.id.nextBtnLanding);
        nextBtn.setAnimation(animationFadein2);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                count++;
                adglogo1.setVisibility(View.INVISIBLE);

                if(count==1){

                    page1enable();

                }else{
                    if (count==2){

                        page2enable();

                    }else{
                        if(count==3){

                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }
                    }
                }



            }

            private void page2enable() {
                tabBtn2.setActivated(!tab1.isActivated());
                tab2.setAnimation(animation3);
                tab1.setVisibility(View.INVISIBLE);
                nextBtn.setText("Start");
                tab2.setVisibility(View.VISIBLE);



            }

            private void page1enable() {
                tabBtn1.setActivated(!tab1.isActivated());
                tab1.setAnimation(animation2);
                tab1.setVisibility(View.VISIBLE);
                btnTab.setAnimation(animationFadein);
                btnTab.setVisibility(View.VISIBLE);
                tabBtn1.setSelected(true);


            }

        });

        tabBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tabBtn1.setActivated(!tab1.isActivated()); tabBtn2.setActivated(false);
                tab1.setAnimation(animation4);
                tab1.setVisibility(View.VISIBLE);
                tab2.setAnimation(animation3);
                tab2.setVisibility(View.INVISIBLE);
                btnTab.setAnimation(animationFadein);
                btnTab.setVisibility(View.VISIBLE);
                tabBtn1.setSelected(true);

            }
        });

        tabBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tabBtn2.setActivated(!tab1.isActivated());
                tab2.setAnimation(animation3);
                tab1.setAnimation(animation4);
                tab1.setVisibility(View.INVISIBLE);
                nextBtn.setText("Start");
                tab2.setVisibility(View.VISIBLE);

            }
        });

    }
}