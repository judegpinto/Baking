package com.example.jp0517.baking;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by jp0517 on 3/3/18.
 */

@RunWith(AndroidJUnit4.class)
public class RecipeDetailActivityTest {
    @Rule
    public ActivityTestRule<RecipeDetailActivity> mRecipeDetailActivityTestRule
            = new ActivityTestRule<>(RecipeDetailActivity.class);

    @Test
    public void testClickOnStep() {

    }
}
