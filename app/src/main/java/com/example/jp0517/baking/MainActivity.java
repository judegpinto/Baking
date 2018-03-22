package com.example.jp0517.baking;

import android.os.Parcelable;
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
import com.example.jp0517.baking.utilities.QueryTask;
import com.example.jp0517.baking.view.RecipeAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements QueryTask.QueryListener {

    private String TAG = getClass().getSimpleName();

    public RecipeAdapter mAdapter;
    private Parcelable mState;
    @BindView(R.id.rv_recipes) RecyclerView mRecyclerView;
    @BindView(R.id.progress) ProgressBar mProgress;
    @BindView(R.id.error_message) LinearLayout mErrorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new RecipeAdapter(this);
        ButterKnife.bind(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mProgress = (ProgressBar) findViewById(R.id.progress);
        mErrorMessage = (LinearLayout) findViewById(R.id.error_message);

        showProgress();
        new QueryTask(this).execute(getString(R.string.recipe_url));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable("state", mRecyclerView.getLayoutManager().onSaveInstanceState());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState.containsKey("state")) {
            mState = savedInstanceState.getParcelable("state");
        }
    }

    private void showProgress()
    {
        mProgress.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessage.setVisibility(View.INVISIBLE);
    }

    private void showRecipes() {
        mProgress.setVisibility(View.INVISIBLE);
        mErrorMessage.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mProgress.setVisibility(View.INVISIBLE);
        mErrorMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void onQueryFinished(String response) {
        if(response == null) {
            showErrorMessage();
            return;
        }
        Log.d(TAG,response);
        Recipe[] recipes = BakingRecipeResponse.parseJSON(response);

        mAdapter.setRecipes(recipes);

        if(mState != null) {
            mRecyclerView.getLayoutManager().onRestoreInstanceState(mState);
            mRecyclerView.getAdapter().notifyDataSetChanged();
        }

        showRecipes();
    }
}
