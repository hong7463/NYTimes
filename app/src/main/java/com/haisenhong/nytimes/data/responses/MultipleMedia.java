package com.haisenhong.nytimes.data.responses;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hison7463 on 10/20/16.
 */

public class MultipleMedia implements Parcelable {

    private int width;
    private String url;
    private int height;
    private String subtype;
    private String type;

    public MultipleMedia() {
    }

    public MultipleMedia(int width, String url, int height, String subtype, String type) {
        this.width = width;
        this.url = url;
        this.height = height;
        this.subtype = subtype;
        this.type = type;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    protected MultipleMedia(Parcel in) {
        this.width = in.readInt();
        this.url = in.readString();
        this.height = in.readInt();
        this.subtype = in.readString();
        this.type = in.readString();
    }

    public static final Creator<MultipleMedia> CREATOR = new Creator<MultipleMedia>() {
        @Override
        public MultipleMedia createFromParcel(Parcel in) {
            return new MultipleMedia(in);
        }

        @Override
        public MultipleMedia[] newArray(int size) {
            return new MultipleMedia[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(width);
        dest.writeString(url);
        dest.writeInt(height);
        dest.writeString(subtype);
        dest.writeString(type);
    }

    @Override
    public String toString() {
        return "MultipleMedia{" +
                "width=" + width +
                ", url='" + url + '\'' +
                ", height=" + height +
                ", subtype='" + subtype + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
