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
import com.example.lluismasdeu.pprog2_p_final.adapters.FavoriteAdapter;
import com.example.lluismasdeu.pprog2_p_final.model.Favorite;
import com.example.lluismasdeu.pprog2_p_final.repositories.DatabaseManagementInterface;
import com.example.lluismasdeu.pprog2_p_final.repositories.implementations.DatabaseManagement;

import java.util.List;

/**
 * Created by eloy on 26-05-2017.
 */

public class ListViewFavoriteFragment extends Fragment {
    private ListView listView;
    private FavoriteAdapter favoriteAdapter;
    List<Favorite> list;
    DatabaseManagementInterface databaseManagementInterface;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview_favorite, container, false);
        list=null;
        listView=(ListView) view.findViewById(R.id.listview_favorite);
        databaseManagementInterface=new DatabaseManagement(getActivity());
        list=(databaseManagementInterface.getAllFavorite());
        favoriteAdapter=new FavoriteAdapter(list,getActivity());
        listView.setAdapter(favoriteAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                TextView temp = (TextView) view.findViewById(R.id.titulo_textView);

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
