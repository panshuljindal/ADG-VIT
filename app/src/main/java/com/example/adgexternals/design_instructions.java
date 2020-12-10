package com.example.adgexternals;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class design_instructions extends Fragment {
    View view;
    Button back,start;
    SharedPreferences pref;
    String token;
    List<questionObjectTechnical> questionsTechnical;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_design_instructions, container, false);

        back = view.findViewById(R.id.back_button_di);
        start = view.findViewById(R.id.startButton_di);

        pref = view.getContext().getSharedPreferences("com.adgexternals.com.token", Context.MODE_PRIVATE);
        token = pref.getString("Token","");

        questionsTechnical = new ArrayList<>();
        onclicklisteners();

        return  view;
    }
    public void onclicklisteners(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate();
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNetworkAvailable(v.getContext())){
                    sendNetworkRequest();
                }
                else{
                    Toast.makeText(v.getContext(), "Please connect to the internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void sendNetworkRequest() {
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
        if (isNetworkAvailable(view.getContext())) {
            Call<List<questionObjectTechnical>> call = client.getQuestionDesign(token);
            call.enqueue(new Callback<List<questionObjectTechnical>>() {
                @Override
                public void onResponse(Call<List<questionObjectTechnical>> call, Response<List<questionObjectTechnical>> response) {
                    if (response.code() == 200) {
                        try {
                            questionsTechnical.clear();
                            questionsTechnical = response.body();
                        } catch (Exception e) {
                            Toast.makeText(view.getContext(), "Try again", Toast.LENGTH_SHORT).show();
                        }
                        saveData();
                    } else if (response.code() == 400) {
                        Toast.makeText(view.getContext(), "You have attempted the quiz before", Toast.LENGTH_SHORT).show();
                        getFragmentManager().popBackStackImmediate();
                    } else {
                        Toast.makeText(view.getContext(), "Error occurred. Please try again", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(view.getContext(), MainActivity.class));
                    }
                }

                @Override
                public void onFailure(Call<List<questionObjectTechnical>> call, Throwable t) {
                    Toast.makeText(view.getContext(), "Network error. Please try again", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(view.getContext(), MainActivity.class));
                }
            });
        }
        else{
            Toast.makeText(view.getContext(), "Please connect to the internet", Toast.LENGTH_SHORT).show();
        }
    }
    public void saveData(){
        Gson gson = new Gson();
        String json = gson.toJson(questionsTechnical);
        Intent intent = new Intent(getActivity(),design_quiz.class);
        intent.putExtra("questionsDesign",json);
        startActivity(intent);
    }
    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}