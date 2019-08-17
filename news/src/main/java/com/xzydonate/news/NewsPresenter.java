package com.xzydonate.news;

import com.trello.rxlifecycle2.components.support.RxFragment;
import com.xzydonate.basesdk.entity.WanResp;
import com.xzydonate.basesdk.mvp.BaseFragPresenter;
import com.xzydonate.basesdk.mvp.base.WanObserver;
import com.xzydonate.basesdk.network.netCall.RetrofitHelper;
import com.xzydonate.news.search.HotKeyResp;

import java.util.List;

import io.reactivex.Observable;

public class NewsPresenter extends BaseFragPresenter {

    private INewsView view = null;
    private NewsApi api = null;

    public NewsPresenter(RxFragment fragment) {
        super(fragment);
        this.view = (NewsFragment) fragment;
        api = RetrofitHelper.RETROFIT_WAN.create(NewsApi.class);
    }

    @Override
    public void destroyPresenter() {
        super.destroyPresenter();
    }

    public void queryBanner() {
        if (view == null || api == null) {
            return;
        }

        Observable<WanResp<List<BannerResp>>> observable = api.queryBanner();
        setSubscribe(observable, new WanObserver<List<BannerResp>>(new WanObserver.OnCallback<List<BannerResp>>() {
            @Override
            public void onCall(List<BannerResp> response) {
                view.queryBannerSuccess(response);
            }

            @Override
            public void onError(String errCode, String errMsg) {
                view.queryBannerFail(errCode, errMsg);
            }
        }));
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

}
