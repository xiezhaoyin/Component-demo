package com.xzydonate.news;

import java.util.List;

public interface INewsView {

    void queryBannerSuccess(List<BannerResp> data);

    void queryBannerFail(String errCode, String errMsg);

    void queryProjectTreeSuccess(List<ProjectTreeResp> data);

    void queryProjectTreeFail(String errCode, String errMsg);

    void queryProjectSuccess(ProjectResp data);

    void queryProjectFail(String errCode, String errMsg);

    void queryMoreProjectSuccess(ProjectResp data);

    void queryMoreProjectFail(String errCode, String errMsg);
}
