package com.example.lluismasdeu.pprog2_p_final.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lluismasdeu.pprog2_p_final.R;
import com.example.lluismasdeu.pprog2_p_final.model.Commentary;

import java.util.List;

/**
 * Created by eloy on 28-05-2017.
 */

public class CommentariesAdapter extends BaseAdapter{
    private Context context;
    private List<Commentary> commentariesList;

    public CommentariesAdapter(Context context, List<Commentary> commentariesList) {
        this.context = context;
        this.commentariesList = commentariesList;
    }

    @Override
    public int getCount() {
        return commentariesList.size();
    }

    @Override
    public Object getItem(int i) {
        return commentariesList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.item_commentaries,viewGroup,false);
        final Commentary commentary = commentariesList.get(i);
        TextView username = (TextView) view.findViewById(R.id.username_textView);
        username.setText(commentary.getUsername());
        TextView comentary = (TextView) view.findViewById(R.id.commentary_textView);
        comentary.setText(commentary.getCommentary());

        return view;
    }

    public void setCommentariesList(List<Commentary> commentariesList) {
        this.commentariesList = commentariesList;
    }
}
