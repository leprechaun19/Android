package com.leprechaun.airport.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leprechaun.airport.R;
import com.leprechaun.airport.activities.AirportActivity;
import com.leprechaun.airport.data.entities.Airport;

import java.util.ArrayList;
import java.util.Collections;

public class AirportRecyclerAdapter extends RecyclerView.Adapter<AirportRecyclerAdapter.ViewHolder> {

    private ArrayList<Airport> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public TextView countryTextView;
        public TextView cityTextView;

        public ViewHolder(View v) {
            super(v);
            nameTextView = (TextView) v.findViewById(R.id.airport_name);
            countryTextView = (TextView) v.findViewById(R.id.airport_country);
            cityTextView = (TextView) v.findViewById(R.id.airport_city);
        }
    }

    public AirportRecyclerAdapter(ArrayList<Airport> dataset) {
        mDataset = dataset;
    }

    public void setDataset(ArrayList<Airport> items) {
        mDataset = items;
        this.notifyDataSetChanged();
    }

    public void addItem(Airport item) {
        Collections.reverse(mDataset);
        mDataset.add(mDataset.size(), item);
        Collections.reverse(mDataset);
        this.notifyItemInserted(0);
    }

    public void removeItem(Airport item) {
        for (Airport i : mDataset) {
            if (i.getAirportID() == item.getAirportID()) {
                int index = mDataset.indexOf(i);
                mDataset.remove(i);
                this.notifyItemRemoved(index);
            }
        }
    }

    public void editItem(Airport item) {
        for (Airport i : mDataset) {
            if (i.getAirportID()== item.getAirportID()) {
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
    public AirportRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.airport_item, parent, false);

        return new AirportRecyclerAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AirportRecyclerAdapter.ViewHolder holder, final int position) {

        holder.nameTextView.setText(mDataset.get(position).getAirportName());
        holder.countryTextView.setText(String.format("%s, ", mDataset.get(position).getCountryName()));
        holder.cityTextView.setText(mDataset.get(position).getCityName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AirportActivity.class);
                intent.putExtra(Airport.class.getSimpleName(), mDataset.get(position));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
