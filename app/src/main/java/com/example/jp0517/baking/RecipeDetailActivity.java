package com.example.jp0517.baking;

import android.content.Intent;
import android.media.Image;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jp0517.baking.recipe.Ingredient;
import com.example.jp0517.baking.recipe.Recipe;
import com.example.jp0517.baking.recipe.Step;
import com.example.jp0517.baking.view.StepAdapter;
import com.example.jp0517.baking.view.StepFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailActivity extends AppCompatActivity implements StepAdapter.StepClickCallback {

    @BindView(R.id.rv_steps) RecyclerView mRecyclerView;
    private StepAdapter mStepAdapter;

    @BindView(R.id.name) TextView mName;
    @BindView(R.id.image) ImageView mImage;
    @BindView(R.id.servings) TextView mServings;
    @BindView(R.id.ingredients) TextView mIngredients;

    private boolean mTwoPane;

    private Bundle mBundle;
    private StepFragment mStepFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_view);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Recipe recipe = intent.getExtras().getParcelable(Recipe.RECIPE);

        if(findViewById(R.id.step_container) != null) {
            mTwoPane = true;
            if(savedInstanceState == null) {

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
}
