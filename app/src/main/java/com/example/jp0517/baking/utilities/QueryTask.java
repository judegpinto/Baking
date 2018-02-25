package com.example.jp0517.baking.utilities;

import android.os.AsyncTask;
import android.util.Log;

import com.example.jp0517.baking.recipe.BakingRecipeResponse;
import com.example.jp0517.baking.recipe.Recipe;

/**
 * Created by jp0517 on 2/24/18.
 */

public class QueryTask extends AsyncTask<String,Void,String> {

    private QueryListener mQueryListener;

    public QueryTask(QueryListener queryListener) {
        mQueryListener = queryListener;
    }

    public interface QueryListener {
        void onQueryFinished(String response);
    }

    @Override
    protected String doInBackground(String... params) {
        return NetworkUtils.queryRecipes(params[0]);
    }

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        mQueryListener.onQueryFinished(response);
    }
}
