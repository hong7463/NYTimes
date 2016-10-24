package com.haisenhong.nytimes.data.responses;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

/**
 * Created by hison7463 on 10/20/16.
 */

public class ArticleSearchResponse implements Parcelable {

    private Response response;
    private String status;

    public ArticleSearchResponse() {
    }

    public ArticleSearchResponse(Response response, String status) {
        this.response = response;
        this.status = status;
    }

    protected ArticleSearchResponse(Parcel in) {
        response = in.readParcelable(Response.class.getClassLoader());
        status = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(response, flags);
        dest.writeString(status);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ArticleSearchResponse> CREATOR = new Creator<ArticleSearchResponse>() {
        @Override
        public ArticleSearchResponse createFromParcel(Parcel in) {
            return new ArticleSearchResponse(in);
        }

        @Override
        public ArticleSearchResponse[] newArray(int size) {
            return new ArticleSearchResponse[size];
        }
    };

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ArticleSearchResponse{" +
                "response=" + response +
                ", status='" + status + '\'' +
                '}';
    }
}
