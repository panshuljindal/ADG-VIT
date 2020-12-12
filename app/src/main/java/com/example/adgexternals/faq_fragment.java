package com.example.adgexternals;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class faq_fragment extends Fragment {

    CardView faqR1,faqD1,faqE1,faqGT1,faqT1;
    Button card1,card2,card3,card4,card5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_faq_fragment, container, false);

        faqR1 = view.findViewById(R.id.faqR);
        faqGT1 = view.findViewById(R.id.faqGT);
        faqT1 = view.findViewById(R.id.faqT);
        faqD1 = view.findViewById(R.id.faqD);
        faqE1 = view.findViewById(R.id.faqE);

        card1 = view.findViewById(R.id.cardbtn1);
        card2 = view.findViewById(R.id.cardbtn2);
        card3 = view.findViewById(R.id.cardbtn3);
        card4 = view.findViewById(R.id.cardbtn4);
        card5 = view.findViewById(R.id.cardbtn5);
        onclickListeners();
        return view;
    }
    void onclickListeners(){

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                faqR fragment = new faqR();
                FragmentManager manager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frameLayout, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                faqGT fragment = new faqGT();
                FragmentManager manager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frameLayout, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                faqT fragment = new faqT();
                FragmentManager manager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frameLayout, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                faqD fragment = new faqD();
                FragmentManager manager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frameLayout, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                faqE fragment = new faqE();
                FragmentManager manager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frameLayout, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        faqR1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                faqR fragment = new faqR();
                FragmentManager manager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frameLayout, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        faqGT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                faqGT fragment = new faqGT();
                FragmentManager manager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frameLayout, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        faqT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                faqT fragment = new faqT();
                FragmentManager manager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frameLayout, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        faqD1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                faqD fragment = new faqD();
                FragmentManager manager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frameLayout, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        faqE1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                faqE fragment = new faqE();
                FragmentManager manager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frameLayout, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }
}