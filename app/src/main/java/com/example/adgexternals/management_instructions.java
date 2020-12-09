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

public class management_instructions extends Fragment {
    View view;
    String token;
    SharedPreferences pref;
    Button back,start;
    List<questionObject> questionManagement;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_management_instructions, container, false);

        back = view.findViewById(R.id.back_button_dm);
        start = view.findViewById(R.id.startButton_dm);
        questionManagement = new ArrayList<>();

        pref = view.getContext().getSharedPreferences("com.adgexternals.com.token",Context.MODE_PRIVATE);
        token = pref.getString("Token","");

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
            }
        });
        return view;
    }
    public void sendNetworkRequest() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://adgrecruitments.herokuapp.com/questions/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        userClient client = retrofit.create(userClient.class);
        if (isNetworkAvailable(getContext())) {
            Call<List<questionObject>> call = client.getQuestionManagement(token);
            call.enqueue(new Callback<List<questionObject>>() {
                @Override
                public void onResponse(Call<List<questionObject>> call, Response<List<questionObject>> response) {
                    if (response.code() == 200) {
                        try {
                            questionManagement.clear();
                            questionManagement = response.body();
                        } catch (Exception e) {

                        }
                        saveData();
                    }
                    else {

                    }
                }

                @Override
                public void onFailure(Call<List<questionObject>> call, Throwable t) {

                }
            });
        }
        else {
            Toast.makeText(getContext(), "Please connect to the internet", Toast.LENGTH_SHORT).show();
        }
    }
    public void saveData(){
        Gson gson = new Gson();
        String json = gson.toJson(questionManagement);
        Log.i("Json",json);
        Intent intent = new Intent(getActivity(),management_quiz.class);
        intent.putExtra("questionsManagement",json);
        Log.i("json",json);
        startActivity(intent);
    }
    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}