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
import com.leprechaun.airport.activities.AirplaneActivity;
import com.leprechaun.airport.adapters.AirplaneRecyclerAdapter;
import com.leprechaun.airport.contentProvider.DatabaseHandler;
import com.leprechaun.airport.data.entities.Airplane;
import com.leprechaun.airport.tasks.Airplane.GetAirplanes;

import java.util.ArrayList;

public class AirplaneFragment  extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

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

        View view = inflater.inflate(R.layout.fragment_airplane,  null);
        Context context = view.getContext();
        AirplaneRecyclerAdapter adapter = new AirplaneRecyclerAdapter(getDataSet());

        recyclerView = view.findViewById(R.id.airplane_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

        SwipeRefreshLayout swipe = view.findViewById(R.id.swipeLayout);
        swipe.setOnRefreshListener(this);
        swipe.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW);

        FloatingActionButton fab = view.findViewById(R.id.fab_airplane);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AirplaneActivity.class);
                v.getContext().startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onRefresh(){
        SwipeRefreshLayout swipe = getView().findViewById(R.id.swipeLayout);
        swipe.setRefreshing(false);
        AirplaneRecyclerAdapter adapter = new AirplaneRecyclerAdapter(getDataSet());
        new GetAirplanes(getView(), adapter).execute("0");
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public ArrayList<Airplane> getDataSet() {
        return db.getAirplanes();
    }

}
