package com.xzydonate.news.article;

import android.os.Bundle;

import com.xzydonate.basesdk.activity.BaseActivity;

import java.util.List;

public class ArticleActivity extends BaseActivity implements IArticleView {
    @Override
    public int createView() {
        return 0;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void resumeView() {

    }

    @Override
    public void destroyView() {

    }

    @Override
    public void onReceive(boolean isSticky, String eventTag, Object event) {

    }

    @Override
    public void queryArticleTreeSuccess(List<ArticleTreeResp> data) {

    }

    @Override
    public void queryArticleTreeFail(String errCode, String errMsg) {

    }

    @Override
    public void queryArticleSuccess(ArticleResp data) {

    }

    @Override
    public void queryArticleFail(String errCode, String errMsg) {

    }

    @Override
    public void queryMoreArticleSuccess(ArticleResp data) {

    }

    @Override
    public void queryMoreArticleFail(String errCode, String errMsg) {

    }
}
