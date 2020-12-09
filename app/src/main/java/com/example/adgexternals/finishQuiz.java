package com.example.adgexternals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class finishQuiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_quiz);

        Intent intent = getIntent();
        String type=intent.getStringExtra("type");
        TextView text = findViewById(R.id.resultText1);
        text.setText("Thank you for the " + type + " Quiz");
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        startActivity(new Intent(finishQuiz.this,MainActivity.class));
    }
}