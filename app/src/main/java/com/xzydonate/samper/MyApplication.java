package com.xzydonate.samper;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.xzydonate.basesdk.SdkApplication;

public class MyApplication extends SdkApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.init(this);
    }
}
