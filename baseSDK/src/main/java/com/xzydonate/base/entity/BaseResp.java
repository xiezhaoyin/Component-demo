package com.xzydonate.base.entity;

public class BaseResp<T> {

    private boolean error;
    private T results;

    public BaseResp() {
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "BaseResp{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }
}
