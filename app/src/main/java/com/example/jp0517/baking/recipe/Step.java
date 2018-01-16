package com.example.jp0517.baking.recipe;

/**
 * Created by jp0517 on 1/15/18.
 */

public class Step {

    public static final String ID = "id";
    public static final String SHORT_DESCRIPTION = "shortDescription";
    public static final String LONG_DESCRIPTION = "description";
    public static final String VIDEO_URL = "videoURL";
    public static final String THUMBNAIL_URL = "thumbnailURL";

    private int mId;
    private String mShortDescription;
    private String mLongDescription;
    private String mVideoURL;
    private String mThumbnailURL;

    public Step(int id,
                String shortDescription,
                String longDescription,
                String videoURL,
                String thumbnailURL) {
        mId = id;
        mShortDescription = shortDescription;
        mLongDescription = longDescription;
        mVideoURL = videoURL;
        mThumbnailURL = thumbnailURL;
    }

    public int getId() {
        return mId;
    }

    public String getShortDescription() {
        return mShortDescription;
    }

    public String getLongDescription() {
        return mLongDescription;
    }

    public String getVideoURL() {
        return mVideoURL;
    }

    public String getThumbnailURL() {
        return mThumbnailURL;
    }
}
