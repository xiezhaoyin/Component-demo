package com.xzydonate.news.project;

public class ProjectReq {

    private int cid;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public ProjectReq() {
    }

    public ProjectReq(int cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "ArticleReq{" +
                "cid=" + cid +
                '}';
    }
}
