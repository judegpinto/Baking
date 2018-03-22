package com.example.jp0517.baking.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jp0517.baking.R;
import com.example.jp0517.baking.StepActivity;
import com.example.jp0517.baking.player.BakingPlayer;
import com.example.jp0517.baking.recipe.Ingredient;
import com.example.jp0517.baking.recipe.Recipe;
import com.example.jp0517.baking.recipe.Step;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import org.w3c.dom.Text;

import java.sql.Array;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jp0517 on 1/19/18.
 */

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> {

    private Context mContext;
    private ArrayList<Step> mSteps;
    private boolean mTwoPane;
    private StepClickCallback mStepCallback;

    public StepAdapter(Context context, boolean twoStep, StepClickCallback stepClickCallback) {
        mContext = context;
        mTwoPane = twoStep;
        mStepCallback = stepClickCallback;
    }

    public interface StepClickCallback {
        void stepClicked(int pos);
    }

    public void setSteps(ArrayList<Step> steps) {
        mSteps = steps;
        notifyDataSetChanged();
    }

    @Override
    public StepAdapter.StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.step_card,parent,false);
        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepViewHolder holder, int position) {
        Step currentStep = mSteps.get(position);
        String shortDescription = currentStep.getShortDescription();
        holder.stepDescription.setText(shortDescription);
        String thumbailURL = currentStep.getThumbnailURL();
        if(thumbailURL == null || thumbailURL.isEmpty()) {
            holder.mImageView.setVisibility(View.GONE);
        } else {
            Glide.with(mContext)
                    .load(thumbailURL)
                    .into(holder.mImageView);
        }
    }

    @Override
    public int getItemCount() {
        if(mSteps == null) {return 0;}
        return mSteps.size();
    }

    class StepViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.step_name) TextView stepDescription;
        @BindView(R.id.thumbnail_image) ImageView mImageView;

        StepViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.card_view_step)
        void showStep() {
            int pos = getAdapterPosition();
            if(!mTwoPane) {
                Intent intent = new Intent(mContext, StepActivity.class);
                intent.putExtra(Recipe.STEPS, mSteps);
                intent.putExtra(Step.ID, pos);
                mContext.startActivity(intent);
            } else {
                mStepCallback.stepClicked(pos);
            }
        }
    }
}
