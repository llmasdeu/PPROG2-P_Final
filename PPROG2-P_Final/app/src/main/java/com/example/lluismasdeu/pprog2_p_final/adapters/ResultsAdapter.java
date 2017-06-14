package com.example.lluismasdeu.pprog2_p_final.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.lluismasdeu.pprog2_p_final.R;
import com.example.lluismasdeu.pprog2_p_final.model.Restaurant;

import java.util.List;

/**
 * Created by eloy on 16-05-2017.
 */

public class ResultsAdapter extends BaseAdapter {
    private List<Restaurant> restaurantList;
    private Context context;

    public ResultsAdapter(List<Restaurant> restaurantList, Context context) {
        this.restaurantList = restaurantList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return restaurantList.size();
    }

    @Override
    public Object getItem(int position) {
        return restaurantList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_results, parent, false);

        Restaurant restaurant = restaurantList.get(position);

        ImageView restaurantImage = (ImageView) itemView.findViewById(R.id.listview_cell_image);
        restaurantImage.setImageBitmap(restaurant.getImage());

        TextView title = (TextView) itemView.findViewById(R.id.resultTitle_textView);
        title.setText((restaurant.getName()));

        TextView address = (TextView) itemView.findViewById(R.id.resultAddress_textView);
        address.setText(restaurant.getAddress());

        RatingBar rate = (RatingBar) itemView.findViewById(R.id.resultRate_ratingBar);
        rate.setRating((float) restaurant.getReview());

        return itemView;
    }

}
