package com.xzydonate.picture;


import com.trello.rxlifecycle2.components.support.RxFragment;
import com.xzydonate.base.entity.BaseResp;
import com.xzydonate.base.network.netCall.RetrofitHelper;
import com.xzydonate.base.presenter.BaseFragPresenter;
import com.xzydonate.base.presenter.base.BaseObserver;
import com.xzydonate.picture.page1.PicturePage1Fragment;

import java.util.List;

import io.reactivex.Observable;

public class PicturePresenter extends BaseFragPresenter {

    private PicturePage1Fragment fragment = null;
    private PictureApi api = null;

    @Override
    public void createPresenter(RxFragment fragment) {
        super.createPresenter(fragment);
        this.fragment = (PicturePage1Fragment) fragment;
        api = RetrofitHelper.retrofit.create(PictureApi.class);
    }

    @Override
    public void destroyPresenter() {
        super.destroyPresenter();
    }

    public void loadPictures() {
        Observable<BaseResp<List<PictureResp>>> observable = api.getPictures(10, 1);
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
