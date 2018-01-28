package com.example.jp0517.baking;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.jp0517.baking.recipe.BakingRecipeResponse;
import com.example.jp0517.baking.recipe.Recipe;
import com.example.jp0517.baking.utilities.JsonTools;
import com.example.jp0517.baking.utilities.NetworkUtils;
import com.example.jp0517.baking.view.RecipeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {

    public RecipeAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgress;
    private LinearLayout mErrorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new RecipeAdapter(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mProgress = (ProgressBar) findViewById(R.id.progress);
        mErrorMessage = (LinearLayout) findViewById(R.id.error_message);

        showProgress();
        new QueryTask().execute(getString(R.string.recipe_url));
    }

    private void showProgress()
    {
        mProgress.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessage.setVisibility(View.INVISIBLE);
    }

    private void showRecipes() {
        mProgress.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mProgress.setVisibility(View.INVISIBLE);
        mErrorMessage.setVisibility(View.VISIBLE);
    }

    private class QueryTask extends AsyncTask<String,Void,String> {
        private String TAG = getClass().getSimpleName();

        @Override
        protected String doInBackground(String... params) {
            return NetworkUtils.queryRecipes(params[0]);
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            if(response == null) {
                showErrorMessage();
                return;
            }
            Log.d(TAG,response);
            Recipe[] recipes = BakingRecipeResponse.parseJSON(response);

            mAdapter.setRecipes(recipes);

            showRecipes();
        }
    }
}
