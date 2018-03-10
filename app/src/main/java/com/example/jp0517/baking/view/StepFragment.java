package com.example.jp0517.baking.view;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by jp0517 on 1/21/18.
 */

public class StepFragment extends Fragment implements Player.EventListener {

    @BindView(R.id.long_description) TextView mLongDescription;
    @BindView(R.id.video_view) SimpleExoPlayerView mVideoView;

    String mVideoUrl;
    BakingPlayer mBakingPlayer;

    public static final String VIDEO_POSITION = "video_position";
    long mPlayPosition;

    public StepFragment() {}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mBakingPlayer = new BakingPlayer(context, this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null) {
            if(savedInstanceState.containsKey(VIDEO_POSITION)) {
                mPlayPosition = savedInstanceState.getLong(VIDEO_POSITION);
            } else {
                mPlayPosition = 0;
            }
        }
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
            mBakingPlayer.getPlayer().seekTo(mPlayPosition);
        }
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBakingPlayer.stopAndReleasePlayer();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPlayPosition = mBakingPlayer.getPlayer().getCurrentPosition();
        outState.putLong(VIDEO_POSITION, mPlayPosition);
    }

    /**
     * ExoPlayer
     */

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

    }

    @Override
    public void onRepeatModeChanged(int repeatMode) {

    }

    @Override
    public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity(int reason) {

    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    @Override
    public void onSeekProcessed() {

    }

    private void loadStepData() {
        int current = getArguments().getInt(Step.ID);
        if(current >= 0) {
            ArrayList<Step> steps = getArguments().getParcelableArrayList(Recipe.STEPS);
            Step currentStep = steps.get(current);
            setLongDescription(currentStep.getLongDescription());
            mVideoUrl = currentStep.getVideoURL();
        }
    }

    public void setLongDescription(String ld) {
        mLongDescription.setText(ld);
    }
}
