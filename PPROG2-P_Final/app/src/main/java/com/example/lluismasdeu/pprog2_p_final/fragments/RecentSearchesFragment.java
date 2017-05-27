package com.example.lluismasdeu.pprog2_p_final.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lluismasdeu.pprog2_p_final.R;
import com.example.lluismasdeu.pprog2_p_final.activities.ResultsActivity;
import com.example.lluismasdeu.pprog2_p_final.adapters.RecentSearchesAdapter;
import com.example.lluismasdeu.pprog2_p_final.utils.HttpRequestHelper;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by lluismasdeu on 13/5/17.
 */

public class RecentSearchesFragment extends Fragment {
    private Context context;
    private RecentSearchesAdapter adapter;
    private List<String> recentSearchesList;
    private ListView recentSearchesListView;

    public RecentSearchesFragment(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recent_searches, container, false);

        // Localizamos los componentes en el layout.
        recentSearchesListView = (ListView) view.findViewById(R.id.recentSearches_listView);

        // Añadimos el adapter a la ListView
        recentSearchesListView.setAdapter(adapter);

        //Listener para listView
        recentSearchesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tmp=(TextView) view.findViewById(R.id.recentSearch_textView);
                String name_tmp=tmp.getText().toString();
                name_tmp=name_tmp.replace(" ","%20");
                updateDetail(name_tmp);
            }
        });
        return view;
    }

    public void setRecentSearchesList(List<String> recentSearchesList) {
        this.recentSearchesList = recentSearchesList;

        adapter = new RecentSearchesAdapter(context, recentSearchesList);
    }
    //Intent de listview hacia resultados
    public void updateDetail(String str) {
        Intent intent = new Intent(getActivity(), ResultsActivity.class);
        intent.putExtra("search_result","s="+str);
        startActivity(intent);
    }
}
