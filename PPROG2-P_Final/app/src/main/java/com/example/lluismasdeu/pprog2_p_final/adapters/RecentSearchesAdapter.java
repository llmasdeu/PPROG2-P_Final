package com.example.lluismasdeu.pprog2_p_final.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lluismasdeu.pprog2_p_final.R;

import java.util.List;


/**
 * Created by lluismasdeu on 13/5/17.
 */

public class RecentSearchesAdapter extends BaseAdapter {
    private Context context;
    private List<String> recentSearchesList;

    public RecentSearchesAdapter(Context context, List<String> recentSearchesList) {
        this.context = context;
        this.recentSearchesList = recentSearchesList;
    }

    @Override
    public int getCount() {
        return recentSearchesList.size();
    }

    @Override
    public Object getItem(int position) {
        return recentSearchesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_recent_searches, parent, false);

        TextView recentSearchTextView = (TextView) view.findViewById(R.id.recentSearch_textView);
        recentSearchTextView.setText((String) getItem(position));

        return view;
    }
}
