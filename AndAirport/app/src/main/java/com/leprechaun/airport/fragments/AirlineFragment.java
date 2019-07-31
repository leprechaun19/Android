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
import com.leprechaun.airport.activities.AirlineActivity;
import com.leprechaun.airport.adapters.AirlineRecyclerAdapter;
import com.leprechaun.airport.contentProvider.DatabaseHandler;
import com.leprechaun.airport.data.entities.Airline;
import com.leprechaun.airport.tasks.Airline.GetAirlineSoapXml;
import com.leprechaun.airport.tasks.Airline.GetAirlines;
import com.leprechaun.airport.tasks.Airline.GetAirlinesWithoutRetrofit;

import java.util.ArrayList;

public class AirlineFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

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

        View view = inflater.inflate(R.layout.fragment_airline,  null);
        Context context = view.getContext();
        AirlineRecyclerAdapter adapter = new AirlineRecyclerAdapter(getDataSet());

        recyclerView = view.findViewById(R.id.airline_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

        SwipeRefreshLayout swipe = view.findViewById(R.id.swipeLayout);
        swipe.setOnRefreshListener(this);
        swipe.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW);

        FloatingActionButton fab = view.findViewById(R.id.fab_airline);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AirlineActivity.class);
                v.getContext().startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onRefresh(){
        SwipeRefreshLayout swipe = getView().findViewById(R.id.swipeLayout);
        swipe.setRefreshing(false);
        AirlineRecyclerAdapter adapter = new AirlineRecyclerAdapter(getDataSet());
        //new GetAirlinesWithoutRetrofit(getView()).execute("0");
        //new GetAirlineSoapXml(getView()).execute("0");
        new GetAirlines(getView(), adapter).execute("0");
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public ArrayList<Airline> getDataSet() {
        return db.getAirlines();
    }

}
