package com.adgvit.externals;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class settings_fragment extends Fragment {
    private Button editProfile,logout,aboutus,feedback,bugreport,refer;
    private ImageView profilepic1;
    private TextView name1,email1;
    private String name,email;
    private SharedPreferences pref,pref1,pref2;
    private boolean status;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings_fragment, container, false);

        pref1 = view.getContext().getSharedPreferences("com.adgexternals.com.token",Context.MODE_PRIVATE);
        pref = view.getContext().getSharedPreferences("com.adgexternals.com.userdata", Context.MODE_PRIVATE);
        pref2 = view.getContext().getSharedPreferences("com.adgexternals.com.status",Context.MODE_PRIVATE);

        status = pref2.getBoolean("status",false);
        name = pref.getString("name","");
        email = pref.getString("email","");

        editProfile = view.findViewById(R.id.editProfileBtn);
        profilepic1 = view.findViewById(R.id.profile_image1);
        logout = view.findViewById(R.id.buttonLogout);
        name1 = view.findViewById(R.id.profileName);
        email1 = view.findViewById(R.id.profileEmail);
        aboutus = view.findViewById(R.id.aboutUsBtn);
        feedback = view.findViewById(R.id.feedbackBtn);
        //bugreport = view.findViewById(R.id.bugReportBtn);
        refer = view.findViewById(R.id.ReferBtn);

        if(status){
            logout.setVisibility(View.VISIBLE);
        }else{
            logout.setVisibility(View.INVISIBLE);
        }

        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                about_us_fragment fragment = new about_us_fragment();
                FragmentManager manager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frameLayout, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        if(name.isEmpty() && email.isEmpty()){
            name1.setText("Name");
            email1.setText("Email ID");
        }else {
            name1.setText(name);
            email1.setText(email);
        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(v.getContext(),R.style.Theme_Dialog);
                dialog.setContentView(R.layout.dialog_logout);
                Button logout = dialog.findViewById(R.id.submit_logout_button);
                Button cancel = dialog.findViewById(R.id.cancel_logout_button);
                dialog.show();
                logout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "Logged Out", Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor1 = pref.edit();
                        editor1.clear();
                        editor1.apply();
                        editor1 = pref1.edit();
                        editor1.clear();
                        editor1.apply();
                        dialog.dismiss();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editProfileFragment nextFrag= new editProfileFragment();
                FragmentManager fragmentManager = ((FragmentActivity) view.getContext()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                nextFrag.setSharedElementEnterTransition(new DetailsTransition());
                nextFrag.setSharedElementReturnTransition(new DetailsTransition());
                fragmentTransaction.addSharedElement(profilepic1,"sharedPIC");
                fragmentTransaction.replace(R.id.frameLayout,nextFrag);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Send feedback
                String app_url = "https://play.google.com/store/apps/details?id=com.adgvit.externals";
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(app_url));
                v.getContext().startActivity(intent);
            }
        });
        /*bugreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Send Bug report
            }
        });*/
        refer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Refer a friend

                String app_url = " https://play.google.com/store/apps/details?id=com.adgvit.externals";
                Intent shareIntent =   new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String text = "ADG-VIT is an application built to give an insight of what this community holds for you. It is one stop app for new recruitments, FAQ, events , projects , various domains and a lot more!\n" +
                        "Apple developers group aims to provide the best learning experience to its members and believes that creativity comes with collaboration! \n" +
                        "Features:\n" +
                        "1. All the latest updates about Apple Developers Group VIT.\n" +
                        "2. All information about its domains, projects, events and team.\n" +
                        "3. Recruitment made easier with interested candidates participating anytime,anywhere on the go. \n" +
                        "4. FAQs that will solve all the doubts you might be having of Apple Developers Group VIT\n" +
                        "5. A journey that will cover all your expectations and take you to unexplored realm of possibilities.\n";
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,"");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,text+app_url);
                v.getContext().startActivity(Intent.createChooser(shareIntent,"Share via"));
            }
        });

        return view;
    }
}