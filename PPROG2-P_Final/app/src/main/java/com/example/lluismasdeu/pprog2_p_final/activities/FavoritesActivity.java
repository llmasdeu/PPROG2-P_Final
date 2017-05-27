package com.example.lluismasdeu.pprog2_p_final.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
<<<<<<< Updated upstream
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
=======
>>>>>>> Stashed changes

import com.example.lluismasdeu.pprog2_p_final.R;
import com.example.lluismasdeu.pprog2_p_final.adapters.TabsAdapter;
import com.example.lluismasdeu.pprog2_p_final.fragments.ListViewFavoriteFragment;
<<<<<<< Updated upstream
import com.example.lluismasdeu.pprog2_p_final.fragments.ListViewFavoriteOpenFragment;
import com.example.lluismasdeu.pprog2_p_final.fragments.ListViewFragment;
import com.example.lluismasdeu.pprog2_p_final.fragments.ListViewFragmentOpen;
=======
>>>>>>> Stashed changes

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        getSupportActionBar().hide();
        createTabs();
    }
    public void onClickFavorite(View view){
        Intent intent=new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }
    private void createTabs() {


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs_favorite);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager_favorite);

<<<<<<< Updated upstream
        ArrayList<TabAdapter.TabEntry> entries = new ArrayList<>();
        entries.add(new TabAdapter.TabEntry(new ListViewFavoriteFragment(), getString(R.string.all)));
       entries.add(new TabAdapter.TabEntry(new ListViewFavoriteOpenFragment(), getString(R.string.only_open)));
=======
        ArrayList<TabsAdapter.TabEntry> entries = new ArrayList<>();
        entries.add(new TabsAdapter.TabEntry(new ListViewFavoriteFragment(), "All"));
       // entries.add(new TabsAdapter.TabEntry(new ListViewFragmentOpen(), "Only open"));
>>>>>>> Stashed changes

        TabsAdapter adapter = new TabsAdapter(getSupportFragmentManager(), entries);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
