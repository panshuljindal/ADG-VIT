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

public class faqE extends Fragment {
    private RecyclerView recyclerView;
    private List<faqitem> list;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faq_e, container, false);

        Button back = view.findViewById(R.id.back_button_faqE);
        recyclerView = view.findViewById(R.id.recyclerView_faqE);

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
                "Q1. What do I do for the club as part of the Editorial team?",
                "-Being a part of the editorial team includes working on social media posts and marketing for upcoming events"));
        list.add(new faqitem("Q2. Do I get to be a part of any technical domain as well?",
                "-Yes, along with being a member of the Editorial Team, you also get to choose a Technical Domain offered by the club."));
        list.add(new faqitem("Q3. What skills do I require to be apply for a place in the Editorial Committee?",
                "-As long as you're able to put yourself in event managing scenarios and work with team members, you're most welcome to this committee.\n" +
                        "Any past experiences definitely help here."));
        list.add(new faqitem
                ("Q4. How will I be benefited if become part of the editorial team?",
                        "- Good writing skills are very important and will always come handy be it applying for jobs or further studies!"));
        list.add(new faqitem(
                "Q5. Can I be part of other management groups if I am in editorial?",
                "- Of course you can! As long as you can manage , you’re most welcome to be part of other teams and gain as much as you can!"));
    }
}