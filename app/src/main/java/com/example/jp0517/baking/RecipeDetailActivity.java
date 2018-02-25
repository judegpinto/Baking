package com.example.jp0517.baking;

import android.content.Intent;
import android.media.Image;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jp0517.baking.recipe.BakingRecipeResponse;
import com.example.jp0517.baking.recipe.Ingredient;
import com.example.jp0517.baking.recipe.Recipe;
import com.example.jp0517.baking.recipe.Step;
import com.example.jp0517.baking.utilities.QueryTask;
import com.example.jp0517.baking.view.StepAdapter;
import com.example.jp0517.baking.view.StepFragment;
import com.example.jp0517.baking.widget.BakingWidgetProvider;
import com.example.jp0517.baking.widget.IngredientUpdateService;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailActivity extends AppCompatActivity
        implements StepAdapter.StepClickCallback, QueryTask.QueryListener {

    @BindView(R.id.rv_steps) RecyclerView mRecyclerView;
    private StepAdapter mStepAdapter;

    @BindView(R.id.name) TextView mName;
    @BindView(R.id.image) ImageView mImage;
    @BindView(R.id.servings) TextView mServings;
    @BindView(R.id.ingredients) TextView mIngredients;
    @BindView(R.id.detail_error_message) LinearLayout mErrorMessage;
    @BindView(R.id.detail_progress) ProgressBar mProgress;

    private boolean mTwoPane;

    private Bundle mBundle;
    private StepFragment mStepFragment;
    private Bundle mSavedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_view);
        ButterKnife.bind(this);

        mSavedInstanceState = savedInstanceState;

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Recipe recipe;
        if(extras.containsKey(Recipe.RECIPE)) {
            recipe = extras.getParcelable(Recipe.RECIPE);
            IngredientUpdateService.startActionUpdateIngredients(this,
                    recipe.getName(),
                    getIngredientsText(recipe.getIngredients()));
            completeInitUI(recipe);
        } else {
            String name = extras.getString(Recipe.NAME);
            mName.setText(name);
            showProgress();
            new QueryTask(this).execute(getString(R.string.recipe_url));
        }

    }

    private void completeInitUI(Recipe recipe) {
        if(findViewById(R.id.step_container) != null) {
            mTwoPane = true;
            if(mSavedInstanceState == null) {
                mStepFragment = new StepFragment();
                mBundle = new Bundle();
                mBundle.putParcelableArrayList(Recipe.STEPS, recipe.getSteps());
                mBundle.putInt(Recipe.ID, 0);
                mStepFragment.setArguments(mBundle);
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .add(R.id.step_container, mStepFragment)
                        .commit();
            }
        } else {
            mTwoPane = false;
        }

        mName.setText(recipe.getName());
        if(recipe.getImageLink().isEmpty()) {
            mImage.setVisibility(View.GONE);
        } else {
            Glide.with(this)
                    .load(recipe.getImageLink())
                    .into(mImage);
        }
        mServings.setText("Servings: " + recipe.getServings());
        mIngredients.setText(getIngredientsText(recipe.getIngredients()));

        mStepAdapter = new StepAdapter(this, mTwoPane, this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mStepAdapter);

        mStepAdapter.setSteps(recipe.getSteps());

        showRecipes();
    }

    private String getIngredientsText(ArrayList<Ingredient> ingredients) {
        StringBuilder builder = new StringBuilder();
        for(Ingredient ingredient: ingredients) {
            builder.append(ingredient.getIngredient()
                    + ": "
                    + ingredient.getQuantity()
                    + " "
                    + ingredient.getMeasure()
                    + "\n");
        }
        return builder.toString();
    }

    @Override
    public void stepClicked(int pos) {
        mStepFragment = new StepFragment();
        mBundle.putInt(Step.ID, pos);
        mStepFragment.setArguments(mBundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.step_container, mStepFragment)
                .commit();
    }

    @Override
    public void onQueryFinished(String response) {
        if(response == null) {
            showErrorMessage();
            return;
        }
        Recipe[] recipes = BakingRecipeResponse.parseJSON(response);

        Recipe recipe = getRecipeFromList(recipes);

        completeInitUI(recipe);
    }

    private Recipe getRecipeFromList(Recipe[] recipes) {
        for(Recipe eachRecipe:recipes) {
            String matchName = mName.getText().toString();
            if(eachRecipe.getName().equals(matchName)) {
                return eachRecipe;
            }
        }
        throw new NullPointerException("cannot find recipe in list");
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
}
