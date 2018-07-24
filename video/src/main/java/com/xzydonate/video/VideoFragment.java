package com.xzydonate.video;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xzydonate.basesdk.activity.BaseEventFragment;
import com.xzydonate.basesdk.util.UrLRouter;

@Route(path = UrLRouter.VIDEO_FRAG)
public class VideoFragment extends BaseEventFragment {
    @Override
    public int createView(Bundle savedInstanceState) {
        return R.layout.activity_video_page_info;
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
