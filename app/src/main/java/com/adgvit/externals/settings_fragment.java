package com.adgvit.externals;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
    private SharedPreferences pref,pref1;
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
        name = pref.getString("name","");
        email = pref.getString("email","");

        editProfile = view.findViewById(R.id.editProfileBtn);
        profilepic1 = view.findViewById(R.id.profile_image1);
        logout = view.findViewById(R.id.buttonLogout);
        name1 = view.findViewById(R.id.profileName);
        email1 = view.findViewById(R.id.profileEmail);
        aboutus = view.findViewById(R.id.aboutUsBtn);
        feedback = view.findViewById(R.id.feedbackBtn);
        bugreport = view.findViewById(R.id.bugReportBtn);
        refer = view.findViewById(R.id.ReferBtn);

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
            }
        });
        bugreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Send Bug report
            }
        });
        refer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Refer a friend
            }
        });

        return view;
    }
}