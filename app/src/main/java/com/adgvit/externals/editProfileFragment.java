package com.adgvit.externals;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class editProfileFragment extends Fragment {

    private Button backBtn;
    private TextView name,regNo,email,phone,github;
    private ImageView tech,manage,design;
    private SharedPreferences pref;
    private String name1,regNo1,email1,phone1,github1;
    private boolean tech1,manage1,design1;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        findViewByids();
        pref = view.getContext().getSharedPreferences("com.adgexternals.com.userdata", Context.MODE_PRIVATE);
        name1= pref.getString("name","");
        regNo1 = pref.getString("regno","");
        email1 = pref.getString("email","");
        phone1 = pref.getString("phone","");
        github1 = pref.getString("github","");
        tech1 = pref.getBoolean("attemptedTechnical",false);
        manage1=pref.getBoolean("attemptedManagement",false);
        design1 = pref.getBoolean("attemptedDesign",false);
        name.setText(name1);
        regNo.setText(regNo1);
        email.setText(email1);
        phone.setText(phone1);
        github.setText(github1);

        name.setText(name1);
        regNo.setText(regNo1);
        email.setText(email1);
        phone.setText(phone1);
        github.setText(github1);

        if(name.getText().toString().isEmpty()){
            name.setText("Name");
        }
        if(regNo.getText().toString().isEmpty()){
            regNo.setText("Registration Number");
        }
        if(email.getText().toString().isEmpty()){
            email.setText("Email Id");
        }
        if(phone.getText().toString().isEmpty()){
            phone.setText("Phone Number");
        }
        if(github.getText().toString().equals("https://github.com/adgvit") | github.getText().toString().isEmpty()){
            github.setText("Github Link");
        }

        if(tech1==true){
            tech.setImageResource(R.drawable.ic_tick);
        }
        else if(tech1==false){
            tech.setImageResource(R.drawable.ic_no_stopping);
        }
        if(manage1==true){
            manage.setImageResource(R.drawable.ic_tick);
        }
        else if(manage1==false){
            manage.setImageResource(R.drawable.ic_no_stopping);
        }
        if(design1==true){
            design.setImageResource(R.drawable.ic_tick);
        }
        else if(design1==false){
            design.setImageResource(R.drawable.ic_no_stopping);
        }

        onclick();


        return view;
    }
    void findViewByids(){
        name = view.findViewById(R.id.editProfileName);
        regNo = view.findViewById(R.id.editProfileRegNo);
        email = view.findViewById(R.id.editProfileEmailid);
        phone = view.findViewById(R.id.editProfilePhoneNumber);
        github = view.findViewById(R.id.editProfileGithub);

        tech = view.findViewById(R.id.statusTechSum);
        manage = view.findViewById(R.id.statusMangSum);
        design = view.findViewById(R.id.statusDesSum);

        backBtn = view.findViewById(R.id.editProfilebackBtn);
    }
    void onclick(){
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate();
            }
        });
    }
}