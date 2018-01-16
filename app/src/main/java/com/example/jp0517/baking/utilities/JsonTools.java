package com.example.jp0517.baking.utilities;

import com.example.jp0517.baking.recipe.Ingredient;
import com.example.jp0517.baking.recipe.Recipe;
import com.example.jp0517.baking.recipe.Step;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jp0517 on 1/15/18.
 */

public final class JsonTools {

    public static int getJSONObjectNum(String unparsed) {
        int numObjects = 0;

        try {
            JSONArray array = new JSONArray(unparsed);
            return array.length();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return numObjects;
    }

    public static Recipe[] getRecipesFromJSON(String unparsed) {
        try {
            JSONArray recipeArray = new JSONArray(unparsed);
            int numRecipes = recipeArray.length();
            Recipe[] recipes = new Recipe[numRecipes];
            for(int i = 0; i < numRecipes; i++) {
                JSONObject recipeInfo = recipeArray.getJSONObject(i);
                Recipe recipe = new Recipe(
                        recipeInfo.getInt(Recipe.ID),
                        recipeInfo.getString(Recipe.NAME),
                        getIngredientsFromJSON(recipeInfo.getString(Recipe.INGREDIENTS)),
                        getStepsFromJSON(recipeInfo.getString(Recipe.STEPS)),
                        recipeInfo.getInt(Recipe.SERVINGS),
                        recipeInfo.getString(Recipe.IMAGE)
                );
                recipes[i] = recipe;
            }
            return recipes;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Ingredient[] getIngredientsFromJSON(String jsonIngredients) {
        try {
            JSONArray ingredientArray = new JSONArray(jsonIngredients);
            int numIngredients = ingredientArray.length();
            Ingredient[] ingredients = new Ingredient[numIngredients];
            for (int i = 0; i < ingredientArray.length(); i++) {
                JSONObject ingredientInfo = ingredientArray.getJSONObject(i);
                Ingredient ingredient = new Ingredient(
                        ingredientInfo.getInt(Ingredient.QUANTITY),
                        ingredientInfo.getString(Ingredient.MEASURE),
                        ingredientInfo.getString(Ingredient.INGREDIENT)
                );
                ingredients[i] = ingredient;
            }
            return ingredients;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Step[] getStepsFromJSON(String jsonSteps) {
        try {
            JSONArray stepArray = new JSONArray(jsonSteps);
            int numSteps = stepArray.length();
            Step[] steps = new Step[numSteps];
            for (int i = 0; i < stepArray.length(); i++) {
                JSONObject stepInfo = stepArray.getJSONObject(i);
                Step step = new Step(
                        stepInfo.getInt(Step.ID),
                        stepInfo.getString(Step.SHORT_DESCRIPTION),
                        stepInfo.getString(Step.LONG_DESCRIPTION),
                        stepInfo.getString(Step.VIDEO_URL),
                        stepInfo.getString(Step.THUMBNAIL_URL)
                );
                steps[i] = step;
            }
            return steps;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
