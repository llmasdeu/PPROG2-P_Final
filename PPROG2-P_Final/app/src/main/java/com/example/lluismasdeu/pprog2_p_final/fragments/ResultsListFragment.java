package com.example.lluismasdeu.pprog2_p_final.fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.lluismasdeu.pprog2_p_final.R;
import com.example.lluismasdeu.pprog2_p_final.activities.DescriptionActivity;
import com.example.lluismasdeu.pprog2_p_final.adapters.ResultsAdapter;
import com.example.lluismasdeu.pprog2_p_final.model.Restaurant;

import java.util.List;

/**
 * Created by eloy on 16-05-2017.
 */

public class ResultsListFragment extends Fragment {
    private static final String TAG = "ResultsListFragment";
    private ListView restaurantsListView;
    private Spinner typesSpinner;
    private ResultsAdapter adapter;
    List<Restaurant> resultsByType;
    private List<Restaurant> restaurantsList;

    public ResultsListFragment(List<Restaurant> restaurantsList) {
        this.restaurantsList = restaurantsList;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results, container, false);

        // Recuperamos el componente gráfico para poder asignarle un adapter.
        typesSpinner = (Spinner) getActivity().findViewById(R.id.menuSort);
        restaurantsListView = (ListView) view.findViewById(R.id.listview);

        adapter = new ResultsAdapter(restaurantsList, getActivity());
        restaurantsListView.setAdapter(adapter);

        //Listener de restaurantsListView
        restaurantsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView temp = (TextView) view.findViewById(R.id.resultTitle_textView);
                String str = temp.getText().toString();
                updateDetail(str);
            }
        });

        return view;
    }

    public void updateRestaurantsList(List<Restaurant> restaurantsList) {
        this.restaurantsList = restaurantsList;

        adapter = new ResultsAdapter(restaurantsList, getActivity());
        adapter.notifyDataSetChanged();
        restaurantsListView.setAdapter(adapter);
    }

    //intent para DescriptionActivity
    public void updateDetail(String str) {
        Intent intent = new Intent(getActivity(), DescriptionActivity.class);
        intent.putExtra("name", str);
        startActivity(intent);
    }
}
