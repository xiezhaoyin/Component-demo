package com.xzydonate.news.search;

public class KArticleReq {

    private String k;

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public KArticleReq(String k) {
        this.k = k;
    }

    @Override
    public String toString() {
        return "KArticleReq{" +
                "k='" + k + '\'' +
                '}';
    }
}
