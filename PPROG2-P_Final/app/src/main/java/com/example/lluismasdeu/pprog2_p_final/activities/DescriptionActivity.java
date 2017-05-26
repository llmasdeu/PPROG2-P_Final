package com.example.lluismasdeu.pprog2_p_final.activities;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.lluismasdeu.pprog2_p_final.R;
import com.example.lluismasdeu.pprog2_p_final.model.Favorite;
import com.example.lluismasdeu.pprog2_p_final.model.User;
import com.example.lluismasdeu.pprog2_p_final.repositories.DatabaseManagementInterface;
import com.example.lluismasdeu.pprog2_p_final.repositories.implementations.DatabaseManagement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class DescriptionActivity extends AppCompatActivity {
    JsonArrayRequest jsArrayRequest;
    TextView name;
    TextView address;
    TextView open;
    TextView close;
    TextView latitud;
    TextView longitud;
    RatingBar rating;
    TextView description;
    FloatingActionButton favorite;
    Favorite favorite_list;
    User user;
    private DatabaseManagementInterface databaseManagementInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        name=(TextView) findViewById(R.id.text_name);
        favorite=(FloatingActionButton) findViewById(R.id.favorite_button);
        address=(TextView) findViewById(R.id.text_address);
        open=(TextView) findViewById(R.id.text_open_hour);
        close=(TextView) findViewById(R.id.text_close_hour);
        description=(TextView) findViewById(R.id.text_description);
        rating=(RatingBar)findViewById(R.id.rate_stars);
        latitud=(TextView) findViewById(R.id.text_latitud);
        longitud=(TextView) findViewById(R.id.text_longitud);
        getSupportActionBar().setTitle("");
        //Recuperamos variable del intent
        final String nombre = getIntent().getStringExtra("name");
        //Hacemos peticion al web services
        String url ="http://testapi-pprog2.azurewebsites.net/api/locations.php?method=getLocations";
        RequestQueue queue = Volley.newRequestQueue(this);
        jsArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            JSONArray search = response;
                            for (int i = 0; i < search.length(); i++) {
                                if(Objects.equals(nombre, search.getJSONObject(i).getString("name")))
                                {
                                    //Llenamos las variables
                                    name.setText(search.getJSONObject(i).getString("name"));
                                    address.setText(search.getJSONObject(i).getString("address"));
                                    open.setText(search.getJSONObject(i).getString("opening"));
                                    close.setText(search.getJSONObject(i).getString("closing"));
                                    description.setText(search.getJSONObject(i).getString("description"));
                                    rating.setRating(Float.parseFloat(search.getJSONObject(i).getString("review")));

                                    JSONObject location=new JSONObject( search.getJSONObject(i).getString("location"));
                                    longitud.setText(location.getString("lng"));
                                    latitud.setText(location.getString("lat"));

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

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseManagementInterface=new DatabaseManagement(getApplicationContext());


              if(!databaseManagementInterface.existFavorite(name.getText().toString()))
                {
                    favorite_list=new Favorite();
                    favorite_list.setName(name.getText().toString());
                    favorite_list.setAddress(address.getText().toString());
                    favorite_list.setRate(String.valueOf(rating.getRating()));
                    favorite_list.setId(1);
                    databaseManagementInterface.registerFavorite(favorite_list);

                }
                else
              {
                  databaseManagementInterface.deleteFavorite(name.getText().toString());
              }


            }
        });

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        //Mostramos actionBar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);

        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_profile:
                // Intent para ingresar al perfil
                Intent intentPerfil = new Intent(this, ProfileActivity.class);
                startActivity(intentPerfil);
                break;

            case R.id.action_favorite:
                //intent para ingresar a favoritos
                Intent intentFavorite = new Intent(this, FavoriteActivity.class);
                startActivity(intentFavorite);
                break;

            default:
                break;
        }

        return true;
    }
    //Intent para mostrar ubicacion en mapa
    public void onClickMap(View view)
    {
        Intent intent=new Intent(this,LocationActivity.class);
        intent.putExtra("latitud",latitud.getText().toString());
        intent.putExtra("longitud",longitud.getText().toString());
        startActivity(intent);
    }

}
