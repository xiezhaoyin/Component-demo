package com.xzydonate.picture.page1;

import com.xzydonate.basesdk.entity.BaseResp;
import com.xzydonate.basesdk.mvp.IModel;
import com.xzydonate.basesdk.mvp.IView;
import com.xzydonate.picture.PictureResp;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PictureContract {

    interface View extends IView {

        void loadSuccess(List<PictureResp> data);

        void loadFail(String errCode , String errMsg);

        void loadMoreSuccess(List<PictureResp> data);

        void loadMoreFail(String errCode , String errMsg);
    }

    interface Mode extends IModel {

        @GET("data/福利/{number}/{page}")
        Observable<BaseResp<List<PictureResp>>> getPictures(@Path("number") int number, @Path("page") int page);
    }
}
