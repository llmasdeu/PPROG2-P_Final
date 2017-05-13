package com.example.lluismasdeu.pprog2_p_final.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lluismasdeu.pprog2_p_final.R;
import com.example.lluismasdeu.pprog2_p_final.adapters.RecentSearchesAdapter;

import java.util.List;

/**
 * Created by lluismasdeu on 13/5/17.
 */

public class RecentSearchesFragment extends Fragment {
    private Context context;
    private RecentSearchesAdapter adapter;
    private List<String> recentSearchesList;
    private ListView recentSearchesListView;

    public RecentSearchesFragment(Context context, List<String> recentSearchesList) {
        this.context = context;
        this.recentSearchesList = recentSearchesList;

        adapter = new RecentSearchesAdapter(context, recentSearchesList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recent_searches, container, false);
        recentSearchesListView = (ListView) view.findViewById(R.id.recentSearches_listView);
        recentSearchesListView.setAdapter(adapter);

        return view;
    }
}
