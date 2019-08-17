package com.xzydonate.news;

import com.xzydonate.news.search.HotKeyResp;

import java.util.List;

public interface INewsView {

    void queryBannerSuccess(List<BannerResp> data);

    void queryBannerFail(String errCode, String errMsg);

    void queryHotKeySuccess(List<HotKeyResp> data);

    void queryHotKeyFail(String errCode, String errMsg);
}
