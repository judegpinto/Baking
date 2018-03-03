package com.example.jp0517.baking;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.jp0517.baking.recipe.Recipe;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.StringContains.containsString;

/**
 * Created by jp0517 on 3/3/18.
 */

@RunWith(AndroidJUnit4.class)
public class RecipeDetailActivityTest {
    @Rule
    public ActivityTestRule<RecipeDetailActivity> mRecipeDetailActivityTestRule
            = new ActivityTestRule<RecipeDetailActivity>(RecipeDetailActivity.class){
        @Override
        protected Intent getActivityIntent() {
            Intent intent = new Intent();
            intent.putExtra(Recipe.NAME, "Nutella Pie");
            return intent;
        }
    };

    @Test
    public void testClickOnStep() {
        onView(withId(R.id.rv_steps))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        onView(withId(R.id.long_description)).check(matches(withText("Recipe Introduction")));
    }

    @Test
    public void testAdvanceToNextStep() {
        onView(withId(R.id.rv_steps))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        onView(withId(R.id.next)).perform(click());
        onView(withId(R.id.long_description)).check(matches(withText(containsString("1"))));
    }


}
