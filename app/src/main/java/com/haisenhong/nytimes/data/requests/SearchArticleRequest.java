package com.haisenhong.nytimes.data.requests;

/**
 * Created by hison7463 on 10/20/16.
 */

public class SearchArticleRequest {

    private String q;
    private String fq;
    private String begin_date;
    private String end_date;
    private String sort;
    private int page;

    public SearchArticleRequest() {
    }

    public SearchArticleRequest(String q, String fq, String begin_date, String end_date, String sort, int page) {
        this.q = q;
        this.fq = fq;
        this.begin_date = begin_date;
        this.end_date = end_date;
        this.sort = sort;
        this.page = page;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getFq() {
        return fq;
    }

    public void setFq(String fq) {
        this.fq = fq;
    }

    public String getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(String begin_date) {
        this.begin_date = begin_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "SearchArticleRequest{" +
                "q='" + q + '\'' +
                ", fq='" + fq + '\'' +
                ", begin_date='" + begin_date + '\'' +
                ", end_date='" + end_date + '\'' +
                ", sort='" + sort + '\'' +
                ", page=" + page +
                '}';
    }
}
