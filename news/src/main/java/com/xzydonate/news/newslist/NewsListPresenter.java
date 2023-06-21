package com.xzydonate.news.newslist;

import com.trello.rxlifecycle3.components.support.RxFragment;
import com.xzydonate.basesdk.entity.WanResp;
import com.xzydonate.basesdk.mvp.BaseFragPresenter;
import com.xzydonate.basesdk.mvp.base.WanObserver;
import com.xzydonate.basesdk.network.RetrofitHelper;
import com.xzydonate.news.NewsApi;
import com.xzydonate.news.article.ArticleResp;
import com.xzydonate.news.project.ProjectResp;

import io.reactivex.Observable;

public class NewsListPresenter extends BaseFragPresenter {

    private INewsListView view = null;
    private NewsApi api = null;

    public NewsListPresenter(RxFragment fragment) {
        super(fragment);
        view = (INewsListView) fragment;
        api = RetrofitHelper.RETROFIT_WAN.create(NewsApi.class);
    }

    @Override
    public void destroyPresenter() {
        super.destroyPresenter();
    }

    public void queryArticle() {
        if (view == null || api == null) {
            return;
        }

        Observable<WanResp<ArticleResp>> observable = api.queryArticle(0);
        setSubscribe(observable, new WanObserver<ArticleResp>(new WanObserver.OnCallback<ArticleResp>() {
            @Override
            public void onCall(ArticleResp response) {
                view.queryArticleSuccess(response);
            }

            @Override
            public void onError(String errCode, String errMsg) {
                view.queryArticleFail(errCode, errMsg);
            }
        }));
    }

    public void queryMoreArticle(int page) {
        if (view == null || api == null) {
            return;
        }

        Observable<WanResp<ArticleResp>> observable = api.queryArticle(page);
        setSubscribe(observable, new WanObserver<ArticleResp>(new WanObserver.OnCallback<ArticleResp>() {
            @Override
            public void onCall(ArticleResp response) {
                view.queryMoreArticleSuccess(response);
            }

            @Override
            public void onError(String errCode, String errMsg) {
                view.queryMoreArticleFail(errCode, errMsg);
            }
        }));
    }

    public void queryProject() {
        if (view == null || api == null) {
            return;
        }

        Observable<WanResp<ProjectResp>> observable = api.queryProject(0);
        setSubscribe(observable, new WanObserver<ProjectResp>(new WanObserver.OnCallback<ProjectResp>() {
            @Override
            public void onCall(ProjectResp response) {
                view.queryProjectSuccess(response);
            }

            @Override
            public void onError(String errCode, String errMsg) {
                view.queryProjectFail(errCode, errMsg);
            }
        }));
    }

    public void queryMoreProject(int page) {
        if (view == null || api == null) {
            return;
        }

        Observable<WanResp<ProjectResp>> observable = api.queryProject(page);
        setSubscribe(observable, new WanObserver<ProjectResp>(new WanObserver.OnCallback<ProjectResp>() {
            @Override
            public void onCall(ProjectResp response) {
                view.queryMoreProjectSuccess(response);
            }

            @Override
            public void onError(String errCode, String errMsg) {
                view.queryMoreProjectFail(errCode, errMsg);
            }
        }));
    }

}
