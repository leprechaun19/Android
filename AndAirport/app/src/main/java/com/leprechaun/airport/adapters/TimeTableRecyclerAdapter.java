package com.leprechaun.airport.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leprechaun.airport.R;
import com.leprechaun.airport.activities.TimeTableActivity;
import com.leprechaun.airport.data.entities.TimeTable;
import java.util.ArrayList;
import java.util.Collections;

public class TimeTableRecyclerAdapter extends RecyclerView.Adapter<TimeTableRecyclerAdapter.ViewHolder> {

    private ArrayList<TimeTable> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView flightTimeTextView;
        public TextView timeTableTextView;

        public ViewHolder(View v) {
            super(v);
            flightTimeTextView = (TextView) v.findViewById(R.id.timetable_flight_time);
            timeTableTextView = (TextView) v.findViewById(R.id.timetable_timetable);
        }
    }

    public TimeTableRecyclerAdapter(ArrayList<TimeTable> dataset) {
        mDataset = dataset;
    }

    public void setDataset(ArrayList<TimeTable> items) {
        mDataset = items;
        this.notifyDataSetChanged();
    }

    public void addItem(TimeTable item) {
        Collections.reverse(mDataset);
        mDataset.add(mDataset.size(), item);
        Collections.reverse(mDataset);
        this.notifyItemInserted(0);
    }

    public void removeItem(TimeTable item) {
        for (TimeTable i : mDataset) {
            if (i.getTimeTableID() == item.getTimeTableID()) {
                int index = mDataset.indexOf(i);
                mDataset.remove(i);
                this.notifyItemRemoved(index);
            }
        }
    }

    public void editItem(TimeTable item) {
        for (TimeTable i : mDataset) {
            if (i.getTimeTableID() == item.getTimeTableID()) {
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
    public TimeTableRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.timetable_item, parent, false);

        return new TimeTableRecyclerAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeTableRecyclerAdapter.ViewHolder holder, final int position) {

        holder.flightTimeTextView.setText(mDataset.get(position).getFlightTime());
        holder.timeTableTextView.setText(mDataset.get(position).getTimeTableString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TimeTableActivity.class);
                intent.putExtra(TimeTable.class.getSimpleName(), mDataset.get(position));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
