package com.example.jp0517.baking;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.jp0517.baking.recipe.Recipe;
import com.example.jp0517.baking.recipe.Step;
import com.example.jp0517.baking.view.StepFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jp0517 on 1/21/18.
 */

public class StepActivity extends AppCompatActivity {

    private ArrayList<Step> mSteps;
    private int currentStep;
    @BindView(R.id.previous) Button previousButton;
    @BindView(R.id.next) Button nextButton;
    private FragmentManager mFragmentManager;
    Intent mIntent;

    public static final String STEP_FRAG_TAG = "step_fragment_tag";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        ButterKnife.bind(this);

        mIntent = getIntent();
        mSteps = mIntent.getParcelableArrayListExtra(Recipe.STEPS);
        currentStep = mIntent.getIntExtra(Step.ID, 0);

        if(savedInstanceState == null) {
            StepFragment stepFragment = new StepFragment();
            stepFragment.setArguments(getIntent().getExtras());

            mFragmentManager= getSupportFragmentManager();

            mFragmentManager.beginTransaction()
                    .add(R.id.step_container,stepFragment, STEP_FRAG_TAG)
                    .commit();
        }
    }

    @OnClick(R.id.next)
    public void next() {
        if(currentStep < (mSteps.size() - 1)) {
            currentStep += 1;

            StepFragment stepFragment = new StepFragment();
            mIntent.putExtra(Step.ID,currentStep);
            stepFragment.setArguments(mIntent.getExtras());
            mFragmentManager.beginTransaction()
                    .replace(R.id.step_container,stepFragment)
                    .commit();
        }
    }

    @OnClick(R.id.previous)
    public void previous() {
        if(currentStep > 0) {
            currentStep -= 1;

            StepFragment stepFragment = new StepFragment();
            mIntent.putExtra(Step.ID,currentStep);
            stepFragment.setArguments(mIntent.getExtras());
            mFragmentManager.beginTransaction()
                    .replace(R.id.step_container,stepFragment)
                    .commit();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
