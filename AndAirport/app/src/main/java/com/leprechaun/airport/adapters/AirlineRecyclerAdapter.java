package com.leprechaun.airport.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leprechaun.airport.R;
import com.leprechaun.airport.activities.AirlineActivity;
import com.leprechaun.airport.data.entities.Airline;

import java.util.ArrayList;
import java.util.Collections;

public class AirlineRecyclerAdapter extends RecyclerView.Adapter<AirlineRecyclerAdapter.ViewHolder> {

    private ArrayList<Airline> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;

        public ViewHolder(View v) {
            super(v);
            nameTextView = (TextView) v.findViewById(R.id.airlineName);
        }
    }

    public AirlineRecyclerAdapter(ArrayList<Airline> dataset) {
        mDataset = dataset;
    }

    public void setDataset(ArrayList<Airline> items) {
        mDataset = items;
        this.notifyDataSetChanged();
    }

    public void addItem(Airline item) {
        Collections.reverse(mDataset);
        mDataset.add(mDataset.size(), item);
        Collections.reverse(mDataset);
        this.notifyItemInserted(0);
    }

    public void removeItem(Airline item) {
        for (Airline i : mDataset) {
            if (i.getAirlineID() == item.getAirlineID()) {
                int index = mDataset.indexOf(i);
                mDataset.remove(i);
                this.notifyItemRemoved(index);
            }
        }
    }

    public void editItem(Airline item) {
        for (Airline i : mDataset) {
            if (i.getAirlineID()== item.getAirlineID()) {
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
    public AirlineRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.airline_item, parent, false);

        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.nameTextView.setText(mDataset.get(position).getAirlineFullName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AirlineActivity.class);
                intent.putExtra(Airline.class.getSimpleName(), mDataset.get(position));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
