package com.xzydonate.news;

import com.trello.rxlifecycle2.components.support.RxFragment;
import com.xzydonate.basesdk.entity.WanResp;
import com.xzydonate.basesdk.mvp.BaseFragPresenter;
import com.xzydonate.basesdk.mvp.base.WanObserver;
import com.xzydonate.basesdk.network.netCall.RetrofitHelper;
import com.xzydonate.basesdk.util.Obj2MapUtil;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public class NewsPresenter extends BaseFragPresenter {

    private NewsFragment fragment = null;
    private NewsApi api = null;

    public NewsPresenter(RxFragment fragment) {
        super(fragment);
        this.fragment = (NewsFragment) fragment;
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

    public void queryProjectTree() {
        if (fragment == null || api == null) {
            return;
        }
        Observable<WanResp<List<ProjectTreeResp>>> observable = api.queryProjectTree();
        setSubscribe(observable, new WanObserver<List<ProjectTreeResp>>(new WanObserver.OnCallback<List<ProjectTreeResp>>() {
            @Override
            public void onCall(List<ProjectTreeResp> response) {
                fragment.queryProjectTreeSuccess(response);
            }

            @Override
            public void onError(String errCode, String errMsg) {
                fragment.queryProjectTreeFail(errCode, errMsg);
            }
        }));
    }

    public void queryProject(ProjectReq req) {
        if (fragment == null || api == null) {
            return;
        }

        Map<String, Object> params = Obj2MapUtil.objectToMap(req);
        Observable<WanResp<ProjectResp>> observable = api.queryProject(1, params);
        setSubscribe(observable, new WanObserver<ProjectResp>(new WanObserver.OnCallback<ProjectResp>() {
            @Override
            public void onCall(ProjectResp response) {
                fragment.queryProjectSuccess(response);
            }

            @Override
            public void onError(String errCode, String errMsg) {
                fragment.queryProjectFail(errCode, errMsg);
            }
        }));
    }

    public void queryMoreProject(int page, Object req) {
        if (fragment == null || api == null) {
            return;
        }

        Map<String, Object> params = Obj2MapUtil.objectToMap(req);
        Observable<WanResp<ProjectResp>> observable = api.queryProject(page, params);
        setSubscribe(observable, new WanObserver<ProjectResp>(new WanObserver.OnCallback<ProjectResp>() {
            @Override
            public void onCall(ProjectResp response) {
                fragment.queryMoreProjectSuccess(response);
            }

            @Override
            public void onError(String errCode, String errMsg) {
                fragment.queryMoreProjectFail(errCode, errMsg);
            }
        }));
    }

}
