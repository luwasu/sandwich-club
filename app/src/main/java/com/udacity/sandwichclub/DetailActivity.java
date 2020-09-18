package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

public class DetailActivity extends AppCompatActivity {

    private ImageView ingredientsIv;

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        intent();
        fetchSandwichDetails();
        setupDefaultUIFramework();



    }

    private Sandwich fetchSandwichDetails() {
        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[intent()];
        Sandwich sandwich = null;
        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();

        }
        return sandwich;
    }

    private int intent() {

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();

        }
        return position;
    }


    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void setupDefaultUIFramework(){

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        populateUI();
        Picasso.with(this)
                .load(fetchSandwichDetails().getImage())
                .into(ingredientsIv);

        setTitle(fetchSandwichDetails().getMainName());

    }

    private void populateUI() {

    }
}
