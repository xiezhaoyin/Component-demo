package com.xzydonate.news.project;

import com.trello.rxlifecycle2.components.support.RxFragment;
import com.xzydonate.basesdk.entity.WanResp;
import com.xzydonate.basesdk.mvp.BaseFragPresenter;
import com.xzydonate.basesdk.mvp.base.WanObserver;
import com.xzydonate.basesdk.network.netCall.RetrofitHelper;
import com.xzydonate.basesdk.util.Obj2MapUtil;
import com.xzydonate.news.BannerResp;
import com.xzydonate.news.NewsApi;
import com.xzydonate.news.NewsFragment;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public class ProjectPresenter extends BaseFragPresenter {

    private IProjectView projectView = null;
    private NewsApi api = null;

    public ProjectPresenter(RxFragment fragment) {
        super(fragment);
//        projectView =  fragment;
        api = RetrofitHelper.RETROFIT_WAN.create(NewsApi.class);
    }

    @Override
    public void destroyPresenter() {
        super.destroyPresenter();
    }

    public void queryProjectTree() {
        if (projectView == null || api == null) {
            return;
        }
        Observable<WanResp<List<ProjectTreeResp>>> observable = api.queryProjectTree();
        setSubscribe(observable, new WanObserver<List<ProjectTreeResp>>(new WanObserver.OnCallback<List<ProjectTreeResp>>() {
            @Override
            public void onCall(List<ProjectTreeResp> response) {
                projectView.queryProjectTreeSuccess(response);
            }

            @Override
            public void onError(String errCode, String errMsg) {
                projectView.queryProjectTreeFail(errCode, errMsg);
            }
        }));
    }

    public void queryProject(ProjectReq req) {
        if (projectView == null || api == null) {
            return;
        }

        Map<String, Object> params = Obj2MapUtil.objectToMap(req);
        Observable<WanResp<ProjectResp>> observable = api.queryProject(1, params);
        setSubscribe(observable, new WanObserver<ProjectResp>(new WanObserver.OnCallback<ProjectResp>() {
            @Override
            public void onCall(ProjectResp response) {
                projectView.queryProjectSuccess(response);
            }

            @Override
            public void onError(String errCode, String errMsg) {
                projectView.queryProjectFail(errCode, errMsg);
            }
        }));
    }

    public void queryMoreProject(int page, Object req) {
        if (projectView == null || api == null) {
            return;
        }

        Map<String, Object> params = Obj2MapUtil.objectToMap(req);
        Observable<WanResp<ProjectResp>> observable = api.queryProject(page, params);
        setSubscribe(observable, new WanObserver<ProjectResp>(new WanObserver.OnCallback<ProjectResp>() {
            @Override
            public void onCall(ProjectResp response) {
                projectView.queryMoreProjectSuccess(response);
            }

            @Override
            public void onError(String errCode, String errMsg) {
                projectView.queryMoreProjectFail(errCode, errMsg);
            }
        }));
    }

}
