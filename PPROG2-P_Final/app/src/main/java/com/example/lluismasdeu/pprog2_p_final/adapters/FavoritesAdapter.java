package com.example.lluismasdeu.pprog2_p_final.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.lluismasdeu.pprog2_p_final.R;
import com.example.lluismasdeu.pprog2_p_final.model.Favorite;

import java.util.List;

/**
 * Created by eloy on 26-05-2017.
 */

public class FavoritesAdapter extends BaseAdapter{

    List<Favorite> list;
    private Context context;

    public FavoritesAdapter(List<Favorite> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.listview_favorite_cell, viewGroup, false);
        Favorite favorite=list.get(i);
        TextView title = (TextView) itemView.findViewById(R.id.titulo_textView);
        title.setText((favorite.getName()));
        TextView address = (TextView) itemView.findViewById(R.id.adrress_textView);
        address.setText(favorite.getAddress());

        RatingBar rate=(RatingBar) itemView.findViewById(R.id.rate_stars);
        rate.setRating(Float.parseFloat(favorite.getRate()));
        return itemView;
    }
}
