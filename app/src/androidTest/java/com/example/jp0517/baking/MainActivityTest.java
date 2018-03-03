package com.example.jp0517.baking;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

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
        onView(withId(R.id.name)).check(matches(withText("Nutella Pie")));
    }

    @Test
    public void testClickOnLastRecipe() {
        int lastPos = mMainActivityTestRule.getActivity().mAdapter.getItemCount()-1;
        onView(withId(R.id.rv_recipes))
                .perform(RecyclerViewActions.scrollToPosition(lastPos))
                .perform(RecyclerViewActions.actionOnItemAtPosition(lastPos, click()));
        onView(withId(R.id.name)).check(matches(withText("Cheesecake")));
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
