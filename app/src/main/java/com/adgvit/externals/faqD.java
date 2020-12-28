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
                "Q.1PhotoShop or Illustrator?",
                "- Whichever tool you're comfortable with as long as you have a basic idea for working with them."));
        list.add(new faqitem("Q.2 Does the design team only make posters or is there more to it? ",
                "- The design team is your one stop for learning and practicing everything Design. UI/UX, Graphic design, Product Design you name it. "));
        list.add(new faqitem("Q.3 I am new to Design. Am I eligible?",
                "- As long as you have the passion to work and determination to learn, you're welcome here."));
        list.add(new faqitem
                ("Q.4 Will this help me in the future?",
                        "- The design team plays a crucial role in every industry and field. With a chance to learn from some of the best designers with practical experience, design as a career is more than just a viable option."));
        list.add(new faqitem(
                "Q.5 I don't have a portfolio but I know how to design. Can I still apply?",
                "- As long as you can prove that you have what it takes. You can be a part of the design team."));
    }
}