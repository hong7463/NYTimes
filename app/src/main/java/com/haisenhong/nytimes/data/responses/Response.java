package com.haisenhong.nytimes.data.responses;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

/**
 * Created by hison7463 on 10/21/16.
 */

public class Response implements Parcelable {

    private Doc[] docs;

    public Response() {
    }

    public Response(Doc[] docs) {
        this.docs = docs;
    }

    protected Response(Parcel in) {
        docs = in.createTypedArray(Doc.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedArray(docs, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Response> CREATOR = new Creator<Response>() {
        @Override
        public Response createFromParcel(Parcel in) {
            return new Response(in);
        }

        @Override
        public Response[] newArray(int size) {
            return new Response[size];
        }
    };

    public Doc[] getDocs() {
        return docs;
    }

    public void setDocs(Doc[] docs) {
        this.docs = docs;
    }

    @Override
    public String toString() {
        return "Response{" +
                "docs=" + Arrays.toString(docs) +
                '}';
    }

    //    private Doc[] docs;
//
//    public ArticleSearchResponse() {
//    }
//
//    public ArticleSearchResponse(Doc[] docs) {
//        this.docs = docs;
//    }
//
//    public Doc[] getDocs() {
//        return docs;
//    }
//
//    public void setDocs(Doc[] docs) {
//        this.docs = docs;
//    }
//
//    protected ArticleSearchResponse(Parcel in) {
//
//        Parcelable[] parcelables = in.readParcelableArray(Doc.class.getClassLoader());
//        if(parcelables != null) {
//            this.docs = Arrays.copyOf(parcelables, parcelables.length, Doc[].class);
//        }
//    }
//
//    public static final Creator<ArticleSearchResponse> CREATOR = new Creator<ArticleSearchResponse>() {
//        @Override
//        public ArticleSearchResponse createFromParcel(Parcel in) {
//            return new ArticleSearchResponse(in);
//        }
//
//        @Override
//        public ArticleSearchResponse[] newArray(int size) {
//            return new ArticleSearchResponse[size];
//        }
//    };
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeParcelableArray(this.docs, flags);
//    }
}
