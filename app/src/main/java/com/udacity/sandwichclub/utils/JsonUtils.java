package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONException;
import org.json.JSONObject;


public final class JsonUtils {

    private static final String TAG = "JsonUtils";


    public static Sandwich parseSandwichJson(String json) throws JSONException {


        JSONObject sandwichObject = new JSONObject(json);
        JSONObject jObjectName = sandwichObject.getJSONObject("name");

        String name = jObjectName.getString("mainName");
        String image = sandwichObject.getString("image");
        String origin = sandwichObject.getString("placeOfOrigin");
        String description = sandwichObject.getString("description");



        return new Sandwich(name, image, origin, description);
    }


}
