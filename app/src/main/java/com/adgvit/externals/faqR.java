package com.adgvit.externals;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class faqR extends Fragment {
    private RecyclerView recyclerView;
    private List<faqitem> list;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faq_r, container, false);

        Button back = view.findViewById(R.id.back_button_faqR);
        recyclerView = view.findViewById(R.id.recyclerView_faqR);

        list=new ArrayList<>();
        addData();

        faqadapter adapter = new faqadapter(list,getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate();
            }
        });
        return view;
    }
    void addData(){
        list.add(new faqitem(
                "Q1. I want to join ADG. So…when do you recruit?",
                "- The tentative recruitment drive in at the start of every winter semester. Anything otherwise, shall be communicated through our social media platforms. Hence, keep checking them."));
        list.add(new faqitem("Q2. Do I need Apple devices such as a MacBook to be a part this chapter?",
                "- It's not at all necessary to have an apple device to be a part of ADG unless you need to be a part of iOS Domain, for iOS App Development MacBook is compulsory."));
        list.add(new faqitem("Q3. Hey, Do I need to be good in coding to be a part of this chapter?",
                "-If you want to be a part of the technical domains then yes, you need to have a decent technical knowledge when it comes to DSA (Don't get intimidated, practice what you know and you are good to go).\n" +
                        "Moreover, we have 3 more domains apart from technical which are Management, Design and Video Editing. So, if you are interested in any of those then you can definitely apply!"));
        list.add(new faqitem
                ("Q4. Is it okay if I'm not from any computer science related branches?",
                        "-Yes, it's completely okay! All you need is enthusiasm to learn and you're good to go!"));
        list.add(new faqitem(
                "Q5. Cool! I think I am ready to join ADG. But where do I apply for recruitment?",
                "-The recruitment preliminary round will be a Quiz, conducted on the ADG-VIT website (adgvit.com), qualified candidates will be contacted by ADG for further round(s) based on their Quiz score."));

    }
}