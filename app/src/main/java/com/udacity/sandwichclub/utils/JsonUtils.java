package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class JsonUtils {


    public static Sandwich parseSandwichJson(String json) throws JSONException {


        JSONObject sandwichObject = new JSONObject(json);
        JSONObject jObjectName = sandwichObject.getJSONObject("name");

        String name = jObjectName.getString("mainName");
        String image = sandwichObject.getString("image");





        return new Sandwich(name, image);
    }


}
