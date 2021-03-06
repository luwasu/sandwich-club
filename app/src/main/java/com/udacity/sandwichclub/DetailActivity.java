package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private ImageView ingredientsIv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        intent();
        fetchSandwichDetails();
        setupDefaultUIFramework();
        populateUIDetailInfo();

    }

    private Sandwich fetchSandwichDetails() {
        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[intent()];
        Sandwich sandwich = null;
        sandwich = JsonUtils.parseSandwichJson(json);
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

    // TODO Add this to the populateUIDetailInfo???
    private void setupDefaultUIFramework() {

        // Set Image View in UI
        ingredientsIv = findViewById(R.id.image_iv);
        Picasso.with(this)
                .load(fetchSandwichDetails().getImage())
                .into(ingredientsIv);

        // Set UI Title
        setTitle(fetchSandwichDetails().getMainName());
    }

    private void populateUIDetailInfo() {

        TextView originTv = findViewById(R.id.origin_tv);
        originTv.setText(fetchSandwichDetails().getPlaceOfOrigin());

        TextView descriptionTv = findViewById(R.id.description_tv);
        descriptionTv.setText(fetchSandwichDetails().getDescription());

        TextView alsoKnownAsTv = findViewById(R.id.also_known_tv);
        alsoKnownAsTv.setText(TextUtils.join(", ", fetchSandwichDetails().getAlsoKnownAs()));

        TextView ingredientsTv = findViewById(R.id.ingredients_tv);;
        ingredientsTv.setText(TextUtils.join(", ", fetchSandwichDetails().getIngredients()));
    }
}
