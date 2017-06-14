package com.example.lluismasdeu.pprog2_p_final.fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eloy on 16-05-2017.
 */

public class ResultsListFragment extends Fragment {
    private ListView restaurantsListView;
    private Spinner typesFilter;
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
        typesFilter = (Spinner) getActivity().findViewById(R.id.menuSort);
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

        // typesFilter para la lista mediante spinner
        typesFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (getString(R.string.filter) != typesFilter.getSelectedItem()) {
                    resultsByType = new ArrayList<Restaurant>();

                    if (typesFilter.getSelectedItem().equals(getString(R.string.all))) {
                        resultsByType = restaurantsList;
                    } else {
                        for (int w = 0; w < restaurantsList.size(); w++) {
                            if (typesFilter.getSelectedItem()
                                    .equals(restaurantsList.get(w).getType()))
                                resultsByType.add(restaurantsList.get(w));
                        }
                    }

                    adapter = new ResultsAdapter(resultsByType, getActivity());
                    adapter.notifyDataSetChanged();
                    restaurantsListView.setAdapter(adapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        return view;
    }

    //intent para DescriptionActivity
    public void updateDetail(String str) {
        Intent intent = new Intent(getActivity(), DescriptionActivity.class);
        intent.putExtra("name", str);
        startActivity(intent);
    }
}
