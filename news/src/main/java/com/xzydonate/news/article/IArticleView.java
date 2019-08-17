package com.xzydonate.news.article;

import java.util.List;

public interface IArticleView {

    void queryArticleTreeSuccess(List<ArticleTreeResp> data);

    void queryArticleTreeFail(String errCode, String errMsg);

    void queryArticleSuccess(ArticleResp data);

    void queryArticleFail(String errCode, String errMsg);

    void queryMoreArticleSuccess(ArticleResp data);

    void queryMoreArticleFail(String errCode, String errMsg);
}
