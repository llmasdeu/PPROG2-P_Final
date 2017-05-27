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
import android.widget.Spinner;
import android.widget.TextView;
import com.example.lluismasdeu.pprog2_p_final.R;
import com.example.lluismasdeu.pprog2_p_final.activities.DescriptionActivity;
import com.example.lluismasdeu.pprog2_p_final.adapters.FavoritesAdapter;
import com.example.lluismasdeu.pprog2_p_final.model.Favorite;
import com.example.lluismasdeu.pprog2_p_final.repositories.DatabaseManagementInterface;
import com.example.lluismasdeu.pprog2_p_final.repositories.implementations.DatabaseManagement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eloy on 26-05-2017.
 */

public class ListViewFavoriteFragment extends Fragment {
    private ListView listView;
    private FavoritesAdapter favoritesAdapter;
    private FavoritesAdapter adapter;
    List<Favorite> list;
    DatabaseManagementInterface databaseManagementInterface;
    List<Favorite> list_tmp;
    Spinner filtro;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview_favorite, container, false);
        list=null;
        listView=(ListView) view.findViewById(R.id.listview_favorite);
        databaseManagementInterface=new DatabaseManagement(getActivity());
        list=(databaseManagementInterface.getAllFavorite());
        filtro=(Spinner) getActivity().findViewById(R.id.menuSort) ;
        favoritesAdapter=new FavoritesAdapter(list,getActivity());
        listView.setAdapter(favoritesAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
       listView.setAdapter(favoritesAdapter);

                TextView temp = (TextView) view.findViewById(R.id.titulo_textView);

                String str = temp.getText().toString();

                updateDetail(str);
            }
        });
        filtro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                if(getString(R.string.filter)!=filtro.getSelectedItem()) {
                    list_tmp=new ArrayList<>(list.size());
                    for(int w=0;w<list.size();w++){
                        if(filtro.getSelectedItem().equals(list.get(w).getType()))
                        {
                            Favorite tmp_rest= new Favorite();
                            tmp_rest.setName(list.get(w).getName());
                            tmp_rest.setAddress(list.get(w).getAddress());
                            tmp_rest.setRate(list.get(w).getRate());
                            list_tmp.add(tmp_rest);

                        }

                    }
                    adapter = new FavoritesAdapter(list_tmp,getActivity());
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
    public void updateDetail(String str) {
        Intent intent = new Intent(getActivity(), DescriptionActivity.class);
        intent.putExtra("name",str);
        startActivity(intent);
    }

}
