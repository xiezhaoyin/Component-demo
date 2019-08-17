package com.xzydonate.news.project;

import java.util.List;

public interface IProjectView {

    void queryProjectTreeSuccess(List<ProjectTreeResp> data);

    void queryProjectTreeFail(String errCode, String errMsg);

    void queryProjectSuccess(ProjectResp data);

    void queryProjectFail(String errCode, String errMsg);

    void queryMoreProjectSuccess(ProjectResp data);

    void queryMoreProjectFail(String errCode, String errMsg);
}
