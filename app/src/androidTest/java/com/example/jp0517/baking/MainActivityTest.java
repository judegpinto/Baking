package com.example.jp0517.baking;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.jp0517.baking.recipe.Recipe;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by jp0517 on 2/25/18.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule public ActivityTestRule<MainActivity> mMainActivityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testClickOnFirstRecipe() {
        onView(withId(R.id.rv_recipes))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        String recipeName = mMainActivityTestRule.getActivity().getString(R.string.first_recipe_name);
        onView(withId(R.id.name)).check(matches(withText(recipeName)));
    }

    @Test
    public void testClickOnLastRecipe() {
        int lastPos = mMainActivityTestRule.getActivity().mAdapter.getItemCount()-1;
        onView(withId(R.id.rv_recipes))
                .perform(RecyclerViewActions.scrollToPosition(lastPos))
                .perform(RecyclerViewActions.actionOnItemAtPosition(lastPos, click()));
        String recipeName = mMainActivityTestRule.getActivity().getString(R.string.last_recipe_name);
        onView(withId(R.id.name)).check(matches(withText(recipeName)));
    }

    @Test
    public void testOnBackPressed() {
        onView(withId(R.id.rv_recipes))
                .perform(RecyclerViewActions.scrollToPosition(0))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.name)).perform(pressBack());
        onView(withId(R.id.rv_recipes))
                .perform(RecyclerViewActions.scrollToPosition(0))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));;
    }
}
