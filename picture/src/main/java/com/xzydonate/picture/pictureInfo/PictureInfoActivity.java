package com.xzydonate.picture.pictureInfo;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xzydonate.basesdk.activity.BaseEventActivity;
import com.xzydonate.basesdk.util.UrLRouter;
import com.xzydonate.picture.R;

@Route(path = UrLRouter.PICTURE_PAGE_INFO_ACT)
public class PictureInfoActivity extends BaseEventActivity {
    @Override
    public int createView(Bundle savedInstanceState) {
        return R.layout.activity_picture_page_info;
    }

    @Override
    public void initView() {

    }

    @Override
    public void resumeView() {

    }

    @Override
    public void destroyView() {

    }

    @Override
    public void onReceive(boolean isSticky, String eventTag, Object event) {
        if(isSticky){
            switch (eventTag){
                case "PictureResp":
                    break;
            }
        }
    }
}
