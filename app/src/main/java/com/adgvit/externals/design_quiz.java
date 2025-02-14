package com.adgvit.externals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class design_quiz extends AppCompatActivity {
    private List<questionObjectTechnical> questionsDesign;
    private SharedPreferences pref1,pref;
    private SharedPreferences.Editor editor;
    private ConstraintLayout cl1,cl2,cl3,cl4;
    private int qno=0;
    private int quiztime=600000;
    private int maxques;
    private TextView option1,option2,option3,option4,question,quesText,time;
    private String option="null";
    private Button next,skip;
    private String token;
    private boolean cheat=false;
    private CountDownTimer countDownTimer;
    private List<postQuestion> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_quiz);

        findViewByIds();
        questionsDesign = new ArrayList<>();
        loadData();
        maxques=questionsDesign.size();
        Log.i("maxQues",String.valueOf(maxques));
        questionList = new ArrayList<>();
        pref1 = getSharedPreferences("com.adgexternals.com.token", Context.MODE_PRIVATE);
        token = pref1.getString("Token","");

        pref = getSharedPreferences("com.adgexternals.com.userdata",Context.MODE_PRIVATE);
        editor=pref.edit();
        setCountDownTimer();
        setOptions();
        onclicklisteners();
    }
    void setCountDownTimer(){
        countDownTimer=new CountDownTimer(quiztime,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateTextView(millisUntilFinished/1000);
            }
            @Override
            public void onFinish() {
                for(int i=qno;i<maxques;i++){
                    questionList.add(new postQuestion(questionsDesign.get(qno).get_id(), "skip"));
                }
                sendNetworkRequest(questionList);
            }
        }.start();
    }
    void updateTextView(long secondsLeft){
        int min = (int) (secondsLeft/60);
        int seconds = (int) (secondsLeft-(min*60));
        String secondString;
        secondString = Integer.toString(seconds);
        if(seconds<=9){
            secondString="0"+secondString;
        }
        if(min>=1) {
            time.setText(Integer.toString(min) + ":" + secondString +"s");
        }
        else{
            time.setText(secondString + "s");
        }
    }
    void findViewByIds(){
        cl1 = findViewById(R.id.cl1D);
        cl2 = findViewById(R.id.cl2D);
        cl3 = findViewById(R.id.cl3D);
        cl4 = findViewById(R.id.cl4D);
        question = findViewById(R.id.designQuestion);
        quesText = findViewById(R.id.textViewDesignQuestionNumber);
        option1 = findViewById(R.id.option1TextD);
        option2 = findViewById(R.id.option2TextD);
        option3 = findViewById(R.id.option3TextD);
        option4 = findViewById(R.id.option4TextD);
        next = findViewById(R.id.buttonDesignQuizNext);
        skip = findViewById(R.id.buttonDesignQuizSkip);
        time = findViewById(R.id.timetextView);
    }
    void setOptions(){
        if(qno<maxques) {
            reset();
            question.setText(questionsDesign.get(qno).getQuestionDescription());
            option1.setText(questionsDesign.get(qno).getOptions().getA());
            option2.setText(questionsDesign.get(qno).getOptions().getB());
            option3.setText(questionsDesign.get(qno).getOptions().getC());
            option4.setText(questionsDesign.get(qno).getOptions().getD());
            quesText.setText(String.valueOf(qno + 1) + "/" +String.valueOf(maxques));
        }
        else if(qno==maxques){
            sendNetworkRequest(questionList);
            next.setEnabled(false);
            skip.setEnabled(false);
        }
    }
    void loadData(){
        Intent intent = getIntent();
        Gson gson = new Gson();
        String json = intent.getStringExtra("questionsDesign");
        Type type =new TypeToken<List<questionObjectTechnical>>() {}.getType();
        questionsDesign = gson.fromJson(json,type);
        if(questionsDesign==null){
            questionsDesign=new ArrayList<>();
            loadData();
        }
    }
    void reset(){
        cl1.setBackground(ContextCompat.getDrawable(this,R.drawable.quizcardback));
        cl2.setBackground(ContextCompat.getDrawable(this,R.drawable.quizcardback));
        cl3.setBackground(ContextCompat.getDrawable(this,R.drawable.quizcardback));
        cl4.setBackground(ContextCompat.getDrawable(this,R.drawable.quizcardback));
    }
    void onclicklisteners(){
        cl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option ="a";
                cl1.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.quizselectedback));
                cl2.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.quizcardback));
                cl3.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.quizcardback));
                cl4.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.quizcardback));
            }
        });
        cl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option ="b";
                cl2.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.quizselectedback));
                cl1.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.quizcardback));
                cl3.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.quizcardback));
                cl4.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.quizcardback));
            }
        });
        cl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option="c";
                cl3.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.quizselectedback));
                cl2.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.quizcardback));
                cl1.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.quizcardback));
                cl4.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.quizcardback));
            }
        });
        cl4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option="d";
                cl4.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.quizselectedback));
                cl2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.quizcardback));
                cl3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.quizcardback));
                cl1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.quizcardback));
            }
        });
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionList.add(new postQuestion(questionsDesign.get(qno).get_id(),"e"));
                qno++;
                option="null";
                reset();
                setOptions();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qno < maxques) {
                    if (option == "null") {
                        Toast.makeText(design_quiz.this, "Please select a option", Toast.LENGTH_SHORT).show();
                    } else if (option == "a") {
                        questionList.add(new postQuestion(questionsDesign.get(qno).get_id(), "a"));
                        qno++;
                        option = "null";
                        setOptions();
                    } else if (option == "b") {
                        questionList.add(new postQuestion(questionsDesign.get(qno).get_id(), "b"));
                        qno++;
                        option = "null";
                        setOptions();
                    } else if (option == "c") {
                        questionList.add(new postQuestion(questionsDesign.get(qno).get_id(), "c"));
                        qno++;
                        option = "null";
                        setOptions();
                    } else if (option == "d") {
                        questionList.add(new postQuestion(questionsDesign.get(qno).get_id(), "d"));
                        qno++;
                        option = "null";
                        setOptions();
                    }
                } else{
                    sendNetworkRequest(questionList);
                    next.setEnabled(false);
                    skip.setEnabled(false);
                }
            }
        });
    }
    void cheating(){
        next.setEnabled(false);
        skip.setEnabled(false);
        for(int i=qno;i<maxques;i++){
            questionList.add(new postQuestion(questionsDesign.get(qno).get_id(), "cheat"));
        }
        sendNetworkRequest(questionList);
    }
    void sendNetworkRequest(List<postQuestion> ques){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://adgrecruitments.herokuapp.com/user/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userClient client = retrofit.create(userClient.class);
        Call<postQuestion> call = client.postQuestionDesign(token,ques);
        call.enqueue(new Callback<postQuestion>() {
            @Override
            public void onResponse(Call<postQuestion> call, Response<postQuestion> response) {
                countDownTimer.cancel();
                if(response.code()==200){
                    if(cheat==true){
                        editor.putBoolean("attemptedDesign", true).commit();
                        editor.apply();
                        Toast.makeText(design_quiz.this, "Cheating", Toast.LENGTH_SHORT).show();
                        Intent intent =new Intent(design_quiz.this,finishQuiz.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        String type="Design (Cheated)";
                        intent.putExtra("type",type);
                        startActivity(intent);
                    }
                    else{
                        editor.putBoolean("attemptedDesign", true).commit();
                        editor.apply();
                        Toast.makeText(design_quiz.this, "Thank you for the quiz", Toast.LENGTH_SHORT).show();
                        Intent intent =new Intent(design_quiz.this,finishQuiz.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        String type="Design";
                        intent.putExtra("type",type);
                        startActivity(intent);
                    }
                }
                else if(response.code()==403){
                    if(cheat==true){
                        editor.putBoolean("attemptedDesign", true).commit();
                        editor.apply();
                        Toast.makeText(design_quiz.this, "Cheating", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(design_quiz.this,MainActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }
                    else{
                        editor.putBoolean("attemptedDesign", true).commit();
                        editor.apply();
                        Toast.makeText(design_quiz.this, "You cannot submit quiz more than once", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(design_quiz.this,MainActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }
                }
            }

            @Override
            public void onFailure(Call<postQuestion> call, Throwable t) {
                next.setEnabled(true);
                skip.setEnabled(true);
                Toast.makeText(design_quiz.this, "Network error. Please try again", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private boolean doubleback=false;
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if(doubleback){
            cheat=true;
            cheating();
        }
        doubleback=true;
        Toast.makeText(this, "Test will be submitted if you press again", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleback=false;
            }
        },3000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        cheat=true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(cheat){
            cheating();
        }
    }
}