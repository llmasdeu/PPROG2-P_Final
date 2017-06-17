package com.example.lluismasdeu.pprog2_p_final.activities;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lluismasdeu.pprog2_p_final.R;
import com.example.lluismasdeu.pprog2_p_final.adapters.CommentariesAdapter;
import com.example.lluismasdeu.pprog2_p_final.model.Commentary;
import com.example.lluismasdeu.pprog2_p_final.model.StaticValues;
import com.example.lluismasdeu.pprog2_p_final.repositories.DatabaseManagementInterface;
import com.example.lluismasdeu.pprog2_p_final.repositories.implementations.DatabaseManagement;

import java.util.ArrayList;
import java.util.List;

public class DescriptionActivity extends AppCompatActivity {
    private static final String TAG = "DescriptionActivity";
    public static final String LATITUDE_EXTRA = "latitude";
    public static final String LONGITUDE_EXTRA = "longitude";

    private DatabaseManagementInterface dbManagement;
    private ImageView pictureImageView;
    private FloatingActionButton favoriteFloatingActionButton;
    private TextView nameTextView;
    private TextView latitudeTextView;
    private TextView longitudeTextView;
    private TextView addressTextView;
    private TextView openingTextView;
    private TextView closingTextView;
    private RatingBar ratingRatingBar;
    private TextView descriptionTextView;
    private TextView nothingToShowTextView;
    private ListView commentariesListView;
    private EditText commentaryEditText;
    private List<Commentary> commentariesList;
    private CommentariesAdapter commentariesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_description);
        getSupportActionBar().setTitle("");

        // Localizamos los componentes en el Layout.
        pictureImageView = (ImageView) findViewById(R.id.restaurant_imageView);
        favoriteFloatingActionButton = (FloatingActionButton)
                findViewById(R.id.favorite_floatingActionButton);
        nameTextView = (TextView) findViewById(R.id.restaurantName_textView);
        latitudeTextView = (TextView) findViewById(R.id.latitude_textView);
        longitudeTextView = (TextView) findViewById(R.id.longitude_textView);
        addressTextView = (TextView) findViewById(R.id.address_textView);
        openingTextView = (TextView) findViewById(R.id.openingHour_textView);
        closingTextView = (TextView) findViewById(R.id.closingHour_textView);
        ratingRatingBar = (RatingBar) findViewById(R.id.restaurantRating_ratingBar);
        descriptionTextView = (TextView) findViewById(R.id.description_textView);
        nothingToShowTextView = (TextView) findViewById(R.id.nothingToShow_textView);
        commentariesListView = (ListView) findViewById(R.id.commentaries_listView);
        commentaryEditText = (EditText) findViewById(R.id.commentary_editText);

        // Iniciamos el conector con la base de datos.
        dbManagement = new DatabaseManagement(this);

        // Creamos el Adapter de la lista de comentarios.
        commentariesAdapter = new CommentariesAdapter(new ArrayList<Commentary>(), this);
        commentariesListView.setAdapter(commentariesAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        setRestaurantInfo();
        setFavoriteButtonColor();
        updateCommentariesList();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Mostramos actionBar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;

        switch (item.getItemId()) {
            case R.id.action_profile:
                intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                resetFields();
                break;

            case R.id.action_favorite:
                intent = new Intent(this, FavoritesActivity.class);
                startActivity(intent);
                resetFields();
                break;

            default:
                break;
        }

        return true;
    }

    public void onMapClick(View view) {
        Intent intent = new Intent(this, LocationActivity.class);
        intent.putExtra(LATITUDE_EXTRA,
                StaticValues.getInstance().getSelectedRestaurant().getLatitude());
        intent.putExtra(LONGITUDE_EXTRA,
                StaticValues.getInstance().getSelectedRestaurant().getLongitude());
        startActivity(intent);
        resetFields();
    }

    public void onAddFavoriteButtonClick(View view) {
        if (!dbManagement.existsFavorite(StaticValues.getInstance().getConnectedUser(),
                StaticValues.getInstance().getSelectedRestaurant())) {
            dbManagement
                    .registerFavorite(StaticValues.getInstance().getConnectedUser(),
                            StaticValues.getInstance().getSelectedRestaurant());
            favoriteFloatingActionButton.setBackgroundTintList(ColorStateList
                    .valueOf(getResources().getColor(R.color.colorPrimary)));
        } else {
            dbManagement
                    .unregisterFavorite(StaticValues.getInstance().getConnectedUser(),
                            StaticValues.getInstance().getSelectedRestaurant());
            favoriteFloatingActionButton.setBackgroundTintList(ColorStateList
                    .valueOf(getResources().getColor(R.color.errorMessageColor)));
        }
    }

    public void onClickSend(View view) {
        String[] messages = getResources().getStringArray(R.array.description_activity_messages);

        if (String.valueOf(commentaryEditText.getText()).equals("")) {
            Toast.makeText(this, messages[0], Toast.LENGTH_SHORT).show();
        } else {
            Commentary commentary = new Commentary(StaticValues.getInstance().getConnectedUser()
                    .getUsername(), String.valueOf(commentaryEditText.getText()));
            dbManagement.registerCommentary(commentary,
                    StaticValues.getInstance().getSelectedRestaurant());
            resetFields();

            Toast.makeText(this, messages[1], Toast.LENGTH_SHORT).show();
        }
    }

    private void setRestaurantInfo() {
        pictureImageView.setImageBitmap(StaticValues.getInstance().getSelectedRestaurant()
                .getImage());
        nameTextView.setText(StaticValues.getInstance().getSelectedRestaurant().getName());
        latitudeTextView.setText(""
                + StaticValues.getInstance().getSelectedRestaurant().getLatitude());
        longitudeTextView.setText(""
                + StaticValues.getInstance().getSelectedRestaurant().getLongitude());
        addressTextView.setText(StaticValues.getInstance().getSelectedRestaurant().getAddress());
        openingTextView.setText(StaticValues.getInstance().getSelectedRestaurant().getOpening());
        closingTextView.setText(StaticValues.getInstance().getSelectedRestaurant().getClosing());
        ratingRatingBar.setRating((float)
                StaticValues.getInstance().getSelectedRestaurant().getRating());

        if (StaticValues.getInstance().getSelectedRestaurant().getDescription() == null
                || StaticValues.getInstance().getSelectedRestaurant().getDescription().equals("")) {
            descriptionTextView.setVisibility(View.GONE);
        } else {
            descriptionTextView.setText(StaticValues.getInstance().getSelectedRestaurant()
                    .getDescription());
            descriptionTextView.setVisibility(View.VISIBLE);
        }
    }

    private void setFavoriteButtonColor() {
        if (dbManagement.existsFavorite(StaticValues.getInstance().getConnectedUser(),
                StaticValues.getInstance().getSelectedRestaurant())) {
            favoriteFloatingActionButton.setBackgroundTintList(ColorStateList
                    .valueOf(getResources().getColor(R.color.colorPrimary)));
        } else {
            favoriteFloatingActionButton.setBackgroundTintList(ColorStateList
                    .valueOf(getResources().getColor(R.color.errorMessageColor)));
        }
    }

    private void updateCommentariesList() {
        commentariesList = dbManagement.getAllComentaries(StaticValues.getInstance()
                .getSelectedRestaurant());

        if (commentariesList.size() >= 1) {
            nothingToShowTextView.setVisibility(View.GONE);
            commentariesListView.setVisibility(View.VISIBLE);

            commentariesAdapter.setCommentariesList(commentariesList);
            commentariesAdapter.notifyDataSetChanged();
        } else {
            nothingToShowTextView.setVisibility(View.VISIBLE);
            commentariesListView.setVisibility(View.GONE);
        }
    }

    private void resetFields() {
        commentaryEditText.setText("");
    }
}
