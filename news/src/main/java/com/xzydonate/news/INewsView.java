package com.xzydonate.news;

import java.util.List;

public interface INewsView {

    void queryBannerSuccess(List<BannerResp> data);

    void queryBannerFail(String errCode, String errMsg);

    void queryNewsSuccess(NewsResp data);

    void queryNewsFail(String errCode, String errMsg);

    void queryMoreNewsSuccess(NewsResp data);

    void queryMoreNewsFail(String errCode, String errMsg);
}
