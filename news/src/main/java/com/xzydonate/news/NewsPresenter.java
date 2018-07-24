package com.xzydonate.news;

import com.trello.rxlifecycle2.components.support.RxFragment;
import com.xzydonate.basesdk.entity.WanResp;
import com.xzydonate.basesdk.network.netCall.RetrofitHelper;
import com.xzydonate.basesdk.presenter.BaseFragPresenter;
import com.xzydonate.basesdk.presenter.base.WanObserver;
import com.xzydonate.basesdk.util.Obj2MapUtil;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;


public class NewsPresenter extends BaseFragPresenter {

    private NewsFragment2 fragment = null;
    private NewsApi api = null;

    @Override
    public void createPresenter(RxFragment fragment) {
        super.createPresenter(fragment);
        this.fragment = (NewsFragment2) fragment;
        api = RetrofitHelper.RETROFIT_WAN.create(NewsApi.class);

    }

    @Override
    public void destroyPresenter() {
        super.destroyPresenter();
    }

    public void queryBanner() {
        if (fragment == null || api == null) {
            return;
        }
        Observable<WanResp<List<BannerResp>>> observable = api.queryBanner();
        setSubscribe(observable, new WanObserver<List<BannerResp>>(new WanObserver.OnCallback<List<BannerResp>>() {
            @Override
            public void onCall(List<BannerResp> response) {
                fragment.queryBannerSuccess(response);
            }

            @Override
            public void onError(String errCode, String errMsg) {
                fragment.queryBannerFail(errCode, errMsg);
            }
        }));
    }

    public void queryNews(Object req) {
        if (fragment == null || api == null) {
            return;
        }
        Map<String, Object> params = Obj2MapUtil.objectToMap(req);
        Observable<WanResp<NewsResp>> observable = api.queryNews(1, params);
        setSubscribe(observable, new WanObserver<NewsResp>(new WanObserver.OnCallback<NewsResp>() {
            @Override
            public void onCall(NewsResp response) {
                fragment.queryNewsSuccess(response);
            }

            @Override
            public void onError(String errCode, String errMsg) {
                fragment.queryNewsFail(errCode, errMsg);
            }
        }));
    }

    public void queryMoreNews(int page, Object req) {
        if (fragment == null || api == null) {
            return;
        }
        Map<String, Object> params = Obj2MapUtil.objectToMap(req);
        Observable<WanResp<NewsResp>> observable = api.queryNews(page, params);
        setSubscribe(observable, new WanObserver<NewsResp>(new WanObserver.OnCallback<NewsResp>() {
            @Override
            public void onCall(NewsResp response) {
                fragment.queryMoreNewsSuccess(response);
            }

            @Override
            public void onError(String errCode, String errMsg) {
                fragment.queryMoreNewsFail(errCode, errMsg);
            }
        }));
    }

}
