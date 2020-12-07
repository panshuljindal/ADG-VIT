package com.example.adgexternals;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.transition.Fade;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class settings_fragment extends Fragment {
    Button editProfile; ImageView profilepic1;
    TextView name1,email1;
    String name,email;
    SharedPreferences pref;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings_fragment, container, false);

        pref = view.getContext().getSharedPreferences("com.adgexternals.com.userdata", Context.MODE_PRIVATE);
        name = pref.getString("name","");
        email = pref.getString("email","");

        editProfile = (Button) view.findViewById(R.id.editProfileBtn);
        profilepic1 = (ImageView) view.findViewById(R.id.profile_image1);
        name1 = view.findViewById(R.id.profileName);
        email1 = view.findViewById(R.id.profileEmail);
        name1.setText(name);
        email1.setText(email);

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(view.getContext(), "edit profile", Toast.LENGTH_SHORT).show();
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

        return view;
    }
}