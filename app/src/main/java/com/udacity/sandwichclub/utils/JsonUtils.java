package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public final class JsonUtils {

    private static final String TAG = "JsonUtils";

    public JSONArray alsoKnownAs;


    public static Sandwich parseSandwichJson(String json) {

        if (json == null) {
            return null;
        }

        try {
            JSONObject sandwichObject = new JSONObject(json);
            JSONObject jObjectName = sandwichObject.getJSONObject("name");

            String name = jObjectName.getString("mainName");
            String image = sandwichObject.getString("image");
            String origin = sandwichObject.getString("placeOfOrigin");
            String description = sandwichObject.getString("description");


            JSONArray alsoKnownAsArray = jObjectName.getJSONArray("alsoKnownAs");
            List<String> alsoKnowAs = new ArrayList<>();
            if (alsoKnownAsArray != null) {
                for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                    String n = (String) alsoKnownAsArray.get(i);
                    alsoKnowAs.add(n);
                    Log.v(TAG, "also known as: " + n);
                }
            }

            JSONArray ingredientsArray = sandwichObject.getJSONArray("ingredients");
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
