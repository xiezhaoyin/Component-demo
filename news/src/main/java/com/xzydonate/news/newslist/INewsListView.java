package com.xzydonate.news.newslist;

import com.xzydonate.news.article.ArticleResp;
import com.xzydonate.news.article.ArticleTreeResp;
import com.xzydonate.news.project.ProjectResp;
import com.xzydonate.news.project.ProjectTreeResp;

import java.util.List;

public interface INewsListView {


    void queryProjectSuccess(ProjectResp data);

    void queryProjectFail(String errCode, String errMsg);

    void queryMoreProjectSuccess(ProjectResp data);

    void queryMoreProjectFail(String errCode, String errMsg);


    void queryArticleSuccess(ArticleResp data);

    void queryArticleFail(String errCode, String errMsg);

    void queryMoreArticleSuccess(ArticleResp data);

    void queryMoreArticleFail(String errCode, String errMsg);
}
