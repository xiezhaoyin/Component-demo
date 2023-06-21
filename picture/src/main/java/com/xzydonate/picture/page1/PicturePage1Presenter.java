package com.xzydonate.picture.page1;

import com.trello.rxlifecycle3.components.support.RxFragment;
import com.xzydonate.basesdk.entity.BaseResp;
import com.xzydonate.basesdk.mvp.BaseFragPresenter;
import com.xzydonate.basesdk.mvp.base.BaseObserver;
import com.xzydonate.basesdk.network.RetrofitHelper;

import com.xzydonate.picture.PictureApi;
import com.xzydonate.picture.PictureResp;

import java.util.List;

import io.reactivex.Observable;

public class PicturePage1Presenter extends BaseFragPresenter {

    private PicturePage1Fragment fragment = null;
    private PictureApi api = null;

    public PicturePage1Presenter(RxFragment fragment) {
        super(fragment);
        this.fragment = (PicturePage1Fragment) fragment;
        api = RetrofitHelper.RETROFIT.create(PictureApi.class);
    }

    @Override
    public void destroyPresenter() {
        super.destroyPresenter();
    }

    public void loadPictures() {
        if (fragment == null || api == null) {
            return;
        }
        Observable<BaseResp<List<PictureResp>>> observable = api.getPictures(2, 1);
        setSubscribe(observable, new BaseObserver<List<PictureResp>>(new BaseObserver.OnCallback<List<PictureResp>>() {
            @Override
            public void onCall(List<PictureResp> response) {
                fragment.loadSuccess(response);
            }

            @Override
            public void onError(String errCode, String errMsg) {
                fragment.loadFail(errCode, errMsg);
            }
        }));
    }

    public void loadMorePictures(int number, int page) {
        if (fragment == null || api == null) {
            return;
        }
        Observable<BaseResp<List<PictureResp>>> observable = api.getPictures(number, page);
        setSubscribe(observable, new BaseObserver<List<PictureResp>>(new BaseObserver.OnCallback<List<PictureResp>>() {
            @Override
            public void onCall(List<PictureResp> response) {
                fragment.loadMoreSuccess(response);
            }

            @Override
            public void onError(String errCode, String errMsg) {
                fragment.loadMoreFail(errCode, errMsg);
            }
        }));
    }
}
