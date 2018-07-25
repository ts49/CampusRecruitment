package com.example.phrs91.campusrecruitment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.phrs91.campusrecruitment.Fragments.Company;
import com.example.phrs91.campusrecruitment.Fragments.Notification;

import java.util.List;

public class companyAdapter extends RecyclerView.Adapter<companyAdapter.MyViewHolder>{
    private List<Company> moviesList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView topic, text;

        public MyViewHolder(View view) {
            super(view);
            topic = (TextView) view.findViewById(R.id.topic);
            text = (TextView) view.findViewById(R.id.text);

        }

    }
    @Override
    public companyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification, parent, false);

        return new companyAdapter.MyViewHolder(itemView);
    }
    public companyAdapter(List<Company> moviesList) {
        this.moviesList = moviesList;
    }
    @Override
    public void onBindViewHolder(companyAdapter.MyViewHolder holder, int position) {
        Company notification= moviesList.get(position);
        holder.topic.setText(notification.getName());
        String x=notification.getDesc();
        holder.text.setText(x.substring(0,Math.min(x.length(),70)));

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
