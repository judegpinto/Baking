package com.example.jp0517.baking.utilities;

import android.os.AsyncTask;
import android.util.Log;

import com.example.jp0517.baking.MainActivity;
import com.example.jp0517.baking.recipe.Recipe;

/**
 * Created by jp0517 on 1/15/18.
 */

public class QueryTask extends AsyncTask<String,Void,String> {
    private String TAG = getClass().getSimpleName();

    @Override
    protected String doInBackground(String... params) {
        return NetworkUtils.queryRecipes(params[0]);
    }

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        Recipe[] recipes = JsonTools.getRecipesFromJSON(response);
        for(Recipe recipe:recipes) {
            Log.d(getClass().getSimpleName(), recipe.toString());
        }
        Log.d(TAG,response);
    }
}
