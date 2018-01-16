package com.example.jp0517.baking.recipe;

/**
 * Created by jp0517 on 1/15/18.
 */

public class Recipe {

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String INGREDIENTS = "ingredients";
    public static final String STEPS = "steps";
    public static final String SERVINGS = "servings";
    public static final String IMAGE = "image";

    private int mId;
    private String mName;
    private Ingredient[] mIngredients;
    private Step[] mSteps;
    private int mServings;
    private String mImageLink;

    public Recipe(int id,
                  String name,
                  Ingredient[] ingredients,
                  Step[] steps,
                  int servings,
                  String imageLink) {
        mId = id;
        mName = name;
        mIngredients = ingredients;
        mSteps = steps;
        mServings = servings;
        mImageLink = imageLink;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public Ingredient[] getIngredients() {
        return mIngredients;
    }

    public Step[] getSteps() {
        return mSteps;
    }

    public int getServings() {
        return mServings;
    }

    public String getImageLink() {
        return mImageLink;
    }
}
