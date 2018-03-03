package com.example.jp0517.baking;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;

import com.example.jp0517.baking.recipe.Recipe;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by jp0517 on 3/3/18.
 */

public class MainActivityIntentTests {

    @Rule
    public IntentsTestRule<MainActivity> mMainActivityIntentTestRule = new IntentsTestRule<>(
            MainActivity.class);

    //Intent Stubbing
    @Before
    public void stubAllExternalIntents() {
        intending(not(isInternal())).respondWith(
                new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }

    @Test
    public void clickFirstRecipe() {
        onView(withId(R.id.rv_recipes))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        intended(allOf(
                hasComponent(RecipeDetailActivity.class.getName()),
                hasExtra(Recipe.RECIPE, mMainActivityIntentTestRule.getActivity().mAdapter.getRecipes()[0])));
    }
}
