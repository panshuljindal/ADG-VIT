package com.example.adgexternals;

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

public class faqD extends Fragment {

    private RecyclerView recyclerView;
    private List<faqitem> list;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faq_d, container, false);

        Button back = view.findViewById(R.id.back_button_faqD);
        recyclerView = view.findViewById(R.id.recyclerView_faqD);

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
        list.add(new faqitem("Q2. Err... Do I need Apple devices such as a MacBook to be a part this chapter?  ",
                "- Its not at all necessary to have an apple device to be the part of ADG unless you need to be the part of iOS Domain, for iOS App Development Macbook is compulsory."));
        list.add(new faqitem("Q3. Hey, Do I need to be good in coding to be a part of this chapter?",
                "-If you want to be a part of the technical domains then yes you need to have a good technical knowledge. However, we have 2 more domains apart from technical which are management and design. So, if you are interested in either of those then you can definitely apply!"));
        list.add(new faqitem
                ("Q4. How many events does ADG conduct in a year? And what kinds of events are they? Or Events and app contributions by Adg",
                        "-ADG usually conducts 2 events during both the technical and the cultural fests. One of these is the flagship event the \"iOS Fusion\" where one gets to learn specifically about swift and its scope by getting some hands on practice during the workshop.\n" +
                                "Unidev..\n" +
                                "During the cultural fest the events conducted are a blend of innovation and tech. A few events hosted in the past year include a treasure hunt and a meme making contest."));
        list.add(new faqitem(
                "Q5. What are the domains in ADG, is it only iOS Dev? Or What all departments are in the club?",
                "- The following are the technical domains in ADG:\n" +
                        "     * IOS dev\n" +
                        "     * Android Dev\n" +
                        "     * Web Dev\n" +
                        "     * ML\n" +
                        "     * Design\n" +
                        "     * Competitive Coding (compulsory)"));
        list.add(new faqitem("Q6. What does the chapter offer in terms of learning?",
                "-The chapter offers a lot of growth in the learning sphere so one can explore different domains as mentioned above and choose the ones they think are the best for them and build projects on them!"));
        list.add(new faqitem("Q7. Is it okay if I'm not from any computer science related branches?",
                "-Yes, it's completely okay! All you need is enthusiasm to learn and you're good to go!"));
        list.add(new faqitem("Q8. Cool! I think I am ready to join ADG. But where do I apply for recruitment?",
                "The recruitment preliminary round will be a Quiz, conducted on the ADG-VIT website (adgvit.com), qualified candidates will be contacted by ADG for further round(s) based on their Quiz score."));

    }
}