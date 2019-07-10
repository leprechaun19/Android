package com.leprechaun.airport.fragments;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leprechaun.airport.R;
import com.leprechaun.airport.activities.AdminnActivity;
import com.leprechaun.airport.activities.LoginActivity;
import com.leprechaun.airport.adapters.AirlineRecyclerAdapter;
import com.leprechaun.airport.contentProvider.DatabaseHandler;
import com.leprechaun.airport.data.entities.Airline;
import com.leprechaun.airport.data.entities.ArrayJSON;
import com.leprechaun.airport.data.entities.ResponseServer;
import com.leprechaun.airport.service.IService;
import com.leprechaun.airport.service.Service;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AirlineFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView recyclerView;
    private DatabaseHandler db;
    boolean fail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fail = false;

        db = new DatabaseHandler(getActivity().getApplicationContext());
        new getAirlineTask().execute("0");
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
        return view;
    }

    @Override
    public void onRefresh(){
        SwipeRefreshLayout swipe = getView().findViewById(R.id.swipeLayout);
        swipe.setRefreshing(false);
        new getAirlineTask().execute("0");
        AirlineRecyclerAdapter adapter = new AirlineRecyclerAdapter(getDataSet());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public ArrayList<Airline> getDataSet() {
        return db.getAirlines();
    }

    public class deleteAirlineTask extends AsyncTask<String, Void, String> {

        Airline airline;

        public deleteAirlineTask(Airline airline) {
            this.airline = airline;
        }

        @Override
        protected void onPostExecute(String s) {
            if (fail) {
                Snackbar bar = Snackbar.make(getView(), "Не удалось удалить запись", Snackbar.LENGTH_LONG);
                bar.getView().setBackgroundColor(Color.RED);
                bar.setAction("Action", null).show();
                fail = false;
            } else {
                Snackbar bar = Snackbar.make(getView(), "Запись успешно удалена", Snackbar.LENGTH_LONG);
                bar.setAction("Action", null).show();
            }
        }

        @Override
        protected String doInBackground(String... params) {
            try {

                final IService apiService =
                        Service.getService();

                Call<ResponseServer> call = apiService.deleteAirline(airline.getAirlineID());
                call.enqueue(new Callback<ResponseServer>() {
                    @Override
                    public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                        if (response.isSuccessful()) {
                            new getAirlineTask().execute("0");
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseServer> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }
    }


    public class getAirlineTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String s) {
            if (fail) {
                Snackbar bar = Snackbar.make(getView(), "Не удалось загрузить данные", Snackbar.LENGTH_LONG);
                bar.getView().setBackgroundColor(Color.RED);
                bar.setAction("Action", null).show();
                fail = false;
            }
        }

        @Override
        protected String doInBackground(String... params) {

            try {

                Service.getService().getAirlines().enqueue(new Callback<ArrayJSON<Airline>>() {
                    @Override
                    public void onResponse(Call<ArrayJSON<Airline>> call, Response<ArrayJSON<Airline>> response) {
                        if (response.body() != null) {
                            ArrayList<Airline> airlines = new ArrayList<>();

                            try {
                                airlines.addAll(response.body().getData());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            AdminnActivity.db.deleteAllAirlines();
                            AdminnActivity.db.addAirlines(airlines);
                        }
                        else {
                            fail = true;
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayJSON<Airline>> call, Throwable t) {
                        Log.e("GetAirlinesFAILED", t.getMessage());
                    }
                });
                return "OK";
            }
            catch (Exception e ){Log.e("GetAirlinesError", e.getMessage());}
            return "Not OK";
        }
    }
}
