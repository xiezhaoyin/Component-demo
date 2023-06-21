package com.xzydonate.video;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xzydonate.basesdk.activity.BaseFragment;
import com.xzydonate.basesdk.router.UrlRouter;

@Route(path = UrlRouter.VIDEO_FRAG)
public class VideoFragment extends BaseFragment {
    @Override
    public int createView() {
        return R.layout.video_activity_video_page_info;
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
}
