package com.example.lluismasdeu.pprog2_p_final.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lluismasdeu.pprog2_p_final.R;
import com.example.lluismasdeu.pprog2_p_final.activities.DescriptionActivity;
import com.example.lluismasdeu.pprog2_p_final.adapters.FavoritesAdapter;
import com.example.lluismasdeu.pprog2_p_final.model.Favorite;
import com.example.lluismasdeu.pprog2_p_final.model.StaticValues;
import com.example.lluismasdeu.pprog2_p_final.repositories.DatabaseManagementInterface;
import com.example.lluismasdeu.pprog2_p_final.repositories.implementations.DatabaseManagement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by eloy on 27-05-2017.
 */

public class ListViewFavoriteOpenFragment extends Fragment {
    private ListView listView;
    private FavoritesAdapter favoriteAdapter;
    List<Favorite> list;
    List<Favorite> list_tmp;
    DatabaseManagementInterface databaseManagementInterface;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview_favorites, container, false);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateformat = new SimpleDateFormat("HH:mm");
        final String datetime = dateformat.format(c.getTime());
        listView=(ListView) view.findViewById(R.id.listview_favorite);
        databaseManagementInterface=new DatabaseManagement(getActivity());
        list=(databaseManagementInterface.getAllFavorite());
        list_tmp = new ArrayList<>(list.size());
        if ((!list.isEmpty())) {
            for (int i = 0; i < list.size(); i++) {
                Favorite favorite=new Favorite();
                if (datetime.compareTo(list.get(i).getOpen()) >= 0 && datetime.compareTo(list.get(i).getClose()) <= 0) {
                    if(list.get(i).getUsername().equals(StaticValues.getInstance().getConnectedUser().getUsername())) {
                        favorite.setName(list.get(i).getName());
                        favorite.setAddress(list.get(i).getAddress());
                        favorite.setRate(list.get(i).getRate());
                        list_tmp.add(favorite);
                    }
                }

            }
            favoriteAdapter=new FavoritesAdapter(list_tmp,getActivity());
           listView.setAdapter(favoriteAdapter);

        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                TextView temp = (TextView) view.findViewById(R.id.resultTitle_textView);

                String str = temp.getText().toString();

                updateDetail(str);
            }
        });


        return view;
    }
    public void updateDetail(String str) {
        Intent intent = new Intent(getActivity(), DescriptionActivity.class);
        intent.putExtra("name",str);
        startActivity(intent);
    }
}
