package com.adgvit.externals;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class faqadapter extends RecyclerView.Adapter<faqadapter.MyViewHolder> {
    List<faqitem> mlist;
    Context mcontext;

    public faqadapter(List<faqitem> mlist, Context mcontext) {
        this.mlist = mlist;
        this.mcontext = mcontext;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView faqQ,faqA;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            faqQ = itemView.findViewById(R.id.faqquestion);
            faqA = itemView.findViewById(R.id.faqanswer);
        }
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.faqquestitem,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        faqitem item = mlist.get(position);
        holder.faqQ.setText(item.getQuestion());
        holder.faqA.setText(item.getAnswer());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }


}
