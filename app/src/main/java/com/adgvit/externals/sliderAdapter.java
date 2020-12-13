package com.adgvit.externals;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class sliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public sliderAdapter(Context c){
        this.context = c;
    }

    public int[] slide_images = {
            R.drawable.adglogo2,
            R.drawable.programminglogo
    };

    public  String[] slide_head = {
            "ADG-VIT",
            "Recruitment Like never before"
    };

    public String[] slide_desc = {
            "ADG’s official app which will open the doors to endless possibilities, into the realm of unexplored",
            "With our mobile platform, you can now apply for recruitments from anywhere"

    };


    @Override
    public int getCount() {
        return slide_head.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slidelayoutonboard,container,false);

        ImageView slideimg = view.findViewById(R.id.imageonboard);
        TextView slideHead = view.findViewById(R.id.titleOnboard);
        TextView slideDesc = view.findViewById(R.id.descOnboard);

        slideimg.setImageResource(slide_images[position]);
        slideHead.setText(slide_head[position]);
        slideDesc.setText(slide_desc[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
