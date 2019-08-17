package com.xzydonate.news.search;

import com.xzydonate.news.article.ArticleResp;

import java.util.List;

public interface ISearchView {

    void queryHotKeySuccess(List<HotKeyResp> data);

    void queryHotKeyFail(String errCode, String errMsg);

    void queryArticleSuccess(ArticleResp data);

    void queryArticleFail(String errCode, String errMsg);

    void queryMoreArticleSuccess(ArticleResp data);

    void queryMoreArticleFail(String errCode, String errMsg);
}
