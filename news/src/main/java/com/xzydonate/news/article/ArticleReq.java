package com.xzydonate.news.article;

public class ArticleReq {

    private int cid;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public ArticleReq() {
    }

    public ArticleReq(int cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "ArticleReq{" +
                "cid=" + cid +
                '}';
    }
}
