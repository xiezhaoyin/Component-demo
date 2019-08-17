package com.xzydonate.news.search;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.xzydonate.basesdk.entity.WanResp;
import com.xzydonate.basesdk.mvp.BaseActPresenter;
import com.xzydonate.basesdk.mvp.base.WanObserver;
import com.xzydonate.basesdk.network.netCall.RetrofitHelper;
import com.xzydonate.basesdk.util.Obj2MapUtil;
import com.xzydonate.news.NewsApi;
import com.xzydonate.news.article.ArticleResp;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public class SearchPresenter extends BaseActPresenter {

    private ISearchView view = null;
    private NewsApi api = null;

    public SearchPresenter(RxAppCompatActivity activity) {
        super(activity);
        view = (ISearchView) activity;
        api = RetrofitHelper.RETROFIT_WAN.create(NewsApi.class);
    }

    @Override
    public void destroyPresenter() {
        super.destroyPresenter();
    }

    public void queryHotKey() {
        if (view == null || api == null) {
            return;
        }
        Observable<WanResp<List<HotKeyResp>>> observable = api.queryHotKey();
        setSubscribe(observable, new WanObserver<List<HotKeyResp>>(new WanObserver.OnCallback<List<HotKeyResp>>() {
            @Override
            public void onCall(List<HotKeyResp> response) {
                view.queryHotKeySuccess(response);
            }

            @Override
            public void onError(String errCode, String errMsg) {
                view.queryHotKeyFail(errCode, errMsg);
            }
        }));
    }

    public void queryKeyArticle(KArticleReq req) {
        if (view == null || api == null) {
            return;
        }

        Map<String, Object> params = Obj2MapUtil.objectToMap(req);
        Observable<WanResp<ArticleResp>> observable = api.queryKeyArticle(0, params);
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

    public void queryMoreKeyArticle(int page, KArticleReq req) {
        if (view == null || api == null) {
            return;
        }

        Map<String, Object> params = Obj2MapUtil.objectToMap(req);
        Observable<WanResp<ArticleResp>> observable = api.queryKeyArticle(page, params);
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

}
