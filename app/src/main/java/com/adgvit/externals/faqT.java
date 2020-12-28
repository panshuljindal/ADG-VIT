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

public class faqT extends Fragment {
    private RecyclerView recyclerView;
    private List<faqitem> list;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faq_t, container, false);

        Button back = view.findViewById(R.id.back_button_faqT);
        recyclerView = view.findViewById(R.id.recyclerView_faqT);

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
                "Q.1 Do I need to have pre requisite knowledge about the domains to be a part of them?",
                "- Just at the starting stage? No worries, we will guide you from scratch be it any domain.Having the will to explore and learn is all you need!"));
        list.add(new faqitem("Q.2 Do I need a Mac to be a part of the iOS domain?",
                "- Yes,to have a smooth experience using Xcode , a Mac is required."));
        list.add(new faqitem("Q.3. Without much coding experience can I be a part of any technical domain?",
                "- You need to have some basic knowledge about problem-solving and at least be acquainted with one programming language to make learning easier for you!"));
        list.add(new faqitem("Q.4  What shall the learning process be like if I get into a technical domain?",
                "- At ADG you will be guided at every step, be it a beginner or a pro. You will learn through working on amazing projects, which you can include in your resume and we shall ensure you become the best version of yourself!"));
        list.add(new faqitem("Q.5 Can I choose what domain I want to be a part of or it will be decided based on the quiz marks?",
                "- Its upto you what domain you want to choose. We have domains for every possible interest!"));
    }
}