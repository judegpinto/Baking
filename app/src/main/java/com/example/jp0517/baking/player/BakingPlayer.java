package com.example.jp0517.baking.player;

import android.content.Context;
import android.net.Uri;

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

/**
 * Created by jp0517 on 2/17/18.
 */
/*ExoPlayer wrapper
* initialize player FIRST
* use getExoPlayer to set to Video View
* call stopAndRelease from onDestroy
* */
public class BakingPlayer {

    private SimpleExoPlayer mExoPlayer;
    private Context mContext;

    public BakingPlayer(Context context) {
        mContext = context;
    }

    public SimpleExoPlayer getPlayer() {
        return mExoPlayer;
    }

    public void stopAndReleasePlayer() {
        if(mExoPlayer != null) {
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }

    //Exoplayer
    public void initializePlayer(Uri uri) {
        if(mExoPlayer == null) {
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(
                    new DefaultRenderersFactory(mContext),
                    new DefaultTrackSelector(),
                    new DefaultLoadControl());
            MediaSource mediaSource = getMediaSource(uri);
            mExoPlayer.prepare(mediaSource);
        }
    }

    private MediaSource getMediaSource(Uri uri) {
        DefaultHttpDataSourceFactory defaultHttpDataSourceFactory = new DefaultHttpDataSourceFactory(mContext.getPackageName());
        return new ExtractorMediaSource(
                uri,
                defaultHttpDataSourceFactory,
                new DefaultExtractorsFactory(),
                null,
                null);
    }


}
