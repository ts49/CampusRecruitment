package com.example.phrs91.campusrecruitment;

/**
 * Created by ashish on 3/6/18.
 */
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.phrs91.campusrecruitment.Fragments.Notification;

import java.util.List;
public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {
    private List<Notification> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView topic, text;

        public MyViewHolder(View view) {
            super(view);
            topic = (TextView) view.findViewById(R.id.topic);
            text = (TextView) view.findViewById(R.id.text);

        }
    }


        public NotificationAdapter(List<Notification> moviesList) {
            this.moviesList = moviesList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.notification, parent, false);

            return new MyViewHolder(itemView);
        }
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Notification notification= moviesList.get(position);
            holder.topic.setText(notification.getTopic());
            holder.text.setText(notification.getText());

        }

        @Override
        public int getItemCount() {
            return moviesList.size();
        }
    }

