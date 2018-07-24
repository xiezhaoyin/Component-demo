package com.xzydonate.news;

public class NewsReq {

    private int cid;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public NewsReq() {
    }

    public NewsReq(int cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "NewsReq{" +
                "cid=" + cid +
                '}';
    }
}
