package com.example.lluismasdeu.pprog2_p_final.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lluismasdeu.pprog2_p_final.R;
import com.example.lluismasdeu.pprog2_p_final.model.Comentaries;

import java.util.List;

/**
 * Created by eloy on 28-05-2017.
 */

public class ComentariesAdapter extends BaseAdapter{
    private List<Comentaries> list;
    Context context;

    public  ComentariesAdapter(List<Comentaries> list,Context context)
    {
        this.list=list;
        this.context=context;
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
        view=inflater.inflate(R.layout.comentary_list,viewGroup,false);
        final Comentaries comentaries= list.get(i);
        TextView username=(TextView) view.findViewById(R.id.username);
        username.setText(comentaries.getUsername());
        TextView comentary=(TextView) view.findViewById(R.id.comentary);
        comentary.setText(comentaries.getComentary());

        return view;
    }
}
