package com.example.jp0517.baking.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jp0517.baking.R;
import com.example.jp0517.baking.recipe.Recipe;
import com.example.jp0517.baking.recipe.Step;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by jp0517 on 1/21/18.
 */

public class StepFragment extends Fragment {

    @BindView(R.id.number) TextView mNumber;
    @BindView(R.id.short_description) TextView mShortDescription;
    @BindView(R.id.long_description) TextView mLongDescription;

    public StepFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_step,container,false);
        ButterKnife.bind(this, rootView);
        loadStepData();

        return rootView;
    }

    private void loadStepData() {
        int current = getArguments().getInt(Step.ID);
        setNumberText(current);
        ArrayList<Step> steps = getArguments().getParcelableArrayList(Recipe.STEPS);
        Step currentStep = steps.get(current);
        setShortDescription(currentStep.getShortDescription());
        setLongDescription(currentStep.getLongDescription());
    }

    public void setNumberText(int number) {
        mNumber.setText("Step #" + (number+1));
    }

    public void setShortDescription(String sd) {
        mShortDescription.setText(sd);
    }

    public void setLongDescription(String ld) {
        mLongDescription.setText(ld);
    }
}
