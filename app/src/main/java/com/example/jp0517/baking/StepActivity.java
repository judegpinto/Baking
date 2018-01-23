package com.example.jp0517.baking;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.jp0517.baking.recipe.Recipe;
import com.example.jp0517.baking.recipe.Step;
import com.example.jp0517.baking.view.StepFragment;

import java.util.ArrayList;

/**
 * Created by jp0517 on 1/21/18.
 */

public class StepActivity extends AppCompatActivity {

    private ArrayList<Step> mSteps;
    private int currentStep;
    private Button previousButton;
    private Button nextButton;
    private FragmentManager mFragmentManager;
    Intent mIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        mIntent = getIntent();
        mSteps = mIntent.getParcelableArrayListExtra(Recipe.STEPS);
        currentStep = mIntent.getIntExtra(Step.ID, 0);

        StepFragment stepFragment = new StepFragment();
        stepFragment.setArguments(getIntent().getExtras());

        mFragmentManager= getSupportFragmentManager();

        mFragmentManager.beginTransaction()
                .add(R.id.step_container,stepFragment)
                .commit();

        previousButton = (Button) findViewById(R.id.previous);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });
        nextButton = (Button) findViewById(R.id.next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });
    }

}
