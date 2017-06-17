package com.example.lluismasdeu.pprog2_p_final.activities;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.example.lluismasdeu.pprog2_p_final.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class LocationActivity extends AppCompatActivity implements OnMapReadyCallback {
    double latitude;
    double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        getSupportActionBar().setTitle("");

        // Recuperamos latitud y longitud del Intent.
        latitude = getIntent().getExtras().getDouble(DescriptionActivity.LATITUDE_EXTRA);
        longitude = getIntent().getExtras().getDouble(DescriptionActivity.LONGITUDE_EXTRA);

        MapFragment mapFragment = MapFragment.newInstance();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, mapFragment);
        transaction.commit();

        mapFragment.getMapAsync(this);

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
                Intent intentFavorite = new Intent(this, FavoritesActivity.class);
                startActivity(intentFavorite);
                break;

            default:
                break;
        }

        return true;
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        LatLng restaurant = new LatLng(latitude, longitude);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(restaurant, 20.0f);
        googleMap.moveCamera(cameraUpdate);

        MarkerOptions markerOptions = new MarkerOptions().position(restaurant);
        googleMap.addMarker(markerOptions);
    }
}
