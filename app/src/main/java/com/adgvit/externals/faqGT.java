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

public class faqGT extends Fragment {

    private RecyclerView recyclerView;
    private List<faqitem> list;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faq_g_t, container, false);

        Button back = view.findViewById(R.id.back_button_faqGT);
        recyclerView = view.findViewById(R.id.recyclerView_faqGT);

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
                "- The tentative recruitment drive starts every winter semester. Anything otherwise, shall be communicated through our social media platforms. Hence, keep checking them."));
        list.add(new faqitem("Q2. Do I need Apple devices such as a MacBook to be a part of this chapter?",
                "- It's not at all necessary to have an apple device to be a part of ADG unless you need to be a part of the iOS Domain, for iOS App Development MacBook is compulsory."));
        list.add(new faqitem("Q3. Hey, Do I need to be good at coding to be a part of this chapter?",
                "-If you want to be a part of the technical domains then yes, you need to have a decent technical knowledge when it comes to DSA (Don't get intimidated, practice what you know and you are good to go).\n" +
                        "Moreover, we have 3 more domains apart from technical which are Management, Design and Video Editing. So, if you are interested in any of those then you can definitely apply!"));
        list.add(new faqitem
                ("Q4. Is it okay if I'm not from any computer science related branches?",
                        "-Yes, it's completely okay! All you need is enthusiasm to learn and you're good to go!"));
        list.add(new faqitem(
                "Q5. Cool! I think I am ready to join ADG. But where do I apply for recruitment?",
                "-The recruitment preliminary round will be a Quiz, conducted on the ADG-VIT website (adgvit.com), qualified candidates will be contacted by ADG for further round(s) based on their Quiz score."));
        list.add(new faqitem(
                "Q6. Do I need to have pre requisite knowledge about the domains to be a part of them?",
                "- Just at the starting stage? No worries, we will guide you from scratch be it any domain.Having the will to explore and learn is all you need!"));
        list.add(new faqitem("Q7. Do I need a Mac to be a part of the iOS domain?",
                "- Yes,to have a smooth experience using Xcode , a Mac is required."));
        list.add(new faqitem("Q8. Without much coding experience can I be a part of any technical domain?",
                "- You need to have some basic knowledge about problem-solving and at least be acquainted with one programming language to make learning easier for you!"));
        list.add(new faqitem("Q9.  What shall the learning process be like if I get into a technical domain?",
                "- At ADG you will be guided at every step, be it a beginner or a pro. You will learn through working on amazing projects, which you can include in your resume and we shall ensure you become the best version of yourself!"));
        list.add(new faqitem("Q10. Can I choose what domain I want to be a part of or it will be decided based on the quiz marks?",
                "- Its upto you what domain you want to choose. We have domains for every possible interest!"));
        list.add(new faqitem(
                "Q11. What do I do for the club as part of the Editorial team?",
                "-Being a part of the editorial team includes working on social media posts and marketing for upcoming events"));
        list.add(new faqitem("Q12. Do I get to be a part of any technical domain as well?",
                "-Yes, along with being a member of the Editorial Team, you also get to choose a Technical Domain offered by the club."));
        list.add(new faqitem("Q13. What skills do I require to apply for a place on the Editorial Committee?",
                "-As long as you're able to put yourself in event managing scenarios and work with team members, you're most welcome to this committee.\n" +
                        "Any past experiences definitely help here."));
        list.add(new faqitem
                ("Q14. How will I be benefited if become part of the editorial team?",
                        "- Good writing skills are very important and will always come in handy be it applying for jobs or further studies!"));
        list.add(new faqitem(
                "Q15. Can I be part of other management groups if I am on the editorial?",
                "- Of course you can! As long as you can manage , you’re most welcome to be part of other teams and gain as much as you can!"));
        list.add(new faqitem(
                "Q16. PhotoShop or Illustrator?",
                "- Whichever tool you're comfortable with as long as you have a basic idea for working with them."));
        list.add(new faqitem("Q17. Does the design team only make posters or is there more to it? ",
                "- The design team is your one stop for learning and practicing everything Design. UI/UX, Graphic design, Product Design you name it. "));
        list.add(new faqitem("Q18. I am new to Design. Am I eligible?",
                "- As long as you have the passion to work and determination to learn, you're welcome here."));
        list.add(new faqitem
                ("Q19. Will this help me in the future?",
                        "- The design team plays a crucial role in every industry and field. With a chance to learn from some of the best designers with practical experience, design as a career is more than just a viable option."));
        list.add(new faqitem(
                "Q20. I don't have a portfolio but I know how to design. Can I still apply?",
                "- As long as you can prove that you have what it takes. You can be a part of the design team."));

    }
}