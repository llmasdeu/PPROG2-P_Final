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
import com.example.lluismasdeu.pprog2_p_final.activities.ResultsActivity;
import com.example.lluismasdeu.pprog2_p_final.adapters.ResultsAdapter;
import com.example.lluismasdeu.pprog2_p_final.model.webserviceResults.PlaceResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eloy on 16-05-2017.
 */

public class ResultsListFragment extends Fragment {
    private ListView listView;
    private ResultsAdapter adapter;
    List<PlaceResult> resultsByType;
    private Spinner typesFilter;
    //List<Restaurant> restaurantsList;
    private List<PlaceResult> placeResults;

    public ResultsListFragment(List<PlaceResult> placeResults) {
        this.placeResults = placeResults;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results, container, false);

        // Recuperamos el componente gráfico para poder asignarle un adapter.
        typesFilter = (Spinner) getActivity().findViewById(R.id.menuSort);
        listView = (ListView) view.findViewById(R.id.listview);

        adapter = new ResultsAdapter(placeResults, getActivity());
        listView.setAdapter(adapter);

        //Listener de listView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView temp = (TextView) view.findViewById(R.id.titulo_textView);
                String str = temp.getText().toString();
                updateDetail(str);
            }
        });

        // typesFilter para la lista mediante spinner
        typesFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (getString(R.string.filter) != typesFilter.getSelectedItem()) {
                    resultsByType = new ArrayList<PlaceResult>(placeResults.size());

                    if (typesFilter.getSelectedItem().equals(getString(R.string.all))) {
                        resultsByType = placeResults;
                    } else {
                        for (int w = 0; w < placeResults.size(); w++) {
                            if (typesFilter.getSelectedItem().equals(placeResults.get(w).getType())) {

                                resultsByType.add(placeResults.get(w));
                            }
                        }
                    }

                    adapter = new ResultsAdapter(resultsByType, getActivity());
                    adapter.notifyDataSetChanged();
                    listView.setAdapter(adapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
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
