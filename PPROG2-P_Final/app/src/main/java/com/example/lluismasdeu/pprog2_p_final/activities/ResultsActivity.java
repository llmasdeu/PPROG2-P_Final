package com.example.lluismasdeu.pprog2_p_final.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;

import com.example.lluismasdeu.pprog2_p_final.R;
import com.example.lluismasdeu.pprog2_p_final.adapters.TabsAdapter;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity {

    Spinner filter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        getSupportActionBar().hide();
        //Recuperamos valor del spinner action bar
        filter=(Spinner) findViewById(R.id.menuSort);

        createTabs();

    }
    // Intenet para ProfileActivity
    public void onClickProfile(View view){
        Intent intent=new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }
    //Intent para Favorite Activity
    public void onClickFavorite(View view){
        Intent intent=new Intent(this,FavoriteActivity.class);
        startActivity(intent);
    }
    private void createTabs() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

        ArrayList<TabsAdapter.TabEntry> entries = new ArrayList<>();
       //entries.add(new TabsAdapter.TabEntry(new ListViewFragment(), "All"));
        // entries.add(new TabsAdapter.TabEntry(new ListViewFragment(), "Only open"));

        TabsAdapter adapter = new TabsAdapter(getSupportFragmentManager(), entries);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

//TODO opcion para filtro de resultados


}
