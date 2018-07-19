package com.xzydonate.picture;

import com.xzydonate.basesdk.entity.BaseResp;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PictureApi {

    @GET("data/福利/{number}/{page}")
    Observable<BaseResp<List<PictureResp>>> getPictures(@Path("number") int number, @Path("page") int page);
}
