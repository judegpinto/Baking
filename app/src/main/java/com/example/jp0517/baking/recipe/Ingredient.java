package com.example.jp0517.baking.recipe;

/**
 * Created by jp0517 on 1/15/18.
 */

public class Ingredient {

    public static final String QUANTITY = "quantity";
    public static final String MEASURE = "measure";
    public static final String INGREDIENT = "ingredient";

    private int mQuantity;
    private String mMeasure;
    private String mIngredient;

    public Ingredient(int quantity,
                      String measure,
                      String ingredient) {
        mQuantity = quantity;
        mMeasure = measure;
        mIngredient = ingredient;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public String getMeasure() {
        return mMeasure;
    }

    public String getIngredient() {
        return mIngredient;
    }
}
