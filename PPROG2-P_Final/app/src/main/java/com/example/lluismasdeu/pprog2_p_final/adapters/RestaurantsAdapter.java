package com.example.lluismasdeu.pprog2_p_final.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.lluismasdeu.pprog2_p_final.R;
import com.example.lluismasdeu.pprog2_p_final.model.Restaurant;

import java.util.List;

/**
 * Created by eloy on 16-05-2017.
 */

public class RestaurantsAdapter extends BaseAdapter {
    private List<Restaurant> list;
    private Context context;

    public RestaurantsAdapter(List<Restaurant> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.listview_cell_layout, parent, false);
        Restaurant restaurant =list.get(position);

        TextView title = (TextView) itemView.findViewById(R.id.titulo_textView);
        title.setText((restaurant.getName()));
        TextView address = (TextView) itemView.findViewById(R.id.adrress_textView);
        address.setText(restaurant.getAddress());

        RatingBar rate=(RatingBar) itemView.findViewById(R.id.rate_stars);
        //rate.setRating(Float.parseFloat(restaurant.getRate()));


        return itemView;
    }

}
