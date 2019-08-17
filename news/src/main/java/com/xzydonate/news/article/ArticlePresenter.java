package com.xzydonate.news.article;

import com.trello.rxlifecycle2.components.support.RxFragment;
import com.xzydonate.basesdk.entity.WanResp;
import com.xzydonate.basesdk.mvp.BaseFragPresenter;
import com.xzydonate.basesdk.mvp.base.WanObserver;
import com.xzydonate.basesdk.network.netCall.RetrofitHelper;
import com.xzydonate.basesdk.util.Obj2MapUtil;
import com.xzydonate.news.NewsApi;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public class ArticlePresenter extends BaseFragPresenter {

    private IArticleView view = null;
    private NewsApi api = null;

    public ArticlePresenter(RxFragment fragment) {
        super(fragment);
//        this.view = fragment;
        api = RetrofitHelper.RETROFIT_WAN.create(NewsApi.class);
    }

    @Override
    public void destroyPresenter() {
        super.destroyPresenter();
    }

    public void queryArticleTree() {
        if (view == null || api == null) {
            return;
        }
        Observable<WanResp<List<ArticleTreeResp>>> observable = api.queryArticleTree();
        setSubscribe(observable, new WanObserver<List<ArticleTreeResp>>(new WanObserver.OnCallback<List<ArticleTreeResp>>() {
            @Override
            public void onCall(List<ArticleTreeResp> response) {
                view.queryArticleTreeSuccess(response);
            }

            @Override
            public void onError(String errCode, String errMsg) {
                view.queryArticleTreeFail(errCode, errMsg);
            }
        }));
    }

    public void queryArticle(ArticleReq req) {
        if (view == null || api == null) {
            return;
        }

        Map<String, Object> params = Obj2MapUtil.objectToMap(req);
        Observable<WanResp<ArticleResp>> observable = api.queryArticle(0, params);
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

    public void queryMoreArticle(int page, ArticleReq req) {
        if (view == null || api == null) {
            return;
        }

        Map<String, Object> params = Obj2MapUtil.objectToMap(req);
        Observable<WanResp<ArticleResp>> observable = api.queryArticle(page, params);
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
