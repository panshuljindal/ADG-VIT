package com.adgvit.externals;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class homefragment extends Fragment {

    private View view;
    private Button adglogoBtn,faq;
    private List<recyler2item> list1;
    private CardView domain,events,projects,teams;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_homefragment, container, false);
        adglogoBtn = view.findViewById(R.id.adglogoBtn);
        faq=view.findViewById(R.id.doubtCardbtn);
        RecyclerView recyclerView1 = view.findViewById(R.id.homeEventsRecycler);
        domain = view.findViewById(R.id.domainsCardview);
        events = view.findViewById(R.id.eventsCardview);
        projects = view.findViewById(R.id.projectsCardview);
        teams = view.findViewById(R.id.teamsCardview);
        sendNetworkTrue();

        list1 = new ArrayList<>();
        addData();
        recyler2adapter adapter = new recyler2adapter(list1,getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView1.setLayoutManager(manager);
        recyclerView1.setAdapter(adapter);
        onclickListeners();
        return view;
    }
    void sendNetworkTrue(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://adgrecruitments.herokuapp.com/user/").addConverterFactory(GsonConverterFactory.create())
                .build();
        userClient client = retrofit.create(userClient.class);
        Call<status> call = client.getRecruitments();
        call.enqueue(new Callback<status>() {
            @Override
            public void onResponse(Call<status> call, Response<status> response) {
                if(response.code()==200){
                    status item = response.body();
                    SharedPreferences preferences = view.getContext().getSharedPreferences("com.adgexternals.com.status",MODE_PRIVATE);
                    SharedPreferences.Editor editor2 = preferences.edit();
                    editor2.putBoolean("status",item.getStatus()).commit();
                    editor2.apply();
                }
            }

            @Override
            public void onFailure(Call<status> call, Throwable t) {
            }
        });
    }
    void addData(){
        list1.add(new recyler2item("Recruitments","Online","9:40","14","JAN"));
        list1.add(new recyler2item("HackGrid","Anna Audi","9:00","23","MAR"));
    }
    void onclickListeners(){
        adglogoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),adg_info.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "This feature will be available soon!", Toast.LENGTH_SHORT).show();
            }
        });
        teams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamFragment fragment = new teamFragment();
                FragmentManager manager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frameLayout, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        domain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                domainsFragment fragment = new domainsFragment();
                FragmentManager manager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frameLayout, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventsFragment fragment = new eventsFragment();
                FragmentManager manager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frameLayout, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        projects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                projectsFragment fragment = new projectsFragment();
                FragmentManager manager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frameLayout, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }
}