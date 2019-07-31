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
import com.leprechaun.airport.activities.UserActivity;
import com.leprechaun.airport.adapters.UserRecyclerAdapter;
import com.leprechaun.airport.contentProvider.DatabaseHandler;
import com.leprechaun.airport.data.entities.User;
import com.leprechaun.airport.tasks.User.GetUsers;

import java.util.ArrayList;

public class UserFragment  extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

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

        View view = inflater.inflate(R.layout.fragment_user,  null);
        Context context = view.getContext();
        UserRecyclerAdapter adapter = new UserRecyclerAdapter(getDataSet());

        recyclerView = view.findViewById(R.id.user_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

        SwipeRefreshLayout swipe = view.findViewById(R.id.swipeLayoutUser);
        swipe.setOnRefreshListener(this);
        swipe.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW);

        FloatingActionButton fab = view.findViewById(R.id.fab_user);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UserActivity.class);
                v.getContext().startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onRefresh(){
        SwipeRefreshLayout swipe = getView().findViewById(R.id.swipeLayoutUser);
        swipe.setRefreshing(false);
        UserRecyclerAdapter adapter = new UserRecyclerAdapter(getDataSet());
        new GetUsers(getView(), adapter).execute("0");
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public ArrayList<User> getDataSet() {
        return db.getUsers();
    }
}
