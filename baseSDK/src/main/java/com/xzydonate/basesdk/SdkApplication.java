package com.xzydonate.basesdk;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

public class SdkApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.init(this);
    }
}
