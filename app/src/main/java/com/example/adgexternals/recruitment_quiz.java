package com.example.adgexternals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class recruitment_quiz extends AppCompatActivity {
    List<questionObjectTechnical> questionsTechnical;
    SharedPreferences pref,pref1;
    ConstraintLayout cl1,cl2,cl3,cl4;
    int qno=0,maxques=6;
    TextView option1,option2,option3,option4,question,quesText;
    String option="null";
    Button next,skip;
    String token;
    boolean cheat=false;
    List<postQuestion> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruitment_quiz);
        findViewByIds();
        loadData();
        questionList = new ArrayList<>();
        pref1 = getSharedPreferences("com.adgexternals.com.token", Context.MODE_PRIVATE);
        token = pref1.getString("Token","");
        Log.i("list", String.valueOf(questionsTechnical.size()));
        setOptions();
        onclicklisteners();
    }
    public void findViewByIds(){
        cl1 = findViewById(R.id.cl1);
        cl2 = findViewById(R.id.cl2);
        cl3 = findViewById(R.id.cl3);
        cl4 = findViewById(R.id.cl4);
        question = findViewById(R.id.technicalQuestion);
        quesText = findViewById(R.id.textViewTechnicalQuestionNumber);
        option1 = findViewById(R.id.option1Text);
        option2 = findViewById(R.id.option2Text);
        option3 = findViewById(R.id.option3Text);
        option4 = findViewById(R.id.option4Text);
        next = findViewById(R.id.buttonTechnicalQuizNext);
        skip = findViewById(R.id.buttonTechnicalQuizSkip);
    }
    public void setOptions(){
        if(qno<maxques) {
            reset();
            question.setText(questionsTechnical.get(qno).getQuestionDescription());
            option1.setText(questionsTechnical.get(qno).getOptions().getA());
            option2.setText(questionsTechnical.get(qno).getOptions().getB());
            option3.setText(questionsTechnical.get(qno).getOptions().getC());
            option4.setText(questionsTechnical.get(qno).getOptions().getD());
            quesText.setText(String.valueOf(qno + 1) + "/6");
        }
        else if(qno==maxques){
            sendNetworkRequest(questionList);
            next.setEnabled(false);
            skip.setEnabled(false);
        }

    }
    public void loadData(){
        pref= getSharedPreferences("com.adgexternals.com.questions",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pref.getString("questionsTechnical","");
        Type type =new TypeToken<List<questionObjectTechnical>>() {}.getType();
        questionsTechnical = gson.fromJson(json,type);
        if(questionsTechnical==null){
            questionsTechnical=new ArrayList<>();
        }
    }
    public void reset(){
        cl1.setBackground(ContextCompat.getDrawable(this,R.drawable.quizcardback));
        cl2.setBackground(ContextCompat.getDrawable(this,R.drawable.quizcardback));
        cl3.setBackground(ContextCompat.getDrawable(this,R.drawable.quizcardback));
        cl4.setBackground(ContextCompat.getDrawable(this,R.drawable.quizcardback));
    }
    public void onclicklisteners(){
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
                questionList.add(new postQuestion(questionsTechnical.get(qno).get_id(),"skip"));
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
                        Toast.makeText(recruitment_quiz.this, "Please select a option", Toast.LENGTH_SHORT).show();
                    } else if (option == "a") {
                        questionList.add(new postQuestion(questionsTechnical.get(qno).get_id(), "a"));
                        qno++;
                        option = "null";
                        setOptions();
                    } else if (option == "b") {
                        questionList.add(new postQuestion(questionsTechnical.get(qno).get_id(), "b"));
                        qno++;
                        option = "null";
                        setOptions();
                    } else if (option == "c") {
                        questionList.add(new postQuestion(questionsTechnical.get(qno).get_id(), "c"));
                        qno++;
                        option = "null";
                        setOptions();
                    } else if (option == "d") {
                        questionList.add(new postQuestion(questionsTechnical.get(qno).get_id(), "d"));
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
    public void cheating(){
        for(int i=qno;i<maxques;i++){
            questionList.add(new postQuestion(questionsTechnical.get(qno).get_id(), "cheat"));
        }
        sendNetworkRequest(questionList);
    }
    public void sendNetworkRequest(List<postQuestion> ques){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpclient = new OkHttpClient.Builder();
        httpclient.addInterceptor(logging);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://adgrecruitments.herokuapp.com/user/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpclient.build()).build();
        userClient client = retrofit.create(userClient.class);
        Call<postQuestion> call = client.postQuestionTechnical(token,ques);
        call.enqueue(new Callback<postQuestion>() {
            @Override
            public void onResponse(Call<postQuestion> call, Response<postQuestion> response) {
                if(response.isSuccessful() && response.code()==200){
                    Toast.makeText(recruitment_quiz.this, "Thank you for the quiz", Toast.LENGTH_SHORT).show();

                }
                else if(response.code()==400){
                    if(cheat==true){
                        Toast.makeText(recruitment_quiz.this, "Cheating", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(recruitment_quiz.this,MainActivity.class));
                    }
                    else{
                        Toast.makeText(recruitment_quiz.this, "You cannot submit quiz more than once", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<postQuestion> call, Throwable t) {

            }
        });
    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        cheat=true;
        cheating();
    }
}