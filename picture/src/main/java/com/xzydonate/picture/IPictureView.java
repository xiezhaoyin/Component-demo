package com.xzydonate.picture;

import java.util.List;

public interface IPictureView {

    void loadSuccess(List<PictureResp> data);

    void loadFail(String errCode , String errMsg);

    void loadMoreSuccess(List<PictureResp> data);

    void loadMoreFail(String errCode , String errMsg);
}
