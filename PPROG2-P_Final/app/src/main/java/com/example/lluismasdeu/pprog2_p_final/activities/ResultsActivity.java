package com.example.lluismasdeu.pprog2_p_final.activities;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.lluismasdeu.pprog2_p_final.R;
import com.example.lluismasdeu.pprog2_p_final.adapters.TabAdapter;
import com.example.lluismasdeu.pprog2_p_final.fragments.ListViewFragment;
import com.example.lluismasdeu.pprog2_p_final.fragments.ListViewFragmentOpen;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity {

    Spinner filter;
    private static final String SEARCH_RESULT_EXTRA = "search_result";
    private String searchParameter="Search";
    ArrayList<String> types=new ArrayList<String>();
    JsonArrayRequest jsArrayRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        getSupportActionBar().hide();
        //Recuperamos valor del spinner action bar
        filter=(Spinner) findViewById(R.id.menuSort);

        //creamos tabs
        createTabs();
        searchParameter=getIntent().getStringExtra(SEARCH_RESULT_EXTRA);

        //llenamos spinner
        types.add(getString(R.string.filter));
        String url ="http://testapi-pprog2.azurewebsites.net/api/locations.php?method=getLocations&";
        RequestQueue queue = Volley.newRequestQueue(this);
        jsArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url+searchParameter,
                null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            JSONArray search = response;
                            for (int i = 0; i < search.length(); i++) {
                                if(!types.contains(search.getJSONObject(i).getString("type")))
                                {

                                    types.add(search.getJSONObject(i).getString("type"));
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );

        queue.add(jsArrayRequest);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,types);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filter.setAdapter(adapter);

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

        ArrayList<TabAdapter.TabEntry> entries = new ArrayList<>();
        entries.add(new TabAdapter.TabEntry(new ListViewFragment(), getString(R.string.all)));
        entries.add(new TabAdapter.TabEntry(new ListViewFragmentOpen(), getString(R.string.only_open)));

        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(), entries);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
    public String getMyData() {
        return searchParameter;
    }




}
