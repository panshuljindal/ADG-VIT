package com.example.adgexternals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class onboardingActivity extends AppCompatActivity {

    private ViewPager vP;
    private Button nextBtnOnBoard, tabBtn1 , tabBtn2;
    private sliderAdapter mslider;
    private int p;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        vP = findViewById(R.id.onboardViewpager);
        nextBtnOnBoard = findViewById(R.id.nextBtnLanding);
        tabBtn1 = findViewById(R.id.tabBtn1);
        tabBtn2 = findViewById(R.id.tabBtn2);

        mslider = new sliderAdapter(this);
        vP.setAdapter(mslider);

        tabbtn(0);
        vP.addOnPageChangeListener(viewlisterner);

        nextBtnOnBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(p==0){
                    vP.setCurrentItem(p+1); tabbtn(p+1);
                }else if(p==1){
                    //change state variable here
                    pref = getSharedPreferences("com.adgexternals.com.count", MODE_PRIVATE);
                    editor = pref.edit();
                    editor.putBoolean("count", true).commit();
                    editor.apply();
                    startActivity(new Intent(onboardingActivity.this, MainActivity.class));
                }
            }
        });

        tabBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vP.setCurrentItem(0); tabbtn(0);
            }
        });

        tabBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vP.setCurrentItem(1);tabbtn(1);
            }
        });

    }

    private void tabbtn(int pos) {
        if(pos ==0){
            tabBtn1.setActivated(true);
            tabBtn2.setActivated(false);
            nextBtnOnBoard.setText("Next");


        }
        if(pos == 1){
            tabBtn1.setActivated(true);
            tabBtn2.setActivated(true);
            nextBtnOnBoard.setText("Start");
        }

    }

    ViewPager.OnPageChangeListener viewlisterner = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            tabbtn(position);p = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {


        }
    };
}