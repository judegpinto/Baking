package com.example.jp0517.baking.recipe;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by jp0517 on 1/15/18.
 */
public class Recipe implements Parcelable {

    public static final String RECIPE = "recipe";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String INGREDIENTS = "ingredients";
    public static final String STEPS = "steps";
    public static final String SERVINGS = "servings";
    public static final String IMAGE = "image";

    @SerializedName(ID)
    private int mId;
    @SerializedName(NAME)
    private String mName;
    @SerializedName(INGREDIENTS)
    private ArrayList<Ingredient> mIngredients;
    @SerializedName(STEPS)
    private ArrayList<Step> mSteps;
    @SerializedName(SERVINGS)
    private int mServings;
    @SerializedName(IMAGE)
    private String mImageLink;

    public Recipe(int id,
                  String name,
                  ArrayList<Ingredient> ingredients,
                  ArrayList<Step> steps,
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

    public ArrayList<Ingredient> getIngredients() {
        return mIngredients;
    }

    public ArrayList<Step> getSteps() {
        return mSteps;
    }

    public int getServings() {
        return mServings;
    }

    public String getImageLink() {
        return mImageLink;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
        dest.writeTypedList(mIngredients);
        dest.writeTypedList(mSteps);
        dest.writeInt(mServings);
        dest.writeString(mImageLink);
    }

    public Recipe(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mIngredients = new ArrayList<>();
        mSteps = new ArrayList<>();
        in.readTypedList(mIngredients,Ingredient.CREATOR);
        in.readTypedList(mSteps,Step.CREATOR);
        mServings = in.readInt();
        mImageLink = in.readString();
    }


    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}
