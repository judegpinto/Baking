package com.example.jp0517.baking.view;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jp0517.baking.R;
import com.example.jp0517.baking.player.BakingPlayer;
import com.example.jp0517.baking.recipe.Recipe;
import com.example.jp0517.baking.recipe.Step;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by jp0517 on 1/21/18.
 */

public class StepFragment extends Fragment {

    @BindView(R.id.long_description) TextView mLongDescription;
    @BindView(R.id.video_view) SimpleExoPlayerView mVideoView;

    String mVideoUrl;
    BakingPlayer mBakingPlayer;

    public StepFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mBakingPlayer = new BakingPlayer(context);
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

        if(mVideoUrl != null && !mVideoUrl.isEmpty()) {
            mVideoView.setDefaultArtwork(BitmapFactory.decodeResource(getResources(), R.drawable.question_mark));
            mVideoView.setVisibility(View.VISIBLE);
            mBakingPlayer.initializePlayer(Uri.parse(mVideoUrl));
            mVideoView.setPlayer(mBakingPlayer.getPlayer());
        }

        return rootView;
    }

    private void loadStepData() {
        int current = getArguments().getInt(Step.ID);
        //setNumberText(current);
        ArrayList<Step> steps = getArguments().getParcelableArrayList(Recipe.STEPS);
        Step currentStep = steps.get(current);
        //setShortDescription(currentStep.getShortDescription());
        setLongDescription(currentStep.getLongDescription());
        mVideoUrl = currentStep.getVideoURL();
    }
/*
    public void setNumberText(int number) {
        mNumber.setText("Step #" + (number+1));
    }

    public void setShortDescription(String sd) {
        mShortDescription.setText(sd);
    }
*/

    public void setLongDescription(String ld) {
        mLongDescription.setText(ld);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBakingPlayer.stopAndReleasePlayer();
    }
}
