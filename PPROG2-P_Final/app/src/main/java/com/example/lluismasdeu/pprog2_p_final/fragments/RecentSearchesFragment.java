package com.example.lluismasdeu.pprog2_p_final.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lluismasdeu.pprog2_p_final.R;
import com.example.lluismasdeu.pprog2_p_final.activities.ResultsActivity;
import com.example.lluismasdeu.pprog2_p_final.activities.SearchActivity;
import com.example.lluismasdeu.pprog2_p_final.adapters.RecentSearchesAdapter;
import com.example.lluismasdeu.pprog2_p_final.utils.HttpRequestHelper;

import org.json.JSONArray;

import java.util.List;

/**
 * Fragment encargado de gestionar la lista de búsquedas recientes.
 * @author Eloy Alberto López
 * @author Lluís Masdeu
 */
public class RecentSearchesFragment extends Fragment {
    private Context context;
    private RecentSearchesAdapter adapter;
    private List<String> recentSearchesList;
    private EditText searchEditText;
    private SeekBar radiusSeekBar;
    private TextView radiusKmTextView;
    private ListView recentSearchesListView;
    private int radiusKm;

    // Clase encargada de peticiones asíncronas.
    private class AsyncRequest extends AsyncTask<String, Void, JSONArray> {
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
        protected JSONArray doInBackground(String... params) {
            return HttpRequestHelper.getInstance().doHttpRequest(params[0]);
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            super.onPostExecute(jsonArray);

            if (progressDialog.isShowing())
                progressDialog.dismiss();

            manageActivityTransition(jsonArray.toString(), jsonArray.length());
        }
    }

    /**
     * Constructor de la clase.
     * @param context
     */
    public RecentSearchesFragment(Context context) {
        this.context = context;
    }

    /**
     * Método encargado de llevar a cabo las tareas al crear el Fragment.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return Vista.
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recent_searches, container, false);
        View originalView = container.getRootView();

        // Localizamos los componentes en el layout.
        searchEditText = (EditText) originalView.findViewById(R.id.search_editText);
        radiusSeekBar = (SeekBar) originalView.findViewById(R.id.radius_seekBar);
        radiusKmTextView = (TextView) originalView.findViewById(R.id.radius_textView);
        recentSearchesListView = (ListView) view.findViewById(R.id.recentSearches_listView);

        // Añadimos el adapter a la ListView
        recentSearchesListView.setAdapter(adapter);

        //Listener para listView
        recentSearchesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                new AsyncRequest(context).execute(HttpRequestHelper.getInstance()
                        .generateHTTPSearchRequest(recentSearchesList.get(i)));
            }
        });

        return view;
    }

    /**
     * Setter de la lista de búsquedas recientes.
     * @param recentSearchesList Lista de búsquedas recientes.
     */
    public void setRecentSearchesList(List<String> recentSearchesList) {
        this.recentSearchesList = recentSearchesList;

        adapter = new RecentSearchesAdapter(context, recentSearchesList);
    }

    /**
     * Método encargado de gestionar la transición hacia la actividad de resultados.
     * @param response Respuesta del Webservice.
     * @param numResults Número de resultados.
     */
    private void manageActivityTransition(String response, int numResults) {
        String[] messages = getResources().getStringArray(R.array.search_activity_messages);

        if (numResults == 0) {
            Toast.makeText(context, messages[1], Toast.LENGTH_SHORT).show();
        } else {
            // Mostramos mensaje
            Toast.makeText(context, messages[3], Toast.LENGTH_SHORT).show();

            // Reiniciamos la interfaz gráfica.
            resetFields();

            // Accedemos a la actividad de resultados.
            Intent intent = new Intent(context, ResultsActivity.class);
            intent.putExtra(SearchActivity.SEARCH_RESULT_EXTRA, response);
            startActivity(intent);
        }
    }

    /**
     * Método encargado de limpiar los campos.
     */
    private void resetFields() {
        searchEditText.setText("");
        radiusKm = 1;
        radiusSeekBar.setProgress(0);
        radiusKmTextView.setText(radiusKm + context.getString(R.string.radius_km));
    }
}
