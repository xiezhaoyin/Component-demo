package com.xzydonate.news.project;

import android.os.Bundle;

import com.xzydonate.basesdk.activity.BaseActivity;

import java.util.List;

public class ProjectActivity extends BaseActivity implements IProjectView {
    @Override
    public int createView() {
        return 0;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void resumeView() {

    }

    @Override
    public void destroyView() {

    }

    @Override
    public void onReceive(boolean isSticky, String eventTag, Object event) {

    }

    @Override
    public void queryProjectTreeSuccess(List<ProjectTreeResp> data) {

    }

    @Override
    public void queryProjectTreeFail(String errCode, String errMsg) {

    }

    @Override
    public void queryProjectSuccess(ProjectResp data) {

    }

    @Override
    public void queryProjectFail(String errCode, String errMsg) {

    }

    @Override
    public void queryMoreProjectSuccess(ProjectResp data) {

    }

    @Override
    public void queryMoreProjectFail(String errCode, String errMsg) {

    }
}
