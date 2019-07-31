package com.leprechaun.airport.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leprechaun.airport.R;
import com.leprechaun.airport.activities.TicketActivity;
import com.leprechaun.airport.adapters.TicketRecyclerAdapter;
import com.leprechaun.airport.contentProvider.DatabaseHandler;
import com.leprechaun.airport.data.entities.Ticket;
import com.leprechaun.airport.tasks.Ticket.GetTickets;

import java.util.ArrayList;

public class TicketFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView recyclerView;
    private DatabaseHandler db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new DatabaseHandler(getActivity().getApplicationContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ticket,  null);
        Context context = view.getContext();
        TicketRecyclerAdapter adapter = new TicketRecyclerAdapter(getDataSet());

        recyclerView = view.findViewById(R.id.ticket_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

        SwipeRefreshLayout swipe = view.findViewById(R.id.swipeLayoutTicket);
        swipe.setOnRefreshListener(this);
        swipe.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW);

        FloatingActionButton fab = view.findViewById(R.id.fab_ticket);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TicketActivity.class);
                v.getContext().startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onRefresh(){
        SwipeRefreshLayout swipe = getView().findViewById(R.id.swipeLayoutTicket);
        swipe.setRefreshing(false);
        TicketRecyclerAdapter adapter = new TicketRecyclerAdapter(getDataSet());
        new GetTickets(getView(), adapter).execute("0");
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public ArrayList<Ticket> getDataSet() {
        return db.getTickets();
    }
}
