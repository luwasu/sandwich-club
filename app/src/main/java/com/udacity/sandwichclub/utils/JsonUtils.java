package com.udacity.sandwichclub.utils;

import android.content.res.Resources;
import android.util.Log;

import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public final class JsonUtils {

    private static final String TAG = "JsonUtils";
    private static final String JSON_KEY = "name";
    private static final String JSON_MAIN_NAME = "mainName";
    private static final String JSON_IMAGE = "image";
    private static final String JSON_ORIGIN = "placeOfOrigin";
    private static final String JSON_DESCRIPTION = "description";
    private static final String JSON_ARRAY_ALSO_KNOWN = "alsoKnownAs";
    private static final String JSON_ARRAY_INGREDIENTS = "ingredients";
    private static final String NO_OTHER_NAME = "No other name known";





    public static Sandwich parseSandwichJson(String json) {

        if (json == null) {
            return null;
        }

        try {
            JSONObject sandwichObject = new JSONObject(json);
            JSONObject jObjectName = sandwichObject.getJSONObject(JSON_KEY);

            String name = jObjectName.getString(JSON_MAIN_NAME);

            String image = sandwichObject.getString(JSON_IMAGE);
            String origin = sandwichObject.getString(JSON_ORIGIN);

            if (origin.equals("")) {
                origin = "Unknown";
            }


            String description = sandwichObject.getString(JSON_DESCRIPTION);


            JSONArray alsoKnownAsArray = jObjectName.optJSONArray(JSON_ARRAY_ALSO_KNOWN);
            List<String> alsoKnowAs = new ArrayList<>();

            if (alsoKnownAsArray.length() == 0) {
                alsoKnowAs.add(NO_OTHER_NAME);
            } else {
            }
            for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                String n = (String) alsoKnownAsArray.get(i);
                alsoKnowAs.add(n);
                Log.v(TAG, "also known as: " + n);
            }


            JSONArray ingredientsArray = sandwichObject.getJSONArray(JSON_ARRAY_INGREDIENTS);
            List<String> ingredients = new ArrayList<>();
            if (ingredientsArray != null) {
                for (int i = 0; i < ingredientsArray.length(); i++) {
                    String n = (String) ingredientsArray.get(i);
                    ingredients.add(n);
                    Log.v(TAG, "ingredients: " + n);
                }
            }

            return new Sandwich(name, image, origin, description, alsoKnowAs, ingredients);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
