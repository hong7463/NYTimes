package com.haisenhong.nytimes.data.responses;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hison7463 on 10/20/16.
 */

public class Headline implements Parcelable {

    private String main;
    private String content_kicker;
    private String kicker;

    public Headline() {
    }

    public Headline(String main, String content_kicker, String kicker) {
        this.main = main;
        this.content_kicker = content_kicker;
        this.kicker = kicker;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getContent_kicker() {
        return content_kicker;
    }

    public void setContent_kicker(String content_kicker) {
        this.content_kicker = content_kicker;
    }

    public String getKicker() {
        return kicker;
    }

    public void setKicker(String kicker) {
        this.kicker = kicker;
    }

    protected Headline(Parcel in) {
        this.main = in.readString();
        this.content_kicker = in.readString();
        this.kicker = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.main);
        dest.writeString(this.content_kicker);
        dest.writeString(this.kicker);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Headline> CREATOR = new Creator<Headline>() {
        @Override
        public Headline createFromParcel(Parcel in) {
            return new Headline(in);
        }

        @Override
        public Headline[] newArray(int size) {
            return new Headline[size];
        }
    };

    @Override
    public String toString() {
        return "Headline{" +
                "main='" + main + '\'' +
                ", content_kicker='" + content_kicker + '\'' +
                ", kicker='" + kicker + '\'' +
                '}';
    }
}
