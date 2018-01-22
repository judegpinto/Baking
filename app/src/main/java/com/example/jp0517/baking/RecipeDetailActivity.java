package com.example.jp0517.baking;

import android.content.Intent;
import android.media.Image;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jp0517.baking.recipe.Ingredient;
import com.example.jp0517.baking.recipe.Recipe;
import com.example.jp0517.baking.view.StepAdapter;

import java.util.ArrayList;

public class RecipeDetailActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private StepAdapter mStepAdapter;

    private TextView mName;
    private ImageView mImage;
    private TextView mServings;
    private TextView mIngredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_view);

        Intent intent = getIntent();
        Recipe recipe = intent.getExtras().getParcelable(Recipe.RECIPE);

        mName = (TextView) findViewById(R.id.name);
        mImage = (ImageView) findViewById(R.id.image);
        mServings = (TextView) findViewById(R.id.servings);
        mIngredients = (TextView) findViewById(R.id.ingredients);

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

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_steps);
        mStepAdapter = new StepAdapter(this);

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
}
