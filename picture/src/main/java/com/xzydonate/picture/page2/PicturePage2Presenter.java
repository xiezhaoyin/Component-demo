package com.xzydonate.picture.page2;


import com.trello.rxlifecycle2.components.support.RxFragment;
import com.xzydonate.basesdk.entity.BaseResp;
import com.xzydonate.basesdk.network.netCall.RetrofitHelper;
import com.xzydonate.basesdk.presenter.BaseFragPresenter;
import com.xzydonate.basesdk.presenter.base.BaseObserver;
import com.xzydonate.picture.PictureApi;
import com.xzydonate.picture.PictureResp;

import java.util.List;

import io.reactivex.Observable;

public class PicturePage2Presenter extends BaseFragPresenter {

    private PicturePage2Fragment fragment = null;
    private PictureApi api = null;

    @Override
    public void createPresenter(RxFragment fragment) {
        super.createPresenter(fragment);
        this.fragment = (PicturePage2Fragment) fragment;
        api = RetrofitHelper.RETROFIT.create(PictureApi.class);
    }

    @Override
    public void destroyPresenter() {
        super.destroyPresenter();
    }

    public void loadPictures() {
        Observable<BaseResp<List<PictureResp>>> observable = api.getPictures(8, 1);
        setSubscribe(observable, new BaseObserver<List<PictureResp>>(new BaseObserver.OnCallback<List<PictureResp>>() {
            @Override
            public void onCall(List<PictureResp> response) {
                 fragment.loadSuccess(response);
            }

            @Override
            public void onError(String errCode, String errMsg) {
                fragment.loadFail(errCode,errMsg);
            }
        }));
    }

    public void loadMorePictures(int number, int page) {
        Observable<BaseResp<List<PictureResp>>> observable = api.getPictures(number, page);
        setSubscribe(observable, new BaseObserver<List<PictureResp>>(new BaseObserver.OnCallback<List<PictureResp>>() {
            @Override
            public void onCall(List<PictureResp> response) {
                fragment.loadMoreSuccess(response);
            }

            @Override
            public void onError(String errCode, String errMsg) {
               fragment.loadMoreFail(errCode,errMsg);
            }
        }));
    }
}
