package com.leprechaun.airport.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leprechaun.airport.R;
import com.leprechaun.airport.activities.UserActivity;
import com.leprechaun.airport.data.entities.User;

import java.util.ArrayList;
import java.util.Collections;

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder> {

    private ArrayList<User> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public TextView phoneTextView;
        public TextView emailTextView;
        public TextView addressTextView;
        public TextView createTimeTextView;

        public ViewHolder(View v) {
            super(v);
            nameTextView = (TextView) v.findViewById(R.id.user_name_text);
            phoneTextView = (TextView) v.findViewById(R.id.user_phone);
            emailTextView = (TextView) v.findViewById(R.id.user_email);
            addressTextView = (TextView) v.findViewById(R.id.user_address);
            createTimeTextView = (TextView) v.findViewById(R.id.user_create_time);
        }
    }

    public UserRecyclerAdapter(ArrayList<User> dataset) {
        mDataset = dataset;
    }

    public void setDataset(ArrayList<User> items) {
        mDataset = items;
        this.notifyDataSetChanged();
    }

    public void addItem(User item) {
        Collections.reverse(mDataset);
        mDataset.add(mDataset.size(), item);
        Collections.reverse(mDataset);
        this.notifyItemInserted(0);
    }

    public void removeItem(User item) {
        for (User i : mDataset) {
            if (i.getUserId() == item.getUserId()) {
                int index = mDataset.indexOf(i);
                mDataset.remove(i);
                this.notifyItemRemoved(index);
            }
        }
    }

    public void editItem(User item) {
        for (User i : mDataset) {
            if (i.getUserId() == item.getUserId()) {
                int index = mDataset.indexOf(i);
                mDataset.set(index, item);
                this.notifyItemChanged(index);
            }
        }
    }

    public void removeAllItems() {
        mDataset = new ArrayList<>();
        this.notifyDataSetChanged();
    }

    @Override
    public UserRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);

        return new UserRecyclerAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserRecyclerAdapter.ViewHolder holder, final int position) {

        holder.nameTextView.setText(mDataset.get(position).getUserName());
        holder.phoneTextView.setText(mDataset.get(position).getPhone());
        holder.emailTextView.setText(mDataset.get(position).getEmail());
        holder.addressTextView.setText(mDataset.get(position).getAddress());
        holder.createTimeTextView.setText(mDataset.get(position).getCreateAt());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UserActivity.class);
                intent.putExtra(User.class.getSimpleName(), mDataset.get(position));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
