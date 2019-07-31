package com.leprechaun.airport.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leprechaun.airport.R;
import com.leprechaun.airport.activities.FlightActivity;
import com.leprechaun.airport.activities.UserActivity;
import com.leprechaun.airport.data.entities.Flight;
import com.leprechaun.airport.data.entities.User;

import java.util.ArrayList;
import java.util.Collections;

public class FlightRecyclerAdapter extends RecyclerView.Adapter<FlightRecyclerAdapter.ViewHolder> {

    private ArrayList<Flight> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView flightNumberTextView;
        public TextView departureDateTextView;
        public TextView destinationDateTextView;
        public TextView airplaneTypeTextView;
        public TextView fromTextView;
        public TextView toTextView;
        public TextView timeTextView;
        public TextView priceTextView;

        public ViewHolder(View v) {
            super(v);
            flightNumberTextView = (TextView) v.findViewById(R.id.number_flight_text);
            departureDateTextView = (TextView) v.findViewById(R.id.flight_departure_date);
            destinationDateTextView = (TextView) v.findViewById(R.id.flight_destination_date);
            airplaneTypeTextView = (TextView) v.findViewById(R.id.flight_airplane_type);
            fromTextView = (TextView) v.findViewById(R.id.flight_airport_from);
            toTextView = (TextView) v.findViewById(R.id.flight_airport_to);
            timeTextView = (TextView) v.findViewById(R.id.flight_time);
            priceTextView = (TextView) v.findViewById(R.id.flight_price);
        }
    }

    public FlightRecyclerAdapter(ArrayList<Flight> dataset) {
        mDataset = dataset;
    }

    public void setDataset(ArrayList<Flight> items) {
        mDataset = items;
        this.notifyDataSetChanged();
    }

    public void addItem(Flight item) {
        Collections.reverse(mDataset);
        mDataset.add(mDataset.size(), item);
        Collections.reverse(mDataset);
        this.notifyItemInserted(0);
    }

    public void removeItem(Flight item) {
        for (Flight i : mDataset) {
            if (i.getFlightID() == item.getFlightID()) {
                int index = mDataset.indexOf(i);
                mDataset.remove(i);
                this.notifyItemRemoved(index);
            }
        }
    }

    public void editItem(Flight item) {
        for (Flight i : mDataset) {
            if (i.getFlightID() == item.getFlightID()) {
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
    public FlightRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.flight_item, parent, false);

        return new FlightRecyclerAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightRecyclerAdapter.ViewHolder holder, final int position) {

        int i = 0;
        holder.flightNumberTextView.setText(String.format("Flight №%d", ++i));
        holder.departureDateTextView.setText(mDataset.get(position).getDepartureDate());
        holder.destinationDateTextView.setText(mDataset.get(position).getDestinationDate());
        holder.airplaneTypeTextView.setText(mDataset.get(position).getAirplaneType());
        holder.fromTextView.setText(mDataset.get(position).getAirportFrom());
        holder.toTextView.setText(mDataset.get(position).getAirportTo());
        holder.timeTextView.setText(mDataset.get(position).getFlightTime());
        holder.priceTextView.setText(String.valueOf(mDataset.get(position).getPriceFlight()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FlightActivity.class);
                intent.putExtra(Flight.class.getSimpleName(), mDataset.get(position));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
