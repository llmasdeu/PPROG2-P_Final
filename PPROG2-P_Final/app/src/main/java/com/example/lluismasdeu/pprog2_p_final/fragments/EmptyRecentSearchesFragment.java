package com.example.lluismasdeu.pprog2_p_final.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lluismasdeu.pprog2_p_final.R;

/**
 * Created by lluismasdeu on 13/5/17.
 */

public class EmptyRecentSearchesFragment extends Fragment {
    private static final String TAG = "EmptyRecentSearches";

    public EmptyRecentSearchesFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_empty_recent_searches, container,false);

        return view;
    }
}
