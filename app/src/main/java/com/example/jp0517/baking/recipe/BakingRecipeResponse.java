package com.example.jp0517.baking.recipe;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jp0517 on 1/28/18.
 */

public class BakingRecipeResponse {
    @SerializedName("recipes")
    Recipe[] mRecipes;

    public BakingRecipeResponse(Recipe[] recipes) {
        mRecipes = recipes;
    }

    public static Recipe[] parseJSON(String response) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(response, Recipe[].class);
    }

    public Recipe[] getRecipes() {
        return mRecipes;
    }
}
