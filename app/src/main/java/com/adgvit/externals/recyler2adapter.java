package com.adgvit.externals;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class recyler2adapter extends RecyclerView.Adapter<recyler2adapter.MyViewHolder> {

    private List<recyler2item> mlist;
    private Context mcontext;
    public recyler2adapter(List<recyler2item> mlist, Context mcontext) {
        this.mlist = mlist;
        this.mcontext = mcontext;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView day,month,heading,venue,time;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            day = itemView.findViewById(R.id.recycler2cardday);
            month = itemView.findViewById(R.id.recycler2cardmonth);
            heading = itemView.findViewById(R.id.recycler2cardevent);
            venue= itemView.findViewById(R.id.recycler2cardvenue);
            time = itemView.findViewById(R.id.timerecyclercard2);
        }
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.recylerhome2,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        recyler2item item = mlist.get(position);
        holder.heading.setText(item.getHeading());
        holder.venue.setText(item.getVenue());
        holder.time.setText(item.getTime() + " AM");
        holder.day.setText(item.getDay());
        holder.month.setText(item.getMonth());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }


}
