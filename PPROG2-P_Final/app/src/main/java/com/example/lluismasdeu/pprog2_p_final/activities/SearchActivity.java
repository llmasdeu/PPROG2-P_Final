package com.example.lluismasdeu.pprog2_p_final.activities;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lluismasdeu.pprog2_p_final.R;
import com.example.lluismasdeu.pprog2_p_final.fragments.NoRecentSearchesFragment;
import com.example.lluismasdeu.pprog2_p_final.fragments.RecentSearchesFragment;
import com.example.lluismasdeu.pprog2_p_final.model.webserviceResults.Response;
import com.example.lluismasdeu.pprog2_p_final.model.webserviceResults.Result;
import com.example.lluismasdeu.pprog2_p_final.repositories.DatabaseManagementInterface;
import com.example.lluismasdeu.pprog2_p_final.repositories.implementations.DatabaseManagement;
import com.example.lluismasdeu.pprog2_p_final.utils.HttpRequestHelper;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Actividad de búsqueda de la aplicación.
 * @author Eloy Alberto López
 * @author Lluís Masdeu
 */
public class SearchActivity extends AppCompatActivity {
    private static final String SEARCH_RESULT_EXTRA = "search_result";
    private DatabaseManagementInterface dbManagement;
    private int radiusKm;
    private Response webserviceResponse;

    // Componentes y estructuras
    private EditText searchEditText;
    private ImageButton clearImageButton;
    private SeekBar radiusSeekBar;
    private TextView radiusKmTextView;
    private ListView recentSearchesListView;
    private List<String> recentSearchesList;
    private NoRecentSearchesFragment noRecentSearchesFragment;
    private RecentSearchesFragment recentSearchesFragment;

    private class AsyncRequest extends AsyncTask<String, Void, Response> {
        private Context context;
        private ProgressDialog progressDialog;

        public AsyncRequest(Context context) {
            this.context = context;

            progressDialog = new ProgressDialog(context);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage(getString(R.string.please_wait));
            progressDialog.show();
        }

        @Override
        protected Response doInBackground(String... params) {
            Gson gson = new Gson();

            return gson.fromJson(HttpRequestHelper.getInstance().doHttpRequest(params[0]),
                    Response.class);
        }

        @Override
        protected void onPostExecute(Response response) {
            super.onPostExecute(response);

            if (progressDialog.isShowing())
                progressDialog.dismiss();

            webserviceResponse = response;
        }
    }

    /**
     * Método encaragado de llevar a cabo las tareas iniciales cuando se crea la actividad.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);
        getSupportActionBar().setTitle("");

        // Localizamos los componentes en el layout.
        searchEditText = (EditText) findViewById(R.id.search_editText);
        clearImageButton = (ImageButton) findViewById(R.id.clear_imageButton);
        radiusSeekBar = (SeekBar) findViewById(R.id.radius_seekBar);
        radiusKmTextView = (TextView) findViewById(R.id.radius_textView);
        recentSearchesListView = (ListView) findViewById(R.id.recentSearches_listView);

        // Añadimos el listener a la SeekBar.
        radiusSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress >= 0 && progress <= 10) {
                    radiusKm = 1;
                } else if (progress > 10 && progress <= 20) {
                    radiusKm = 2;
                } else if (progress > 20 && progress <= 30) {
                    radiusKm = 3;
                } else if (progress > 30 && progress <= 40) {
                    radiusKm = 4;
                } else if (progress > 40 && progress <= 50) {
                    radiusKm = 5;
                } else if (progress > 50 && progress <= 60) {
                    radiusKm = 6;
                } else if (progress > 60 && progress <= 70) {
                    radiusKm = 7;
                } else if (progress > 70 && progress <= 80) {
                    radiusKm = 8;
                } else if (progress > 80 && progress <= 90) {
                    radiusKm = 9;
                } else {
                    radiusKm = 10;
                }

                radiusKmTextView.setText(radiusKm + getString(R.string.radius_km));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // Inicializamos el gestor de la base de datos.
        dbManagement = new DatabaseManagement(this);

        // Inicializamos los fragmentos.
        noRecentSearchesFragment = new NoRecentSearchesFragment();
        recentSearchesFragment = new RecentSearchesFragment(this, recentSearchesList);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Iniciamos a 0 la barra de progreso de la SeekBar, y configuramos el mensaje del radio a
        // 1 Km.
        radiusKm = 1;
        radiusSeekBar.setProgress(0);
        radiusKmTextView.setText(radiusKm + getString(R.string.radius_km));

        // Actualizamos la lista de búsquedas recientes.
        updateRecentSearchesList();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        //Mostramos actionBar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);

        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void onLocationSearchButtonClick(View view) {
        // TODO
    }

    public void onClearButtonClick(View view) {
        // Limpiamos el campo de búsqueda.
        searchEditText.setText("");
    }

    public void onSearchButtonClick(View view) {
        String[] messages = getResources().getStringArray(R.array.search_activity_messages);

        if (String.valueOf(searchEditText.getText()).equals("")) {
            Toast.makeText(this, messages[0], Toast.LENGTH_SHORT).show();
        } else {
            new AsyncRequest(this)
                    .execute(HttpRequestHelper.getInstance()
                            .generateHTTPSearchRequest(String.valueOf(searchEditText.getText())));

            if (webserviceResponse != null) {
                Toast.makeText(this, messages[1], Toast.LENGTH_SHORT).show();
            } else if (webserviceResponse.getResults().length == 0) {
                Toast.makeText(this, messages[1], Toast.LENGTH_SHORT).show();
            } else {
                // Registramos la búsqueda reciente.
                dbManagement.registerRecentSearch(String.valueOf(searchEditText.getText()));

                // Serializamos la respuesta.
                Gson gson = new Gson();
                String searchResult = gson.toJson(webserviceResponse).toString();

                // Mostramos mensaje
                Toast.makeText(this, messages[2], Toast.LENGTH_SHORT).show();

                // Reiniciamos la interfaz gráfica.
                resetFields();

                // Accedemos a la actividad de resultados.
                Intent intent = new Intent(this, RegisterActivity.class);
                intent.putExtra(SEARCH_RESULT_EXTRA, searchResult);
                startActivity(intent);
            }
        }
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

    private void updateRecentSearchesList() {
        recentSearchesList = dbManagement.getAllRecentSearches();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        if (recentSearchesList.isEmpty()) {
            transaction.replace(R.id.recentSearches_frameLayout, noRecentSearchesFragment);
        } else {
            transaction.replace(R.id.recentSearches_frameLayout, recentSearchesFragment);
        }

        transaction.commit();
    }

    private void resetFields() {
        searchEditText.setText("");
        radiusKm = 1;
        radiusSeekBar.setProgress(0);
        radiusKmTextView.setText(radiusKm + getString(R.string.radius_km));
    }
}
