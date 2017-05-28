package com.example.lluismasdeu.pprog2_p_final.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.lluismasdeu.pprog2_p_final.R;
import com.example.lluismasdeu.pprog2_p_final.model.webserviceResults.PlaceResult;

import java.util.List;

/**
 * Created by eloy on 16-05-2017.
 */

public class ResultsAdapter extends BaseAdapter {
    private List<PlaceResult> list;
    private Context context;

    public ResultsAdapter(List<PlaceResult> list, Context context) {
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

        View itemView = inflater.inflate(R.layout.item_results, parent, false);
        PlaceResult result = list.get(position);

        TextView title = (TextView) itemView.findViewById(R.id.resultTitle_textView);
        title.setText((result.getName()));
        TextView address = (TextView) itemView.findViewById(R.id.resultAddress_textView);
        address.setText(result.getAddress());

        RatingBar rate = (RatingBar) itemView.findViewById(R.id.resultRate_ratingBar);
        rate.setRating((float) result.getReview());

        return itemView;
    }

}
