package com.example.jp0517.baking.recipe;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jp0517 on 1/15/18.
 */

public class Ingredient implements Parcelable {

    public static final String QUANTITY = "quantity";
    public static final String MEASURE = "measure";
    public static final String INGREDIENT = "ingredient";

    private float mQuantity;
    private String mMeasure;
    private String mIngredient;

    public Ingredient(int quantity,
                      String measure,
                      String ingredient) {
        mQuantity = quantity;
        mMeasure = measure;
        mIngredient = ingredient;
    }

    public float getQuantity() {
        return mQuantity;
    }

    public String getMeasure() {
        return mMeasure;
    }

    public String getIngredient() {
        return mIngredient;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(mQuantity);
        dest.writeString(mMeasure);
        dest.writeString(mIngredient);
    }

    public Ingredient(Parcel in) {
        mQuantity = in.readInt();
        mMeasure = in.readString();
        mIngredient = in.readString();
    }


    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };
}
