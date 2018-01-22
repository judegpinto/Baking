package com.example.jp0517.baking.recipe;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jp0517 on 1/15/18.
 */

public class Step implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mShortDescription);
        dest.writeString(mLongDescription);
        dest.writeString(mVideoURL);
        dest.writeString(mThumbnailURL);
    }


    public Step(Parcel in) {
        mId = in.readInt();
        mShortDescription = in.readString();
        mLongDescription = in.readString();
        mVideoURL = in.readString();
        mThumbnailURL = in.readString();
    }


    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Step createFromParcel(Parcel in) {
            return new Step(in);
        }

        public Step[] newArray(int size) {
            return new Step[size];
        }
    };
}
