package com.leprechaun.airport.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leprechaun.airport.R;
import com.leprechaun.airport.activities.TicketActivity;
import com.leprechaun.airport.data.entities.Ticket;

import java.util.ArrayList;
import java.util.Collections;

public class TicketRecyclerAdapter extends RecyclerView.Adapter<TicketRecyclerAdapter.ViewHolder> {

    private ArrayList<Ticket> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView fromToTextView;
        public TextView departureDateTextView;
        public TextView userNameTextView;
        public TextView seatTextView;
        public TextView priceTextView;
        public TextView orderDateTextView;

        public ViewHolder(View v) {
            super(v);
            fromToTextView = (TextView) v.findViewById(R.id.ticket_from_where);
            departureDateTextView = (TextView) v.findViewById(R.id.ticket_departure_date);
            userNameTextView = (TextView) v.findViewById(R.id.ticket_user);
            seatTextView = (TextView) v.findViewById(R.id.ticket_seat);
            orderDateTextView = (TextView) v.findViewById(R.id.ticket_order_date);
            priceTextView = (TextView) v.findViewById(R.id.ticket_price);
        }
    }

    public TicketRecyclerAdapter(ArrayList<Ticket> dataset) {
        mDataset = dataset;
    }

    public void setDataset(ArrayList<Ticket> items) {
        mDataset = items;
        this.notifyDataSetChanged();
    }

    public void addItem(Ticket item) {
        Collections.reverse(mDataset);
        mDataset.add(mDataset.size(), item);
        Collections.reverse(mDataset);
        this.notifyItemInserted(0);
    }

    public void removeItem(Ticket item) {
        for (Ticket i : mDataset) {
            if (i.getTicketID() == item.getTicketID()) {
                int index = mDataset.indexOf(i);
                mDataset.remove(i);
                this.notifyItemRemoved(index);
            }
        }
    }

    public void editItem(Ticket item) {
        for (Ticket i : mDataset) {
            if (i.getTicketID() == item.getTicketID()) {
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
    public TicketRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ticket_item, parent, false);

        return new TicketRecyclerAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketRecyclerAdapter.ViewHolder holder, final int position) {

        Ticket ticket = mDataset.get(position);

        holder.fromToTextView.setText(getFromToTitle(ticket.getAirportFrom(), ticket.getAirportTo()));
        holder.departureDateTextView.setText(ticket.getDepartureDate());
        holder.userNameTextView.setText(ticket.getUser().getUserName());
        holder.seatTextView.setText(String.valueOf(ticket.getSeat()));
        holder.priceTextView.setText(String.valueOf(ticket.getPrice()));
        holder.orderDateTextView.setText(ticket.getOrderDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TicketActivity.class);
                intent.putExtra(Ticket.class.getSimpleName(), mDataset.get(position));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    private String getFromToTitle(String from, String to){

        int start = from.indexOf(',') + 1;
        int end = from.indexOf(')');
        String cityFrom = from.substring(start, end);

        int start1 = to.indexOf(',') + 1;
        int end1 = to.indexOf(')');
        String cityTo = to.substring(start1, end1);

        return cityFrom + " -" + cityTo;
    }
}
