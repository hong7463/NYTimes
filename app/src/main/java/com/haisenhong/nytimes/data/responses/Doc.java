package com.haisenhong.nytimes.data.responses;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

/**
 * Created by hison7463 on 10/20/16.
 */

public class Doc implements Parcelable {

    private String web_url;
    private String snippet;
    private String lead_paragraph;
    private String source;
    private MultipleMedia[] multimedia;
    private Headline headline;
    private String pub_date;
    private String document_type;
    private String news_desk;
    private String section_name;
    private String subsection_name;

    public Doc() {
    }

    public Doc(String web_url, String snippet, String lead_paragraph, String source, MultipleMedia[] multipleMedia, Headline headline, String pub_date, String document_type, String news_desk, String section_name, String subsection_name) {
        this.web_url = web_url;
        this.snippet = snippet;
        this.lead_paragraph = lead_paragraph;
        this.source = source;
        this.multimedia = multipleMedia;
        this.headline = headline;
        this.pub_date = pub_date;
        this.document_type = document_type;
        this.news_desk = news_desk;
        this.section_name = section_name;
        this.subsection_name = subsection_name;
    }

    protected Doc(Parcel in) {
        this.web_url = in.readString();
        this.snippet = in.readString();
        this.lead_paragraph = in.readString();
        this.source = in.readString();

        Parcelable[] parcelables = in.readParcelableArray(MultipleMedia.class.getClassLoader());
        if(parcelables != null) {
            this.multimedia = Arrays.copyOf(parcelables, parcelables.length, MultipleMedia[].class);
        }

        this.headline = in.readParcelable(Headline.class.getClassLoader());
        this.pub_date = in.readString();
        this.document_type = in.readString();
        this.news_desk = in.readString();
        this.section_name = in.readString();
        this.subsection_name = in.readString();
    }

    public static final Creator<Doc> CREATOR = new Creator<Doc>() {
        @Override
        public Doc createFromParcel(Parcel in) {
            return new Doc(in);
        }

        @Override
        public Doc[] newArray(int size) {
            return new Doc[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.web_url);
        dest.writeString(this.snippet);
        dest.writeString(this.lead_paragraph);
        dest.writeString(this.source);
        dest.writeParcelableArray(this.getMultipleMedia(), flags);
        dest.writeParcelable(this.headline, flags);
        dest.writeString(this.pub_date);
        dest.writeString(this.document_type);
        dest.writeString(this.news_desk);
        dest.writeString(this.section_name);
        dest.writeString(this.subsection_name);
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getLead_paragraph() {
        return lead_paragraph;
    }

    public void setLead_paragraph(String lead_paragraph) {
        this.lead_paragraph = lead_paragraph;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public MultipleMedia[] getMultipleMedia() {
        return multimedia;
    }

    public void setMultipleMedia(MultipleMedia[] multipleMedia) {
        this.multimedia = multipleMedia;
    }

    public Headline getHeadline() {
        return headline;
    }

    public void setHeadline(Headline headline) {
        this.headline = headline;
    }

    public String getPub_date() {
        return pub_date;
    }

    public void setPub_date(String pub_date) {
        this.pub_date = pub_date;
    }

    public String getDocument_type() {
        return document_type;
    }

    public void setDocument_type(String document_type) {
        this.document_type = document_type;
    }

    public String getNews_desk() {
        return news_desk;
    }

    public void setNews_desk(String news_desk) {
        this.news_desk = news_desk;
    }

    public String getSection_name() {
        return section_name;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }

    public String getSubsection_name() {
        return subsection_name;
    }

    public void setSubsection_name(String subsection_name) {
        this.subsection_name = subsection_name;
    }

    @Override
    public String toString() {
        return "Doc{" +
                "web_url='" + web_url + '\'' +
                ", snippet='" + snippet + '\'' +
                ", lead_paragraph='" + lead_paragraph + '\'' +
                ", source='" + source + '\'' +
                ", multipleMedia=" + Arrays.toString(multimedia) +
                ", headline=" + headline +
                ", pub_date='" + pub_date + '\'' +
                ", document_type='" + document_type + '\'' +
                ", news_desk='" + news_desk + '\'' +
                ", section_name='" + section_name + '\'' +
                ", subsection_name='" + subsection_name + '\'' +
                '}';
    }
}
