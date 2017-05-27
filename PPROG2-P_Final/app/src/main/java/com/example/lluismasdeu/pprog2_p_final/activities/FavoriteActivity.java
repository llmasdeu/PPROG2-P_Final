package com.example.lluismasdeu.pprog2_p_final.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.lluismasdeu.pprog2_p_final.R;
import com.example.lluismasdeu.pprog2_p_final.adapters.TabAdapter;
import com.example.lluismasdeu.pprog2_p_final.fragments.ListViewFavoriteFragment;
import com.example.lluismasdeu.pprog2_p_final.fragments.ListViewFavoriteOpenFragment;
import com.example.lluismasdeu.pprog2_p_final.fragments.ListViewFragment;
import com.example.lluismasdeu.pprog2_p_final.fragments.ListViewFragmentOpen;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        getSupportActionBar().hide();
        createTabs();
    }
    private void createTabs() {


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs_favorite);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager_favorite);

        ArrayList<TabAdapter.TabEntry> entries = new ArrayList<>();
        entries.add(new TabAdapter.TabEntry(new ListViewFavoriteFragment(), "All"));
       entries.add(new TabAdapter.TabEntry(new ListViewFavoriteOpenFragment(), "Only open"));

        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(), entries);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
