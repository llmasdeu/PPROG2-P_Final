package com.example.lluismasdeu.pprog2_p_final.activities;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.lluismasdeu.pprog2_p_final.R;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.Objects;

public class DescriptionActivity extends AppCompatActivity {
    JsonArrayRequest jsArrayRequest;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        textView=(TextView) findViewById(R.id.text_view);
        getSupportActionBar().setTitle("");
        final String nombre = getIntent().getStringExtra("name");
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
                                   //textView.setText(search.getJSONObject(i).getString("type"));

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
}
