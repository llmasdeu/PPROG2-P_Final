package com.example.lluismasdeu.pprog2_p_final.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.lluismasdeu.pprog2_p_final.R;
import com.example.lluismasdeu.pprog2_p_final.adapters.TabsAdapter;
import com.example.lluismasdeu.pprog2_p_final.fragments.ListViewFavoriteFragment;
import com.example.lluismasdeu.pprog2_p_final.fragments.ListViewFavoriteOpenFragment;
import com.example.lluismasdeu.pprog2_p_final.model.Favorite;
import com.example.lluismasdeu.pprog2_p_final.repositories.DatabaseManagementInterface;
import com.example.lluismasdeu.pprog2_p_final.repositories.implementations.DatabaseManagement;

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    Spinner filter;
    ArrayList<String> types=new ArrayList<String>();
    DatabaseManagementInterface databaseManagementInterface;
    List<Favorite> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        getSupportActionBar().hide();
        filter=(Spinner) findViewById(R.id.menuSort);
        databaseManagementInterface=new DatabaseManagement(this);
        list=(databaseManagementInterface.getAllFavorite());
        types.add(getString(R.string.filter));
        for (int i=0;i<list.size();i++)
        {
            if(!types.contains(list.get(i).getType()))
            {

                types.add(list.get(i).getType());
            }
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,types);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filter.setAdapter(adapter);
        createTabs();
    }
    public void onClickFavorite(View view){
        Intent intent=new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }
    private void createTabs() {


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs_favorite);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager_favorite);

        ArrayList<TabsAdapter.TabEntry> entries = new ArrayList<>();
        entries.add(new TabsAdapter.TabEntry(new ListViewFavoriteFragment(), getString(R.string.all)));
       entries.add(new TabsAdapter.TabEntry(new ListViewFavoriteOpenFragment(), getString(R.string.only_open)));


        TabsAdapter adapter = new TabsAdapter(getSupportFragmentManager(), entries);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
