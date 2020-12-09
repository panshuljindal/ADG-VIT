package com.example.adgexternals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class management_quiz extends AppCompatActivity {

    TextView q1D,q2D,q3D,q4D,q5D,q6D,q7D,q8D,q9D,q10D;
    EditText q1A,q2A,q3A,q4A,q5A,q6A,q7A,q8A,q9A,q10A;
    Button submit;
    List<questionObject> questionManagement;
    List<postQuestion> questionAnswer;
    String token;
    Boolean cheat=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_quiz);
        findViewByIds();
        questionManagement = new ArrayList<>();
        questionAnswer= new ArrayList<>();
        loadData();
        setData();
        onclicklisteners();
    }
    public void onclicklisteners(){
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNetworkAvailable(v.getContext())) {
                    questionAnswer.add(new postQuestion(questionManagement.get(0).get_id(), q1A.getText().toString()));
                    questionAnswer.add(new postQuestion(questionManagement.get(1).get_id(), q2A.getText().toString()));
                    questionAnswer.add(new postQuestion(questionManagement.get(2).get_id(), q3A.getText().toString()));
                    questionAnswer.add(new postQuestion(questionManagement.get(3).get_id(), q4A.getText().toString()));
                    questionAnswer.add(new postQuestion(questionManagement.get(4).get_id(), q5A.getText().toString()));
                    questionAnswer.add(new postQuestion(questionManagement.get(5).get_id(), q6A.getText().toString()));
                    questionAnswer.add(new postQuestion(questionManagement.get(6).get_id(), q7A.getText().toString()));
                    questionAnswer.add(new postQuestion(questionManagement.get(7).get_id(), q8A.getText().toString()));
                    questionAnswer.add(new postQuestion(questionManagement.get(8).get_id(), q9A.getText().toString()));
                    questionAnswer.add(new postQuestion(questionManagement.get(9).get_id(), q10A.getText().toString()));
                    sendNetWorkRequest(questionAnswer);
                }
                else{
                    Toast.makeText(management_quiz.this, "Please connect to the internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void setData(){
        q1D.setText(questionManagement.get(0).getDescription());
        q2D.setText(questionManagement.get(1).getDescription());
        q3D.setText(questionManagement.get(2).getDescription());
        q4D.setText(questionManagement.get(3).getDescription());
        q5D.setText(questionManagement.get(4).getDescription());
        q6D.setText(questionManagement.get(5).getDescription());
        q7D.setText(questionManagement.get(6).getDescription());
        q8D.setText(questionManagement.get(7).getDescription());
        q9D.setText(questionManagement.get(8).getDescription());
        q10D.setText(questionManagement.get(9).getDescription());
    }
    public void findViewByIds(){
        submit = findViewById(R.id.submit_button_management);
        q1D = findViewById(R.id.q1D);
        q1A = findViewById(R.id.q1A);
        q2D = findViewById(R.id.q2D);
        q2A = findViewById(R.id.q2A);
        q3D = findViewById(R.id.q3D);
        q3A = findViewById(R.id.q3A);
        q4D = findViewById(R.id.q4D);
        q4A = findViewById(R.id.q4A);
        q5D = findViewById(R.id.q5D);
        q5A = findViewById(R.id.q5A);
        q6D = findViewById(R.id.q6D);
        q6A = findViewById(R.id.q6A);
        q7D = findViewById(R.id.q7D);
        q7A = findViewById(R.id.q7A);
        q8D = findViewById(R.id.q8D);
        q8A = findViewById(R.id.q8A);
        q9D = findViewById(R.id.q9D);
        q9A = findViewById(R.id.q9A);
        q10D = findViewById(R.id.q10D);
        q10A = findViewById(R.id.q10A);
    }
    public void loadData(){
        questionManagement.clear();
        Gson gson = new Gson();
        Intent intent = getIntent();
        String json = intent.getStringExtra("questionsDesign");
        Type type =new TypeToken<List<questionObjectTechnical>>() {}.getType();
        questionManagement = gson.fromJson(json,type);
        if(questionManagement==null){
            questionManagement=new ArrayList<>();
        }
        SharedPreferences preferences = getSharedPreferences("com.adgexternals.com.token",MODE_PRIVATE);
        token = preferences.getString("Token","");
    }
    public void sendNetWorkRequest(List<postQuestion> ques){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpclient = new OkHttpClient.Builder();
        httpclient.addInterceptor(logging);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://adgrecruitments.herokuapp.com/questions/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpclient.build())
                .build();
        userClient client = retrofit.create(userClient.class);
        if(isNetworkAvailable(this)){
        Call<postQuestion> call = client.postQuestionManagement(token,ques);
        call.enqueue(new Callback<postQuestion>() {
            @Override
            public void onResponse(Call<postQuestion> call, Response<postQuestion> response) {

            }

            @Override
            public void onFailure(Call<postQuestion> call, Throwable t) {

            }
        });
        }
        else {
            Toast.makeText(this, "Please connect to the internet", Toast.LENGTH_SHORT).show();
        }
    }
    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        cheat =true;
        cheating();
    }
}