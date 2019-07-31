package com.leprechaun.airport.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leprechaun.airport.R;
import com.leprechaun.airport.activities.AirplaneActivity;
import com.leprechaun.airport.data.entities.Airplane;

import java.util.ArrayList;
import java.util.Collections;

public class AirplaneRecyclerAdapter extends RecyclerView.Adapter<AirplaneRecyclerAdapter.ViewHolder> {

    private ArrayList<Airplane> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView type;
        public TextView numOfSeats;
        public TextView airlineName;

        public ViewHolder(View v) {
            super(v);
            type = (TextView) v.findViewById(R.id.airplane_type);
            numOfSeats = (TextView) v.findViewById(R.id.airplane_seats);
            airlineName = (TextView) v.findViewById(R.id.airplane_airline_name);
        }
    }

    public AirplaneRecyclerAdapter(ArrayList<Airplane> dataset) {
        mDataset = dataset;
    }

    public void setDataset(ArrayList<Airplane> items) {
        mDataset = items;
        this.notifyDataSetChanged();
    }

    public void addItem(Airplane item) {
        Collections.reverse(mDataset);
        mDataset.add(mDataset.size(), item);
        Collections.reverse(mDataset);
        this.notifyItemInserted(0);
    }

    public void removeItem(Airplane item) {
        for (Airplane i : mDataset) {
            if (i.getAirlineID() == item.getAirlineID()) {
                int index = mDataset.indexOf(i);
                mDataset.remove(i);
                this.notifyItemRemoved(index);
            }
        }
    }

    public void editItem(Airplane item) {
        for (Airplane i : mDataset) {
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
    public AirplaneRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.airplane_item, parent, false);

        return new AirplaneRecyclerAdapter.ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull AirplaneRecyclerAdapter.ViewHolder holder, final int position) {

        holder.type.setText(mDataset.get(position).getAirplaneType());
        holder.numOfSeats.setText(mDataset.get(position).getNumPlaces());
        holder.airlineName.setText(mDataset.get(position).getAirline().getAirlineFullName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AirplaneActivity.class);
                intent.putExtra(Airplane.class.getSimpleName(), mDataset.get(position));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
