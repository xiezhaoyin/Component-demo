package com.xzydonate.news;

import com.xzydonate.basesdk.entity.WanResp;
import com.xzydonate.news.article.ArticleResp;
import com.xzydonate.news.article.ArticleTreeResp;
import com.xzydonate.news.project.ProjectResp;
import com.xzydonate.news.project.ProjectTreeResp;
import com.xzydonate.news.search.HotKeyResp;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface NewsApi {

    @GET("/banner/json")
    Observable<WanResp<List<BannerResp>>> queryBanner();

    @GET("/hotkey/json")
    Observable<WanResp<List<HotKeyResp>>> queryHotKey();

    @FormUrlEncoded
    @POST("/article/query/{page}/json")
    Observable<WanResp<ArticleResp>> queryKeyArticle(@Path("page") int page, @FieldMap Map<String, Object> req);

    @GET("/article/top/json")
    Observable<WanResp<ArticleResp>> queryTopArticle();

    @GET("/tree/json")
    Observable<WanResp<List<ArticleTreeResp>>> queryArticleTree();

    @GET("/article/list/{page}/json")
    Observable<WanResp<ArticleResp>> queryArticle(@Path("page") int page);

    @GET("/article/list/{page}/json")
    Observable<WanResp<ArticleResp>> queryArticle(@Path("page") int page, @QueryMap Map<String, Object> req);

    @GET("/project/tree/json")
    Observable<WanResp<List<ProjectTreeResp>>> queryProjectTree();

    @GET("/project/list/{page}/json")
    Observable<WanResp<ProjectResp>> queryProject(@Path("page") int page);

    @GET("/project/list/{page}/json")
    Observable<WanResp<ProjectResp>> queryProject(@Path("page") int page, @QueryMap Map<String, Object> req);


}
