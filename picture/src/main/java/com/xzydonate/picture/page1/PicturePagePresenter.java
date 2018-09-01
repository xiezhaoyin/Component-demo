package com.xzydonate.picture.page1;


import com.xzydonate.basesdk.entity.BaseResp;
import com.xzydonate.basesdk.mvp.BasePresenter;
import com.xzydonate.basesdk.mvp.base.BaseObserver;
import com.xzydonate.basesdk.network.netCall.RetrofitHelper;
import com.xzydonate.picture.PictureResp;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class PicturePagePresenter extends BasePresenter<PictureContract.Mode,PictureContract.View> {

    private PictureContract.Mode api = null;

    @Inject
    public PicturePagePresenter(PictureContract.Mode mode, PictureContract.View view){
        super(mode,view);
    }

    @Override
    public void createPresenter() {
        api = RetrofitHelper.RETROFIT.create(PictureContract.Mode.class);
    }

    @Override
    public void destroyPresenter() {
        super.destroyPresenter();
    }

    public void loadPictures() {
        if (mView == null || api == null) {
            return;
        }
        Observable<BaseResp<List<PictureResp>>> observable = api.getPictures(2, 1);
        setSubscribe(observable, new BaseObserver<List<PictureResp>>(new BaseObserver.OnCallback<List<PictureResp>>() {
            @Override
            public void onCall(List<PictureResp> response) {
                mView.loadSuccess(response);
            }

            @Override
            public void onError(String errCode, String errMsg) {
                mView.loadFail(errCode, errMsg);
            }
        }));
    }

    public void loadMorePictures(int number, int page) {
        if (mView == null || api == null) {
            return;
        }
        Observable<BaseResp<List<PictureResp>>> observable = api.getPictures(number, page);
        setSubscribe(observable, new BaseObserver<List<PictureResp>>(new BaseObserver.OnCallback<List<PictureResp>>() {
            @Override
            public void onCall(List<PictureResp> response) {
                mView.loadMoreSuccess(response);
            }

            @Override
            public void onError(String errCode, String errMsg) {
                mView.loadMoreFail(errCode, errMsg);
            }
        }));
    }
}
