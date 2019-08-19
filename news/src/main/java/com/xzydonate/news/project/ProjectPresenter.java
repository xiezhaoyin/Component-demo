package com.xzydonate.news.project;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.xzydonate.basesdk.entity.WanResp;
import com.xzydonate.basesdk.mvp.BaseActPresenter;
import com.xzydonate.basesdk.mvp.base.WanObserver;
import com.xzydonate.basesdk.network.netCall.RetrofitHelper;
import com.xzydonate.basesdk.util.Obj2MapUtil;
import com.xzydonate.news.NewsApi;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public class ProjectPresenter extends BaseActPresenter {

    private IProjectView view = null;
    private NewsApi api = null;

    public ProjectPresenter(RxAppCompatActivity activity) {
        super(activity);
        view = (IProjectView) activity;
        api = RetrofitHelper.RETROFIT_WAN.create(NewsApi.class);
    }

    @Override
    public void destroyPresenter() {
        super.destroyPresenter();
    }

    public void queryProjectTree() {
        if (view == null || api == null) {
            return;
        }
        Observable<WanResp<List<ProjectTreeResp>>> observable = api.queryProjectTree();
        setSubscribe(observable, new WanObserver<List<ProjectTreeResp>>(new WanObserver.OnCallback<List<ProjectTreeResp>>() {
            @Override
            public void onCall(List<ProjectTreeResp> response) {
                view.queryProjectTreeSuccess(response);
            }

            @Override
            public void onError(String errCode, String errMsg) {
                view.queryProjectTreeFail(errCode, errMsg);
            }
        }));
    }

    public void queryProject(ProjectReq req) {
        if (view == null || api == null) {
            return;
        }

        Map<String, Object> params = Obj2MapUtil.objectToMap(req);
        Observable<WanResp<ProjectResp>> observable = api.queryProject(0, params);
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

    public void queryMoreProject(int page, ProjectReq req) {
        if (view == null || api == null) {
            return;
        }

        Map<String, Object> params = Obj2MapUtil.objectToMap(req);
        Observable<WanResp<ProjectResp>> observable = api.queryProject(page, params);
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
